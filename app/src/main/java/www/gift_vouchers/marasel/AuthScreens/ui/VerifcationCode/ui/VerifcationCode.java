package www.gift_vouchers.marasel.AuthScreens.ui.VerifcationCode.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.Drivers.UI.AddOffer.UI.AddOfferModelView;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.VerifcationCodeBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifcationCode extends Fragment implements View.OnClickListener {
    VerifcationCodeBinding binding;
    String txt1, txt2, txt3, txt4;
    String word;
    VerifcationCodeModelView verifcationCodeModelView;

    public VerifcationCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.verifcation_code, container, false);
        View view = binding.getRoot();

        verifcationCodeModelView = new VerifcationCodeModelView();

        move_next_num();

        binding.confirmOrder.setOnClickListener(this);
        binding.resendCode.setOnClickListener(this);

        return view;
    }


    void move_next_num() {
        //TEXT ONE
        binding.edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit1.clearFocus();
                    binding.edit2.requestFocus();
                    binding.edit2.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //TEXT TWO
        binding.edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit2.clearFocus();
                    binding.edit3.requestFocus();
                    binding.edit3.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //TEXT THREE
        binding.edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit3.clearFocus();
                    binding.edit4.requestFocus();
                    binding.edit4.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm_order) {
            //OPEN DIALOG
            new utils().set_dialog(getContext());

            if ((binding.edit1.getText().toString().equals("")) || (binding.edit2.getText().toString().equals(""))
                    || (binding.edit3.getText().toString().equals("")) || (binding.edit4.getText().toString().equals(""))) {
                Toasty.error(getContext(), getString(R.string.insert_code), Toasty.LENGTH_SHORT).show();
            } else {
                txt1 = binding.edit1.getText().toString();
                txt2 = binding.edit2.getText().toString();
                txt3 = binding.edit3.getText().toString();
                txt4 = binding.edit4.getText().toString();
                word = txt1 + txt2 + txt3 + txt4;

                //CALL API
                verifcationCodeModelView.getData("Bearer " + new saved_data().get_token(getContext()), word);
            }
        } else if (v.getId() == R.id.resend_code) {
            //OPEN DIALOG
            new utils().set_dialog(getContext());
            //CALL API
            verifcationCodeModelView.getDataResend("Bearer " + new saved_data().get_token(getContext()));
        }
    }

    void getData()
    {

    }
}
