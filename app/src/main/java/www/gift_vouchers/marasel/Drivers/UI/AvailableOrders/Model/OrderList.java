package www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.Model;

public class OrderList {
    String id, type, ResTitle, code, time, resImg;

    public OrderList(String id, String type, String resTitle, String code, String time, String resImg) {
        this.id = id;
        this.type = type;
        ResTitle = resTitle;
        this.code = code;
        this.time = time;
        this.resImg = resImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResTitle() {
        return ResTitle;
    }

    public void setResTitle(String resTitle) {
        ResTitle = resTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResImg() {
        return resImg;
    }

    public void setResImg(String resImg) {
        this.resImg = resImg;
    }
}
