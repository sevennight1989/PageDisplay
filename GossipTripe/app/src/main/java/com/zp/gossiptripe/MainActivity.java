package com.zp.gossiptripe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zp.gossiptripe.main.gallery.GalleryFragment;
import com.zp.gossiptripe.main.movement.MovementFragment;
import com.zp.gossiptripe.main.personal.PersonalFragment;
import com.zp.gossiptripe.main.program.ProgramFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    List<Fragment> fragments;

    static final int GALLERY = 0;
    static final int MOVEMENT = 1;
    static final int PROGRAM = 2;
    static final int PERSONAL = 3;

    FragmentManager fm;
    FragmentTransaction ft;

    @BindString(R.string.gallery)
    String gallery;

    @BindString(R.string.movement)
    String movement;

    @BindString(R.string.program)
    String program;

    @BindString(R.string.personal)
    String personal;

    @BindColor(R.color.tap_gallery)
    int color_gallery;

    @BindColor(R.color.tap_movement)
    int color_movement;

    @BindColor(R.color.tap_program)
    int color_program;

    @BindColor(R.color.tap_personal)
    int color_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.tab_gallery, gallery)
        .setActiveColor(color_gallery))
        .addItem(new BottomNavigationItem(R.mipmap.tab_movement, movement)
        .setActiveColor(color_movement))
        .addItem(new BottomNavigationItem(R.mipmap.tab_program, program)
        .setActiveColor(color_program))
        .addItem(new BottomNavigationItem(R.mipmap.tab_personal, personal)
        .setActiveColor(color_personal))
        .setFirstSelectedPosition(0)
        .initialise();

        fragments = getFragments();
        initFragments(GALLERY);
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    @SuppressLint("CommitTransaction")
    private void initFragments(int defaultFragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.container, fragments.get(GALLERY), "gallery");
        ft.add(R.id.container, fragments.get(MOVEMENT), "movement");
        ft.add(R.id.container, fragments.get(PROGRAM), "program");
        ft.add(R.id.container, fragments.get(PERSONAL), "personal");
        showFragment(defaultFragment);
        ft.commitAllowingStateLoss();
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(GalleryFragment.newInstance());
        list.add(MovementFragment.newInstance());
        list.add(ProgramFragment.newInstance());
        list.add(PersonalFragment.newInstance());
        return list;
    }

    private void showFragment(int toShow) {
        Logger.d(toShow);
        for (int i = 0; i < fragments.size(); i++) {
            if (i == toShow) {
                ft.show(fragments.get(toShow));
            } else {
                ft.hide(fragments.get(i));
            }
        }
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onTabSelected(int position) {
        ft = fm.beginTransaction();
        showFragment(position);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
