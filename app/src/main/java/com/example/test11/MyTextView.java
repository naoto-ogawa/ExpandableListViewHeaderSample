package com.example.test11;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    private boolean above;

    private int shreshold;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setType(boolean above) {
        this.above = above;
    }

    public void setShreshold(int shreshold) {
        this.shreshold = shreshold * -1;
        this.invalidate();
    }

    public void setShreshold(int shreshold, String s) {
        this.shreshold = shreshold * -1;
        setText(s);
    }

    @Override
    public void draw(Canvas canvas) {
        int h = this.getHeight();
        int w = this.getWidth();
        canvas.save();
        if (above) {
            canvas.clipRect(0, 0, w, h - shreshold);
        } else {
            canvas.translate(0, h - shreshold);
        }
        super.draw(canvas);
        canvas.restore();
    }
}
