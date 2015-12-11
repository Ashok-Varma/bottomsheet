package com.flipboard.bottomsheet.sample;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.R;

public class BottomSheetWithFABActivity extends AppCompatActivity {

    BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_with_fab);

        setSupportActionBar((Toolbar) findViewById(R.id.actionBar));

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);
        bottomSheetLayout.setPeekSheetTranslation(dp2px(300));//peek

        final View view = LayoutInflater.from(BottomSheetWithFABActivity.this).inflate(R.layout.sheet_view_with_fab, bottomSheetLayout, false);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);


        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            final int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            view.setPadding(0, actionBarHeight, 0, 0);
        }else{
            view.setPadding(0, dp2px(48), 0, 0);
        }


//        final LinearLayout belowFab = (LinearLayout) view.findViewById(R.id.below_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                }
            }
        });

        findViewById(R.id.with_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetLayout.showWithSheetView(view);
            }
        });
    }

    private int dp2px(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.getResources().getDisplayMetrics());
        return Math.round(px);
    }
}
