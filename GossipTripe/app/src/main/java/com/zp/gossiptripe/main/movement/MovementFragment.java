package com.zp.gossiptripe.main.movement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zp.gossiptripe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementFragment extends Fragment {


    public MovementFragment() {
        // Required empty public constructor
    }
    public static MovementFragment newInstance(){
        return new MovementFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movement, container, false);
    }

}
