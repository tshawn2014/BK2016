package com.hit.cqcoder.bk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by 唐诗焓 on 2015/7/5.
 * This is custom component which exist all the time.
 */


public class MainControl extends LinearLayout{
    public MainControl(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.main_control,this);

        Button button_1 = (Button)findViewById(R.id.button_1); //支出按钮
        ImageButton imageButton_2 = (ImageButton)findViewById(R.id.button_2);//添加按钮
        Button button_3 = (Button)findViewById(R.id.button_3);//分析按钮

        button_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //支出按钮, 实现从当前 activity 到 MainActivity
                Intent intent = new Intent((Activity)getContext(),MainActivity.class);
                Log.d("button_1","onClick");
                ((Activity)getContext()).startActivity(intent);
            }
        });

        imageButton_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加按钮,实现从当前 activity 到 AddActivity
                Log.d("image_button_2","成功click");
                Intent intent = new Intent((Activity)getContext(),AddActivity.class);
                ((Activity)getContext()).startActivity(intent);

            }
        });

        button_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //分析按钮,实现从当前 activity 到 AnalysisActivity
                Intent intent = new Intent("com.hit.cqcoder.bk.ACTION_START");
                ((Activity)getContext()).startActivity(intent);
            }
        });
    }
}
