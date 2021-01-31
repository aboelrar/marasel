package www.gift_vouchers.marasel.MainScreen.ui.Search.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.SearchRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.SearchBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    SearchBinding binding;
    SearchModelView searchModelView;
    Datum datum;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.search, container, false);
        View view = binding.getRoot();

        searchModelView = new SearchModelView();

//        getData();

        return view;
    }

    void getData() {
        searchModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                "30.109760", "31.247240", getArguments().getString("text"));

        searchModelView.MutableLiveData.observe(this, new Observer<SearchRoot>() {
            @Override
            public void onChanged(SearchRoot searchRoot) {
               datum = searchRoot.getData();

            }
        });
    }
}
