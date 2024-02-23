package org.restaurant.feignClients;

import org.restaurant.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "pizzaServiceClient", url = "${api.pizzaservice}")
public interface PizzaClient {

    @GetMapping(value = "")
    List<ProductDto> getMenu();

}
