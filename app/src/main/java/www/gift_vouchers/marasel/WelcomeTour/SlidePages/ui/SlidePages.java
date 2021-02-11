package www.gift_vouchers.marasel.WelcomeTour.SlidePages.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import me.relex.circleindicator.CircleIndicator;
import www.gift_vouchers.marasel.AuthScreens.ui.auth;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.WelcomeTour.SlidePages.pattern.ViewImage;

import static www.gift_vouchers.marasel.local_data.send_data.set_welcome_num;

public class SlidePages extends AppCompatActivity {
    LinearLayout skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_pages);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        ViewImage viewimage = new ViewImage(SlidePages.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlidePages.this, auth.class));
                set_welcome_num(SlidePages.this,"1");
            }
        });

    }
}
