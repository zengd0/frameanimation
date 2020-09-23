package com.zengd0.frameanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mrwang.imageframe.ImageFrameHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyImageFrameCustomView mV;
    private Button mBtnLoop;
    private Button mBtnCount;
    private Button mBtnStay;
    private Button mBtnStopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mV = (MyImageFrameCustomView) findViewById(R.id.v);
        mBtnLoop = (Button) findViewById(R.id.btn_loop);
        mBtnCount = (Button) findViewById(R.id.btn_count);
        mBtnStay = (Button) findViewById(R.id.btn_stay);
        mBtnStopTime = (Button) findViewById(R.id.btn_stop_time);

        mBtnLoop.setOnClickListener(this);
        mBtnCount.setOnClickListener(this);
        mBtnStay.setOnClickListener(this);
        mBtnStopTime.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loop:
                mV.delayFinishTime = 0;
                mV.playNull = true;
                mV.startImageFrame(new ImageFrameHandler.AssetsHandlerBuilder("test"
                        , getApplicationContext())
                        .setLoop(true)// 设置是否循环播放
                        .openLruCache(false)// 设置是否开启LRU缓存,如果不循环,建议不开启,如果循环,建议开启,不过多次测试性能相差并不大。ps:在listview中设为false
                        .setFps(24).build());
                break;
            case R.id.btn_count:
                mV.delayFinishTime = 0;
                mV.playNull = true;
                mV.startImageFrame(new ImageFrameHandler.AssetsHandlerBuilder("test"
                        , getApplicationContext())
                        .setLoop(false)// 设置是否循环播放
                        .openLruCache(false)// 设置是否开启LRU缓存,如果不循环,建议不开启,如果循环,建议开启,不过多次测试性能相差并不大。ps:在listview中设为false
                        .setFps(24).build()
                        .setPlayCount(5));
                break;
            case R.id.btn_stay:
                mV.delayFinishTime = 0;
                mV.playNull = false;
                mV.startImageFrame(new ImageFrameHandler.AssetsHandlerBuilder("test"
                        , getApplicationContext())
                        .setLoop(false)// 设置是否循环播放
                        .openLruCache(false)// 设置是否开启LRU缓存,如果不循环,建议不开启,如果循环,建议开启,不过多次测试性能相差并不大。ps:在listview中设为false
                        .setFps(24).build()
                        .setPlayCount(1));
                break;
            case R.id.btn_stop_time:
                mV.delayFinishTime = 5000;
                mV.playNull = true;
                mV.startImageFrame(new ImageFrameHandler.AssetsHandlerBuilder("test"
                        , getApplicationContext())
                        .setLoop(false)// 设置是否循环播放
                        .openLruCache(false)// 设置是否开启LRU缓存,如果不循环,建议不开启,如果循环,建议开启,不过多次测试性能相差并不大。ps:在listview中设为false
                        .setFps(24).build()
                        .setPlayCount(1));
                break;
        }
    }
}
