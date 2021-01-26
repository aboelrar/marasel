package www.gift_vouchers.marasel.MainScreen.ui.Store.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Store.model.Category;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.Image;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ImgList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.ProductList;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.SingleStore;
import www.gift_vouchers.marasel.MainScreen.ui.Store.pattern.ImgAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.Store.pattern.StoreAdapter;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ProductBinding;
import www.gift_vouchers.marasel.databinding.StoreBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.TinyDB;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Store extends Fragment {
    StoreBinding binding;
    Datum datum;
    Image[] image;
    Category[] Category;
    ArrayList<ImgList> ImgList = new ArrayList<>();
    ArrayList<ProductList> ProductList = new ArrayList<>();

    public Store() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.store, container, false);
        View view = binding.getRoot();

        binding.shimmerViewContainer.startShimmerAnimation();
        binding.shimmerContainer.startShimmerAnimation();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    void getData()
    {
        StoreModelView productModelView = new StoreModelView();
        productModelView.getData(new saved_data().get_token(getContext()), getArguments().getString("id"));

        productModelView.MutableLiveData.observe(this, new Observer<SingleStore>() {
            @Override
            public void onChanged(SingleStore singleStore) {
                setData(singleStore);
            }
        });
    }

    void setData(SingleStore singleStore)
    {
        datum = singleStore.getData();
        image = datum.getImages();
        Category = datum.getCategories();

        binding.title.setText(datum.getName());
        binding.distance.setText(datum.getDistance());

        Glide.with(getContext())
                .load(datum.getIcon())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        binding.shimmerViewContainer.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(binding.img);
        binding.shimmerContainer.stopShimmerAnimation();
        binding.shimmer.setVisibility(View.GONE);

        binding.rate.setText(""+datum.getRate());
        binding.type.setText(datum.getCat());

        //set Data for Image
        for(int i = 0 ; i< image.length ; i++)
        {
            ImgList.add(new ImgList(""+ image[i].getId(), image[i].getImage()));
        }
        new utils_adapter().Horozintal(binding.imgList,new ImgAdapter(getContext(),ImgList),getContext());

        //set Data for Product
        for(int i = 0 ; i< Category.length ; i++)
        {
            ProductList.add(new ProductList(""+ Category[i].getId(), Category[i].getName(),
                    Category[i].getIcon(), "22 EGP"));
        }

        new utils_adapter().Horozintal(binding.productList,new StoreAdapter(getContext(), ProductList,
                getArguments().getString("id")),getContext());


        // Set Data for Store
        setStore();

    }

    // Set Data for Store
    void setStore()
    {
        ArrayList<String> storeItem = new ArrayList<String>();
        storeItem.add(datum.getName());
        storeItem.add(datum.getDistance());
        storeItem.add(datum.getIcon());
        storeItem.add(""+ datum.getRate());
        storeItem.add(""+ datum.getCat());


        TinyDB tinydb = new TinyDB(getContext());
        tinydb.putListString("storeItem", storeItem);
        tinydb.putListImgList("images", ImgList);



    }


}
