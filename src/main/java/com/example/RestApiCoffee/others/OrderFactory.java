package com.example.RestApiCoffee.others;

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
import lombok.AllArgsConstructor;

import java.util.Collections;

@AllArgsConstructor
public class OrderFactory {
    private final ProductFactory productFactory;
    private final IngredientsFactory ingredientsFactory;

    public Order getOrder(){
        Order order = new Order();
        order.setCoffeeOrder(Collections.singleton(getCoffeeOrder()));
        order.setDessertOrder(Collections.singleton(getDessertOrder()));
        order.setSandwichOrder(Collections.singleton(getSandwichOrder()));
        order.setSnackOrder(Collections.singleton(getSnackOrder()));
        order.setTeaOrder(Collections.singleton(getTeaOrder()));
        User user = new User();
        user.setPhone("phone");
        user.setPassword("password");
        user.setPoints(1);
        order.setUser(user);
        return order;
    }

    public CoffeeOrder getCoffeeOrder(){
        CoffeeOrder order = new CoffeeOrder();
        order.setCoffee(productFactory.getCoffee());
        order.setAlcohol(ingredientsFactory.getAlcohol());
        order.setMilk(ingredientsFactory.getMilk());
        order.setSupplement(ingredientsFactory.getSupplement());
        order.setSyrup(ingredientsFactory.getSyrup());
        CoffeeSize size = new CoffeeSize();
        size.setName("name");
        size.setDescription("description");
        size.setPrice(1d);
        order.setSize(size);
        order.setCount(1);
        order.setActive(true);
        return order;
    }
    public DessertOrder getDessertOrder(){
        DessertOrder order = new DessertOrder();
        order.setDessert(productFactory.getDessert());
        order.setSupplement(ingredientsFactory.getSupplement());
        order.setSyrup(ingredientsFactory.getSyrup());
        DessertSize size = new DessertSize();
        size.setName("name");
        size.setDescription("description");
        size.setPrice(1d);
        order.setSize(size);
        order.setCount(1);
        order.setActive(true);
        return order;
    }

    public SandwichOrder getSandwichOrder(){
        SandwichOrder order = new SandwichOrder();
        order.setSandwich(productFactory.getSandwich());
        order.setSupplement(ingredientsFactory.getSupplement());
        order.setSauce(ingredientsFactory.getSauce());
        SandwichSize size = new SandwichSize();
        size.setName("name");
        size.setDescription("description");
        size.setPrice(1d);
        order.setSize(size);
        order.setCount(1);
        order.setActive(true);
        return order;
    }

    public SnackOrder getSnackOrder(){
        SnackOrder order = new SnackOrder();
        order.setSnack(productFactory.getSnack());
        order.setSupplement(ingredientsFactory.getSupplement());
        order.setSauce(ingredientsFactory.getSauce());
        SnackSize size = new SnackSize();
        size.setName("name");
        size.setDescription("description");
        size.setPrice(1d);
        order.setSize(size);
        order.setCount(1);
        order.setActive(true);
        return order;
    }

    public TeaOrder getTeaOrder(){
        TeaOrder order = new TeaOrder();
        order.setTea(productFactory.getTea());
        order.setSupplement(ingredientsFactory.getSupplement());
        order.setMilk(ingredientsFactory.getMilk());
        order.setSyrup(ingredientsFactory.getSyrup());
        TeaSize size = new TeaSize();
        size.setName("name");
        size.setDescription("description");
        size.setPrice(1d);
        order.setSize(size);
        order.setCount(1);
        order.setActive(true);
        return order;
    }
}
