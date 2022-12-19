package com.example.RestApiCoffee.service.order;

import com.example.RestApiCoffee.dto.requests.order.OrderRequest;
import com.example.RestApiCoffee.dto.requests.order.products.*;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.entities.product.coffee.CoffeeSize;
import com.example.RestApiCoffee.entities.product.dessert.DessertSize;
import com.example.RestApiCoffee.entities.product.sandwich.SandwichSize;
import com.example.RestApiCoffee.entities.product.snack.SnackSize;
import com.example.RestApiCoffee.entities.product.tea.TeaSize;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.repository.CityRepository;
import com.example.RestApiCoffee.repository.OrderRepository;
import com.example.RestApiCoffee.repository.PaymentFormRepository;
import com.example.RestApiCoffee.repository.ingredients.*;
import com.example.RestApiCoffee.repository.product.*;
import com.example.RestApiCoffee.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private final CityRepository cityRepository;
    private final PaymentFormRepository paymentFormRepository;

    private final UserService userService;

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
                for (CoffeeSize size : coffeeOrder.getCoffee().getSizes()) {
                    if (size.getId().equals(coffeeOrder.getSize().getId())) {
                        price += size.getPrice();
                    }
                }
                if (coffeeOrder.getAlcohol() != null) {
                    price += coffeeOrder.getAlcohol().getPrice();
                }
                if (coffeeOrder.getMilk() != null) {
                    price += coffeeOrder.getMilk().getPrice();
                }
                if (coffeeOrder.getSyrup() != null) {
                    price += coffeeOrder.getSyrup().getPrice();
                }
                if (coffeeOrder.getSupplement() != null) {
                    price += coffeeOrder.getSupplement().getPrice();
                }
                price *= coffeeOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getTeaOrder() != null){
            for(TeaOrder productOrder : order.getTeaOrder()){
                double price = 0;
                for (TeaSize size : productOrder.getTea().getSizes()) {
                    if (size.getId().equals(productOrder.getSize().getId())) {
                        price += size.getPrice();
                    }
                }
                if (productOrder.getMilk() != null) {
                    price += productOrder.getMilk().getPrice();
                }
                if (productOrder.getSyrup() != null) {
                    price += productOrder.getSyrup().getPrice();
                }
                if (productOrder.getSupplement() != null) {
                    price += productOrder.getSupplement().getPrice();
                }
                price *= productOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getSnackOrder() != null){
            for(SnackOrder productOrder : order.getSnackOrder()){
                double price = 0;
                for (SnackSize size : productOrder.getSnack().getSizes()) {
                    if (size.getId().equals(productOrder.getSize().getId())) {
                        price += size.getPrice();
                    }
                }
                if (productOrder.getSauce() != null) {
                    price += productOrder.getSauce().getPrice();
                }
                if (productOrder.getSupplement() != null) {
                    price += productOrder.getSupplement().getPrice();
                }
                price *= productOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getSandwichOrder() != null){
            for(SandwichOrder productOrder : order.getSandwichOrder()){
                double price = 0;
                for (SandwichSize size : productOrder.getSandwich().getSizes()) {
                    if (size.getId().equals(productOrder.getSize().getId())) {
                        price += size.getPrice();
                    }
                }
                if (productOrder.getSauce() != null) {
                    price += productOrder.getSauce().getPrice();
                }
                if (productOrder.getSupplement() != null) {
                    price += productOrder.getSupplement().getPrice();
                }
                price *= productOrder.getCount();
                mainPrice += price;
            }
        }
        if (order.getDessertOrder() != null) {
            for(DessertOrder productOrder : order.getDessertOrder()){
                double price = 0;
                for (DessertSize size : productOrder.getDessert().getSizes()) {
                    if (size.getId().equals(productOrder.getSize().getId())) {
                        price += size.getPrice();
                    }
                }
                if (productOrder.getSyrup() != null) {
                    price += productOrder.getSyrup().getPrice();
                }
                if (productOrder.getSupplement() != null) {
                    price += productOrder.getSupplement().getPrice();
                }
                price *= productOrder.getCount();
                mainPrice += price;
            }
        }
        return Math.toIntExact(Math.round(mainPrice));
    }

    public Map<String, String> buildOrder(OrderRequest orderRequest, User user) {
        Map<String, String> exceptions = new HashMap<>();
        Order order = new Order();
        order.setCity(cityRepository.findById(orderRequest.getCity().getId()).orElse(null));
        if(order.getCity() == null){
            exceptions.put("city", "There is no city with id " + orderRequest.getCity().getId());
        }
        order.setPaymentForm(paymentFormRepository.findById(orderRequest.getPaymentForm().getId()).orElse(null));
        if(order.getPaymentForm() == null){
            exceptions.put("payment form", "There is no payment form with id " + orderRequest.getPaymentForm().getId());
        }
        if (orderRequest.getCoffeeOrders() != null){
            Set<CoffeeOrder> coffeeOrderSet = new HashSet<>();
            for(CoffeeOrderRequest coffeeOrderRequest : orderRequest.getCoffeeOrders()){
                CoffeeOrder coffeeOrder = new CoffeeOrder();
                coffeeOrder.setCoffee(coffeeRepository.findById(coffeeOrderRequest.getCoffee().getId()).orElse(null));
                if(coffeeOrder.getCoffee() == null){
                    exceptions.put("coffee", "There is no coffee with id " + coffeeOrderRequest.getCoffee().getId());
                } else {
                    boolean searchSize = true;
                    for (CoffeeSize size : coffeeOrder.getCoffee().getSizes()) {
                        if (size.getId().equals(coffeeOrderRequest.getSize().getId())) {
                            coffeeOrder.setSize(size);
                            searchSize = false;
                            break;
                        }
                    }
                    if (searchSize) {
                        exceptions.put("coffee size", "There is no coffee size with id " + coffeeOrderRequest.getSize().getId());
                    }
                    if (coffeeOrderRequest.getAlcohol() != null) {
                        coffeeOrder.setAlcohol(alcoholRepository.findById(coffeeOrderRequest.getAlcohol().getId()).orElse(null));
                        if (coffeeOrder.getAlcohol() == null) {
                            exceptions.put("coffee alcohol", "There is no alcohol with the id " + coffeeOrderRequest.getAlcohol().getId());
                        }
                    }
                    if (coffeeOrderRequest.getMilk() != null) {
                        coffeeOrder.setMilk(milkRepository.findById(coffeeOrderRequest.getMilk().getId()).orElse(null));
                        if (coffeeOrder.getMilk() == null) {
                            exceptions.put("coffee milk", "There is no milk with id " + coffeeOrderRequest.getMilk().getId());
                        }
                    }
                    if (coffeeOrderRequest.getSyrup() != null) {
                        coffeeOrder.setSyrup(syrupRepository.findById(coffeeOrderRequest.getSyrup().getId()).orElse(null));
                        if (coffeeOrder.getSyrup() == null) {
                            exceptions.put("coffee syrup", "There is no syrup with id " + coffeeOrderRequest.getSyrup().getId());
                        }
                    }
                    if (coffeeOrderRequest.getSupplement() != null) {
                        coffeeOrder.setSupplement(supplementRepository.findById(coffeeOrderRequest.getSupplement().getId()).orElse(null));
                        if (coffeeOrder.getSupplement() == null) {
                            exceptions.put("coffee supplement", "There is no supplement with id " + coffeeOrderRequest.getSupplement().getId());
                        }
                    }
                    if (coffeeOrderRequest.getCount()!= null){
                        if (coffeeOrderRequest.getCount() <= 0) {
                            exceptions.put("coffee count", "Count must be greater than 0");
                        } else {
                            coffeeOrder.setCount(coffeeOrderRequest.getCount());
                        }
                    }
                }
                coffeeOrderSet.add(coffeeOrder);
            }
            order.setCoffeeOrder(coffeeOrderSet);
        }
        if (orderRequest.getTeaOrders() != null){
            Set<TeaOrder> teaOrderSet = new HashSet<>();
            for(TeaOrderRequest teaOrderRequest : orderRequest.getTeaOrders()){
                TeaOrder teaOrder = new TeaOrder();
                teaOrder.setTea(teaRepository.findById(teaOrderRequest.getTea().getId()).orElse(null));
                if(teaOrder.getTea() == null){
                    exceptions.put("tea", "There is no tea with id " + teaOrderRequest.getTea().getId());
                } else {
                    boolean searchSize = true;
                    for (TeaSize size : teaOrder.getTea().getSizes()) {
                        if (size.getId().equals(teaOrderRequest.getSize().getId())) {
                            teaOrder.setSize(size);
                            searchSize = false;
                            break;
                        }
                    }
                    if (searchSize) {
                        exceptions.put("tea size", "There is no tea size with id " + teaOrderRequest.getSize().getId());
                    }
                    if (teaOrderRequest.getMilk() != null) {
                        teaOrder.setMilk(milkRepository.findById(teaOrderRequest.getMilk().getId()).orElse(null));
                        if (teaOrder.getMilk() == null) {
                            exceptions.put("tea milk", "There is no milk with id " + teaOrderRequest.getMilk().getId());
                        }
                    }
                    if (teaOrderRequest.getSyrup() != null) {
                        teaOrder.setSyrup(syrupRepository.findById(teaOrderRequest.getSyrup().getId()).orElse(null));
                        if (teaOrder.getSyrup() == null) {
                            exceptions.put("tea syrup", "There is no syrup with id " + teaOrderRequest.getSyrup().getId());
                        }
                    }
                    if (teaOrderRequest.getSupplement() != null) {
                        teaOrder.setSupplement(supplementRepository.findById(teaOrderRequest.getSupplement().getId()).orElse(null));
                        if (teaOrder.getSupplement() == null) {
                            exceptions.put("tea supplement", "There is no supplement with id " + teaOrderRequest.getSupplement().getId());
                        }
                    }
                    if (teaOrderRequest.getCount()!= null){
                        if (teaOrderRequest.getCount() <= 0) {
                            exceptions.put("tea count", "Count must be greater than 0");
                        } else {
                            teaOrder.setCount(teaOrderRequest.getCount());
                        }
                    }
                }
                teaOrderSet.add(teaOrder);
            }
            order.setTeaOrder(teaOrderSet);
        }
        if (orderRequest.getSnackOrders() != null){
            Set<SnackOrder> snackOrderSet = new HashSet<>();
            for(SnackOrderRequest snackOrderRequest : orderRequest.getSnackOrders()){
                SnackOrder snackOrder = new SnackOrder();
                snackOrder.setSnack(snackRepository.findById(snackOrderRequest.getSnack().getId()).orElse(null));
                if(snackOrder.getSnack() == null){
                    exceptions.put("snack", "There is no snack with id " + snackOrderRequest.getSnack().getId());
                } else {
                    boolean searchSize = true;
                    for (SnackSize size : snackOrder.getSnack().getSizes()) {
                        if (size.getId().equals(snackOrderRequest.getSize().getId())) {
                            snackOrder.setSize(size);
                            searchSize = false;
                            break;
                        }
                    }
                    if (searchSize) {
                        exceptions.put("snack size", "There is no snack size with id " + snackOrderRequest.getSize().getId());
                    }
                    if (snackOrderRequest.getSauce() != null) {
                        snackOrder.setSauce(sauceRepository.findById(snackOrderRequest.getSauce().getId()).orElse(null));
                        if (snackOrder.getSauce() == null) {
                            exceptions.put("snack syrup", "There is no sauce with id " + snackOrderRequest.getSauce().getId());
                        }
                    }
                    if (snackOrderRequest.getSupplement() != null) {
                        snackOrder.setSupplement(supplementRepository.findById(snackOrderRequest.getSupplement().getId()).orElse(null));
                        if (snackOrder.getSupplement() == null) {
                            exceptions.put("snack supplement", "There is no supplement with id " + snackOrderRequest.getSupplement().getId());
                        }
                    }
                    if (snackOrderRequest.getCount()!= null){
                        if (snackOrderRequest.getCount() <= 0) {
                            exceptions.put("snack count", "Count must be greater than 0");
                        } else {
                            snackOrder.setCount(snackOrderRequest.getCount());
                        }
                    }
                }
                snackOrderSet.add(snackOrder);
            }
            order.setSnackOrder(snackOrderSet);
        }
        if (orderRequest.getSandwichOrders() != null){
            Set<SandwichOrder> sandwichOrderSet = new HashSet<>();
            for(SandwichOrderRequest sandwichOrderRequest : orderRequest.getSandwichOrders()){
                SandwichOrder sandwichOrder = new SandwichOrder();
                sandwichOrder.setSandwich(sandwichRepository.findById(sandwichOrderRequest.getSandwich().getId()).orElse(null));
                if(sandwichOrder.getSandwich() == null){
                    exceptions.put("sandwich", "There is no sandwich with id " + sandwichOrderRequest.getSandwich().getId());
                } else {
                    boolean searchSize = true;
                    for (SandwichSize size : sandwichOrder.getSandwich().getSizes()) {
                        if (size.getId().equals(sandwichOrderRequest.getSize().getId())) {
                            sandwichOrder.setSize(size);
                            searchSize = false;
                            break;
                        }
                    }
                    if (searchSize) {
                        exceptions.put("sandwich size", "There is no sandwich size with id " + sandwichOrderRequest.getSize().getId());
                    }
                    if (sandwichOrderRequest.getSauce() != null) {
                        sandwichOrder.setSauce(sauceRepository.findById(sandwichOrderRequest.getSauce().getId()).orElse(null));
                        if (sandwichOrder.getSauce() == null) {
                            exceptions.put("sandwich syrup", "There is no sauce with id " + sandwichOrderRequest.getSauce().getId());
                        }
                    }
                    if (sandwichOrderRequest.getSupplement() != null) {
                        sandwichOrder.setSupplement(supplementRepository.findById(sandwichOrderRequest.getSupplement().getId()).orElse(null));
                        if (sandwichOrder.getSupplement() == null) {
                            exceptions.put("sandwich supplement", "There is no supplement with id " + sandwichOrderRequest.getSupplement().getId());
                        }
                    }
                    if (sandwichOrderRequest.getCount()!= null){
                        if (sandwichOrderRequest.getCount() <= 0) {
                            exceptions.put("sandwich count", "Count must be greater than 0");
                        } else {
                            sandwichOrder.setCount(sandwichOrderRequest.getCount());
                        }
                    }
                }
                sandwichOrderSet.add(sandwichOrder);
            }
            order.setSandwichOrder(sandwichOrderSet);
        }
        if (orderRequest.getDessertOrders() != null){
            Set<DessertOrder> dessertOrderSet = new HashSet<>();
            for(DessertOrderRequest dessertOrderRequest : orderRequest.getDessertOrders()){
                DessertOrder dessertOrder = new DessertOrder();
                dessertOrder.setDessert(dessertRepository.findById(dessertOrderRequest.getDessert().getId()).orElse(null));
                if(dessertOrder.getDessert() == null){
                    exceptions.put("dessert", "There is no dessert with id " + dessertOrderRequest.getDessert().getId());
                } else {
                    boolean searchSize = true;
                    for (DessertSize size : dessertOrder.getDessert().getSizes()) {
                        if (size.getId().equals(dessertOrderRequest.getSize().getId())) {
                            dessertOrder.setSize(size);
                            searchSize = false;
                            break;
                        }
                    }
                    if (searchSize) {
                        exceptions.put("dessert size", "There is no dessert size with id " + dessertOrderRequest.getSize().getId());
                    }
                    if (dessertOrderRequest.getSyrup() != null) {
                        dessertOrder.setSyrup(syrupRepository.findById(dessertOrderRequest.getSyrup().getId()).orElse(null));
                        if (dessertOrder.getSyrup() == null) {
                            exceptions.put("dessert syrup", "There is no syrup with id " + dessertOrderRequest.getSyrup().getId());
                        }
                    }
                    if (dessertOrderRequest.getSupplement() != null) {
                        dessertOrder.setSupplement(supplementRepository.findById(dessertOrderRequest.getSupplement().getId()).orElse(null));
                        if (dessertOrder.getSupplement() == null) {
                            exceptions.put("dessert supplement", "There is no supplement with id " + dessertOrderRequest.getSupplement().getId());
                        }
                    }
                    if (dessertOrderRequest.getCount()!= null){
                        if (dessertOrderRequest.getCount() <= 0) {
                            exceptions.put("dessert count", "Count must be greater than 0");
                        } else {
                            dessertOrder.setCount(dessertOrderRequest.getCount());
                        }
                    }
                }
                dessertOrderSet.add(dessertOrder);
            }
            order.setDessertOrder(dessertOrderSet);
        }
        if(exceptions.isEmpty()) {
            order.setTime(orderRequest.getTime());
            order.setDate(orderRequest.getDate());
            order.setComment(orderRequest.getComment());
            order.setFlat(orderRequest.getFlat());
            order.setEntrance(orderRequest.getEntrance());
            order.setHome(orderRequest.getHome());
            order.setUser(user);
            Integer points = getPrice(order);
            userService.addPoints(user, points);
            save(order);
            Map<String, String> message = new HashMap<>();
            message.put("order", "Successfully created");
            return message;
        } else {
            return exceptions;
        }

    }
}
