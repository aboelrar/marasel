package www.gift_vouchers.marasel.MainScreen.ui.home.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.Drivers.Drivers;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI.WorkAsStar;
import www.gift_vouchers.marasel.MainScreen.ui.Search.UI.Search;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Cat;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.MaraselServiceList;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.NearestStoresList;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.Store;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;
import www.gift_vouchers.marasel.MainScreen.ui.home.pattern.MaraselServiceAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.home.pattern.NearestStoresAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.HomeBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment implements View.OnClickListener, EditText.OnEditorActionListener {
    HomeBinding binding;
    HomeModeView homeModeView = new HomeModeView();
    Datum datum;
    Store[] stores;
    Cat[] cat;


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.home, container, false);
        View view = binding.getRoot();

        getData();

        binding.driver.setOnClickListener(this);
        binding.search.setOnEditorActionListener(this);

        return view;
    }


    void getData() {

        homeModeView.getData(new saved_data().get_token(getContext()));

        homeModeView.MutableLiveData.observe(this, new Observer<homeRoot>() {
            @Override
            public void onChanged(homeRoot homeRoot) {
                ArrayList<NearestStoresList> NearestStoresList = new ArrayList<>();
                ArrayList<MaraselServiceList> MaraselServiceList = new ArrayList<>();

                binding.progressCircular.setVisibility(View.GONE);

                datum = homeRoot.getData(); // Data
                stores = datum.getStores(); // stores
                cat = datum.getCats(); // Cats

                //add Data TO store
                for (int i = 0; i < stores.length; i++) {
                    NearestStoresList.add(new NearestStoresList("" + stores[i].getId(), stores[i].getIcon(),
                            stores[i].getName(), stores[i].getCat(), stores[i].getDistance(),
                            "" + stores[i].getRate(), "" + stores[i].getFreeDelivery()));
                }

                new utils_adapter().Horozintal(binding.nearestStoreList, new NearestStoresAdapter(getContext(), NearestStoresList), getContext());

                //add Data TO Cats
                for (int i = 0; i < cat.length; i++) {
                    MaraselServiceList.add(new MaraselServiceList("" + cat[i].getId(),
                            cat[i].getIcon(), cat[i].getName()));
                }

                new utils_adapter().griddAdapters(binding.maraselServiceList,
                        new MaraselServiceAdapter(getContext(), MaraselServiceList), getContext(), 3);

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.driver) {
            startActivity(new Intent(getContext(), Drivers.class));
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            replaceFragment();
            return true;
        }
        return false;
    }

    private void replaceFragment() {
        Fragment search = new Search();
        Bundle bundle = new Bundle();
        bundle.putString("text", binding.search.getText().toString());
        //set Fragmentclass Arguments
        search.setArguments(bundle);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, search).addToBackStack(null).commit();
    }
}
