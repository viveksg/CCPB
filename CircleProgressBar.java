/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viveksg.ccpb.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;


public class CircleProgressBar extends ProgressBar{
     public static final String PROGRESS_COLOR="progresscolor";
     public static final String BACKGROUND_COLOR="backgroundcolor";
     public static final String STROKE_WIDTH="strokewidth";
     public static final String TITLE="title";
     public static final String SUBTITLE="subtitle";
     public static final String TITLE_COLOR="titlecolor";
     public static final String SUBTITLE_COLOR="subtitlecolor";
     public static final String TITLE_TEXTSIZE="titletextsize";
     public static final String SUBTITLE_TEXTSIZE="subtitletextsize";
     public static final String IS_SHADOW_ENABLED="shadowenabled";
     public static final String SHADOW_COLOR="shadowcolor";
     public static final String RECT_WIDTH="rectwidth";
     public static final String RECT_HEIGHT="rectheight";
     public static final String FINAL_PROGRESS="finalprogress";
     public static final String RECTS_STROKE_WIDTH="rswidth";
     public static final String OFFSET="offset";
     Context context;
     int progresscolor=0,backgroundcolor=0,titlecolor=0,subtitlecolor=0,shadowcolor=0,rectwidth=0,rectheight=0,finalprogress=0;
     float strokewidth=0,tsizetitle=0,tsizesubtitle=0,rswidth=0,fillstrokewidth=0, p5=0.5f;
     String title="",subtitle="",progresstext="";
     boolean isshadowenabled=false;
     Paint titlepaint=null,subtitlepaint=null,progresspaint=null,backgroundpaint=null,firstpaint=null,secondpaint=null;
     RectF circlerectf=null,circlerectf1=null,circlerectf2=null;
     double centertext=0;
     float offset=20;
    public CircleProgressBar (Context context)
    {
        super(context);
     
    }
    public CircleProgressBar (Context context,Bundle bundle,int style)
    {
        super(context,null,style);
        this.context=context;
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        progresscolor=bundle.getInt(PROGRESS_COLOR);
        backgroundcolor=bundle.getInt(BACKGROUND_COLOR);
        titlecolor=bundle.getInt(TITLE_COLOR);
        subtitlecolor=bundle.getInt(SUBTITLE_COLOR);
        shadowcolor=bundle.getInt(SHADOW_COLOR);
        rectwidth=bundle.getInt(RECT_WIDTH);
        rectheight=bundle.getInt(RECT_HEIGHT);
        offset=bundle.getInt(OFFSET);
        finalprogress=bundle.getInt(FINAL_PROGRESS);
        strokewidth=bundle.getFloat(STROKE_WIDTH);
        tsizetitle=bundle.getFloat(TITLE_TEXTSIZE);
        tsizesubtitle=bundle.getFloat(SUBTITLE_TEXTSIZE);
        rswidth=bundle.getFloat(RECTS_STROKE_WIDTH);
        title=bundle.getString(TITLE);
        subtitle=bundle.getString(SUBTITLE);
        isshadowenabled=bundle.getBoolean(IS_SHADOW_ENABLED);
        circlerectf=new RectF();
        circlerectf1=new RectF();
        circlerectf2=new RectF();

        fillstrokewidth=offset;//rswidt<offset;
         Log.v("values of stroke", offset+" "+rswidth+" "+fillstrokewidth);
        progresspaint=new Paint();
        progresspaint.setColor(progresscolor);
        progresspaint.setAntiAlias(true);
        progresspaint.setStyle(Paint.Style.STROKE);
        progresspaint.setStrokeWidth(fillstrokewidth);

        firstpaint=new Paint();
        firstpaint.setColor(Color.WHITE);
        firstpaint.setAntiAlias(true);
        firstpaint.setStyle(Paint.Style.STROKE);
        firstpaint.setStrokeWidth(rswidth);

        secondpaint=new Paint();
        secondpaint.setColor(Color.WHITE);
        secondpaint.setAntiAlias(true);
        secondpaint.setStyle(Paint.Style.STROKE);
        secondpaint.setStrokeWidth(rswidth);

        backgroundpaint=new Paint();
        backgroundpaint.setColor(backgroundcolor);
        backgroundpaint.setAntiAlias(true);
        backgroundpaint.setStyle(Paint.Style.FILL);
        backgroundpaint.setStrokeWidth(strokewidth);

        titlepaint=new Paint();
        titlepaint.setColor(Color.WHITE);
        titlepaint.setAntiAlias(true);
        titlepaint.setStyle(Style.FILL);
        titlepaint.setTextSize(tsizetitle);
     
      //  titlepaint.setShadowLayer(0.1f, 0, 1, shadowcolor);

        subtitlepaint=new Paint();
        subtitlepaint.setColor(subtitlecolor);
        subtitlepaint.setAntiAlias(true);
        subtitlepaint.setStyle(Style.FILL);
        subtitlepaint.setTextSize(strokewidth);
       
       


    }
    public CircleProgressBar(Context context,AttributeSet aset)
    {
        super(context,aset);
       
    }

