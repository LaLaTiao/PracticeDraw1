package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
////        综合练习
////        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//
//
//
//        Path path = new Path();
//        Paint line = new Paint();
//        Paint text = new Paint();
//        line.setAntiAlias(true);
//        line.setStyle(Paint.Style.STROKE);
//        line.setColor(Color.WHITE);
//        line.setTextSize(18);
//        line.setStrokeWidth(3);
//
//        path.moveTo(350, 85);
//        path.lineTo(300, 40);
//        path.rLineTo(-200, 0);
//
//        path.moveTo(350,625);
//        path.lineTo(310,670);
//        path.rLineTo(-130,0);
//
//        text.setStrokeWidth(1);
//        canvas.drawText("Lollipop",30,40,text);
//        canvas.drawText("KitKat",120,675,text);
//        canvas.drawPath(path, line);
//
//
//
//        paint.setColor(Color.RED);
//        canvas.drawArc(200, 50, 800, 650, -180, 135, true, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawArc(220, 70, 820, 670, -180, -100, true, paint);
//
//        paint.setColor(Color.GREEN);
//        canvas.drawArc(220, 70, 820, 670, -283, -50, true, paint);
//        paint.setColor(Color.GRAY);
//        canvas.drawArc(220, 70, 820, 670, -336, -10, true, paint);
//        paint.setColor(Color.CYAN);
//        canvas.drawArc(220, 70, 820, 670, -349, -10, true, paint);
//        paint.setColor(Color.YELLOW);
//        canvas.drawArc(220, 70, 820, 670, -45, 43, true, paint);
//    }


    private float radius;
    private List<Data> datas;
    private Paint paint;
    private RectF rectF;
    private float total;
    private float max;

    float startAngle = 0f; // 开始的角度
    float sweepAngle;      // 扫过的角度
    float lineAngle;       // 当前扇形一半的角度

    float lineStartX = 0f; // 直线开始的X坐标
    float lineStartY = 0f; // 直线开始的Y坐标
    float lineEndX;        // 直线结束的X坐标
    float lineEndY;        // 直线结束的Y坐标


    private void init() {
        radius = 300;
        datas = new ArrayList<>();
        Data data = new Data("Gingerbread", 10.0f, Color.WHITE);
        datas.add(data);
        data = new Data("Ice Cream Sandwich", 18.0f, Color.MAGENTA);
        datas.add(data);
        data = new Data("Jelly Bean", 22.0f, Color.GRAY);
        datas.add(data);
        data = new Data("KitKat", 27.0f, Color.GREEN);
        datas.add(data);
        data = new Data("Lollipop", 40.0f, Color.BLUE);
        datas.add(data);
        data = new Data("Marshmallow", 60.0f, Color.RED);
        datas.add(data);
        data = new Data("Nougat", 33.5f, Color.YELLOW);
        datas.add(data);
        total = 0.0f;
        max = Float.MIN_VALUE;
        for (Data d : datas) {
            total += d.getNumber();
            max = Math.max(max, d.getNumber());
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        rectF = new RectF(-300, -300, 300, 300);
    }
    /**
     * 1.创建一个集合,将需要进行绘制的对象输入进去
     * 2.迭代集合,将扇形占比汇总,选出最大值
     * 3.创建正方形,边长为600
     * 4.将绘制中心移至中心,保证绘制物品在最中心
     * 5.获取每一个需要绘制对象的扇形占比角度 ==> sweepAngle
     * 6.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将画布(0，0)坐标点移到画布的中心
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        //这句代码很重要，不然有bug
        startAngle = 0f;
        for (Data data : datas) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(data.getColor());
            sweepAngle = data.getNumber() / total * 360f;
            lineAngle = startAngle + sweepAngle / 2;
            //求出圆上的x,y以及延伸50dp的x,y
            lineStartX = radius * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineStartY = radius * (float) Math.sin(lineAngle / 180 * Math.PI);
            lineEndX = (radius + 50) * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineEndY = (radius + 50) * (float) Math.sin(lineAngle / 180 * Math.PI);
            //判断是否是最大的扇形,不是的话,正常画,空出一点.
            //最大的扇形,将圆心平移扇形边的中心点的1/10的位置
            if (data.getNumber() == max) {
                canvas.save();
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle - 1.0f, true, paint);
            }
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            //画出连接圆的延伸线
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (lineAngle > 90 && lineAngle <= 270) {
                //左半圆
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                //在距离延伸线的10px + 需要drawText的宽度处开始
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY, paint);
            } else {
                //右半圆
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY, paint);
            }
            //将绘制最大扇形时保存的圆点位置,在偏移绘制完成后重新还原回来.
            if (data.getNumber() == max) {
                canvas.restore();
            }
            startAngle += sweepAngle;
        }
    }
}
