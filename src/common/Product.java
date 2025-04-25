package common;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int categoryId; // 👈 اضافه شده: کد دسته‌بندی محصول

    // 🔹 سازنده بدون پارامتر (پیش‌فرض)
    public Product() {
    }

    // 🔹 سازنده کامل با همه پارامترها
    public Product(int id, String name, double price, int stock, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    // 🔹 متدهای Getter و Setter
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

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // 🔹 برای ذخیره در فایل به صورت رشته
    @Override
    public String toString() {
        return id + ";" + name + ";" + price + ";" + stock + ";" + categoryId;
    }

    // 🔹 برای تبدیل رشته فایل به شی Product
    public static Product fromString(String line) {
        String[] parts = line.split(";");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}
