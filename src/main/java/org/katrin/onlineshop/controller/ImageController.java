package org.katrin.onlineshop.controller;

import lombok.AllArgsConstructor;
import org.katrin.onlineshop.model.ImageEntity;
import org.katrin.onlineshop.repository.ImageRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@AllArgsConstructor

@RestController
public class ImageController {
    private ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable int id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
                .header("fileName", imageEntity.getOriginalFileName())
                .contentType(MediaType.valueOf(imageEntity.getContentType()))
                .contentLength(imageEntity.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageEntity.getBytes())));
    }
}
