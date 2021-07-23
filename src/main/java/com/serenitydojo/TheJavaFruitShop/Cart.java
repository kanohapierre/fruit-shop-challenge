package com.serenitydojo.TheJavaFruitShop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final Catalog catalog;
    private final List<CartItem> items;

    public Cart(Catalog catalog) {
        this.catalog = catalog;
        this.items = new ArrayList<>();
    }

    public CartAdder add(Double amount) {
        return new CartAdder(this, amount);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public Double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalCost).sum();
    }

    public class CartAdder {
        private final Cart Cart;
        private final Double amount;

        public CartAdder(Cart shoppingCart, Double amount) {
            this.Cart = shoppingCart;
            this.amount = amount;
        }

        public Cart kilosOf(Fruit fruit) {
            double basePrice = Cart.catalog.getPriceOf(fruit);
            double discountedPrice = (amount >= 5) ? basePrice * 0.9 : basePrice;
            CartItem item = new CartItem(fruit, amount, discountedPrice * amount);
           Cart.items.add(item);
            return Cart;
        }
    }
}
