package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.repository.product.SnackRepository;
import com.example.RestApiCoffee.repository.product.TeaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeaServiceTest {
    @Autowired
    private TeaService teaService;
    @MockBean
    private TeaRepository teaRepository;
    @Test
    void findAllActive() {
        List<Tea> products = teaService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(teaRepository, Mockito.times(1)).findAllActive();
        Mockito.verify(teaRepository, Mockito.times(1)).findAllActive();
    }
}