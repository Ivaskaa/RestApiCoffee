package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.entities.product.coffee.CoffeeSize;
import com.example.RestApiCoffee.entities.product.dessert.Dessert;
import com.example.RestApiCoffee.entities.product.dessert.DessertSize;
import com.example.RestApiCoffee.entities.product.sandwich.Sandwich;
import com.example.RestApiCoffee.entities.product.sandwich.SandwichSize;
import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.entities.product.snack.SnackSize;
import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.entities.product.tea.TeaSize;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.repository.OrderRepository;
import com.example.RestApiCoffee.repository.ingredients.*;
import com.example.RestApiCoffee.repository.product.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CoffeeRepository coffeeRepository;
    private final TeaRepository teaRepository;
    private final SandwichRepository sandwichRepository;
    private final SnackRepository snackRepository;
    private final DessertRepository dessertRepository;

    private final AlcoholRepository alcoholRepository;
    private final MilkRepository milkRepository;
    private final SauceRepository sauceRepository;
    private final SyrupRepository syrupRepository;
    private final SupplementRepository supplementRepository;

    public Order findById(Long id) {
        log.info("get order by id: {}", id);
        Order order = orderRepository.findById(id).orElseThrow();
        log.info("success");
        return order;
    }

    public Order save(Order order) {
        log.info("save order: {}", order);
        orderRepository.save(order);
        log.info("success");
        return order;
    }

    public List<Order> findByUser(User user) {
        log.info("get order by user: {}", user.getName());
        List<Order> orders = orderRepository.findByUser(user);
        log.info("success");
        return orders;
    }

    public Integer getPrice(Order order) {
        double mainPrice = 0;
        if (order.getCoffeeOrder() != null){
            for(CoffeeOrder coffeeOrder : order.getCoffeeOrder()){
                double price = 0;
                Coffee coffee = coffeeRepository.findById(coffeeOrder.getCoffee().getId()).orElse(null);
                for(CoffeeSize size : coffee.getSizes()){
                    if(size.getId().equals(coffeeOrder.getSize().getId()))
                        price += size.getPrice();
                }
                if (coffeeOrder.getAlcohol() != null) {
                    Alcohol alcohol = alcoholRepository.findById(coffeeOrder.getAlcohol().getId()).orElse(null);
                    if (alcohol != null) {
                        price += alcohol.getPrice();
                    }
                }
                if (coffeeOrder.getMilk() != null) {
                    Milk milk = milkRepository.findById(coffeeOrder.getMilk().getId()).orElse(null);
                    if (milk != null) {
                        price += milk.getPrice();
                    }
                }
                if (coffeeOrder.getSyrup() != null) {
                    Syrup syrup = syrupRepository.findById(coffeeOrder.getSyrup().getId()).orElse(null);
                    if (syrup != null) {
                        price += syrup.getPrice();
                    }
                }
                if (coffeeOrder.getSupplement() != null) {
                    Supplement supplement = supplementRepository.findById(coffeeOrder.getSupplement().getId()).orElse(null);
                    if (supplement != null) {
                        price += supplement.getPrice();
                    }
                }
                price *= coffeeOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getTeaOrder() != null){
            for(TeaOrder teaOrder : order.getTeaOrder()){
                double price = 0;
                Tea tea = teaRepository.findById(teaOrder.getTea().getId()).orElse(null);
                for(TeaSize size : tea.getSizes()){
                    if(size.getId().equals(teaOrder.getSize().getId()))
                        price += size.getPrice();
                }
                if (teaOrder.getMilk() != null) {
                    Milk milk = milkRepository.findById(teaOrder.getMilk().getId()).orElse(null);
                    if (milk != null) {
                        price += milk.getPrice();
                    }
                }
                if (teaOrder.getSyrup() != null) {
                    Syrup syrup = syrupRepository.findById(teaOrder.getSyrup().getId()).orElse(null);
                    if (syrup != null) {
                        price += syrup.getPrice();
                    }
                }
                if (teaOrder.getSupplement() != null) {
                    Supplement supplement = supplementRepository.findById(teaOrder.getSupplement().getId()).orElse(null);
                    if (supplement != null) {
                        price += supplement.getPrice();
                    }
                }
                price *= teaOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getSnackOrder() != null){
            for(SnackOrder snackOrder : order.getSnackOrder()){
                double price = 0;
                Snack snack = snackRepository.findById(snackOrder.getSnack().getId()).orElse(null);
                for(SnackSize size : snack.getSizes()){
                    if(size.getId().equals(snackOrder.getSize().getId()))
                        price += size.getPrice();
                }
                if (snackOrder.getSauce() != null) {
                    Sauce sauce = sauceRepository.findById(snackOrder.getSauce().getId()).orElse(null);
                    if (sauce != null) {
                        price += sauce.getPrice();
                    }
                }
                if (snackOrder.getSupplement() != null) {
                    Supplement supplement = supplementRepository.findById(snackOrder.getSupplement().getId()).orElse(null);
                    if (supplement != null) {
                        price += supplement.getPrice();
                    }
                }
                price *= snackOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getSandwichOrder() != null){
            for(SandwichOrder sandwichOrder : order.getSandwichOrder()){
                double price = 0;
                Sandwich sandwich = sandwichRepository.findById(sandwichOrder.getSandwich().getId()).orElse(null);
                for(SandwichSize size : sandwich.getSizes()){
                    if(size.getId().equals(sandwichOrder.getSize().getId()))
                        price += size.getPrice();
                }
                if (sandwichOrder.getSauce() != null) {
                    Sauce sauce = sauceRepository.findById(sandwichOrder.getSauce().getId()).orElse(null);
                    if (sauce != null) {
                        price += sauce.getPrice();
                    }
                }
                if (sandwichOrder.getSupplement() != null) {
                    Supplement supplement = supplementRepository.findById(sandwichOrder.getSupplement().getId()).orElse(null);
                    if (supplement != null) {
                        price += supplement.getPrice();
                    }
                }
                price *= sandwichOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getDessertOrder() != null) {
            for (DessertOrder dessertOrder : order.getDessertOrder()) {
                double price = 0;
                Dessert dessert = dessertRepository.findById(dessertOrder.getDessert().getId()).orElse(null);
                for (DessertSize size : dessert.getSizes()) {
                    if (size.getId().equals(dessertOrder.getSize().getId()))
                        price += size.getPrice();
                }
                if (dessertOrder.getSyrup() != null) {
                    Syrup syrup = syrupRepository.findById(dessertOrder.getSyrup().getId()).orElse(null);
                    if (syrup != null) {
                        price += syrup.getPrice();
                    }
                }
                if (dessertOrder.getSupplement() != null) {
                    Supplement supplement = supplementRepository.findById(dessertOrder.getSupplement().getId()).orElse(null);
                    if (supplement != null) {
                        price += supplement.getPrice();
                    }
                }
                price *= dessertOrder.getCount();
                mainPrice += price;
            }
        }
        return Math.toIntExact(Math.round(mainPrice));
    }
}
