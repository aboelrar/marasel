package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model;

public class MyOrderList {
    String id, name, orderName, code, expectTime, status, productImg;

    public MyOrderList(String id, String name, String orderName, String code, String expectTime, String status, String productImg) {
        this.id = id;
        this.name = name;
        this.orderName = orderName;
        this.code = code;
        this.expectTime = expectTime;
        this.status = status;
        this.productImg = productImg;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
