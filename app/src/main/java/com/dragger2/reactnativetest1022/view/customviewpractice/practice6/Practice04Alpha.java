package com.dragger2.reactnativetest1022.view.customviewpractice.practice6;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dragger2.reactnativetest1022.R;

public class Practice04Alpha extends RelativeLayout {
    Button animateBt;
    ImageView imageView;
    int mClickNum = 0;

    public Practice04Alpha(Context context) {
        super(context);
    }

    public Practice04Alpha(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice04Alpha(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                //在这里处理点击事件，通过 View.animate().alpha() 来改变 View 的透明度
                switch (mClickNum) {
                    case 0:
                        imageView.animate().alpha(0);
                        break;
                    case 1:
                        imageView.animate().alpha(1);
                        break;
                }
                mClickNum++;
                if (mClickNum == 2) {
                    mClickNum = 0;
                }
            }
        });
    }
}