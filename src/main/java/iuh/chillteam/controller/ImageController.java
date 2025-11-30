package iuh.chillteam.controller;

import iuh.chillteam.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final CloudinaryService cloudinaryService;

    /**
     * Upload single image
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadProductImage(file);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "imageUrl", imageUrl
            ));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    /**
     * Upload multiple images
     */
    @PostMapping("/upload-multiple")
    public ResponseEntity<?> uploadMultipleImages(
            @RequestParam("files") List<MultipartFile> files
    ) {
        List<String> imageUrls = cloudinaryService.uploadMultipleImages(
                files,
                "chillglasses/products"
        );
        return ResponseEntity.ok(Map.of(
                "success", true,
                "imageUrls", imageUrls
        ));
    }

    /**
     * Delete image
     */
    @DeleteMapping
    public ResponseEntity<?> deleteImage(@RequestParam("url") String imageUrl) {
        try {
            cloudinaryService.deleteImage(imageUrl);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }
}