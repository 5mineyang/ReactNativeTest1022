package com.dragger2.reactnativetest1022.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View {
    //默认开始绘制
    private boolean isStart = true;
    private int mRadius;
    private int mLeftPath;
    private int mRightPath;
    private Paint mPaint;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置宽度
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLeftPath <= 0) {
            //获取控件半径
            mRadius = getMeasuredWidth() / 2;
            mRightPath = mLeftPath = mRadius;
        }
        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //画一条底线
        canvas.drawLine(0,getMeasuredHeight()/2,getMeasuredWidth(),getMeasuredHeight()/2,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(
            mLeftPath -= 10, //开始x坐标
            getMeasuredHeight() / 2, //开始y坐标
            mRightPath += 10, //结束x坐标
            getMeasuredHeight() / 2, //结束y坐标
            mPaint);
        if (isStart) {
            invalidate();
        }
    }

    public void setState(boolean state) {
        this.isStart = state;
    }
}
