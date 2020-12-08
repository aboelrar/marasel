package www.gift_vouchers.marasel.WelcomeTour.SplashScreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.WelcomeTour.SlidePages.ui.SlidePages;
import www.gift_vouchers.marasel.utils.utils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        utils utils = new utils();
        utils.splash_screen(this, SlidePages.class);
    }
}
