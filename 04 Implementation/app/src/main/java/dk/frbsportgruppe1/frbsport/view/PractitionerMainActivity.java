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

    /**
     * Hvilket view der vises når denne klasse instantieres. eller bliver kaldt i en Intent
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practiotionermain);

        bottomNavigationView = findViewById(R.id.practitionerbottomNavigationView);
        practitionerMainViewPager = findViewById(R.id.practitionerMainViewPager);
        practitionerMainViewPager.setAdapter(new PractitionerMainViewPagerAdapter(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        practitionerMainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                MenuItem prevMenuItem = bottomNavigationView.getMenu().getItem(position);
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    /**
     * Denne metode håndtere hvilken item der trykkes på nede i bottomnavigatino bar.
     * @param item Der bruges en Menuitem, hvor der kigges på hvilken en der trykkes på.
     * @return
     */
    //TODO: Disse skal passe til practiotioner og ikke tage på patient delen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
            switch (item.getItemId()) {
                case R.id.practitioner_menuitem_beskeder:
                    practitionerMainViewPager.setCurrentItem(0);
                    return true;
                case R.id.menuitem_placeholder:
                    practitionerMainViewPager.setCurrentItem(1);
                    return true;
                case R.id.practitioner_menuitem_bookingkalender:
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
