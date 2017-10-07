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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cropcart.R;
import com.cropcart.ui.MainActivity;
import com.cropcart.utils.GPSTracker;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class OrderItem extends Fragment {
    private ImageView imageView;
    private TextView name, price, location;
    private EditText editText;
    private TextView amount;
    private Button order;
    private String TAG = "ORDER";
    private boolean iseeror = true;
    private GPSTracker gps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.orderitem, container, false);
        setHasOptionsMenu(true);
        bindviews(v);
        gps = new GPSTracker(getActivity());
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getArguments();
        try {
            JSONObject order = new JSONObject(b.getString("data"));
            Log.d(TAG, "onCreateView: " + order.toString());
            String ordername = order.getString("name");
            String price = order.getString("price");
            String picid = order.getString("picid");
            ImageLoader.getInstance().displayImage(String.format("%s%s%s.jpg", Urls.domain, Urls.imagepath, picid), imageView);
            name.setText(ordername);
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
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        if (charSequence.toString().length() > 0) {
                            if (Integer.parseInt(charSequence.toString()) < 100) {
                                iseeror = false;
                                amount.setText("\u20B9 " + String.valueOf(Integer.parseInt(charSequence.toString()) * 250));
                            } else {
                                iseeror = true;
                                editText.setError("Limit Exceeded");
                            }
                        } else {
                            amount.setText("\u20B9 0");
                        }
                    } catch (NumberFormatException e) {
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!iseeror)
                    Toast.makeText(getActivity(), "Order Successfull", Toast.LENGTH_SHORT).show();
            }
        });
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
        amount = v.findViewById(R.id.amount);
        order = v.findViewById(R.id.order);


    }
}
