package com.example.loadingdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
//import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.concurrent.TimeUnit;

public class LoadingAnim extends View{
    float velocity = (float)10;
    float velocityY = velocity;
    float startVelocity = velocity*2;
    int justAddItToSizeOfLayout = (int)velocity;
    boolean back = false;
    float boost = (float)-0.9;
    float moduleBoost = -boost;
    public int color = Color.rgb(226, 123, 125);
    float size = 40;
    float x = size + getPaddingLeft();
    float y = size + getPaddingTop();
    int currentTime = 0;
    MyTask myTask = new MyTask();
    boolean flag = false;
    int moveX = 1;
    int moveY = 0;
    int someside = 0;
    int time = (int)Math.ceil(startVelocity/moduleBoost);
    float S  = startVelocity*time + boost * time*time/2;
    float myway = 0f;

    public LoadingAnim(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!flag) {
            flag = true;
            if(!myTask.isCancelled())
                startMotion();
        }
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(x, y, size, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(justAddItToSizeOfLayout + (int)Math.ceil(S + size*2) + getPaddingLeft()+getPaddingRight(),
                justAddItToSizeOfLayout + (int)Math.ceil(S + size*2) + getPaddingBottom()+getPaddingRight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float cx = event.getX();
        float cy = event.getY();
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            boolean cancel = (cx>getWidth()+getMyPadding()+size||cx<0)&&(cy<0||cy>getHeight()+getMyPadding()+size);
            System.out.println(cancel + " отмена");
            if(cancel){
                changeFlag();
                stopMotion();
            }
        }
        return super.onTouchEvent(event);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while(!isCancelled()){
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {}
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            if((velocity>0||velocityY>0)&&!back) {
                velocity = (startVelocity + boost * currentTime)*moveX;
                velocityY = (startVelocity + boost * currentTime)*moveY;
                currentTime++;
                x+=velocity;
                myway+=velocity;
                y+=velocityY;

            }else if ((velocity < 0||velocityY<0) && back) {
                velocity = (-startVelocity + boost * currentTime)*moveX;
                velocityY = (-startVelocity + boost * currentTime)*moveY;
                currentTime++;
                x+=velocity;
                y+=velocityY;
            }
            else{
                if(!back){
                    velocity = startVelocity*moveX;
                    velocityY = startVelocity*moveY;
                    boost = -moduleBoost;
                }else{
                    velocity = -startVelocity*moveX;
                    velocityY = -startVelocity*moveY;
                    boost = moduleBoost;
                }
            }
            if((int)velocityY==0&&(int)velocity==0){
                if(someside<1)
                    someside++;
                else{
                    someside = 0;
                    back = !back;
                }
                velocity = 0;
                myway = 0;
                currentTime = 0;
                moveX = (moveX-1)*(moveX-1);
                moveY = (moveY-1)*(moveY-1);
            }
            invalidate();
        }
    }
    public void startMotion(){
        myTask.execute();
    }
    public void stopMotion(){
        myTask.cancel(true);
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void changeFlag(){
        flag = false;
    }
    public int getMyPadding(){
        return getPaddingLeft();
    }
}