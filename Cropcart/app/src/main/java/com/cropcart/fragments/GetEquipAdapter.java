package com.cropcart.fragments;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.cropcart.R;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class GetEquipAdapter extends RecyclerView.Adapter<GetEquipAdapter.MyHelper> {
    private ArrayList<String> arrayList = new ArrayList<>();
    private LayoutInflater inflater;
    //   private LinkedList<String> chosen

    public GetEquipAdapter(Context context, ArrayList<String> arrayList) {
        inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }


    @Override
    public MyHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.equipitem, parent, false);
        return new MyHelper(v);
    }

    @Override
    public void onBindViewHolder(MyHelper holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHelper extends RecyclerView.ViewHolder {
        private TextView textView;


        public MyHelper(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.equipment);

        }
    }
}
