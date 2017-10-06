package com.cropcart.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cropcart.R;
import com.cropcart.preferences.SharedPref;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadStatusDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by BHUSRI on 10/7/2017.
 */

public class Callusfrag extends Fragment implements UploadStatusDelegate {
    private ImageView choosephoto;
    private EditText estimated, expected;
    private TextView liveprices;
    private Spinner chosencommodity;
    private Uri selectedImage;
    private Button submit;
    private String bestprices = "";
    private String TAG = "CALLUS";
    int statemax = 0;
    int districtmax = 0;
    int countrymax = 0;
    private String market;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.approvalfrag, container, false);
        bindviews(v);
        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v2 = LayoutInflater.from(getActivity()).inflate(R.layout.choosepic, null);
                TextView capture, gallery, cancel;
                capture = v2.findViewById(R.id.capture);
                gallery = v2.findViewById(R.id.gallery);
                cancel = v2.findViewById(R.id.cancel);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(v2);
                final AlertDialog dialog = builder.create();
                dialog.show();
                capture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);
                        dialog.dismiss();
                    }
                });
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent pictureActionIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pictureActionIntent, 1);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        chosencommodity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String commodity = (String) chosencommodity.getSelectedItem();
                getMandiPrices(commodity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }

    private void getMandiPrices(String commodity) {
        SharedPref pref = new SharedPref(getActivity());
        final String district = pref.getUserdistrict();
        String state = pref.getUserState();
        String country = pref.getUserCountry();
        statemax = 0;
        districtmax = 0;
        countrymax = 0;
        bestprices = "";
        final StringRequest request = new StringRequest(Request.Method.GET, Urls.mandiapi + Urls.commodityfilter + commodity.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("records");
                    if (!array.toString().equals("[]")) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject myobj = array.getJSONObject(i);
                            Log.d("DEBUGGGG", "onResponse: " + myobj);
                            int maxprice = myobj.getInt("max_price");
                            if (maxprice > countrymax) {
                                countrymax = maxprice;
                                market = myobj.getString("market");
                            }
                        }
                        bestprices = bestprices + "maximum price in country is at " + market + " \u20B9" + countrymax;
                        liveprices.setText(bestprices);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("error", "onErrorResponse:3 ");
            }
        });
        Log.d(TAG, "getMandiPrices: " + Urls.mandiapi + Urls.statefilter + state.replaceAll(" ", "%20") + Urls.commodityfilter + commodity.replaceAll(" ", "%20"));
        final StringRequest request2 = new StringRequest(Request.Method.GET, Urls.mandiapi + Urls.statefilter + state.replaceAll(" ", "%20") + Urls.commodityfilter + commodity.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("records");
                    if (!array.toString().equals("[]")) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject myobj = array.getJSONObject(i);
                            int maxprice = myobj.getInt("max_price");
                            if (maxprice > statemax) {
                                statemax = maxprice;
                                market = myobj.getString("market");
                            }
                        }
                        bestprices = bestprices + "maximum price in state is at " + market + " \u20B9" + statemax + " ";
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Volley.newRequestQueue(getActivity()).add(request);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("error", "onErrorResponse:2 ");
            }
        });
        StringRequest request3 = new StringRequest(Request.Method.GET, Urls.mandiapi + Urls.districtfilter + district.replaceAll(" ", "%20") + Urls.commodityfilter + commodity.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("records");
                    if (!array.toString().equals("[]")) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject myobj = array.getJSONObject(i);
                            int maxprice = myobj.getInt("max_price");
                            if (maxprice > districtmax) {
                                districtmax = maxprice;
                                market = myobj.getString("market");
                            }
                        }
                        bestprices = bestprices + "maximum price in district is at " + market + " \u20B9" + districtmax + " ";
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Volley.newRequestQueue(getActivity()).add(request2);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "onErrorResponse:1 ");
                error.printStackTrace();
            }
        });
        Log.d(TAG, "getMandiPrices: " + Urls.mandiapi + Urls.districtfilter + district.replaceAll(" ", "%20") + Urls.commodityfilter + commodity.replaceAll(" ", "%20"));
        Volley.newRequestQueue(getActivity()).add(request3);
    }

    private void bindviews(View v) {
        choosephoto = v.findViewById(R.id.choosepic);
        estimated = v.findViewById(R.id.estimated);
        expected = v.findViewById(R.id.expected);
        liveprices = v.findViewById(R.id.mandiprices);
        chosencommodity = v.findViewById(R.id.commodity);
        submit = v.findViewById(R.id.submit);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null) {
            selectedImage = data.getData();
            ImageLoader.getInstance().displayImage(String.valueOf(selectedImage), choosephoto);
            // choosephoto.setImageURI(selectedImage);
        }
    }

    public void uploadMultipart(String name, String path) {
        String mydomain = Urls.domain + "upload.php";
        //getting name for the image
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();
            System.setProperty("http.keepAlive", "false");
            //Creating a multi part request
            String mytype = path.substring(path.lastIndexOf("."));
            Log.d("MYTYPEE", "uploadMultipart: " + path);
            if (mydomain.length() > 0) {
                MultipartUploadRequest request = new MultipartUploadRequest(getActivity(), mydomain)
                        .addFileToUpload(path, "image") //Adding file
                        .addParameter("type", mytype)
                        .setMethod("POST")
                        .addParameter("name", name) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig().setRingToneEnabled(false))
                        .setMaxRetries(2);
                request.setDelegate(Callusfrag.this);
                request.startUpload();
            }
            //Starting the upload
        } catch (Exception exc) {
            exc.printStackTrace();
            Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgress(Context context, UploadInfo uploadInfo) {

    }

    @Override
    public void onError(Context context, UploadInfo uploadInfo, ServerResponse serverResponse, Exception exception) {

    }

    @Override
    public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {

    }

    @Override
    public void onCancelled(Context context, UploadInfo uploadInfo) {

    }
}
