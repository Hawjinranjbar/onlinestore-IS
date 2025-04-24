package common;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product() {
    }

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    // برای ذخیره در فایل متنی
    @Override
    public String toString() {
        return id + ";" + name + ";" + price + ";" + stock;
    }

    // برای تبدیل از فایل متنی به شی Product
    public static Product fromString(String line) {
        String[] parts = line.split(";");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}
