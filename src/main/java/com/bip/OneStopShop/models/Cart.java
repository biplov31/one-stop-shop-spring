package com.bip.OneStopShop.models;

import com.bip.OneStopShop.models.CartItem;

import java.util.HashSet;
import java.util.Set;

public class Cart {

    private Set<CartItem> cart = new HashSet<>();
    private Double totalCost;

    public Cart(Set<CartItem> cart, Double totalCost) {
        this.cart = cart;
        this.totalCost = totalCost;
    }

    public Set<CartItem> getCart() {
        return cart;
    }

    public void setCart(Set<CartItem> cart) {
        this.cart = cart;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
