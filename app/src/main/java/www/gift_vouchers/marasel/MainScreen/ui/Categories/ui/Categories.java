package www.gift_vouchers.marasel.MainScreen.ui.Categories.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.CategoryList;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.pattern.CategoriesAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.CategoriesBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {
   CategoriesBinding binding;
   CategoriesModeView categoriesModeView = new CategoriesModeView();
   Datum[] data;
   ArrayList<CategoryList> CategoryList = new ArrayList<>();
   String id;

    int pagenum=0;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoading = true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previous_total=0;
    private int viewthrishold=12;

    CategoriesAdapter adapter;
    LinearLayoutManager layoutManager;

    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.categories, container, false);
        View view = binding.getRoot();

        binding.resturantList.setNestedScrollingEnabled(true);
        layoutManager=new LinearLayoutManager(getContext());
        binding.resturantList.setHasFixedSize(true);
        binding.resturantList.setLayoutManager(layoutManager);
        adapter = new CategoriesAdapter(getContext(),CategoryList);
        binding.resturantList.setAdapter(adapter);

        id = getArguments().getString("id"); //GET id
        binding.title.setText(getArguments().getString("title"));

        getData();

        return view;
    }


    void getData()
    {
        categoriesModeView.getData(new saved_data().get_token(getContext()), id,"0");
        categoriesModeView.MutableLiveData.observe(this, new Observer<StoreByService>() {
            @Override
            public void onChanged(StoreByService storeByService) {
                setData(storeByService);
            }
        });
    }

    void setData(StoreByService storeByService)
    {

        data = storeByService.getData();
        for(int i = 0; i<data.length;i++)
        {
            CategoryList.add(new CategoryList("" +data[i].getId(),
                    data[i].getIcon() ,data[i].getName(),data[i].getCat(),
                    data[i].getDistance(),"" +data[i].getRate()));
        }

        adapter.addList(CategoryList);

        if (storeByService.isIsPaginate() == true)
        {
            doPagination();
        }
    }


    /**
     * DO PAGINATION
     */
    private void doPagination()
    {

        binding.resturantList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=linearLayoutManager.getChildCount();
                totalItemCount=linearLayoutManager.getItemCount();
                pastVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
                if(dy>0)
                {
                    if(isLoading)
                    {
                        if(totalItemCount>previous_total)
                        {
                            isLoading=false;
                            previous_total=totalItemCount;
                        }
                    }
                    if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItem+viewthrishold))
                    {
                        pagenum++;
                        isLoading=true;
                        categoriesModeView.getData(new saved_data().get_token(getContext()), id,""+ pagenum);

                    }}
            }
        });
    }


}
