package com.example.pz.webviewstudy.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;



/**
 * author: xupz
 */
public class MyGridView extends GridView {


    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //从新排版 , 使得WRAP_CONTENT时可以完全显示
        int heightSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
