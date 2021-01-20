package www.gift_vouchers.marasel.MainScreen.ui.Offers.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import java.util.Objects;

import www.gift_vouchers.marasel.MainScreen.ui.MainActivity;
import www.gift_vouchers.marasel.R;


public class CancelDialog {

    public void dialog(final Context context, int resource, double widthh, Callback callback) {
        final Dialog dialog = new Dialog(context);
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

        final RadioGroup radio = dialog.findViewById(R.id.radio_group);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

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
                        break;

                }
            }
        });

    }


}

