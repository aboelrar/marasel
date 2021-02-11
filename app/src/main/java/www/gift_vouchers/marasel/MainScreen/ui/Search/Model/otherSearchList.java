package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;

public class otherSearchList {
    String id, title, address, img;
    Boolean status;

    public otherSearchList(String id, String title, String address, Boolean status, String img) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.status = status;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
