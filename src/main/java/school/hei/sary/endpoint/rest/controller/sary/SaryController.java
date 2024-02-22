package school.hei.sary.endpoint.rest.controller.sary;

import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.hei.sary.service.sary.SaryService;

@RestController
public class SaryController {

  @Autowired private SaryService saryService;

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "/blacks/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public String turnToBlackAndWhite(
      @PathVariable(name = "id") String id, @RequestPart MultipartFile file) throws IOException {
    System.out.println(file);
    return saryService.uploadTransformedImage(id, file);
  }

  @GetMapping("/blacks/{id}")
  public String getBlackAndWhiteImage(@PathVariable String id) {
    return id + "is blacked";
  }
}
