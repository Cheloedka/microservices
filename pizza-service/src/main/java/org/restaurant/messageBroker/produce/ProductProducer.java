package org.restaurant.messageBroker.produce;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderPriceDto;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductProducer {
    private final StreamBridge streamBridge;


    public void sendOrderConfirmationToOrderService(OrderPriceDto dto) {
        streamBridge.send("consumerOrderConfirmation-in-0", dto);
    }
}
