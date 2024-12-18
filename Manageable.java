package SaarAviviOfekKanariRonAfriat;

public interface Manageable {
    void setP(Product p);
    boolean checkIfBuyerExist(String userName);
    boolean addBuyer(String userName, String password, Address address);
    boolean checkIfSellerExist(String userName);
    boolean addSeller(String userName, String password);
    void displaySellerName();
    void displayBuyerName();
    void displaySellersInfo();
    void displayBuyersInfo();
    int getBuyerIndex(String userName);
    int getSellerIndex(String userName);
    Seller findSeller(String name);
    Seller getSeller(String username);
    Buyer getBuyer(String username);
    void addSellerProduct(String sellerName, float price, String name, Category category, boolean specialPackage);
    boolean addBuyerProduct(String buyerName, String sellerName, int productIndex);
    void displayProducts(String username);
    void payment(String username);
    void showCategories();
    Category intToEnum(int category);
    void displayProductByCategory(Category category);
    String showBuyerHistory(String buyerName);
    boolean checkIfCartIsEmpty(String buyerName);
    void switchCartByIndex(int cartNum, String buyerName);






}
