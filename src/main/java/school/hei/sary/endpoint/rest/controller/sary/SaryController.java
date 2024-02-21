package school.hei.sary.endpoint.rest.controller.sary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.hei.sary.repository.model.OperationSary;
import school.hei.sary.service.sary.SaryService;

import java.io.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/sary")
public class SaryController {

    @Autowired
    private SaryService saryService;

    @PostMapping("/uploadSary/{id}")
    public String uploadBlackAndWhiteSary (@PathVariable String id, @RequestParam(name = "sary") File sary) {

        return saryService.uploadTransformedImage(id, sary);
    }

    @GetMapping("/operations")
    public ResponseEntity<List<OperationSary>> getAllOperations() {
        List<OperationSary> operations = saryService.getAllOperations();
        return ResponseEntity.ok(operations);
    }
}

