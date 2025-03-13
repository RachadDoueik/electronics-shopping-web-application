package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.models.Color;

import java.util.List;

public interface IColorService {
    public Color createColor(Color color);

    // Get all colors
    public List<Color> getAllColors();

    // Get a color by ID
    public Color getColorById(Long id);

    // Update a color
    public Color updateColor(Long id, Color colorDetails);

    // Delete a color
    public void deleteColor(Long id);

}
