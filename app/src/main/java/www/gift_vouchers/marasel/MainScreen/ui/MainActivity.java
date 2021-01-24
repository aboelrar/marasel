package www.gift_vouchers.marasel.MainScreen.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI.myOrder;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI.PersonalInformation;
import www.gift_vouchers.marasel.MainScreen.ui.home.ui.home;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new utils().Replace_Fragment(new home(), R.id.frag, this);

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.requests) {
            new utils().Replace_Fragment(new myOrder(), R.id.frag, MainActivity.this);
        } else if (menuItem.getItemId() == R.id.stores) {
            new utils().Replace_Fragment(new PersonalInformation(), R.id.frag, MainActivity.this);
        }

        return false;
    }
}
