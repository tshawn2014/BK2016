package com.hit.cqcoder.bk;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ArrayList<Item> itemList  = new ArrayList<>(100);
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public void onResume(){
        super.onResume();
        dbHelper = new MyDatabaseHelper(this,"ItemList.db",null,1);
//
        initItem();
//        db第一次访问存在判断

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item_list,itemList);

        ListView lv = (ListView)findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item iitt = itemList.get(position);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);


                intent.putExtra("date",iitt.getmDateString());
                intent.putExtra("cost",iitt.getmCostString());
                intent.putExtra("content",iitt.getmContent());
                intent.putExtra("sort",iitt.getmSort());

                startActivity(intent);
            }
        });
//        itemList = null;
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

    private  static int i = 0;
    private void initItem(){
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Item",null,"id",null,null,null,null,null);
        i = 0;
//        ArrayList<Item> itt = new ArrayList<>(100);

        itemList.clear();
        if (cursor.moveToFirst()){
            do{

                itemList.add(i, new Item(cursor.getDouble(cursor.getColumnIndex("cost")),
                        cursor.getString(cursor.getColumnIndex("sort")),
                        cursor.getString(cursor.getColumnIndex("date")),
                        cursor.getString(cursor.getColumnIndex("content"))));

                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
