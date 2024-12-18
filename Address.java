package SaarAviviOfekKanariRonAfriat;

public class Address {
    private String country;
    private String city;
    private String street;
    private int houseNumber;
    private int zipCode;


    public Address(String country, String city, String street, int houseNumber, int zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Address: ");
        sb.append("\n Country: ")
                .append(country)
                .append(",  City: ")
                .append(city)
                .append(", Street: ")
                .append(street)
                .append(", House Number: ")
                .append(houseNumber);
        return sb.toString();
    }
}
