package com.sjacobpowell.dallascountyfrogcalls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by S Jacob Powell on 5/2/2016.
 */
public class CustomAdapter extends ArrayAdapter {

    public static final int TYPE_HERP_ROW = 0;
    public static final int TYPE_TITLE = 1;

    private ListViewItem[] objects;

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ListViewItem listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {

            if (listViewItemType == TYPE_HERP_ROW) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.herp_row, null);
            } else if (listViewItemType == TYPE_TITLE) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.title, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.my_text);
            viewHolder = new ViewHolder(textView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.getText().setText(listViewItem.getText());

        return convertView;
    }

}