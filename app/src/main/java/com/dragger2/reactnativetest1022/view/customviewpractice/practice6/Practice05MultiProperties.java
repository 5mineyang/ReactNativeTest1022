package com.dragger2.reactnativetest1022.view.customviewpractice.practice6;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dragger2.reactnativetest1022.R;

public class Practice05MultiProperties extends ConstraintLayout {
    Button animateBt;
    ImageView imageView;
    int mClickNum = 0;

    public Practice05MultiProperties(Context context) {
        super(context);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
        imageView.setAlpha(0f);
        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里处理点击事件，同时对多个属性做动画
                switch (mClickNum) {
                    case 0:
                        //x轴移到某位置
                        imageView.animate().translationX(600);
                        //旋转360度
                        imageView.animate().rotation(360);
                        //大小和透明度恢复
                        imageView.animate().scaleX(1);
                        imageView.animate().scaleY(1);
                        imageView.animate().alpha(1);
                        break;
                    case 1:
                        imageView.animate().translationX(0);
                        imageView.animate().rotation(0);
                        imageView.animate().scaleX(0);
                        imageView.animate().scaleY(0);
                        imageView.animate().alpha(0);
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
