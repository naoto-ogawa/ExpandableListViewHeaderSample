package com.example.test11;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Map;

public class MyExpandableListViewHeader
        extends FrameLayout
        implements AbsListView.OnScrollListener {

    private MyTextView textView1;
    private MyTextView textView2;

    public MyExpandableListViewHeader(Context context) {
        super(context);
        init();
    }

    public MyExpandableListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyExpandableListViewHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textView1 = getTextView(true);
        addView(textView1);
        textView2 = getTextView(false);
        addView(textView2);


    }

    private MyTextView getTextView(boolean above) {
        MyTextView textView = new MyTextView(this.getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setPadding(60, 0, 0, 0);
        textView.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Large);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int height = (int) (metrics.density * 60);
        // Log.d("xxx", "" + height);
        textView.setHeight(height);
        textView.setType(above);
        return textView;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        ExpandableListView exp = (ExpandableListView) view;
        long getExpandableListPosition = exp.getExpandableListPosition(firstVisibleItem);
        int getPackedPositionType = ExpandableListView.getPackedPositionType(getExpandableListPosition);
        int getPackedPositionGroup = ExpandableListView.getPackedPositionGroup(getExpandableListPosition);
        int getPackedPositionChild = ExpandableListView.getPackedPositionChild(getExpandableListPosition);

        Log.d("xxx", String.format(
                "1st=%d, packec=%d, type=%d group=%d, child=%d",
                firstVisibleItem,
                getExpandableListPosition,
                getPackedPositionType,
                getPackedPositionGroup,
                getPackedPositionChild

        ));

        View child = view.getChildAt(0);
        if (child == null) {
            return;
        }

        TextView tv = (TextView) child.findViewById(R.id.group);
        if ((tv == null || textView2.getText().toString().equals(tv.getText().toString()))) {
            return;
        }

        updateText(exp, child, getPackedPositionGroup, textView2);
        if (getPackedPositionGroup > 0) {
            updateText(exp, child, getPackedPositionGroup - 1, textView1);
        }

    }

    private void updateText(ExpandableListView exp, View child, int index, MyTextView text1) {
        Map<String, Object> data = (Map<String, Object>) exp.getExpandableListAdapter().getGroup(index);
        text1.setShreshold(child.getTop(), index + " : " + data.get("value"));
        text1.setBackgroundColor(((index) % 2 == 0) ? Color.parseColor("#FFA9E9F9") : Color.parseColor("#FF7FF6E2"));
    }
}
