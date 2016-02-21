package com.example.test11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableListView);
        list.setAdapter(new MyExpandableListAdapter(this));
        list.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int p = list.getFirstVisiblePosition();
                // Log.d("xxx", "first pos" + p);
                if (p == 0) {
                    list.setSelection(1);
                    return true;
                }
                return false;
            }
        });
        MyExpandableListViewHeader header = (MyExpandableListViewHeader) findViewById(R.id.expandableListHeader);
        list.setOnScrollListener(header);
        list.setSelection(1);

    }

}
