package com.dragger2.reactnativetest1022.view.customviewpractice.practice1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        综合练习
        //        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Path path = new Path();
        RectF rectF = new RectF(250, 100, 850, 700);        //圆位置
        RectF rectF2 = new RectF(230, 120, 830, 720);       //圆位置

        String colors[] = {"#ff00ff", "#5cc2d0", "#00ff00", "#000ff0", "#ffff00", "#ff0000"};     //每个扇形颜色
        int angle[] = {150, 30, 10, 8, 30, 120};     //每个扇形角度
        int initAngle = 180;       //初始画圆角度位置

        //画圆饼分布图
        for (int i = 0; i < angle.length; i++) {
            paint.setColor(Color.parseColor(colors[i]));
            if (i == angle.length - 1) {        //最后一个圆改变一下位置和画的角度大小
                //角度画满 位置稍微往下移动些
                canvas.drawArc(rectF2, initAngle, angle[i] + 4, true, paint);
                initAngle = initAngle + angle[i];
            } else if (i == angle.length - 2) {
                canvas.drawArc(rectF, initAngle, angle[i], true, paint);
                //直接就从上个圆终点开始画
                initAngle = initAngle + angle[i];
            } else {
                canvas.drawArc(rectF, initAngle, angle[i], true, paint);
                //每次画圆角度位置+2
                initAngle = initAngle + angle[i] + 2;
            }
        }
    }
}
