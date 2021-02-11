package www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model;

public class MyOfferList {
    String id, product, icon, address, time, status, clientImg;

    public MyOfferList(String id, String product, String icon, String address, String time, String status, String clientImg) {
        this.id = id;
        this.product = product;
        this.icon = icon;
        this.address = address;
        this.time = time;
        this.status = status;
        this.clientImg = clientImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientImg() {
        return clientImg;
    }

    public void setClientImg(String clientImg) {
        this.clientImg = clientImg;
    }
}
