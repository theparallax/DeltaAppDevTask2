package com.example.rogith.deltaappdevtask2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;


public class myViewComponent extends View {

    private ShapeDrawable shapeDrawable;
    public myViewComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public static final int Square =0;
    public static final int Circle =1;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(shapeDrawable !=null){
            shapeDrawable.draw(canvas);
        }
    }

    public void DrawShape(Context mContext,int Shape,int x, int y,int height,int width,boolean drawCenter){

        switch (Shape){
            case 0:
                shapeDrawable = new ShapeDrawable(new RectShape());
                shapeDrawable.getPaint().setColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                if(drawCenter){
                    shapeDrawable.setBounds(x-width/2,y-height/2,x+width/2,y+height/2);
                }
                else{
                    shapeDrawable.setBounds(x,y,x+width,y+height);
                }
                break;
            case 1:
                shapeDrawable = new ShapeDrawable((new OvalShape()));
                shapeDrawable.getPaint().setColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                if(drawCenter){
                    shapeDrawable.setBounds(x-width/2,y-height/2,x+width/2,y+height/2);
                }
                else{
                    shapeDrawable.setBounds(x,y,x+width/2,height/2);
                }
                break;
            default: break;
        }
        invalidate();
    }
}
