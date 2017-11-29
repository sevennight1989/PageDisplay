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
import com.blankj.utilcode.util.SizeUtils;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.gallery.choice.ChoiceFragment;
import com.zp.gossiptripe.main.gallery.latest.LatestUpdateFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements PagerSlidingTabStrip.OnPositionChangeListener{

    ViewPager mVp;
    PagerSlidingTabStrip mtab;



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
        mVp = (ViewPager) view.findViewById(R.id.vp);
        mtab = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        init();
        return view;
    }

    private void init(){
        String str_choice = getResources().getString(R.string.choice);
        String str_latestUpdate = getResources().getString(R.string.latestupdate);
        mTitles.add(str_choice);
        mTitles.add(str_latestUpdate);
        mFragments = getFragments();
        fm = getChildFragmentManager();
        mVp.setAdapter(new GalleryFragmentAdapter(fm));
        mtab.setViewPager(mVp);
        mtab.setSelectedTextColorResource(R.color.tap_personal);
        mtab.setTextSize(SizeUtils.dp2px(20));
        mtab.setTabPaddingLeftRight(100);

        mtab.setOnPositionChangeListener(this);
        mtab.setIndicatorPadding(120);
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

    @Override
    public void onChage(int position) {

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
