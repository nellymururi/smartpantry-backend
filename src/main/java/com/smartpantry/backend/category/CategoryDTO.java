package com.smartpantry.backend.category;

import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDTO {
    private UUID id;
    private String name;
    private String slug;
    private String icon;
    private String description;

}
