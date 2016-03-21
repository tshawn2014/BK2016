package com.hit.cqcoder.bk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Created by TSH on 2015/7/5.
 * This is AnalysisActivity to show users' expenditure with pics.
 */

public class AnalysisActivity extends ActionBarActivity{

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dbHelper = new MyDatabaseHelper(this,"ItemList.db",null,1);
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Item", null, "id", null, null, null, null, null);
        double gouwu = 0,xuexi = 0,yiliao = 0,yinliao = 0,jiaotong = 0,canshi = 0,yule = 0;
        if(cursor.moveToFirst()){
            do{
                if("Shopping".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    gouwu += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else if("Study".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    xuexi += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else if("Medicine".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    yiliao += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else if("Drink".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    yinliao += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else if("Food".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    canshi += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else if("Transportation".equals(cursor.getString(cursor.getColumnIndex("sort")))){
                    jiaotong += cursor.getDouble(cursor.getColumnIndex("cost"));
                }
                else{
                    yule += cursor.getDouble(cursor.getColumnIndex("cost"));

                }
            }while (cursor.moveToNext());
        }
        TextView tv_yule = (TextView)findViewById(R.id.yule);
        TextView tv_canshi = (TextView)findViewById(R.id.canshi);
        TextView tv_yinliao = (TextView)findViewById(R.id.yinliao);
        TextView tv_xuexi = (TextView)findViewById(R.id.xuexi);
        TextView tv_jiaotong = (TextView)findViewById(R.id.jiaotong);
        TextView tv_yiliao = (TextView)findViewById(R.id.yiliao);
        TextView tv_gouwu = (TextView)findViewById(R.id.gouwu);

        tv_canshi.setText(Double.toString(canshi));
        tv_yule.setText(Double.toString(yule));
        tv_yinliao.setText(Double.toString(yinliao));
        tv_yiliao.setText(Double.toString(yiliao));
        tv_jiaotong.setText(Double.toString(jiaotong));
        tv_xuexi.setText(Double.toString(xuexi));
        tv_gouwu.setText(Double.toString(gouwu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
