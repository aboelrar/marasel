package www.gift_vouchers.marasel.MainScreen.ui.Offers.Model;

public class OfferList {
    String id, name, star, status, desc, productImg, driverId, phone;

    public OfferList(String id, String name, String star, String status, String desc, String productImg, String driverId, String phone) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.status = status;
        this.desc = desc;
        this.productImg = productImg;
        this.driverId = driverId;
        this.phone = phone;
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
