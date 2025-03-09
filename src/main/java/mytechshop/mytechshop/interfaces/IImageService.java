package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.models.Image;
import mytechshop.mytechshop.requests.CreateImageRequest;
import mytechshop.mytechshop.requests.UpdateImageRequest;

import java.util.List;

public interface IImageService {

    Image createImage(CreateImageRequest createImageRequest);

    Image updateImage(Long id, UpdateImageRequest updateImageRequest);

    Image getImageById(Long id);

    List<Image> getAllImages();

    void deleteImage(Long id);

    List<Image> getImagesByProductId(Long productId);
}