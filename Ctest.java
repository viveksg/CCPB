package com.viveksg.ccpb.test;

import android.app.Activity;
import android.os.Bundle;

public class Ctest extends Activity
{
    int viewwidth=0;
    int viewheight=0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void setViewDimensions() {
        int dimensions[]=getDimensions();
        viewwidth=dimensions[0]/rownos;
        viewheight=dimensions[1]/rownos;
    }

    public int[] getDimensions() {
        int dimensions[] = new int[2];
        WindowManager wmgr = getActivity().getWindowManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point point = new Point();
            wmgr.getDefaultDisplay().getSize(point);
            dimensions[0] = point.x;
            dimensions[1] = point.y;
        } else {
            Display display = wmgr.getDefaultDisplay();
            dimensions[0] = display.getWidth();
            dimensions[1] = display.getHeight();

        }
        return dimensions;
    }
}
