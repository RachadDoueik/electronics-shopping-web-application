package mytechshop.mytechshop.services;

import mytechshop.mytechshop.models.Image;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // Upload multiple images and return their URLs
    public List<String> uploadImages(List<MultipartFile> files) {
        List<String> imageUrls = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                imageUrls.add(uploadImage(file));
            }
        }
        return imageUrls;
    }

    // Upload a single image and return its URL
    public String uploadImage(MultipartFile file) {
        validateImageFile(file);

        // Create the upload directory if it doesn't exist
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        try {
            // Save the file to the server
            file.transferTo(new File(filePath));

            // Generate the image URL
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    // Save image URLs to the database and associate them with a product
    public List<Image> saveImages(List<String> imageUrls, Product product) {
        List<Image> images = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            Image image = new Image();
            image.setImageUrl(imageUrl);
            image.setProduct(product);
            images.add(image);
        }
        return imageRepository.saveAll(images); // Save all images in a batch
    }

    // Validate file type
    private void validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Only JPEG and PNG images are allowed");
        }
    }

    // Delete images associated with a product
    public void deleteImagesByProduct(Product product) {
        List<Image> images = imageRepository.findByProduct(product);
        for (Image image : images) {
            deleteImageFile(image.getImageUrl());
        }
        imageRepository.deleteAll(images);
    }

    // Helper method to delete the image file from the server
    private void deleteImageFile(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            // Extract the file name from the URL
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            File file = new File(Paths.get(uploadDir, fileName).toString());

            if (file.exists()) {
                file.delete();
            }
        }
    }
}