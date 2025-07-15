package org.example.controller;

import org.example.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Servicio funcionando correctamente");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = fileService.uploadFile(file);
        return ResponseEntity.ok("Archivo subido exitosamente. ID: " + key);
    }

    @GetMapping("/download/{key}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
        byte[] data = fileService.downloadFile(key);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + key)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteFile(@PathVariable String key) {
        fileService.deleteFile(key);
        return ResponseEntity.ok("Archivo eliminado: " + key);
    }

    @GetMapping
    public ResponseEntity<List<String>> listFiles() {
        return ResponseEntity.ok(fileService.listFiles());
    }
}
