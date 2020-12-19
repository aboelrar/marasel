package www.gift_vouchers.marasel.MainScreen.ui.Store.model;

public class ProductList {
    String id, name, icon, price;

    public ProductList(String id, String name, String icon, String price) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
