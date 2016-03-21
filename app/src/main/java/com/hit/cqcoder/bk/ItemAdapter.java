package com.hit.cqcoder.bk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/7/9.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    private  int resourceId;
    public ItemAdapter (Context context, int textViewResourceId, List<Item> object){
        super(context, textViewResourceId, object);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Item it = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
             view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.itemContent = (TextView)view.findViewById(R.id.cost_list_item_contentTextView);
            viewHolder.itemCost = (TextView)view.findViewById(R.id.cost_list_item_costTextView);
            viewHolder.itemDate = (TextView)view.findViewById(R.id.cost_list_item_dateTextView);
            viewHolder.itemSort = (TextView)view.findViewById(R.id.cost_list_item_sortTextView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
        viewHolder = (ViewHolder)view.getTag();}

        viewHolder.itemContent.setText(it.getmContent());
        viewHolder.itemCost.setText(it.getmCostString());
        viewHolder.itemDate.setText(it.getmDateString());
        viewHolder.itemSort.setText(it.getmSort());
        return view;
    }

    class ViewHolder{
        TextView itemContent;
        TextView itemCost;
        TextView itemDate;
        TextView itemSort;

    }
}
