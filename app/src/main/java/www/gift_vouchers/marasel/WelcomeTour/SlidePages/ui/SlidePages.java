package www.gift_vouchers.marasel.WelcomeTour.SlidePages.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import me.relex.circleindicator.CircleIndicator;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.WelcomeTour.SlidePages.pattern.ViewImage;

public class SlidePages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_pages);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        ViewImage viewimage = new ViewImage(SlidePages.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

    }
}
