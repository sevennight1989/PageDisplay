package com.zp.gossiptripe.main.gallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.gallery.choice.ChoiceFragment;
import com.zp.gossiptripe.main.gallery.latest.LatestUpdateFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip mtab;

    @BindString(R.string.choice)
    String str_choice;
    @BindString(R.string.latestupdate)
    String str_latestUpdate;

    List<Fragment> mFragments;
    FragmentManager fm;
    List<String> mTitles = new ArrayList<>();

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        mTitles.add(str_choice);
        mTitles.add(str_latestUpdate);
        mFragments = getFragments();
        fm = getChildFragmentManager();
        mVp.setAdapter(new GalleryFragmentAdapter(fm));
        mtab.setViewPager(mVp);
        mtab.setTextColorResource(R.color.tap_personal);
        mtab.setTextSize(60);
        mtab.setTabPaddingLeftRight(100);
        mtab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private List<Fragment> getFragments(){
        List<Fragment> list  = new ArrayList<>();
        list.add(ChoiceFragment.newInstance());
        list.add(LatestUpdateFragment.newInstance());
        return list;
    }


    private class GalleryFragmentAdapter extends FragmentPagerAdapter{

        public GalleryFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }


}
