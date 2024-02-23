package org.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private float price;
}
