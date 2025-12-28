package com.example.csvwrite.controller;

import com.example.csvwrite.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    private static final Logger log = LoggerFactory.getLogger(CsvController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Entered into upload controller" );
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        csvService.csvUpload(file);
        csvService.sendMail("malalatha60@gmail.com");
        log.info("completed in upload controller");
        return ResponseEntity.ok("Data inserted successfully");
    }
}
