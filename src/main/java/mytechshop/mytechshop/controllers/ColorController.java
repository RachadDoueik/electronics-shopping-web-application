package mytechshop.mytechshop.controllers;

import mytechshop.mytechshop.models.Color;
import mytechshop.mytechshop.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    // Create a new color
    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color createdColor = colorService.createColor(color);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }

    // Get all colors
    @GetMapping
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.getAllColors();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    // Get a color by ID
    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable Long id) {
        Color color = colorService.getColorById(id);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    // Update a color
    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
        Color updatedColor = colorService.updateColor(id, colorDetails);
        return new ResponseEntity<>(updatedColor, HttpStatus.OK);
    }

    // Delete a color
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}