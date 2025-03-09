package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface IBrandService {
    Brand saveBrand(Brand brand);
    Brand updateBrand(Long id, Brand brand);
    Optional<Brand> getBrandById(Long id);
    List<Brand> getAllBrands();
    void deleteBrand(Long id);
}