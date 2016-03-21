package com.hit.cqcoder.bk;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2015/7/7.
 */
public class AddActivity extends ActionBarActivity{

    private EditText editText_1;
    private EditText editText_2;
    private EditText editText_3;
    private Spinner spinner;
    private Button confirmButton;
    private Button cancelButton;
    private Item newItem;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editText_1 = (EditText)findViewById(R.id.cost);
        editText_2 = (EditText)findViewById(R.id.content);
        editText_3 = (EditText)findViewById(R.id.date);
        confirmButton = (Button)findViewById(R.id.confirm);
        cancelButton = (Button)findViewById(R.id.cancel);
        spinner = (Spinner)findViewById(R.id.spinner);

        dbHelper = new MyDatabaseHelper(this, "ItemList.db",null,1);
        dbHelper.getWritableDatabase();

        confirmButton.setOnClickListener(new View.OnClickListener() {

            Item it = new Item();

            @Override
            public void onClick(View v) {
                it.setmCostString(editText_1.getText().toString());
                it.setmContent(editText_2.getText().toString());
                it.setmDateString(editText_3.getText().toString());
                it.setmSort(spinner.getSelectedItem().toString());
                if(!TextUtils.isEmpty(it.getmCostString())&&
                        !TextUtils.isEmpty(it.getmContent())&&
                        !TextUtils.isEmpty(it.getmDateString())&&
                        !TextUtils.isEmpty(it.getmSort())){
                    //保存写入数据

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put("cost",it.getmCost());
                    values.put("content",it.getmContent());
                    values.put("date",it.getmDateString());
                    values.put("sort",it.getmSort());
                    db.insert("Item",null,values);
                    values.clear();

                    Toast.makeText(getApplicationContext(),getString(R.string.success),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),getString(R.string.tips),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
