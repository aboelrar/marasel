package www.gift_vouchers.marasel.MainScreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.marasel.MainScreen.ui.home.ui.home;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new utils().Replace_Fragment(new home(),R.id.frag,this);
    }
}
