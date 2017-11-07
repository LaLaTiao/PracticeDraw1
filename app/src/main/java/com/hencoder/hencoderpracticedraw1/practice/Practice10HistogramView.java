package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        Paint paint = new Paint();
        Path path = new Path();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        path.moveTo(100,100);
        path.lineTo(100,600);
        path.rLineTo(840,0);

        paint.setTextSize(18);
        canvas.drawText("Froyo",145,620,paint);
        canvas.drawText("GB",245,620,paint);
        canvas.drawText("ICS",345,620,paint);
        canvas.drawText("JB",445,620,paint);
        canvas.drawText("KitKat",535,620,paint);
        canvas.drawText("L",660,620,paint);
        canvas.drawText("M",750,620,paint);

        paint.setTextSize(24);

        canvas.drawText("直方图",450,680,paint);
        canvas.drawPath(path,paint);

    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        Paint paint = new Paint();
        Path path = new Path();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL);
        //第一个柱状体
        path.addRect(120,598,200,600, Path.Direction.CW);

        //第二个柱状体
        paint.setColor(Color.GREEN);
        path.addRect(220,590,300,600, Path.Direction.CCW);

        //第三个柱状体
        path.addRect(320,590,400,600, Path.Direction.CCW);

        //第四个柱状体
        path.addRect(420,320,500,600, Path.Direction.CCW);

        //第五个柱状体
        path.addRect(520,220,600,600, Path.Direction.CCW);

        //第六个柱状体
        path.addRect(620,130,700,600, Path.Direction.CCW);

        //第七个柱状体
        path.addRect(720,380,800,600, Path.Direction.CCW);

        canvas.drawPath(path,paint);
    }
}
