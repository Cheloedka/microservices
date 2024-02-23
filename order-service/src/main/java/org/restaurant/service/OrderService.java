package org.restaurant.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderDto;
import org.restaurant.dto.OrderPriceDto;
import org.restaurant.dto.ProductDto;
import org.restaurant.entity.Order;
import org.restaurant.entity.OrderLine;
import org.restaurant.feignClients.PizzaClient;
import org.restaurant.messageBroker.produce.OrderProducer;
import org.restaurant.repository.OrderLineRepository;
import org.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    private final PizzaClient pizzaClient;
    private final OrderProducer orderProducer;

    @Transactional
    public Long makeOrder(OrderDto dto) {
        var order = Order.builder()
                .customerName(dto.getCustomerName())
                .street(dto.getStreet())
                .build();

        var savedOrder = orderRepository.save(order);
        dto.setIdOrder(savedOrder.getId());

        dto.getOrderLines()
                .forEach((key, value) -> orderLineRepository.save(
                        OrderLine.builder()
                                .order(order)
                                .idProduct(key)
                                .count(value)
                                .build()
                ));

        orderProducer.sendOrderToProductService(dto);

        return savedOrder.getId();
    }

    public void setPrice(OrderPriceDto dto) {
        System.out.println("setPriceAfterConfirmation(): " + dto);
        var order = orderRepository.findById(dto.getOrderId()).orElseThrow(EntityNotFoundException::new);
        order.setPrice(dto.getPrice());
        orderRepository.save(order);
    }


    public List<ProductDto> getMenu() throws ExecutionException, InterruptedException {
        return CompletableFuture
                .supplyAsync(() -> pizzaClient.getMenu()
                        .stream()
                        .map(p -> ProductDto.builder()
                                .name(p.getName())
                                .id(p.getId())
                                .price(p.getPrice())
                                .description(p.getDescription())
                                .build()
                        ).toList()).get();
    }
}
