package bello.andrea.calcolatrice;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class MioBottone extends Button {


    public MioBottone(Context context) {
        super(context);
    }

    public MioBottone(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        int min;
        if(width < height){
            min = width;
        }else{
            min = height;
        }

        setMeasuredDimension(min, min);
    }

}
