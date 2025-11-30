package iuh.chillteam.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    /**
     * Upload ảnh sản phẩm
     * @param file File ảnh
     * @return URL của ảnh trên Cloudinary
     */
    String uploadProductImage(MultipartFile file) throws IOException;

    /**
     * Upload ảnh với folder tùy chỉnh
     */
    String uploadImage(MultipartFile file, String folder) throws IOException;

    /**
     * Xóa ảnh theo public_id
     */
    void deleteImage(String imageUrl) throws IOException;

    /**
     * Upload nhiều ảnh
     */
    java.util.List<String> uploadMultipleImages(
            java.util.List<MultipartFile> files,
            String folder
    );
}
