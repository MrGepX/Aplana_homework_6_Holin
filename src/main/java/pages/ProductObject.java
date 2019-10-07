package pages;

public class ProductObject {
    private String name;
    private int price;
    private String warranty;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductObject(String name, int price, String warranty, String description) {
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
    }


}
