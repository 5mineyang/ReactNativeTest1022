package com.dragger2.reactnativetest1022.view.customviewpractice.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200,200,700,500,190,30,false,paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(200,200,700,500,250,105,true,paint);
//
        canvas.drawArc(200,200,700,500,20,150,false,paint);
    }
}
