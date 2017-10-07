package com.cropcart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cropcart.R;
import com.cropcart.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class GetEquip extends Fragment {
    private String TAG = "GETEQUIP";
    private RecyclerView recyclerView;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.getequip, container, false);
        setHasOptionsMenu(true);
        Bundle b = getArguments();
        final ArrayList<String> arrayList = (ArrayList<String>) b.getSerializable("data");
        bindviews(v);
        GetEquipAdapter adapter = new GetEquipAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + arrayList);
                Toast.makeText(getActivity(), "Request submitted", Toast.LENGTH_SHORT).show();
            }
        });
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
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

    private void bindviews(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);
        submit = v.findViewById(R.id.submit);
    }
}
