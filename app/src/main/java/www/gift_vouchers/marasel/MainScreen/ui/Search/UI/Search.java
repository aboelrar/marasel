package www.gift_vouchers.marasel.MainScreen.ui.Search.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.GoogleArray;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.MarasellArray;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.OpeningHour;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.SearchList;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.SearchRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Model.otherSearchList;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Pattern.OtherSearchAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.Search.Pattern.SearchAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.SearchBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    SearchBinding binding;
    SearchModelView searchModelView;
    Datum datum;
    MarasellArray[] marasellArrays;
    GoogleArray[] googleArrays;
    OpeningHour openingHour;

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

        getData();

        return view;
    }

    void getData() {
        searchModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                "30.109760", "31.247240", getArguments().getString("text"));

        searchModelView.MutableLiveData.observe(this, new Observer<SearchRoot>() {
            @Override
            public void onChanged(SearchRoot searchRoot) {
                ArrayList<SearchList> searchLists = new ArrayList<>();
                ArrayList<otherSearchList> otherSearchList = new ArrayList<>();
                datum = searchRoot.getData();
                marasellArrays = datum.getMarasellArray();
                googleArrays = datum.getGoogleArray();

                //ADD SEARCH RESULT
                for (int index = 0; index < marasellArrays.length; index++) {
                    searchLists.add(new SearchList("" + marasellArrays[index].getId(), marasellArrays[index].getIcon(),
                            marasellArrays[index].getName(), marasellArrays[index].getCat(),
                            marasellArrays[index].getDistance(), "" + marasellArrays[index].getRate()));
                }

                new utils_adapter().Adapter(binding.searchResult,
                        new SearchAdapter(getContext(), searchLists), getContext());

                //ADD OTHER LIST RESULT
                for (int index = 0; index < googleArrays.length; index++) {
                    openingHour = googleArrays[index].getOpeningHours();

                    otherSearchList.add(new otherSearchList("" + googleArrays[index].getPlaceId(),
                            googleArrays[index].getName(), googleArrays[index].getFormattedAddress(),
                             openingHour.isOpenNow(), googleArrays[index].getIcon()));
                }

                new utils_adapter().Adapter(binding.otherSearchResult,
                        new OtherSearchAdapter(getContext(), otherSearchList), getContext());
            }
        });
    }
}
