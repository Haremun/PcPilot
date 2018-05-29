package com.example.kamil.ciripilot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendPacketFragment extends Fragment {

    @BindView(R.id.edit_msg)
    EditText msgText;
    @BindView(R.id.text_subnet)
    TextView subnetText;

    private Unbinder unbinder;
    private TcpConnection tcpConnection;

    public SendPacketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_send_packet, container, false);
        unbinder = ButterKnife.bind(this, view);

        tcpConnection = new TcpConnection();
        tcpConnection.start();
        return view;
    }

    @OnClick(R.id.btnSend)
    public void send(){
        tcpConnection.sendMessage(msgText.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        tcpConnection.closeConnection();
    }
}
