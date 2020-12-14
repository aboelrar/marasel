package www.gift_vouchers.marasel.MainScreen.ui.home.model;

public class NearestStoresList {
    String id, icon, name, type, location, rate, freeDelivery;

    public NearestStoresList(String id, String icon, String name, String type, String location, String rate,String  freeDelivery) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.type = type;
        this.location = location;
        this.rate = rate;
        this.freeDelivery = freeDelivery;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(String freeDelivery) {
        this.freeDelivery = freeDelivery;
    }
}
