package com.example.administrator.xmlparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ChuPeng on 2016/9/27.
 */
public class ListViewAdapter extends BaseAdapter
{
    private Context context;
    private List<Goods> goodsList;
    private LayoutInflater mInflater;
    public ListViewAdapter(Context context, List<Goods> goodsList)
    {
        this.context = context;
        this.goodsList = goodsList;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return goodsList.size();
    }

    public Object getItem(int position)
    {
        return goodsList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Goods goods = goodsList.get(position);
        View view = mInflater.inflate(R.layout.listview_item, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView refercode = (TextView) view.findViewById(R.id.refercode);
        TextView status = (TextView) view.findViewById(R.id.status);
        name.setText(goods.getName());
        refercode.setText(goods.getRefercode());
        status.setText(goods.getStatus());
        return view;
    }
}
