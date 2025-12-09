package com.smartmenu.backend.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartmenu.backend.Repository.RestaurantRepository;
import com.smartmenu.backend.exception.NotFoundException;
import com.smartmenu.backend.model.Restaurant;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id,
                                       @RequestBody Restaurant updated) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        existing.setName(updated.getName());
        existing.setLocation(updated.getLocation());
    

        return restaurantRepository.save(existing);
    }

   
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new NotFoundException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }
}
