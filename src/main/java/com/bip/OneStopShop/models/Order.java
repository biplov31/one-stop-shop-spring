package com.bip.OneStopShop.models;

import java.util.HashSet;
import java.util.Set;

public class Order {

    private Set<OrderItem> order = new HashSet<>();
    private Integer userId;
    private Double cost;

}
