package www.gift_vouchers.marasel.Drivers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStar;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class Drivers extends AppCompatActivity implements Callback {
    Callback callback;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        bottomNavigationView = findViewById(R.id.nav_driver);
        callback = this;
        new utils().Replace_Fragment(new WorkAsStar(callback), R.id.frag, this);

    }

    @Override
    public void setBottomNavVisible() {
      bottomNavigationView.setVisibility(View.VISIBLE);
    }
}