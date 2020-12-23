package www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.CartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Product;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Store;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.CartBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Cart extends Fragment {
    CartBinding binding;
    CartModelView cartModelView = new CartModelView();
    Product[] products;
    Datum datum;
    Store store;
    int totalPrice;

    public Cart() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.cart, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    void getData() {
        cartModelView.getData("Bearer " + new saved_data().get_token(getContext()));

        cartModelView.MutableLiveData.observe(this, new Observer<CartRoot>() {
            @Override
            public void onChanged(CartRoot cartRoot) {
                datum = cartRoot.getData();  //ADD ALL DATA
                products = datum.getProducts(); //ADD PRODUCTS
                store = datum.getStore(); //ADD STORES
                totalPrice = datum.getTotalPrice(); //GET TOTAL PRICE


                Log.e("ssss",  ""+ products[0].getQuantity());


                binding.catTitle.setText(store.getName()); //SET CAT TITLE
                Glide.with(getContext()).load(store.getIcon()).into(binding.catLogo); //SET CAT IMAGE
                binding.title.setText(products[0].getName()); //SET TITLE
                binding.quantityNum.setText("" + products[0].getQuantity()); //SET QUANTITY
                binding.quantity.setText("" + products[0].getQuantity()); //SET QUANTITY NUM
                Glide.with(getContext()).load(products[0].getIcon()).into(binding.productImg); //SET IMAGE
                binding.price.setText(products[0].getPrice()); //SET PRICE
                binding.totalPrice.setText("" + totalPrice + " " + getString(R.string.egp)); //SET TOTAL PRICE
                binding.lastPrice.setText("" + datum.getTotalPrice() + " " + getString(R.string.egp)); //SET TOTAL PRICE

                // SET QUANTITY
                new utils().setQuantity(binding.inc, binding.dec, products[0].getQuantity(),
                        binding.quantityNum, totalPrice,
                        Integer.parseInt(products[0].getPrice()),
                        binding.totalPrice, getContext(), binding.quantity ,binding.lastPrice);

            }
        });

    }
}