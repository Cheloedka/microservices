package org.restaurant.messageBroker.produce;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderDto;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {
    private final StreamBridge streamBridge;

    public void sendOrderToProductService(OrderDto dto) {
        streamBridge.send("consumerProcessOrder-in-0", dto);
    }

}
