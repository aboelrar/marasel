package www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.CartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Datum;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.MyCartList;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Product;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.Store;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Pattern.MyCartAdapter;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Ui.makeOrder;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.CartBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;
import www.gift_vouchers.marasel.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Cart extends Fragment implements View.OnClickListener, Callback {
    CartBinding binding;
    CartModelView cartModelView = new CartModelView();
    Product[] products;
    Datum datum;
    Store store;
    int totalPrice;
    ArrayList<MyCartList> cartList = new ArrayList<>();
    Callback callback;

    public Cart() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.cart, container, false);
        View view = binding.getRoot();

        callback = this;

        getData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    void getData() {
        cartModelView.getData("Bearer " + new saved_data().get_token(getContext()));

        cartModelView.MutableLiveData.observe(this, new Observer<CartRoot>() {
            @Override
            public void onChanged(CartRoot cartRoot) {
                binding.progressCircular.setVisibility(View.GONE); //PROGRESS BAR GONE
                datum = cartRoot.getData();  //ADD ALL DATA
                products = datum.getProducts(); //ADD PRODUCTS
                store = datum.getStore(); //ADD STORES
                totalPrice = datum.getTotalPrice(); //GET TOTAL PRICE
                binding.catTitle.setText(store.getName()); //SET CAT TITLE
                Glide.with(getContext()).load(store.getIcon()).into(binding.catLogo); //SET CAT IMAGE
                binding.lastPrice.setText("" + datum.getTotalPrice() + " " + getString(R.string.egp)); //SET TOTAL PRICE

                //Add data to cart
                for (int index = 0; index < products.length; index++) {
                    //TOTAL PRICE
                    int totalPrice = products[index].getQuantity() * Integer.parseInt(products[index].getPrice());

                    cartList.add(new MyCartList("" + products[index].getId(),
                            products[index].getName(), products[index].getPrice(),
                            "" + totalPrice, "" + products[index].getQuantity(),
                            products[index].getIcon(), "" + datum.getStore().getId()));
                }

                new utils_adapter().Adapter(binding.myCartList,
                        new MyCartAdapter(getContext(), cartList, cartModelView, callback), getContext());

            }
        });

        //SET ON MAkE ORdER
        binding.completeOrderNow.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.complete_order_now) {
            send_data.setStoreTitle(getContext(), store.getName()); //title
            send_data.setStoreImg(getContext(), store.getIcon()); //Icon
            send_data.setStoreLat(getContext(), store.getLat()); //Lat
            send_data.setStoreLng(getContext(), store.getLng()); //Lng
            send_data.setStoreId(getContext(), "" + store.getId()); //ID

            //REPLACE FRAGMENT
            replaceFragment(store.getCat());
        }
    }

    @Override
    public void callbackMethod() {
        binding.parent.setVisibility(View.GONE);
        binding.noData.setVisibility(View.VISIBLE);

        binding.completeOrderNow.setClickable(false);

    }

    @Override
    public void setTotalPrice(String totalPrice) {
        binding.lastPrice.setText(totalPrice + " " + getString(R.string.egp));
    }

    void replaceFragment(String type) {
        Fragment makeOrder = new makeOrder();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        //set Fragmentclass Arguments
        makeOrder.setArguments(bundle);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, makeOrder).addToBackStack(null).commit();
    }
}