package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;

public class PractitionerMainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    BottomNavigationView bottomNavigationView;
    ViewPager2 practiotionerMainViewPager;
    private static final String TAG = "PractitionerMainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practiotionermain);

        bottomNavigationView = findViewById(R.id.practitionerbottomNavigationView);
        practiotionerMainViewPager = findViewById(R.id.practitionerMainViewPager);
        practiotionerMainViewPager.setAdapter(new PractiotionerMainViewPagerAdapter(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    //TODO: Disse skal passe til practiotioner og ikke tage på patient delen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
            switch (item.getItemId()) {
                case R.id.menuitem_beskeder:
                    practiotionerMainViewPager.setCurrentItem(0);
                    return true;
                case R.id.menuitem_booking:
                    practiotionerMainViewPager.setCurrentItem(1);
                    return true;
                case R.id.menuitem_kalender:
                    practiotionerMainViewPager.setCurrentItem(2);
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
