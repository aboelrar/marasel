package www.gift_vouchers.marasel.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class send_data {

    public static void send_lan(Context context, String lan) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lan", lan);
        editor.commit();
    }


    //SAVE PERSONAL_NAME
    public static void send_name(Context context, String name) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

   //SAVE PERSONAL_ID
    public static void sendId(Context context, String id) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.commit();
    }

    //SAVE PERSONAL_NAME
    public static void send_email(Context context, String email) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }

    //SAVE PERSONAL_TOKEN
    public static void send_token(Context context, String token) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    //SAVE PERSONAL_PHONE
    public static void send_phone(Context context, String phone) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.commit();
    }

    //SAVE PERSONAL_TYPE
    public static void send_type(Context context, String type) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type", type);
        editor.commit();
    }


    //SET LOGIN TRUE
    public static void login_status(Context context, boolean status) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("login_bool", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login_bool", status);
        editor.commit();
    }

    //SET USER IMAGE
    public static void set_user_img(Context context, String image) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", image);
        editor.commit();
    }

    //SET USER IMAGE
    public static void set_user_gender(Context context, String gender) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gender", gender);
        editor.commit();
    }


    //SET ORDER ID
    public static void set_order_id(Context context, String id) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("order_id", id);
        editor.commit();
    }

    //SET ORDER NAME
    public static void set_order_name(Context context, String id) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("order_name", id);
        editor.commit();
    }


    //SET price
    public static void price(Context context, String price) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("price", price);
        editor.commit();
    }

    //SET CAT ID
    public static void cat_id(Context context, String cat_id) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cat_id", cat_id);
        editor.commit();
    }

    //SET TYPE
    public static void set_type(Context context, String type) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type", type);
        editor.commit();
    }

    //SET LOGO
    public static void set_logo(Context context, String logo) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("logo", logo);
        editor.commit();
    }

    //SET DESIGN
    public static void set_design(Context context, String phone) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("design", phone);
        editor.commit();
    }

    //SET CHECK CART
    public static void check_card(Context context, String cart) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart", cart);
        editor.commit();
    }

    //SET WINCH LAT
    public static void winch_owner_lat(Context context, double lat) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("winch_lat", Double.doubleToRawLongBits(lat));
        editor.commit();
    }

    //SET WINCH LAT
    public static void winch_owner_lng(Context context, double lng) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("winch_lng", Double.doubleToRawLongBits(lng));
        editor.commit();
    }

    //SET WINCH LAT
    public static void request_id(Context context, String id) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("request_id", id);
        editor.commit();
    }

    //SET USER PHONE
    public static void set_user_phone(Context context, String phone) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.commit();
    }

    //SET USER PHONE
    public static void set_welcome_num(Context context, String num) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("welcome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("num", num);
        editor.commit();
    }

    //SET STORE DATA
    public static void setStoreData(Context context, String num) {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences = context.getSharedPreferences("welcome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("num", num);
        editor.commit();
    }

    //SET TITLE DATA Store
    public static void setStoreTitle(Context context, String title) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("title", title);
        editor.commit();
    }

    //SET IMG DATA Store
    public static void setStoreImg(Context context, String img) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("img", img);
        editor.commit();
    }

    //SET IMG DATA LAT
    public static void setStoreLat(Context context, String lat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lat", lat);
        editor.commit();
    }

    //SET IMG DATA LNG
    public static void setStoreLng(Context context, String lng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lng", lng);
        editor.commit();
    }

    //SET DELIVERY TIME
    public static void setDeliveryTime(Context context, String time) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("deliveryTime", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("time", time);
        editor.commit();
    }

    //SET DELIVERY TIME
    public static void setStoreId(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.commit();
    }

     //CHECK ACTIVE STAR
    public static void checkActiveStar(Context context, boolean status) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("star", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("status", status);
        editor.commit();
    }
}
