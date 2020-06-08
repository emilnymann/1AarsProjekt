package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dk.frbsportgruppe1.frbsport.R;

public class PractitionerMainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ViewPager2 practitionerMainViewPager;
    private static final String TAG = "PractitionerMainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practiotionermain);

        bottomNavigationView = findViewById(R.id.practitionerbottomNavigationView);
        practitionerMainViewPager = findViewById(R.id.practitionerMainViewPager);
        practitionerMainViewPager.setAdapter(new PractiotionerMainViewPagerAdapter(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    //TODO: Disse skal passe til practiotioner og ikke tage på patient delen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
            switch (item.getItemId()) {
                case R.id.menuitem_beskeder:
                    practitionerMainViewPager.setCurrentItem(0);
                    return true;
                case R.id.menuitem_booking:
                    practitionerMainViewPager.setCurrentItem(1);
                    return true;
                case R.id.menuitem_kalender:
                    practitionerMainViewPager.setCurrentItem(2);
                    return true;
                default:
                    return false;

            }
        }
        else {
            return false;
        }
    }

}
