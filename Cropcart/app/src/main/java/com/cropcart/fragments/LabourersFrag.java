package com.cropcart.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cropcart.R;
import com.cropcart.preferences.SharedPref;
import com.cropcart.ui.MainActivity;
import com.cropcart.utils.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class LabourersFrag extends Fragment {
    private GPSTracker gps;
    private EditText numworkers;
    private TextView amount, location;
    private Button send;
    private boolean iseeror = true;
    private int labourersavailable;
    private int numlabourschosen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.labourers, container, false);
        setHasOptionsMenu(true);
        gps = new GPSTracker(getActivity());
        numworkers = v.findViewById(R.id.numworkers);
        amount = v.findViewById(R.id.price);
        location = v.findViewById(R.id.location);
        send = v.findViewById(R.id.send);
        SharedPref pref = new SharedPref(getActivity());
        labourersavailable = pref.getLabourersavailable();
        // check if GPS enabled
        if (gps.canGetLocation()) {
            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();
            gps.stopUsingGPS();
            if (latitude == 0.0 || longitude == 0.0) {
            } else {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    if (addresses != null) {
                        String address = addresses.get(0).getAddressLine(0);// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        final String city = addresses.get(0).getLocality();
                        final String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName();
                        String text = "";
                        if (knownName != null)
                            text = text + knownName + "\n";
                        text = text + city + "," + state;
                        if (postalCode != null)
                            text = text + "\nPincode:" + postalCode;

                        location.setText(text);
                    } else {
                        location.setText("Location tracked(" + latitude + "," + longitude + ")");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            numworkers.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() > 0) {
                        if (Integer.parseInt(charSequence.toString()) < labourersavailable) {
                            numlabourschosen = Integer.parseInt(charSequence.toString());
                            iseeror = false;
                            amount.setText("\u20B9 " + String.valueOf(Integer.parseInt(charSequence.toString()) * 250));
                        } else {
                            iseeror = true;
                            numworkers.setError("Limit Exceeded");
                        }
                    } else {
                        amount.setText("\u20B9 0");
                    }
                }


                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String workers = numworkers.getText().toString();
                    String loc = location.getText().toString();
                    if (!iseeror)
                        setRequest(workers, loc);
                }
            });

        } else

        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            // Setting Dialog Title
            alertDialog.setTitle("GPS is settings");

            // Setting Dialog Message
            alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
            alertDialog.setCancelable(false);
            // On pressing Settings button
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            // on pressing cancel button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Location is required", Toast.LENGTH_SHORT).show();

                }
            });

            // Showing Alert Message
            alertDialog.show();
        }
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

    private void setRequest(String workers, String loc) {
        SharedPref pref = new SharedPref(getActivity());
        pref.setLaboursavailable(labourersavailable - numlabourschosen);
        Toast.makeText(getActivity(), "Request sent", Toast.LENGTH_SHORT).show();
    }
}
