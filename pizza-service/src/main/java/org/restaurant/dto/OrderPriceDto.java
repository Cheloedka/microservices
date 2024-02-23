package org.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderPriceDto {
    private Long orderId;
    private float price;
}