    public CircleProgressBar(Context context,AttributeSet aset,int style)
    {
        super(context,aset,style);
       
    }
    @Override
    public synchronized void onDraw(Canvas canvas)
    {
        //canvas.drawArc(circlerectf, 0, 360, false, backgroundpaint);
        canvas.drawArc(circlerectf1, 0, 360, false, firstpaint);
        canvas.drawArc(circlerectf2, 0, 360, false, secondpaint);
        float progress=getProgress(),max=getMax();
        float progresssweep=(float) (max > 0 ? (progress / max) * 360 : 0);
      //  Log.v("Pdata ",""+progress+" "+max+" "+progresssweep);
      //  progresspaint.setShadowLayer(3,0,1, progresscolor);
        progresspaint.setStrokeWidth(fillstrokewidth);
        canvas.drawArc(circlerectf, 270, progresssweep, false,progresspaint);
         float cy=(canvas.getWidth()/2-2*offset-rswidth*p5);
        titlepaint.setTextSize(cy);
        float heightoftitle=Math.abs(titlepaint.descent()+titlepaint.ascent());
        float widthoftitle=Math.abs((titlepaint.measureText(title)));
        int x=(int)(canvas.getWidth()/2-widthoftitle/2);
        int y=(int)((canvas.getHeight()+heightoftitle)/2);
       
        /*int subtitlepresent=0;
        float heightoftitle=Math.abs(titlepaint.descent()+titlepaint.ascent());
        if(subtitle!=null&&subtitle.length()>0)
        {  subtitlepresent=1;
           y+=heightoftitle/2;
        }*/
      //  Log.v("CORDINATES...",canvas.getWidth()+" "+canvas.getHeight()+" "+rectwidth+" "+rectheight+" "+title+" "+ x+" "+y+" "+centertext);
       // Log.v("Cal",""+(offset+rswidth*p5+fillstrokewidth*p5)+" "+(offset+rswidth*p5+fillstrokewidth*p5)+" "+(rectwidth-offset-rswidth*p5-fillstrokewidth*p5)+" "+ (rectwidth-offset-rswidth*p5-fillstrokewidth*p5));
        canvas.drawText(title, x, y, titlepaint);
        titlepaint.setTextSize(cy/2);
        canvas.drawText("%", x+widthoftitle, y-heightoftitle/2, titlepaint);

        titlepaint.setTextSize((float)(cy/3.4));
        float heightofprog=Math.abs(titlepaint.descent()+titlepaint.ascent());
        float widthofprog=Math.abs((titlepaint.measureText(progresstext)));
        int x1=(int)(canvas.getWidth()/2-widthofprog/2);
        int y1=(int)(y+3*heightofprog/2);
        canvas.drawText(progresstext, x1, y1, titlepaint);
       /* if(subtitlepresent==1)
        {
            y+=heightoftitle;
            x=(int)(getMeasuredWidth()/2-subtitlepaint.measureText(subtitle));
           canvas.drawText(subtitle, x, y, subtitlepaint);

        }*/

        super.onDraw(canvas);
    }
    @Override
    public void onMeasure(int width,int height)
    {
        setMeasuredDimension(rectwidth,rectheight);
        
        circlerectf1.set(offset,offset, rectwidth-offset, rectwidth-offset);
        //circlerectf.set(2*(offset-rswidth),2*(offset-rswidth), rectwidth-2*(offset-rswidth), rectwidth-2*(offset-rswidth));
        //circlerectf.set(offset+rswidth*p5+fillstrokewidth*p5,offset+rswidth*p5+fillstrokewidth*p5, rectwidth-offset-rswidth*p5-fillstrokewidth*p5, rectwidth-offset-rswidth*p5-fillstrokewidth*p5);
        circlerectf.set(offset+fillstrokewidth*p5,offset+fillstrokewidth*p5, rectwidth-offset-fillstrokewidth*p5, rectwidth-offset-fillstrokewidth*p5);
        circlerectf2.set(2*offset,2*offset, rectwidth-2*offset, rectwidth-2*offset);



    }
    @Override
    public synchronized void setProgress(int progress)
    {
        super.setProgress(progress);
        invalidate();
    }

    @Override
    public synchronized void setMax(int max)
    {
        super.setMax(max);
        invalidate();
    }
    public int getFinalProgress()
    {
        return this.finalprogress;
    }
    public void setTitle(String stitle)
    {
        this.title=stitle;
        invalidate();
    }

    public void setProgresscolor(int color)
    {
        progresspaint.setColor(color);
    }

    public void setTface(Typeface tface)
    {
          titlepaint.setTypeface(Typeface.create(tface, Typeface.BOLD));
    }
    public void setProgressText(String pro)
    {
        progresstext=pro;
    }
}
