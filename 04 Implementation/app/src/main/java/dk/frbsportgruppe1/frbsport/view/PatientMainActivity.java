package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import androidx.preference.PreferenceManager;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dk.frbsportgruppe1.frbsport.R;

public class PatientMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmain);

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_username", "TestUsername").apply(); // TODO: Skal sættes i login, ikke her.


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
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
                    Fragment beskedhistorikFragment = new MessageIndexFragment();
                    navigateToFragment(beskedhistorikFragment);
                    return true;
                case R.id.menuitem_booking:
                    Fragment bookingFragment = new BookingFragment();
                    navigateToFragment(bookingFragment);
                    return true;
                case R.id.menuitem_kalender:
                    Fragment kalenderFragment = new KalenderFragment();
                    navigateToFragment(kalenderFragment);
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

    /**
     * Udskifter denne activity's fragment container.
     * @param fragment Den fragment som skal vises
     */
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.patientMainFragment, fragment);
        transaction.commit();
    }
}
