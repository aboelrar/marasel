package www.gift_vouchers.marasel.MainScreen.ui.Categories.model;

public class CategoryList {
    String id, img, title, type, distance, rate;

    public CategoryList(String id, String img, String title, String type, String distance, String rate) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.type = type;
        this.distance = distance;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
