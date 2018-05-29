package com.example.kamil.ciripilot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TouchPadFragment extends Fragment {

    private TouchPadSurfaceView touchPadSurfaceView;
    private TcpConnection tcpConnection;
    private Unbinder unbinder;

    public TouchPadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_touch_pad, container, false);
        unbinder = ButterKnife.bind(this, view);

        tcpConnection = new TcpConnection();
        touchPadSurfaceView = view.findViewById(R.id.touch_pad);
        touchPadSurfaceView.setTcpConnection(tcpConnection);

        return view;
    }

    @OnClick(R.id.btnRefresh)
    public void refresh() {
        if (tcpConnection != null) {
            tcpConnection.closeConnection();
            tcpConnection.interrupt();
        }
        tcpConnection = new TcpConnection();
        tcpConnection.start();
        touchPadSurfaceView.setTcpConnection(tcpConnection);
    }

    @OnClick({R.id.scrollUp, R.id.scrollDown})
    public void scroll(ImageButton imageButton) {
        if(tcpConnection != null){
            switch (imageButton.getId()){
                case R.id.scrollUp:{
                    tcpConnection.sendMessage("UP");
                    break;
                }
                case R.id.scrollDown:{
                    tcpConnection.sendMessage("DOWN");
                    break;
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tcpConnection.start();
        touchPadSurfaceView.MyGameSurfaceView_OnResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        tcpConnection.closeConnection();
        touchPadSurfaceView.MyGameSurfaceView_OnPause();
        unbinder.unbind();
    }
}
