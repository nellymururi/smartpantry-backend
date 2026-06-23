package com.smartpantry.backend.category;

import com.smartpantry.backend.category.Category;
import com.smartpantry.backend.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        List<String[]> defaults = List.of(
                new String[]{"Foodstuffs", "foodstuffs", "ti-wheat", "Dry and packaged foods"},
                new String[]{"Groceries", "groceries", "ti-shopping-cart", "Fresh grocery items"},
                new String[]{"Toiletries", "toiletries", "ti-droplet", "Personal care products"},
                new String[]{"Cleaning Supplies", "cleaning", "ti-sparkles", "Household cleaning items"},
                new String[]{"Medicine & Health", "medicine", "ti-first-aid-kit", "Health and medical supplies"},
                new String[]{"Other", "other", "ti-box", "Miscellaneous items"}
        );

        for (String[] data : defaults) {
            if (!categoryRepository.existsBySlug(data[1])) {
                Category c = new Category();
                c.setName(data[0]);
                c.setSlug(data[1]);
                c.setIcon(data[2]);
                c.setDescription(data[3]);
                categoryRepository.save(c);
            }
        }
    }
}