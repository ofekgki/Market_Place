package SaarAviviOfekKanariRonAfriat;

public class Product {
    private String name;
    private int uniqueIdCounter = 1000;
    private String serialId;
    private Category category;
    private boolean hasSpecialPackage;
    private float finalPrice;
    private String sellerName;

    public Product(Seller user, float basePrice, String name, Category category, boolean hasSpecialPackage) {
        this.finalPrice = basePrice;
        this.name = name;
        this.category = category;
        if(hasSpecialPackage)
        {
            this.hasSpecialPackage = true;
            finalPrice += 20;
        }
        this.serialId = generateSerialId();
        this.sellerName = user.getUserName();
    }

    public Product(Product p) {
        this.finalPrice = p.getFinalPrice();
        this.name = p.name;
        this.category = p.category;
        this.hasSpecialPackage = p.hasSpecialPackage;
        this.serialId = generateSerialId();
        this.sellerName = p.sellerName;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public String generateSerialId() {
        uniqueIdCounter++;
        return "PRD#" + uniqueIdCounter;
    }

    public String getSerialId() {
        return serialId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n Product Information: ");
        sb.append("\n \t Name: ")
                .append(name)
                .append("\n \t Price: ")
                .append(finalPrice)
                .append("\n \t Category: ")
                .append(category);
        return sb.toString();
    }


    public String getSellerName() {
        return sellerName;
    }
}
