package SaarAviviOfekKanariRonAfriat;

import java.util.Arrays;

public class Seller extends User implements Comparable<Seller>{

    private final int ARRAY_INITIALIZER = 0;
    private Product[] catalog;
    private int cataLogic;

    public Seller(String name, String password) {
        super(name, password);
        catalog = new Product[ARRAY_INITIALIZER];
        cataLogic = ARRAY_INITIALIZER;
    }

    public void addProduct(Seller user, float price, String name, Category category, boolean specialPackage) {
        Product temp = new Product(user, price, name, category, specialPackage);
        if (cataLogic == 0) {
            catalog = Arrays.copyOf(catalog, 1);
            catalog[0] = temp;
        } else {
            if (catalog.length == cataLogic) {
                catalog = Arrays.copyOf(catalog, cataLogic * 2);
            }
            catalog[cataLogic] = temp;
        }
        cataLogic++;
    }

    public void displayCatalog() {
        System.out.println(super.getUserName() + "'s Catalog: ");
        for (int i = 0; i < cataLogic; i++) {
            System.out.println((i + 1) + ".  " + catalog[i].toString());

        }
    }

    public Product getProduct(int index) {
        if (checkIfProductExist(index)) {
            return catalog[index];
        }
        return null;
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Seller - ");
        sb.append("\n Username: ")
                .append(super.getUserName())
                .append("\n Catalog- ");
        for (int i = 0; i < cataLogic; i++) {
            sb.append(catalog[i].toString());
        }
        return sb.toString();
    }

    public boolean checkIfProductExist(int productIndex) {
        if (catalog[productIndex] != null) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Seller s) {
        return this.cataLogic - s.cataLogic;
    }
}
