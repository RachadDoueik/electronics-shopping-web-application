package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IBrandService;
import mytechshop.mytechshop.models.Brand;
import mytechshop.mytechshop.repositories.BrandRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;


    public BrandService(BrandRepository brandRepository){
         this.brandRepository = brandRepository;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

        existingBrand.setName(brand.getName());
        return brandRepository.save(existingBrand);
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}