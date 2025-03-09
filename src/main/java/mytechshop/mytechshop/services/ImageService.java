package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IImageService;
import mytechshop.mytechshop.models.Image;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.ImageRepository;
import mytechshop.mytechshop.repositories.ProductRepository;
import mytechshop.mytechshop.requests.CreateImageRequest;
import mytechshop.mytechshop.requests.UpdateImageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {


    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;


    public ImageService(ImageRepository imageRepository , ProductRepository productRepository){
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Image createImage(CreateImageRequest createImageRequest) {
        // Fetch the product by ID
        Product product = productRepository.findById(createImageRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + createImageRequest.getProductId()));

        // Create and save the image
        Image image = new Image();
        image.setImageUrl(createImageRequest.getImageUrl());
        image.setProduct(product);
        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(Long id, UpdateImageRequest updateImageRequest) {
        // Fetch the existing image
        Image existingImage = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));

        // Fetch the product by ID
        Product product = productRepository.findById(updateImageRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + updateImageRequest.getProductId()));

        // Update the image fields
        existingImage.setImageUrl(updateImageRequest.getImageUrl());
        existingImage.setProduct(product);
        return imageRepository.save(existingImage);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> getImagesByProductId(Long productId) {
        // Fetch the product by ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Return images associated with the product
        return imageRepository.findByProduct(product);
    }
}