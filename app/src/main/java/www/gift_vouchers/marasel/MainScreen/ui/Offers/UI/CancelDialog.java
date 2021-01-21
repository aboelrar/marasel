package www.gift_vouchers.marasel.MainScreen.ui.Offers.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.MainScreen.ui.MainActivity;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.UI.myOrder;
import www.gift_vouchers.marasel.MainScreen.ui.Offers.Model.CancelOrderRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.utils.utils;


public class CancelDialog implements View.OnClickListener {

    OffersModelView offersModelView;
    Context context;
    String orderId;
    RadioButton radioButton;
    String reason;
    int index = 50;
    EditText another_reason_tell_us;
    Dialog dialog;

    public void dialog(final Context context, int resource, double widthh, Callback callback, OffersModelView offersModelView, String orderId) {
        this.context = context;
        this.offersModelView = offersModelView;
        this.orderId = orderId;

        dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            dialog.show();
        }

        another_reason_tell_us = dialog.findViewById(R.id.another_reason_tell_us);

        RadioGroup radio = dialog.findViewById(R.id.radio_group);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                another_reason_tell_us.setVisibility(View.GONE);
                radioButton = radio.findViewById(checkedId);
                index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0:
                        callback.setType("1");
                        break;
                    case 1:
                        callback.setType("2");
                        break;
                    case 2:
                        callback.setType("3");
                        break;
                    case 3:
                        callback.setType("4");
                        another_reason_tell_us.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        Button send = dialog.findViewById(R.id.send);
        send.setOnClickListener(this);

    }


    void getData() {
        offersModelView.cancelOrder("Bearer " + new saved_data().get_token(context), orderId, "" + index, reason);
        offersModelView.MutableLiveCancelOrder.observe(((AppCompatActivity) context), new Observer<CancelOrderRoot>() {
            @Override
            public void onChanged(CancelOrderRoot cancelOrderRoot) {
                new utils().dismiss_dialog(context); //STOP DIALOG
                dialog.dismiss(); //DISMISS DIALOG
                new utils().Replace_Fragment(new myOrder(), R.id.frag, context); //REPLACE FRAGMENT
                Toasty.success(context, "" + cancelOrderRoot.getMessage(), Toasty.LENGTH_SHORT).show(); //SUCCESS MESSAGE
            }
        });
    }

    void checkCancelReason() {
        //CHECK THE REASON TO CANCEL ORDER
        if (index == 3) {
            reason = another_reason_tell_us.getText().toString();
        } else if (index == 50) {
        } else {
            reason = radioButton.getText().toString();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {

            checkCancelReason(); //CHECK CANCEL REASON

            if ((reason == null || (reason.equals("")))) {
                Toasty.warning(context, context.getString(R.string.pls_insert_cancel_reason), Toasty.LENGTH_SHORT).show();
            } else {
                new utils().set_dialog(context);
                getData();
            }
        }
    }
}

