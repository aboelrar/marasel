package www.gift_vouchers.marasel.MainScreen.ui.Cart.Model;

public class MyCartList {
    String id , name, price, tPrice, quantity, img, storeId;

    public MyCartList(String id, String name, String price, String tPrice, String quantity, String img, String storeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tPrice = tPrice;
        this.quantity = quantity;
        this.img = img;
        this.storeId = storeId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
