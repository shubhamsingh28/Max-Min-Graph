package com.shubham.miniproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Shubham on 20-08-2017.
 */

public class CustomView extends View {
    Paint paint,paint1;
    int nodes,type;
    public CustomView(Context context,int n,int f) {
        super(context);
        paint = new Paint();
        paint1=new Paint();
        paint.setColor(Color.BLUE);
        paint1.setColor(Color.RED);
        nodes=n;
        type=f;//2 for max 1 for min
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if(type==1){
            int n=nodes;
            int flag=1;
            int q=n;
            int x = getWidth();
            int y = getHeight();
            while(q!=0){
                if((q&(q-1))!=q-1){
                    flag=0;
                }
                q>>=1;
            }
            if(flag==1){
                //perfect
                int level=log(n+1,2)-1;
                float []yCoordinates=new float[level+2];
                yCoordinates[0]=0;
                int k=0;
                ArrayList<Pair<Float,Float>> l = new ArrayList <Pair <Float,Float> > ();
                for(int i=1;i<=level+1;i++){
                    yCoordinates[i]=i*(y/(level+2));
                    int xNodesOnLevel=(int)Math.pow(2,(double)i-1);
                    float []xCoordinates=new float[xNodesOnLevel];
                    for(int j=0;j<xNodesOnLevel;j++){
                        xCoordinates[j]=(j+1)*(x/(xNodesOnLevel+1));
                        canvas.drawCircle(xCoordinates[j],yCoordinates[i],300/n, paint);
                        l.add(new Pair<Float, Float>(xCoordinates[j],yCoordinates[i]));
                    }
                }
                float oldx;
                float oldy;
                for(int i=0;i<n;i++){
                    oldx=l.get((i-1)/2).first;
                    oldy=l.get((i-1)/2).second;
                    float newx=l.get(i).first;
                    float newy=l.get(i).second;
                    canvas.drawLine(oldx,oldy,newx,newy,paint1);
                }

            }
            else {
                //not perfect
                int level=log(n+1,2);
                float []yCoordinates=new float[level+2];
                yCoordinates[0]=0;
                int k=0;
                ArrayList<Pair<Float,Float>> l = new ArrayList <Pair <Float,Float> > ();
                for(int i=1;i<=level+1;i++){
                    yCoordinates[i]=i*(y/(level+2));
                    int xNodesOnLevel=(int)Math.pow(2,(double)i-1);
                    float []xCoordinates=new float[xNodesOnLevel];
                    for(int j=0;j<xNodesOnLevel;j++){
                        xCoordinates[j]=(j+1)*(x/(xNodesOnLevel+1));
                        canvas.drawCircle(xCoordinates[j],yCoordinates[i],300/n, paint);
                        l.add(new Pair<Float, Float>(xCoordinates[j],yCoordinates[i]));
                        k++;
                        if(k>=n){
                            break;
                        }
                    }
                }
                float oldx;
                float oldy;
                for(int i=0;i<n;i++){
                    oldx=l.get((i-1)/2).first;
                    oldy=l.get((i-1)/2).second;
                    float newx=l.get(i).first;
                    float newy=l.get(i).second;
                    canvas.drawLine(oldx,oldy,newx,newy,paint1);
                }
            }
        }
        else if(type==2){
            int n=nodes;
            int flag=1;
            int q=n;
            int x = getWidth();
            int y = getHeight();
            int level=(n-1)/2;
            int nodesX=func(n);
            ArrayList<Pair<Float,Float>> l = new ArrayList <Pair <Float,Float> > ();
            float []yCoordinates=new float[level+2];
            float []xCoordinates=new float[nodesX+1];
            yCoordinates[0]=0;
            xCoordinates[0]=0;
            int k=1;
            for(int i=1;i<=nodesX;i++){
                xCoordinates[i]=i*(x/(nodesX+1));
            }
            for(int i=1;i<=level+1;i++){
                yCoordinates[i]=i*(y/(level+1));
            }
            int c=1;
            int radius=fun2(n);
            canvas.drawCircle(xCoordinates[1],yCoordinates[0],radius, paint);
            l.add(new Pair<Float, Float>(xCoordinates[1],yCoordinates[0]));
            for(int i=1;i<=level;i++){
                canvas.drawCircle(xCoordinates[k-1],yCoordinates[i],radius, paint);
                canvas.drawCircle(xCoordinates[k+1],yCoordinates[i],radius, paint);
                l.add(new Pair<Float, Float>(xCoordinates[k-1],yCoordinates[i]));
                l.add(new Pair<Float, Float>(xCoordinates[k+1],yCoordinates[i]));
                k++;
                c+=2;
            }
            if(c<n){
                l.add(new Pair<Float, Float>(xCoordinates[k-1],yCoordinates[level+1]));
                canvas.drawCircle(xCoordinates[k-1],yCoordinates[level+1],radius,paint);c++;
            }
            if(n%2==0)
            l.add(new Pair<Float, Float>(xCoordinates[k],yCoordinates[level]));
            float oldx;
            float oldy;
            oldx=l.get(0).first;
            oldy=l.get(0).second;
            for(int i=1;i<l.size();i+=2){
                float newx=l.get(i).first;
                float newy=l.get(i).second;
                float new2x=l.get(i+1).first;
                float new2y=l.get(i+1).second;
                canvas.drawLine(oldx,oldy,newx,newy,paint1);
                canvas.drawLine(oldx,oldy,new2x,new2y,paint1);
                oldx=l.get(i+1).first;
                oldy=l.get(i+1).second;
            }

        }

    }

    private int fun2(int n) {
        if(n<=3){
            return 20;
        }
        if(n<10)
            return 10;
        else
            return 200/n;
    }

    private int func(int n) {
        if(n<=3)
            return n;
        else
            return (n+3)/2;
    }

    public int log(int x, int base)
    {
        return (int) (Math.log(x) / Math.log(base));
    }
}
