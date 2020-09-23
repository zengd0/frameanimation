package com.zengd0.frameanimation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mrwang.imageframe.ImageFrameHandler;


public class MyImageFrameCustomView extends AppCompatImageView {
    private ImageFrameHandler imageFrameHandler;
    private Handler mHandler;

    public boolean playNull = true;
    public int delayFinishTime;
    private static final int CODE_FINISH = 101;

    public MyImageFrameCustomView(Context context) {
        this(context, null);
    }

    public MyImageFrameCustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageFrameCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case CODE_FINISH:
                        setImageRes(-1);
                        if (mFinishedListener != null) {
                            mFinishedListener.onFinished();
                        }

                        break;
                }
                return false;
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        if (imageFrameHandler != null) {
            imageFrameHandler.stop();
        }
        mHandler.removeMessages(CODE_FINISH);
        super.onDetachedFromWindow();
    }

    public MyImageFrameCustomView startImageFrame(final ImageFrameHandler imageFrameHandler) {

        mHandler.removeMessages(CODE_FINISH);
        if (this.imageFrameHandler == null) {
            this.imageFrameHandler = imageFrameHandler;
        } else {
            this.imageFrameHandler.stop();
            this.imageFrameHandler = imageFrameHandler;
        }
        this.imageFrameHandler.setOnImageLoaderListener(new ImageFrameHandler.OnImageLoadListener() {
            @Override
            public void onImageLoad(BitmapDrawable drawable) {
                setImageDrawable(drawable);
            }

            @Override
            public void onPlayFinish() {

                if (playNull) {
                    mHandler.removeMessages(CODE_FINISH);
                    mHandler.sendEmptyMessageDelayed(CODE_FINISH, delayFinishTime);
                }

            }
        });
        post(new Runnable() {
            @Override
            public void run() {
                imageFrameHandler.start();
            }
        });
        return this;
    }


    public Runnable mPlayRunnable = new Runnable() {
        @Override
        public void run() {
            imageFrameHandler.stop();
            setImageRes(-1);
        }
    };


    @Nullable
    public ImageFrameHandler getImageFrameHandler() {
        return imageFrameHandler;
    }

    public void setImageRes(int res) {
        if (res == -1) {
            setImageDrawable(null);
        } else {
            setImageResource(res);
        }

    }

    public OnFinishedListener mFinishedListener;


    public interface OnFinishedListener {
        void onFinished();
    }
}
