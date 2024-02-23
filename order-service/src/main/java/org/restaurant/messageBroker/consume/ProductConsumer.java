package org.restaurant.messageBroker.consume;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderPriceDto;
import org.restaurant.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ProductConsumer {
    private final OrderService orderService;

    @Bean
    public Consumer<OrderPriceDto> consumerOrderConfirmation() {
        return orderService::setPrice;
    }
}
