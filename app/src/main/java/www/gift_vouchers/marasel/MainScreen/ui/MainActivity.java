package www.gift_vouchers.marasel.MainScreen.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import www.gift_vouchers.marasel.Drivers.Drivers;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui.Cart;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI.myOrder;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.UI.Offers;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI.PersonalInformation;
import www.gift_vouchers.marasel.MainScreen.ui.Settings.UI.Settings;
import www.gift_vouchers.marasel.MainScreen.ui.home.ui.home;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.network_check_status.regist_network_broadcast;
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

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(MainActivity.this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.requests) {
            new utils().Replace_Fragment(new myOrder(), R.id.frag, MainActivity.this);
        } else if (menuItem.getItemId() == R.id.stores) {
            new utils().Replace_Fragment(new home(), R.id.frag, MainActivity.this);
        } else if (menuItem.getItemId() == R.id.profile) {
            new utils().Replace_Fragment(new Settings(), R.id.frag, MainActivity.this);
        } else if (menuItem.getItemId() == R.id.cart) {
            new utils().Replace_Fragment(new Cart(), R.id.frag, MainActivity.this);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT
        Log.e("num_is", "" + f);

        if (f instanceof home) {  //CHECK IF FRAGMENT == MAIN PAGE TO CLOSE
            Log.e("num_is", "1");
            moveTaskToBack(true);

        } else if (f instanceof Offers) {
            Log.e("num_is", "2");
            moveTaskToBack(true);

        } else if (f instanceof PersonalInformation) {
            Log.e("num_is", "3");
            moveTaskToBack(true);

        } else if (fm.getBackStackEntryCount() > 0) {
            Log.e("num_is", "4");
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.e("num_is", "5");
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
