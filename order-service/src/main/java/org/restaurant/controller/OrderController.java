package org.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderDto;
import org.restaurant.dto.ProductDto;
import org.restaurant.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<String> makeOrder(@RequestBody OrderDto dto) {

        Long id = orderService.makeOrder(dto);
        return ResponseEntity.ok("Order is made, its id: " + id);
    }

    @GetMapping("/menu")
    public List<ProductDto> getLastOrdersInfo() throws InterruptedException, ExecutionException {
        return orderService.getMenu();
    }
}
