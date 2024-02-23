package org.restaurant.messageBroker.consume;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderDto;
import org.restaurant.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class OrdersConsumer {
    private final ProductService productService;

    @Bean
    public Consumer<OrderDto> consumerProcessOrder() {
        return productService::sumPrice;
    }

}
