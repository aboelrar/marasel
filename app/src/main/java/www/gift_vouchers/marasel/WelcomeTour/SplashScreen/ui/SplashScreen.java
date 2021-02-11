package www.gift_vouchers.marasel.WelcomeTour.SplashScreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.marasel.AuthScreens.ui.auth;
import www.gift_vouchers.marasel.MainScreen.ui.MainActivity;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.WelcomeTour.SlidePages.ui.SlidePages;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        if (new saved_data().get_welcome_num(SplashScreen.this).equals("1")) {
            if (new saved_data().get_login_status(SplashScreen.this) == true) {
                new utils().splash_screen(this, MainActivity.class);
            } else {
                new utils().splash_screen(this, auth.class);
            }
        } else {
            new utils().splash_screen(this, SlidePages.class);
        }
    }
}
