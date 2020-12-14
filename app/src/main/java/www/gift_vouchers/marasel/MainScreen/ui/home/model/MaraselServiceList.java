package www.gift_vouchers.marasel.MainScreen.ui.home.model;

public class MaraselServiceList {
    String id, icon, title;

    public MaraselServiceList(String id, String icon, String title) {
        this.id = id;
        this.icon = icon;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
