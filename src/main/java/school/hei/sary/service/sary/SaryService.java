package school.hei.sary.service.sary;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageConverter;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartFile;
import school.hei.sary.file.BucketComponent;
import school.hei.sary.repository.SaryRepository;
import school.hei.sary.repository.model.OperationSary;


import javax.imageio.ImageIO;

import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

import static org.reflections.vfs.Vfs.DefaultUrlTypes.directory;

@Service
@AllArgsConstructor
public class SaryService {

    @Autowired
    private BucketComponent bucketComponent;

    private final SaryRepository saryRepository;

    public File ConvertToBlackAndWhite(String name, File saryOriginal) throws IOException {

        String extension = FilenameUtils.getExtension(saryOriginal.getName());
        ImagePlus openedSaryOriginal = IJ.openImage(saryOriginal.getPath());

        new ImageConverter(openedSaryOriginal).convertToGray32();

        File saryBlackAndWhite = File.createTempFile(
                name + "-N&B",
                "." + extension
        );

        ImageIO.write(
                openedSaryOriginal.getBufferedImage(),
                extension,
                saryBlackAndWhite
        );
        return saryBlackAndWhite;
    }

    public String uploadTransformedImage(String id , MultipartFile sary) throws IOException {

        String suffix = "." + FilenameUtils.getExtension(sary.getOriginalFilename());

        String originalImagePrefix = id + "-original";
        String transformedImagePrefix = id + "-B&W";

        String bucketKeyOriginalImage = directory + originalImagePrefix + suffix;
        String bucketKeyTransformedImage = directory + transformedImagePrefix + suffix;

        File toUpload = File.createTempFile(originalImagePrefix, suffix);
        sary.transferTo(toUpload);
        File blackandWhiteImage = this.ConvertToBlackAndWhite( id, toUpload);

        bucketComponent.upload(toUpload,bucketKeyOriginalImage);
        bucketComponent.upload(blackandWhiteImage, bucketKeyTransformedImage);

        return bucketComponent.presign(bucketKeyTransformedImage, Duration.ofMinutes(30)).toString();

    }

    public List<OperationSary> getAllOperations() {
        return saryRepository.findAll();
    }

    private void saveOperation(String name) {
        OperationSary operation = new OperationSary(name);
        saryRepository.save(operation);
    }
}
