package com.hit.cqcoder.bk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/7/13.
 */
public class EditActivity extends ActionBarActivity{

    private EditText editText_1;
    private EditText editText_2;
    private EditText editText_3;
    private Spinner spinner;
    private Button confirmButton;
    private Button deleteButton;
    private Button cancelButton;
//    private Item newItem;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText_1 = (EditText)findViewById(R.id.cost_edit);
        editText_2 = (EditText)findViewById(R.id.content_edit);
        editText_3 = (EditText)findViewById(R.id.date_edit);
        confirmButton = (Button)findViewById(R.id.confirm_edit);
        deleteButton = (Button)findViewById(R.id.delete_edit);
        cancelButton = (Button)findViewById(R.id.cancel_edit);
        spinner = (Spinner)findViewById(R.id.spinner_edit);

        dbHelper = new MyDatabaseHelper(this, "ItemList.db",null,1);
        dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        final String data_date = intent.getStringExtra("date");
        final String data_cost = intent.getStringExtra("cost");
        final String data_content = intent.getStringExtra("content");
        final String data_sort = intent.getStringExtra("sort");

        editText_1.setText(data_cost);
        editText_2.setText(data_content);
        editText_3.setText(data_date);


        confirmButton.setOnClickListener(new View.OnClickListener() {

            Item it = new Item();

            @Override
            public void onClick(View v) {
                it.setmCostString(editText_1.getText().toString());
                it.setmContent(editText_2.getText().toString());
                it.setmDateString(editText_3.getText().toString());
                it.setmSort(spinner.getSelectedItem().toString());
                if (!TextUtils.isEmpty(it.getmCostString()) &&
                        !TextUtils.isEmpty(it.getmContent()) &&
                        !TextUtils.isEmpty(it.getmDateString()) &&
                        !TextUtils.isEmpty(it.getmSort())) {
                    //保存写入数据

                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    update(db, "Item", data_date, it);


                    Toast.makeText(getApplicationContext(), getString(R.string.success),
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.tips),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                delete(db, "Item",data_date);
                Toast.makeText(getApplicationContext(), getString(R.string.delete),
                        Toast.LENGTH_SHORT).show();
                finish();

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

    public void update(SQLiteDatabase db,String table,String date,Item it)
    {
        ContentValues values = new ContentValues();
        values.put("cost",it.getmCost());
        values.put("content",it.getmContent());
        values.put("date",it.getmDateString());
        values.put("sort",it.getmSort());
        String whereClause = "date=?";
        String[] whereArgs = new String[] { String.valueOf(date) };
        db.update("Item",values,whereClause,whereArgs);
        values.clear();
    }

    public  void delete(SQLiteDatabase db,String table, String date){
        String whereClause =  "date=?";
        String[] whereArgs = new String[] {String.valueOf(date)};
        db.delete(table, whereClause, whereArgs);
    }

}
