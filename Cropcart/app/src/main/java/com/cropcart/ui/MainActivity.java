package com.cropcart.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cropcart.R;
import com.cropcart.fragments.Callusfrag;
import com.cropcart.fragments.ChatFrag;
import com.cropcart.fragments.ConsumerMainFrag;
import com.cropcart.fragments.EquipFrag;
import com.cropcart.fragments.FarmerMainFrag;
import com.cropcart.fragments.GetEquip;
import com.cropcart.fragments.LabourersFrag;
import com.cropcart.fragments.MyFarm;
import com.cropcart.fragments.OrderItem;
import com.cropcart.fragments.PostCommodity;
import com.cropcart.preferences.SharedPref;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        SharedPref pref = new SharedPref(MainActivity.this);
        if (!pref.issignedin())
            startActivity(new Intent(MainActivity.this, Signup.class));
        if (pref.isfarmer()) {
            FarmerMainFrag frag = new FarmerMainFrag();
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).add(R.id.container, frag).commit();
        } else {
            ConsumerMainFrag frag = new ConsumerMainFrag();
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).add(R.id.container, frag).commit();
        }
    }

    public void postCommodity() {
        PostCommodity frag = new PostCommodity();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }


    public void myfarm() {
        MyFarm frag = new MyFarm();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    public void equipmentsfrag() {
        EquipFrag frag = new EquipFrag();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    public void orderitem(String s) {
        OrderItem frag = new OrderItem();
        Bundle bundle = new Bundle();
        bundle.putString("data", s);
        frag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void chatfrag() {
        ChatFrag frag = new ChatFrag();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    public void labourersfrag() {
        LabourersFrag frag = new LabourersFrag();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPref pref = new SharedPref(MainActivity.this);
                pref.setIssignedin(false);
                pref.setUserid("0");
                startActivity(new Intent(MainActivity.this, Signup.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void movetogetEquip(ArrayList<String> selectedcategories) {
        GetEquip frag = new GetEquip();
        Bundle b = new Bundle();
        b.putSerializable("data", selectedcategories);
        frag.setArguments(b);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }

    public void callusfrag() {
        Callusfrag frag=new Callusfrag();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.container, frag).commit();

    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
