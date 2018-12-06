package com.dragger2.reactnativetest1022.view.customviewpractice.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Path path = new Path();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);

        //横竖白线
        path.moveTo(100,50);
        path.lineTo(100,700);
        path.rLineTo(900,0);
        canvas.drawPath(path,paint);

        //文字
        paint.setTextSize(30);
        canvas.drawText("盐城",200,740,paint);
        canvas.drawText("南京",300,740,paint);
        canvas.drawText("上海",400,740,paint);
        canvas.drawText("广州",500,740,paint);
        canvas.drawText("北京",600,740,paint);
        canvas.drawText("苏州",700,740,paint);
        canvas.drawText("安庆",800,740,paint);

        //柱状图
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(205,680,255,700,paint);
        canvas.drawRect(305,580,355,700,paint);
        canvas.drawRect(405,550,455,700,paint);
        canvas.drawRect(505,500,555,700,paint);
        canvas.drawRect(605,400,655,700,paint);
        canvas.drawRect(705,600,755,700,paint);
        canvas.drawRect(805,680,855,700,paint);
    }
}
