package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //useCenter false 是2个点之间连起来,true是 从椭圆圆心连接2点
        //sweepAngle是默认顺时针方向,是你想要顺时针转多少度
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(400,300,800,500,-180,70,false,paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(400,300,800,500,-100,100,true,paint);

        canvas.drawArc(400,300,800,500,-200,-140,false,paint);

    }
}
