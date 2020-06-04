package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dk.frbsportgruppe1.frbsport.R;

public class PatientMainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    BottomNavigationView bottomNavigationView;
    ViewPager2 patientMainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmain);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        patientMainViewPager = findViewById(R.id.patientMainViewPager);
        patientMainViewPager.setAdapter(new PatientMainViewPagerAdapter(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /**
     * Menu item selection handler til vores BottomNavigationView
     * @param item Den menu item der blev trykket på
     * @return Returnerer true hvis selection event blev håndteret og false hvis den ikke blev håndteret
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
            switch (item.getItemId()) {
                case R.id.menuitem_beskeder:
                    patientMainViewPager.setCurrentItem(0);
                    return true;
                case R.id.menuitem_booking:
                    patientMainViewPager.setCurrentItem(1);
                    return true;
                case R.id.menuitem_kalender:
                    patientMainViewPager.setCurrentItem(2);
                    return true;
                default:
                    return false;
            }
        }
        else {
            return false;
        }

    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
