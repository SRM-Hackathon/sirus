package com.cropcart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cropcart.R;

import android.content.Context;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cropcart.preferences.SharedPref;
import com.cropcart.ui.MainActivity;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BHUSRI on 10/6/2017.
 */

public class ChatFrag extends Fragment {
    private static final String TAG = "BLINDCHAT";
    private ChatAdapter chatAdapter;
    private RelativeTimeTextView timeTextView;
    private EditText editText;
    private ViewGroup rootView;
    private ImageView emojiButton;
    private ArrayList<CropChatVar> arrayList = new ArrayList<>();
    private int nummessages = 0;
    // private MyHelper helper;
    private ImageView sendButton;
    private String chatid, hostuserid;
    private LinearLayoutManager llm;
    private RecyclerView recyclerView;
    private AlertDialog dialog;
    private LinearLayout editlayout;
    private TextView isactivetext;
    private boolean isblindchatactive = true;
    private boolean isnotificationmute;
    private MenuItem muteitem;
    private boolean isblindsuccess;
    private String username, profileppicid;
    private int currentpage = 0;
    private int fetchcount = 0;
    private TextView chatname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        chatAdapter = new ChatAdapter((MainActivity) getActivity(), arrayList, isblindsuccess, profileppicid);
        editText = v.findViewById(R.id.main_activity_chat_bottom_message_edittext);
        rootView = v.findViewById(R.id.main_activity_root_view);
        sendButton = v.findViewById(R.id.main_activity_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String text = editText.getText().toString().trim();
                Log.d(TAG, "onClick: send button");
                if (text.length() > 0) {
                    SharedPref pref = new SharedPref(getActivity());
                    sendMessage(getActivity(), hostuserid, text, String.valueOf(Calendar.getInstance().getTimeInMillis()), pref.getUserid());
                    editText.setText("");
                }
            }
        });

        recyclerView = v.findViewById(R.id.main_activity_recycler_view);
        recyclerView.setAdapter(chatAdapter);
        llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);
        CropChatVar obj = new CropChatVar();
        obj.setStatus("1");
        obj.setTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        obj.setMessage("Hello,Please tell us your problem and we will try our best to solve your problem");
        obj.setUserid("1");
        arrayList.add(0, obj);
        chatAdapter.notifyDataSetChanged();
        return v;
    }


    private void sendMessage(Context context, final String hostuserid, final String textmessage, final String time, final String userid) {
        CropChatVar obj = new CropChatVar();
        obj.setStatus("1");
        obj.setTime(time);
        obj.setMessage(textmessage);
        obj.setUserid(userid);
        arrayList.add(0, obj);
        chatAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);
    }


}
