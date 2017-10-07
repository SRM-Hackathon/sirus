package com.cropcart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cropcart.R;
import com.cropcart.ui.MainActivity;

import org.w3c.dom.Text;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class OrderItem extends Fragment {
    private ImageView imageView;
    private TextView name, price, location;
    private EditText editText;
    private Spinner spinner;
    private TextView amount;
    private Button order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.orderitem, container, false);
        bindviews(v);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStackImmediate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindviews(View v) {
        imageView = v.findViewById(R.id.image);
        name = v.findViewById(R.id.name);
        location = v.findViewById(R.id.location);
        editText = v.findViewById(R.id.editText);
        spinner = v.findViewById(R.id.spinner);
        amount = v.findViewById(R.id.amount);
        order = v.findViewById(R.id.order);


    }
}
