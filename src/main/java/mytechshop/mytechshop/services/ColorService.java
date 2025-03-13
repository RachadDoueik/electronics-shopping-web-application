package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IColorService;
import mytechshop.mytechshop.models.Color;
import mytechshop.mytechshop.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository){
        this.colorRepository = colorRepository;
    }

    // Create a new color
    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    // Get all colors
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    // Get a color by ID
    public Color getColorById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found"));
    }

    // Update a color
    public Color updateColor(Long id, Color colorDetails) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found"));

        color.setName(colorDetails.getName());
        color.setHexCode(colorDetails.getHexCode());

        return colorRepository.save(color);
    }

    // Delete a color
    public void deleteColor(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found"));

        colorRepository.delete(color);
    }
}