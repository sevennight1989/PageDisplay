package com.zp.gossiptripe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zp.gossiptripe.event.ProductEvent;
import com.zp.gossiptripe.main.gallery.GalleryFragment;
import com.zp.gossiptripe.main.movement.MovementFragment;
import com.zp.gossiptripe.main.personal.PersonalFragment;
import com.zp.gossiptripe.main.program.ProgramFragment;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.product.AddProductActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar mBottomNavigationBar;
    List<Fragment> fragments;

    static final int GALLERY = 0;
    static final int MOVEMENT = 1;
    static final int PROGRAM = 2;
    static final int PERSONAL = 3;

    FragmentManager fm;
    FragmentTransaction ft;
    TextView mActionbarTitle;
    ImageView mAddProductBt;
    String gallery;
    String movement;
    String program;
    String personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ActionBar actionBar = getSupportActionBar();
        View actionBarLayout = LayoutInflater.from(this).inflate(R.layout.mainactionbarlayout, null);
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
        }
        mAddProductBt = (ImageView) actionBarLayout.findViewById(R.id.addProductBt);
        mActionbarTitle = (TextView) actionBarLayout.findViewById(R.id.main_title);
        gallery = getResources().getString(R.string.gallery);
        mActionbarTitle.setText(gallery);
        movement = getResources().getString(R.string.movement);
        program = getResources().getString(R.string.program);
        personal = getResources().getString(R.string.personal);
        int color_gallery = getResources().getColor(R.color.tap_gallery);
        int color_movement = getResources().getColor(R.color.tap_movement);
        int color_program = getResources().getColor(R.color.tap_program);
        int color_personal = getResources().getColor(R.color.tap_personal);
        initViews();
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
        mAddProductBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void publishEvent(ProductEvent productEvent) {
        Toast.makeText(this, productEvent.getmMsg(), Toast.LENGTH_SHORT).show();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void initViews() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
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
        updateActionBarTitle(toShow);
        for (int i = 0; i < fragments.size(); i++) {
            if (i == toShow) {
                ft.show(fragments.get(toShow));
            } else {
                ft.hide(fragments.get(i));
            }
        }
    }

    private void updateActionBarTitle(int action) {
        String title = "";
        switch (action) {
            case GALLERY:
                title = gallery;
                break;
            case MOVEMENT:
                title = movement;
                break;
            case PROGRAM:
                title = program;
                break;
            case PERSONAL:
                title = personal;
                break;
        }
        mActionbarTitle.setText(title);
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
