package SaarAviviOfekKanariRonAfriat;

import org.w3c.dom.ranges.RangeException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class Management implements Manageable {
    private final int ARRAY_INITIALIZER = 0;
    private Seller[] sellerList;
    private int sellerLogic;
    private Buyer[] buyerList;
    private int buyerLogic;
    private Product[] generalCatalog;
    private int generalCatalogic;

    private static Product p;
    public static Seller s;
    public static Exception_market e;

    public Management() {
        sellerList = new Seller[ARRAY_INITIALIZER];
        sellerLogic = ARRAY_INITIALIZER;
        buyerList = new Buyer[ARRAY_INITIALIZER];
        buyerLogic = ARRAY_INITIALIZER;
        generalCatalog = new Product[ARRAY_INITIALIZER];
        generalCatalogic = ARRAY_INITIALIZER;

    }

    public void setP(Product p) {
        Management.p = new Product(p);
    }


    public boolean checkIfBuyerExist(String userName) {
        for (int i = 0; i < buyerLogic; i++) {
            if (buyerList[i].getUserName().equals(userName))
                return true;
        }
        return false;
    }

    public boolean addBuyer(String userName, String password, Address address) {
        if (buyerLogic == 0) {
            buyerList = Arrays.copyOf(buyerList, 1);
            Buyer temp = new Buyer(userName, password, address);
            buyerList[0] = temp;
        } else {
            if (buyerList.length == buyerLogic) {
                buyerList = Arrays.copyOf(buyerList, buyerList.length * 2);
            }
            if (checkIfBuyerExist(userName)) {
                return false;

            }
            Buyer temp = new Buyer(userName, password, address);
            buyerList[buyerLogic] = temp;
        }
        buyerLogic++;
        return true;
    }

    public boolean checkIfSellerExist(String userName) {
        for (int i = 0; i < sellerLogic; i++) {
            if (sellerList[i].getUserName().equals(userName))
                return true;
        }
        return false;
    }

    public boolean addSeller(String userName, String password) {
        if (sellerLogic == 0) {
            sellerList = Arrays.copyOf(sellerList, 1);
            Seller temp = new Seller(userName, password);
            sellerList[0] = temp;
        } else {
            if (sellerList.length == sellerLogic) {
                sellerList = Arrays.copyOf(sellerList, sellerList.length * 2);
            }
            if (checkIfSellerExist(userName)) {
                return false;

            }
            Seller temp = new Seller(userName, password);
            sellerList[sellerLogic] = temp;
        }
        sellerLogic++;
        return true;
    }

    public void displaySellerName() {
        System.out.println("Seller list: ");
        for (int i = 0; i < sellerLogic; i++) {
            System.out.println((1 + i) + ". " + sellerList[i].getUserName());
        }
    }

    public void displayBuyerName() {
        System.out.println("Buyer list: ");
        for (int i = 0; i < buyerLogic; i++) {
            System.out.println((1 + i) + ".  " + buyerList[i].getUserName());
        }
    }

    public void displaySellersInfo() {
        Arrays.sort(sellerList);
        System.out.println("Seller list: \n ");
        for (int i = 0; i < sellerLogic; i++) {
            System.out.println();
            System.out.println((1 + i) + ". " + sellerList[i].toString());
        }
    }

    public void displayBuyersInfo() {
        Arrays.sort(buyerList);
        System.out.println("Buyer list: ");
        for (int i = 0; i < buyerList.length; i++) {
            System.out.println((1 + i) + ".  " + buyerList[i].toString());
        }
    }

    public int getBuyerIndex(String userName) {
        for (int i = 0; i < buyerLogic; i++) {
            if (userName.equals(buyerList[i].getUserName())) {
                return i;
            }
        }
        return -1;
    }

    public int getSellerIndex(String userName) {
        for (int i = 0; i < sellerLogic; i++) {
            if (userName.equals(sellerList[i].getUserName())) {
                return i;
            }
        }
        return -1;
    }

    public Seller findSeller(String name) {
        for (int i = 0; i < sellerList.length; i++) {
            if (Objects.equals(name, sellerList[i].getUserName())) {
                return sellerList[i];
            }
        }
        return null;
    }

    public Seller getSeller(String username) {
        for (int i = 0; i < sellerLogic; i++) {
            if (sellerList[i].getUserName().equals(username)) {
                return sellerList[i];
            }
        }
        return null;
    }

    public Buyer getBuyer(String username) {
        for (int i = 0; i < buyerLogic; i++) {
            if (checkIfBuyerExist(username)) {
                if (buyerList[i].getUserName().equals(username)) {
                    return buyerList[i];
                }
            }
        }

        return null;
    }

    public void addSellerProduct(String sellerName, float price, String name, Category category, boolean specialPackage) {
        if (checkIfSellerExist(sellerName)) {
            Seller seller = findSeller(sellerName);
            if (seller == null) {
                System.out.println("Seller not found.");
            } else {
                Product temp = new Product(seller, price, name, category, specialPackage);
                Objects.requireNonNull(getSeller(sellerName)).addProduct(seller, price, name, category, specialPackage);
                if (generalCatalogic == 0) {
                    generalCatalog = Arrays.copyOf(generalCatalog, 1);
                    generalCatalog[0] = temp;
                } else {
                    if (generalCatalog.length == generalCatalogic) {
                        generalCatalog = Arrays.copyOf(generalCatalog, generalCatalog.length * 2);
                    }
                    generalCatalog[generalCatalogic] = temp;
                }
                generalCatalogic++;
            }
        }
    }

    public boolean addBuyerProduct(String buyerName, String sellerName, int productIndex) {
        int indexB = getBuyerIndex(buyerName);
        int indexS = getSellerIndex(sellerName);
        if (sellerList[indexS].checkIfProductExist(productIndex)) {
            Product temp = sellerList[indexS].getProduct(productIndex);
            buyerList[indexB].addProduct(temp);
            System.out.println("Product added: " + temp.getName());
            return true;
        } else {
            System.out.println("You typed a number of a product that does not exist!");
            return false;
        }
    }

    public void displayProducts(String username) {
        if (checkIfSellerExist(username)) {
            Objects.requireNonNull(getSeller(username)).displayCatalog();
        } else {
            System.out.println("Seller Does not exist...");
        }
    }

    public void payment(String username) {
        if (checkIfBuyerExist(username)) {
            getBuyer(username).payment();
        } else {
            System.out.println("Buyer Doesn't exist");
        }
    }

    public void showCategories() {
        int i = 1;
        for (Category category : Category.values()) {
            System.out.println(i + ". " + category);
            i++;
        }
        System.out.println();
    }

    public Category intToEnum(int category) {
        return switch (category) {
            case 1 -> Category.KIDS;
            case 2 -> Category.ELECTRIC;
            case 3 -> Category.OFFICE;
            case 4 -> Category.CLOTHES;
            default ->
                    throw new IllegalArgumentException("Invalid value: " + category + " , please choose a number from 1-4");
        };
    }

    public void displayProductByCategory(Category category) {
        for (int i = 0; i < generalCatalogic; i++) {
            setP(generalCatalog[i]);
            if (p.getCategory().equals(category)) {
                System.out.println("Seller --> " + p.getSellerName() + ". " + p.getName());
            }
        }
    }

    public String showBuyerHistory(String buyerName) {
        Buyer b = getBuyer(buyerName);
        return b.showHistory();
    }

    public boolean checkIfCartIsEmpty(String buyerName) {
        Buyer b = getBuyer(buyerName);
        return (b.getShoppingCart().isEmpty());
    }

    public void switchCartByIndex(int cartNum, String buyerName) {
        Buyer b = getBuyer(buyerName);
        b.setCartFromHistory(cartNum);

    }


}
