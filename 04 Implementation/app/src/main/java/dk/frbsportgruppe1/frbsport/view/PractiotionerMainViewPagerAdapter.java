package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PractiotionerMainViewPagerAdapter extends FragmentStateAdapter {

    public PractiotionerMainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    //TODO: de andre cases skal tilpasses practiotioner activities
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PatientIndexFragment();
            case 1:
                return new BookingFragment();
            case 2:
                return new CalendarFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
