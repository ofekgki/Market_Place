package SaarAviviOfekKanariRonAfriat;

import java.time.LocalDate;
import java.util.Arrays;

public class Cart {
    private final int ARRAY_INITIALIZER = 0;
    private float price;
    private LocalDate shopDate;
    private Product[] shoppingCart;
    private int cartLogic;

    public Cart() {
        shoppingCart = new Product[ARRAY_INITIALIZER];
        cartLogic = ARRAY_INITIALIZER;

    }

    public Cart(Cart c) {
        this.price = c.price;
        this.shopDate = c.shopDate;
        shoppingCart = new Product[c.shoppingCart.length];
        for (int i = 0; i < c.cartLogic; i++) {
            shoppingCart[i] = new Product(c.shoppingCart[i]);
        }
        cartLogic = c.cartLogic;
    }

    public void addProduct(Product p) {
        if (cartLogic == 0) {
            shoppingCart = Arrays.copyOf(shoppingCart, 1);
            shoppingCart[0] = p;
        } else {
            if (shoppingCart.length == cartLogic) {
                shoppingCart = Arrays.copyOf(shoppingCart, shoppingCart.length * 2);
                shoppingCart[cartLogic] = p;
                price += p.getFinalPrice();
            }
            shoppingCart[cartLogic] = p;
        }
        cartLogic++;
        price += p.getFinalPrice();
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n Shopping Cart: ");
        sb.append("Total Price -  \t")
                .append(price)
                .append("\n Products - ");

        for (int i = 0; i < cartLogic; i++) {
            sb.append("\t")
                    .append(shoppingCart[i].toString());

        }
        return sb.toString();
    }

    public void setShopDate(LocalDate now) {
        this.shopDate = now;
    }

    public boolean isEmpty() {
        if(cartLogic == 0) {
            return true;
        }
        return false;
    }
}
