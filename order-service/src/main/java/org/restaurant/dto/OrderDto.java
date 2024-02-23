package org.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long idOrder;
    private String customerName;
    private String street;
    private Map<Long, Integer> orderLines;
}
