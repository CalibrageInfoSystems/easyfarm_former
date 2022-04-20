package in.calibrage.easyfarm.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import in.calibrage.easyfarm.Fragments.TabFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] title;

    public ViewPagerAdapter(FragmentManager manager, String[] title) {
        super(manager);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(position, this.title.length);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    public int gettabssize() {
        return this.title.length;
    }
}

