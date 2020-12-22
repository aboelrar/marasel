package www.gift_vouchers.marasel.MainScreen.ui.Cart.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.CartRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.CartBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Cart extends Fragment {
   CartBinding binding;
    CartModelView cartModelView = new CartModelView();
    public Cart()
    {

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

    void getData()
   {
       cartModelView.getData("Barear " + new saved_data().get_token(getContext()));
       cartModelView.MutableLiveData.observe(this, new Observer<CartRoot>() {
           @Override
           public void onChanged(CartRoot cartRoot) {
               Log.e("oiooo",cartRoot.getMessage());
           }
       });
   }
}