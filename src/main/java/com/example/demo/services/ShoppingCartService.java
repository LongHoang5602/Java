package com.example.demo.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.CartItem;

@Service
@SessionScope
public class ShoppingCartService {
    Map<Integer, CartItem> maps = new HashMap<>();

    public void add(CartItem item) {
        CartItem cartItem = maps.get(item.getProductId());
        if (cartItem == null) {
            maps.put(item.getProductId(), item);
        } else {
            cartItem.setQty(cartItem.getQty() + 1);
        }
    }

    public void remove(int id) {
        maps.remove(id);
    }

    public CartItem update(int productId, int qty) {
        CartItem cartItem = maps.get(productId);
        cartItem.setQty(qty);
        return cartItem;
    }

    public void clear() {
        maps.clear();
    }

    public Collection<CartItem> getAllItem() {
        return maps.values();
    }

    public double getAmount() {
        return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
    }
}
