package www.gift_vouchers.marasel.AuthScreens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import www.gift_vouchers.marasel.AuthScreens.ui.ChooseLoginType.ui.ChooseLoginType;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.utils.utils;

public class auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        //ADD LOGIN
        new utils().Replace_Fragment(new ChooseLoginType(), R.id.frag, this);
    }
}
