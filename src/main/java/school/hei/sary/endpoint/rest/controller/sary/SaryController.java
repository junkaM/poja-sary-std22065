package school.hei.sary.endpoint.rest.controller.sary;

import java.io.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.sary.repository.model.OperationSary;
import school.hei.sary.service.sary.SaryService;

@RestController
@RequestMapping("/api/sary")
public class SaryController {

  @Autowired private SaryService saryService;

  //  @PostMapping("/uploadSary/{id}")
  //  public String uploadBlackAndWhiteSary(
  //      @PathVariable String id, @RequestParam(name = "sary") File sary) {
  //
  //    return saryService.uploadTransformedImage(id, sary);
  //  }

  @GetMapping("/operations")
  public ResponseEntity<List<OperationSary>> getAllOperations() {
    List<OperationSary> operations = saryService.getAllOperations();
    return ResponseEntity.ok(operations);
  }
}
