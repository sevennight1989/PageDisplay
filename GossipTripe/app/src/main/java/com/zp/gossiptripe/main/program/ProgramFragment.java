package com.zp.gossiptripe.main.program;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramFragment extends Fragment {


    public ProgramFragment() {
        // Required empty public constructor
    }
    public static ProgramFragment newInstance(){
        return new ProgramFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_program, container, false);
    }

}
