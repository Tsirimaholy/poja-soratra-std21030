package hei.school.soratra.endpoint.rest.controller;

import hei.school.soratra.service.FileUtil;
import hei.school.soratra.service.SoratraService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class SoratraController {
    private final SoratraService service;

    @PutMapping("/soratra/{id}")
    public ResponseEntity uploadSoratra(@PathVariable("id") String id, @RequestBody byte[] file) throws IOException {
        final String SUFFIX = ".txt";
        File temporaryFile = FileUtil.convertByteArrayToFile(id, file, SUFFIX);
        service.uploadFile(temporaryFile, id, id);
        return ResponseEntity.ok().body(null);
    }
}
