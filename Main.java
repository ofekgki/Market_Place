package SaarAviviOfekKanariRonAfriat;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //Saar Avivi 207881350
    //Ofek Kanari 212140008
    //Ron Afriat 322614926
    //241012805
    //Pini Shlomi

    private static Management m;
    private static Scanner io;
    private static Exception_market eX;

    public static int Menu() throws Exception_market {
        io = new Scanner(System.in);
        String starter = "";
        eX = new Exception_market(starter);
        System.out.println("Please choose an action:");
        System.out.println("0 - Exit program");
        System.out.println("1 - Add seller");
        System.out.println("2 - Add buyer");
        System.out.println("3 - Add seller product");
        System.out.println("4 - Add product to cart");
        System.out.println("5 - Cart payment");
        System.out.println("6 - Display all buyer's information");
        System.out.println("7 - Display all seller's information");
        System.out.println("8 - Display all products from category");
        System.out.println("9 - Replace cart");
        int choice;
        try {
            String action = io.nextLine();
            choice = eX.validateInt(action);
            if (eX.checkRangeAction(action) == -1) {
                System.out.println("Choose a number between 0 - 9");
                return 1;
            } else {
                choice = Integer.parseInt(action);
            }
        } catch (Exception_market e) {
            System.out.println("You should enter a number");
            return 1;
        }
        switch (choice) {
            case 0: {
                return close();
            }
            case 1: {
                addSeller();
                break;
            }
            case 2: {
                addBuyer();
                break;
            }
            case 3: {
                addSellerProduct();
                break;
            }
            case 4: {
                addProductToCart();
                break;
            }
            case 5: {
                payment();
                break;
            }
            case 6: {
                m.displayBuyersInfo();
                break;
            }
            case 7: {
                m.displaySellersInfo();
                break;
            }
            case 8: {
                productByCategory();
                break;
            }
            case 9: {
                newCartFromHistory();
                break;
            }
        }
        return 1;
    }


    private static void newCartFromHistory() {
        String buyerName = "";
        String newCart = "";
        String cartString = "";
        int cartNum;
        m.displayBuyerName();
        System.out.println("Type the name of The buyer you would like to Choose - ");
        try {
            buyerName = io.nextLine();
            eX.validateUserName(buyerName);
        } catch (Exception_market e) {
            return;
        }
        if (!m.checkIfCartIsEmpty(buyerName)) {
            System.out.println("Would you like to create a new cart? Y/N (default:Y) - ");
            try {
                newCart = io.nextLine();
                eX.validateYN(newCart);
            } catch (InputMismatchException e) {
                return;
            }
            if (newCart.equalsIgnoreCase("N")) {
                return;
            } else {
                String history = m.showBuyerHistory(buyerName);
                System.out.println(history);
                System.out.println("Choose The cart index you would like to reuse");
                try {
                    cartString= io.nextLine();
                    cartNum = eX.validateInt(cartString);
                    if (eX.positiveNum(cartNum) == 0) {
                        System.out.println("input should be larger than 0");
                        return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input must be a number!");
                    return;
                } catch (Exception_market e) {
                    throw new RuntimeException(e);
                }
                m.switchCartByIndex(cartNum, buyerName);
                io.nextLine();
            }
        }
    }

    private static void addSeller() {
        System.out.println("Enter user name - ");
        String sellerName = "";
        String sellerPassword = "";
        try {
            sellerName = io.nextLine();
            eX.validateUserName(sellerName);
        } catch (Exception_market e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("Enter password - ");
        try {
            sellerPassword = io.nextLine();
            eX.validatePassword(sellerPassword);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        while (!m.addSeller(sellerName, sellerPassword)) {
            System.out.println("Name is taken, Choose another name - ");
            sellerName = io.nextLine();
        }
        System.out.println("Seller added successfully.");
    }

    private static void addBuyer() {
        String buyerName = "";
        String password = "";
        String country = "";
        String city = "";
        String street = "";
        String houseNum = "";
        String zip = "";
        int houseNumber;
        int zipNumber;
        System.out.println("Enter user name - ");
        try {
            buyerName = io.nextLine();
            eX.validateUserName(buyerName);
        } catch (Exception_market e) {
            return;
        }
        System.out.println("Enter password - ");
        try {
            password = io.nextLine();
            eX.validatePassword(password);
        } catch (InputMismatchException e) {
            return;
        }
        System.out.println("Enter country - ");
        try {
            country = io.nextLine();
            eX.validateOnlyLetters(country);
        } catch (InputMismatchException e) {
            return;
        }
        System.out.println("Enter city - ");
        try {
            city = io.nextLine();
            eX.validateOnlyLetters(city);
        } catch (InputMismatchException e) {
            return;
        }
        System.out.println("Enter street name - ");
        try {
            street = io.nextLine();
            eX.validateOnlyLetters(street);
        } catch (InputMismatchException e) {
            return;
        }
        System.out.println("Enter house number - ");
        try {
            houseNum = io.nextLine();
            houseNumber = eX.validateInt(houseNum);
            if (eX.positiveNum(houseNumber) == 0) {
                System.out.println("input should be larger than 0");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number!");
            return;
        } catch (Exception_market e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter zip code - ");
        try {
            zip = io.nextLine();
            zipNumber = eX.validateInt(zip);
            if (eX.positiveNum(zipNumber) == 0) {
                System.out.println("input should be larger than 0");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number!");
            return;
        } catch (Exception_market e) {
            throw new RuntimeException(e);
        }
        Address temp = new Address(country, city, street, houseNumber, zipNumber);
        while (!m.addBuyer(buyerName, password, temp)) {
            System.out.println("Name is taken, Choose other name - ");
            try {
                buyerName = io.nextLine();
                eX.validateUserName(buyerName);
            } catch (Exception_market e) {
                return;
            }
        }
        System.out.println("Buyer added successfully");
    }

    private static void addSellerProduct() {
        String sellerName = "";
        String productName = "";
        String productPrice = "";
        String specialPackage = "";
        String choice = "";
        float priceNum;
        int choiceNum;
        String hopPoint = "";
        m.displaySellerName();
        System.out.println("Type the name of The seller you would like to add - ");
        try {
            sellerName = io.nextLine();
            eX.validateUserName(sellerName);
        } catch (Exception_market e) {
            return;
        }
        if (m.checkIfSellerExist(sellerName)) {
            System.out.println("Enter the name of the product - ");
            try {
                productName = io.nextLine();
                eX.validateOnlyLetters(productName);
            } catch (InputMismatchException e) {
                return;
            }
            System.out.println("Enter the price of the product - ");
            try {
                productPrice = io.nextLine();
                eX.validateFloat(productPrice);
                if (eX.checkRangePrice(productPrice) == 1) {
                    priceNum = Integer.parseInt(productPrice);
                } else {
                    System.out.println("input should be larger than 0");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number!");
                return;
            }
            System.out.println("Does the product have special packaging? Y/N (default:N) - ");
            try {
                specialPackage = io.nextLine();
                eX.validateYN(specialPackage);
            } catch (InputMismatchException e) {
                return;
            }
            System.out.println("Choose category by number");
            m.showCategories();
            try {
                choice = io.nextLine();
                choiceNum = eX.validateInt(choice);
                if (eX.checkRangeCategory(choiceNum) == 0) {
                    choiceNum = Integer.parseInt(choice);
                } else {
                    System.out.println("input should be between 1-4");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number!");
                return;
            } catch (Exception_market e) {
                throw new RuntimeException(e);
            }
            Category uCategory = m.intToEnum(choiceNum);
            if (specialPackage.equalsIgnoreCase("Y")) {
                m.addSellerProduct(sellerName, priceNum, productName, uCategory, true);
                System.out.println("Product with a special package listed successfully");
            } else {
                m.addSellerProduct(sellerName, priceNum, productName, uCategory, false);
                System.out.println("Product listed successfully");
            }
        } else if (!m.checkIfSellerExist(sellerName)) {
            System.out.println("The seller does not exist, try a different seller or create a new one.");
            System.out.println("Would you like to try another name? Y/N");
            try {
                hopPoint = io.nextLine();
                eX.validateYN(hopPoint);
            } catch (InputMismatchException e) {
                return;
            }
            if (hopPoint.equalsIgnoreCase("Y")) {
                addSellerProduct();
            }
        }
    }

    private static void addProductToCart() {
        String buyerName = "";
        String sellerName1 = "";
        String productIndex = "";
        int productNum;
        m.displayBuyerName();
        System.out.println("Type the name of The buyer you would like to Choose - ");
        try {
            buyerName = io.nextLine();
            eX.validateUserName(buyerName);
        } catch (Exception_market e) {
            return;
        }
        m.displaySellerName();
        System.out.println("Type the name of The seller you would like to Choose - ");
        try {
            sellerName1 = io.nextLine();
            eX.validateUserName(sellerName1);
        } catch (Exception_market e) {
            return;
        }
        m.displayProducts(sellerName1);
        System.out.println("Enter The number of the product you would like to buy");
        try {
            productIndex = io.nextLine();
            productNum = eX.validateInt(productIndex);
            if (eX.positiveNum(productNum) == 0) {
                System.out.println("input should be larger than 0");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number!");
            return;
        } catch (Exception_market e) {
            throw new RuntimeException(e);
        }

        if (m.addBuyerProduct(buyerName, sellerName1, productNum - 1)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Something went Wrong, please try again.");
        }
    }

    private static void payment() {
        String paymentName = "";
        m.displayBuyerName();
        System.out.println("Enter buyer name: ");
        try {
            paymentName = io.nextLine();
            eX.validateUserName(paymentName);
        } catch (Exception_market e) {
            return;
        }
        m.payment(paymentName);
    }

    private static void productByCategory() {
        String choice = "";
        int categoryChoice;
        m.showCategories();
        System.out.println("Choose a category:");
        try {
            choice = io.nextLine();
            categoryChoice = eX.validateInt(choice);
            if (eX.checkRangeCategory(categoryChoice) == 0) {
                System.out.println("input should be between 1-4");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number!");
            return;
        } catch (Exception_market e) {
            throw new RuntimeException(e);
        }
        Category choiceCategory = m.intToEnum(categoryChoice);
        m.displayProductByCategory(choiceCategory);

    }

    public static void Run() throws Exception_market {
        m = new Management();
        int close = 1;
        do {
            close = Menu();
        }
        while (close != 0);
    }

    private static int close() {
        System.out.println("Closing...");
        return 0;
    }

    public static void main(String[] args) throws Exception_market {
        Run();
    }

    public static void seteX(Exception_market eX) {
        Main.eX = eX;
    }
}