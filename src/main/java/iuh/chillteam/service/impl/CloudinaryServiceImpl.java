package iuh.chillteam.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryServiceImpl implements iuh.chillteam.service.CloudinaryService {

    private final Cloudinary cloudinary;

    @Value("${cloudinary.folder.products}")
    private String productFolder;

    @Override
    public String uploadProductImage(MultipartFile file) throws IOException {
        return uploadImage(file, productFolder);
    }

    @Override
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        // Validate file
        validateImageFile(file);

        // Generate unique filename
        String publicId = folder + "/" + UUID.randomUUID();

        // Upload với tối ưu
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "public_id", publicId,
                        "folder", folder,
                        "resource_type", "auto",
                        "quality", "auto:good", // Tự động nén
                        "fetch_format", "auto"  // Tự động chọn format tốt nhất (WebP cho Chrome)
                )
        );

        String imageUrl = (String) uploadResult.get("secure_url");
        log.info("Image uploaded successfully: {}", imageUrl);
        return imageUrl;
    }

    @Override
    public void deleteImage(String imageUrl) throws IOException {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }

        // Extract public_id từ URL
        String publicId = extractPublicIdFromUrl(imageUrl);

        Map<String, Object> result = cloudinary.uploader().destroy(
                publicId,
                ObjectUtils.emptyMap()
        );

        log.info("Image deleted: {} - Result: {}", publicId, result.get("result"));
    }

    @Override
    public java.util.List<String> uploadMultipleImages(
            java.util.List<MultipartFile> files,
            String folder
    ) {
        return files.stream()
                .map(file -> {
                    try {
                        return uploadImage(file, folder);
                    } catch (IOException e) {
                        log.error("Failed to upload image: {}", file.getOriginalFilename(), e);
                        return null;
                    }
                })
                .filter(url -> url != null)
                .toList();
    }

    @Override
    public String uploadUserAvatar(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "chillglasses/avatars",
                        "overwrite", true,
                        "resource_type", "image"
                )
        );
        return uploadResult.get("secure_url").toString();
    }


    /**
     * Validate file ảnh
     */
    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }

        // Giới hạn 10MB
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("File size must not exceed 10MB");
        }
    }

    /**
     * Extract public_id từ Cloudinary URL
     * Example: https://res.cloudinary.com/demo/image/upload/v1234/folder/image.jpg
     * → folder/image
     */
    private String extractPublicIdFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/upload/");
        if (parts.length < 2) {
            return "";
        }
        String pathAfterUpload = parts[1];
        // Remove version (v1234567890/)
        pathAfterUpload = pathAfterUpload.replaceFirst("v\\d+/", "");
        // Remove file extension
        int lastDot = pathAfterUpload.lastIndexOf('.');
        if (lastDot > 0) {
            pathAfterUpload = pathAfterUpload.substring(0, lastDot);
        }
        return pathAfterUpload;
    }
}