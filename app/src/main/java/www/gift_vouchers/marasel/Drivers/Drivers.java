package www.gift_vouchers.marasel.Drivers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI.AddOffer;
import www.gift_vouchers.marasel.Drivers.UI.AvailableOrders.UI.AvailableOrders;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI.DeliveryPersonalInfo;
import www.gift_vouchers.marasel.Drivers.UI.MyOffers.UI.MyOffers;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStar;
import www.gift_vouchers.marasel.MainScreen.ui.MainActivity;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.Driver;
import www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.UI.PersonalInformation;
import www.gift_vouchers.marasel.MainScreen.ui.Settings.UI.Settings;
import www.gift_vouchers.marasel.MainScreen.ui.home.ui.home;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.network_check_status.regist_network_broadcast;
import www.gift_vouchers.marasel.utils.utils;

public class Drivers extends AppCompatActivity implements Callback, BottomNavigationView.OnNavigationItemSelectedListener {
    Callback callback;
    BottomNavigationView bottomNavigationView;
    View bottom_nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        bottom_nav_view = findViewById(R.id.bottom_nav_view);

        bottomNavigationView = findViewById(R.id.nav_driver);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        callback = this;
        new utils().Replace_Fragment(new WorkAsStar(callback), R.id.frag, this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(Drivers.this);

    }

    @Override
    public void setBottomNavVisible() {
        bottom_nav_view.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.home) {
            new utils().Replace_Fragment(new DeliveryPersonalInfo(), R.id.frag, Drivers.this);
        } else if (menuItem.getItemId() == R.id.personal_name) {
            new utils().Replace_Fragment(new PersonalInformation(), R.id.frag, Drivers.this);
        } else if (menuItem.getItemId() == R.id.my_offers) {
            new utils().Replace_Fragment(new MyOffers(), R.id.frag, Drivers.this);
        } else if (menuItem.getItemId() == R.id.profile) {
            new utils().Replace_Fragment(new Settings(), R.id.frag, Drivers.this);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT
        Log.e("num_is", "" + f);

        if (f instanceof DeliveryPersonalInfo) {  //CHECK IF FRAGMENT == MAIN PAGE TO CLOSE
            Log.e("num_is", "1");
            moveTaskToBack(true);

        } else if (f instanceof MyOffers) {
            Log.e("num_is", "2");
            moveTaskToBack(true);

        } else if (f instanceof AddOffer) {
            Log.e("num_is", "3");
            new utils().Replace_Fragment(new AvailableOrders(), R.id.frag, Drivers.this);

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