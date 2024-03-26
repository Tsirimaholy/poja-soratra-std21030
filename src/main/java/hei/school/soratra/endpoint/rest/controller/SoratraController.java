package hei.school.soratra.endpoint.rest.controller;

import hei.school.soratra.repository.SoratraRepository;
import hei.school.soratra.repository.model.MappedSoratra;
import hei.school.soratra.repository.model.Soratra;
import hei.school.soratra.service.FileUtil;
import hei.school.soratra.service.SoratraService;
import java.io.File;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SoratraController {
  private final SoratraService service;
  private final SoratraRepository soratraRepository;

  @PutMapping("/soratra/{id}")
  public ResponseEntity uploadSoratra(@PathVariable("id") String id, @RequestBody byte[] file)
      throws IOException {
    final String SUFFIX = ".txt";
    File temporaryFile = FileUtil.convertByteArrayToFile(id, file, SUFFIX);
    service.uploadFile(temporaryFile, id, id);
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/soratra/{id}")
  public MappedSoratra getFile(@PathVariable("id") String id) throws IOException {
    Soratra soratra = soratraRepository.findById(id).get();
    MappedSoratra mappedSoratra = service.getSOratra(soratra);
    return mappedSoratra;
  }
}
