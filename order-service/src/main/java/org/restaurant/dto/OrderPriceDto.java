package org.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrderPriceDto {
    private Long orderId;
    private float price;
}
