package com.cropcart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cropcart.R;
import com.cropcart.ui.MainActivity;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class EquipFrag extends Fragment {
    private String keys[] = {"Light Fixtures", "LED Drivers", "Nutrient Solution", "Trays", "Light Support", "Sensors", "Cable Harnesses", "Air Flow System", "Power meter"};
    private ArrayList<String> selectedcategories = new ArrayList<>();
    private List<TextView> textviews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.equipments, container, false);
        FlexboxLayout flexboxLayout = v.findViewById(R.id.flexbox);
        for (int i = 0; i < keys.length; i++) {
            View subchild = getLayoutInflater().inflate(R.layout.single_chip, null);
            final TextView tv = subchild.findViewById(R.id.txt_title);
            tv.setText(keys[i]);
            final int finalI = i;
            final String[] finalKeys = keys;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv.getTag() != null && tv.getTag().equals("selected")) {
                        tv.setTag("unselected");
                        tv.setBackgroundResource(R.drawable.chip_unselected);
                        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.filters_chips));
                        removeFromSelectedMap(finalKeys[finalI]);
                    } else {
                        tv.setTag("selected");
                        tv.setBackgroundResource(R.drawable.chip_selected);
                        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.filters_header));
                        addToSelectedMap(finalKeys[finalI]);
                    }
                }
            });

            if (selectedcategories.contains(keys[finalI])) {
                tv.setTag("selected");
                tv.setBackgroundResource(R.drawable.chip_selected);
                tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.filters_header));
            } else {
                tv.setBackgroundResource(R.drawable.chip_unselected);
                tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.filters_chips));
            }
            textviews.add(tv);

            flexboxLayout.addView(subchild);
        }
        TextView submit = v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNetworkRequest();
            }
        });
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                (getActivity()).onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setNetworkRequest() {
        ((MainActivity) getActivity()).movetogetEquip(selectedcategories);

    }

    private void addToSelectedMap(String value) {
        selectedcategories.add(value);
    }

    private void removeFromSelectedMap(String value) {
        selectedcategories.remove(value);
    }
}
