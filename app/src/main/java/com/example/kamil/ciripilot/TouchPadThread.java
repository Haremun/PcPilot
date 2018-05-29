package com.example.kamil.ciripilot;

import android.util.Log;

public class TouchPadThread extends Thread {

    volatile boolean running;
    private TouchPadSurfaceView mTouchPadSurfaceView;
    private long mSleepTime;


    TouchPadThread(TouchPadSurfaceView touchPadSurfaceView, long sleepTime) {
        this.mTouchPadSurfaceView = touchPadSurfaceView;
        this.mSleepTime = sleepTime;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                sleep(mSleepTime);
                mTouchPadSurfaceView.Update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

