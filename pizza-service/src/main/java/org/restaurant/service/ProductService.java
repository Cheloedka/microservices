package org.restaurant.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.restaurant.dto.OrderDto;
import org.restaurant.dto.OrderPriceDto;
import org.restaurant.dto.ProductDto;
import org.restaurant.entity.DailyMoney;
import org.restaurant.entity.Product;
import org.restaurant.messageBroker.produce.ProductProducer;
import org.restaurant.repository.DailyMoneyRepository;
import org.restaurant.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final DailyMoneyRepository dailyMoneyRepository;
    private final ProductProducer productProducer;

    public void newProduct(ProductDto dto) {
        var product = Product.builder()
                .price(dto.getPrice())
                .description(dto.getDescription())
                .name(dto.getName())
                .build();

        productRepository.save(product);
    }

    public List<ProductDto> getMenu() {

        return productRepository.findAll()
                .stream()
                .map(p -> ProductDto.builder()
                        .id(p.getId())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .name(p.getName())
                    .build())
                .toList();
    }

    @Transactional
    public void sumPrice(OrderDto dto) {
        var items = dto.getOrderLines();
        var products = productRepository.findAllById(items.keySet());

        float sum = products.stream()
                .map(p -> items.get(p.getId()) * p.getPrice())
                .reduce(0F, Float::sum);

        var money = DailyMoney
                        .builder()
                        .date(new Date())
                        .income(sum)
                        .build();

        dailyMoneyRepository.save(money);

        var toSend = OrderPriceDto.builder()
                .orderId(dto.getIdOrder())
                .price(sum)
                .build();

        productProducer.sendOrderConfirmationToOrderService(toSend);
    }
}
