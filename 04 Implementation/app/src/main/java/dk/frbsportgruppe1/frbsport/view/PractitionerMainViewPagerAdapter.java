package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Klassen skal håndtere at programmet ikke ændre status når der skiftet activity ved brug af bottomnavigationbar.
 */
public class PractitionerMainViewPagerAdapter extends FragmentStateAdapter {

    /**
     * Constructor til klassen
     * @param fragmentActivity
     */
    public PractitionerMainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Denne ændrer metode ændrer activity i forhold til hvilket knap der trykkes på i bunden.
     * @param position
     * @return
     */
    //TODO: de andre cases skal tilpasses practiotioner activities
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PatientIndexFragment();
            case 1:
                return new PractitionerBookingFragment();
            case 2:
                return new PractitionerBookingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
