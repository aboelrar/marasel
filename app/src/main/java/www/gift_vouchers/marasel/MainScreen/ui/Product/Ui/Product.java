package www.gift_vouchers.marasel.MainScreen.ui.Product.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.Image;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.ProductsByCat;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Pattern.ImgAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Pattern.ProductAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ImgList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ProductList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.SingleStore;
import www.gift_vouchers.marasel.MainScreen.ui.Store.ui.StoreModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ProductBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.TinyDB;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Product extends Fragment {
   ProductBinding binding;
   Datum[] datum;
   Image[] image;
   ArrayList<www.gift_vouchers.marasel.MainScreen.ui.Store.model.ProductList> ProductList = new ArrayList<>();

    public Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.product, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();  //  ProductData
        getStore(); //  Store Data
    }

    void getData()
    {
        ProductModelView productModelView = new ProductModelView();
        productModelView.getData(new saved_data().get_token(getContext()), getArguments().getString("storeId"), getArguments().getString("id"));

       productModelView.MutableLiveData.observe(this, new Observer<ProductsByCat>() {
           @Override
           public void onChanged(ProductsByCat productsByCat) {
               setData(productsByCat);
           }
       });
    }

    void setData(ProductsByCat productsByCat)
    {
        datum = productsByCat.getData();

        //set Data for Image
        for(int i = 0 ; i< datum.length ; i++)
        {
            ProductList.add(new ProductList(""+ datum[i].getId(),datum[i].getName(),datum[i].getIcon(),datum[i].getPrice()));
        }
        new utils_adapter().Adapter(binding.productList,new ProductAdapter(getContext(),ProductList),getContext());

    }


    // Get Data for Store
    void getStore()
    {
        TinyDB tinyDB = new TinyDB(getContext());
        ArrayList<String> storeItem = tinyDB.getListString("storeItem");
        ArrayList<ImgList> ImgList = tinyDB.getListImgList("images");

        binding.title.setText(storeItem.get(0));
        binding.distance.setText(storeItem.get(1));
        Glide.with(getContext()).load(storeItem.get(2)).into(binding.img);
        binding.rate.setText(""+ storeItem.get(3));
        binding.type.setText(storeItem.get(4));

        new utils_adapter().Horozintal(binding.imgList,new ImgAdapter(getContext(),ImgList),getContext());

    }


}
