package com.touhidapps.viewpagerandtablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by touhid on 8/4/17.
 */

public class MyFragmentTwo extends Fragment implements View.OnClickListener {

    Button buttonTwo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonTwo = view.findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonTwo:
                Toast.makeText(getActivity(), "Hello from second fragment", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
