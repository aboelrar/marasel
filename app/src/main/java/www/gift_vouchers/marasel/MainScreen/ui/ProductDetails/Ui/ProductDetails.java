package www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.Image;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.SingleProduct;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ProductDetailsBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetails extends Fragment {
    ProductDetailsBinding binding;
    Datum datum;
    Image[] image;

    public ProductDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.product_details, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    void getData()
    {
        ProductDetailsModelView productModelView = new ProductDetailsModelView();
        productModelView.getData(new saved_data().get_token(getContext()), getArguments().getString("id"));

        productModelView.MutableLiveData.observe(this, new Observer<SingleProduct>() {
            @Override
            public void onChanged(SingleProduct SingleProduct) {
                setData(SingleProduct);
            }
        });
    }

    void setData(SingleProduct SingleProduct)
    {
        datum = SingleProduct.getData();
//        image = datum.getImages();
//        Category = datum.getCategories();

        binding.title.setText(datum.getName());
        binding.price.setText(datum.getPrice());


    }
}
