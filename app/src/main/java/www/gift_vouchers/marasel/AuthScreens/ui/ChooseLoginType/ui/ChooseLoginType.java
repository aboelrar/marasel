package www.gift_vouchers.marasel.AuthScreens.ui.ChooseLoginType.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.AuthScreens.ui.login.ui.login;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.ChooseLoginTypeBinding;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLoginType extends Fragment {
   ChooseLoginTypeBinding binding;

    public ChooseLoginType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.choose_login_type, container, false);
        View view = binding.getRoot();

        OnClick();

        return view;
    }

    public void OnClick()
    {
      binding.seller.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new utils().Replace_Fragment(new login(), R.id.frag, getContext());
          }
      });

      binding.user.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new utils().Replace_Fragment(new login(), R.id.frag, getContext());
          }
      });
    }
}
