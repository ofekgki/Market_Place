package SaarAviviOfekKanariRonAfriat;

import java.time.LocalDate;
import java.util.Arrays;

public class Buyer extends User implements Comparable<Buyer>{
    private final int ARRAY_INITIALIZER = 0;
    private Address buyerAddress;
    private Cart shoppingCart;
    private Cart[] shoppingHistory;
    private int historyLogic;

    public Buyer(String name, String password, Address buyerAddress) {
        super(name, password);
        this.buyerAddress = buyerAddress;
        shoppingCart = new Cart();
        shoppingHistory = new Cart[ARRAY_INITIALIZER];
        historyLogic = ARRAY_INITIALIZER;

    }

    public String getUserName() {
        return super.getUserName();
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Cart[] getShoppingHistory() {
        return shoppingHistory;
    }

    public void setShoppingHistory(Cart[] shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }

    public void addProduct(Product temp) {
        shoppingCart.addProduct(temp);
    }

    public void payment() {
        System.out.println("The cart Price: " + shoppingCart.getPrice());
        addCartToHistory(shoppingCart);
        shoppingCart = new Cart();
    }

    public void addCartToHistory(Cart c) {
        if (historyLogic == 0) {
            shoppingHistory = Arrays.copyOf(shoppingHistory, 1);
            Cart temp = new Cart(c);
            shoppingHistory[0] = c;
        } else {
            if (shoppingHistory.length == historyLogic) {
                copyHistoryArr(shoppingHistory);
            }
            shoppingHistory[historyLogic] = c;
        }
        c.setShopDate(LocalDate.now());
        historyLogic++;
    }

    public void setCartFromHistory(int number) {
        int index = number - 1;
        this.shoppingCart = new Cart(shoppingHistory[index]);
    }

    public void copyHistoryArr(Cart[] c) {
        shoppingHistory = new Cart[c.length * 2];
        for (int i = 0; i < c.length; i++) {
            shoppingHistory[i] = new Cart(c[i]);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Buyer name - ");
        sb.append(super.getUserName())
                .append("\n")
                .append(buyerAddress)
                .append(" \n ")
                .append(shoppingCart.toString())
                .append(" \n History - ");

        for (int i = 0; i < historyLogic; i++) {
            sb.append("\n")
                    .append((i + 1))
                    .append(".   ")
                    .append(shoppingHistory[i].toString());
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Buyer b) {
        return (this.getUserName().toLowerCase().charAt(0) - b.getUserName().toLowerCase().charAt(0));
    }

    public String showHistory() {
        StringBuilder sb = new StringBuilder("History --> ");
                for (int i = 0; i < historyLogic; i++) {
            sb.append("\n")
                    .append((i + 1))
                    .append(".   ")
                    .append(shoppingHistory[i].toString());
        }
        return sb.toString();
    }
}