package com.zp.gossiptripe.main.gallery.choice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zp.gossiptripe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends Fragment {


    public ChoiceFragment() {
        // Required empty public constructor
    }

    public static ChoiceFragment newInstance(){
        return new ChoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

}
