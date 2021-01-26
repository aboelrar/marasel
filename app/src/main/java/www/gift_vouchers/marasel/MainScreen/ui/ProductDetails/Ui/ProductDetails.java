package www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui.Cart;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.Image;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.SingleProduct;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Pattern.ViewImageProductDetails;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ProductDetailsBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetails extends Fragment {
    ProductDetailsBinding binding;
    Datum datum;
    Image[] image;
    View view;
    int quantity = 1;
    int totalPrice;
    int price;

    public ProductDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.product_details, container, false);
        view = binding.getRoot();

        binding.shimmerViewContainer.startShimmerAnimation();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    void getData() {
        ProductDetailsModelView productModelView = new ProductDetailsModelView();
        productModelView.getData(new saved_data().get_token(getContext()), getArguments().getString("id"));

        productModelView.MutableLiveData.observe(this, new Observer<SingleProduct>() {
            @Override
            public void onChanged(SingleProduct SingleProduct) {
                setData(SingleProduct);
            }
        });
    }

    void setData(SingleProduct SingleProduct) {
        binding.shimmerViewContainer.stopShimmerAnimation();

        datum = SingleProduct.getData();
        image = datum.getImages();

        binding.title.setText(datum.getName());
        binding.price.setText(datum.getPrice() + " " + getString(R.string.egp));

        ArrayList<String> imageSt = new ArrayList<>();
        for (int index = 0; index < image.length; index++) {
            imageSt.add(image[index].getImage());
        }

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ViewImageProductDetails ViewImageProductDetails = new ViewImageProductDetails(getContext(), imageSt);
        viewPager.setAdapter(ViewImageProductDetails);
        binding.indicator.setViewPager(viewPager);

        //SET QUANTITY AND TOTAL PRICE
        price = Integer.parseInt(datum.getPrice());

        // SET QUANTITY
        new utils().setQuantity(binding.inc, binding.dec, quantity,
                binding.quantity, totalPrice, price, binding.totalPrice,
                getContext(), null, null);

        //SET ADD TO CART
        setAddToCart();


    }

    // SET ADD TO CART
    void setAddToCart() {
        AddToCartModelView addToCartModelView = new AddToCartModelView();

        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //OPEN PROGRESS DIALOG
                new utils().set_dialog(getContext());

                addToCartModelView.getData("Bearer " + new saved_data().get_token(getContext()),
                        getArguments().getString("id"), binding.quantity.getText().toString());
            }
        });

        //OBSERVE DATA
        addToCartModelView.MutableLiveData.observe(this, new Observer<AddToCartRoot>() {
            @Override
            public void onChanged(AddToCartRoot addToCartRoot) {

                //CLOSE PROGRESS DIALOG
                new utils().dismiss_dialog(getContext());

                Toasty.success(getContext(), addToCartRoot.getMessage(), Toasty.LENGTH_SHORT).show();

                new utils().Replace_Fragment(new Cart(), R.id.frag, getContext());
            }
        });
    }

}
