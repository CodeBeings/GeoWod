package com.urocks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sony on 18-10-2015.
 */
public class ListViewAdapter extends ArrayAdapter<String> {

    String[] values;
    int[] drawables = new int[]{R.drawable.home, R.drawable.challenges, R.drawable.leaderboard, R.drawable.points,
            R.drawable.profile, R.drawable.logout};

    public ListViewAdapter(Context context, int resource, String[] values) {
        super(context, resource, values);
        this.values = values;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_drawer, parent, false);
            view = new ViewHolder();
            view.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            view.textView = (TextView) convertView.findViewById(R.id.editText);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.imageView.setBackground(getContext().getResources().getDrawable(drawables[position]));
        view.textView.setText(values[position]);
        return convertView;
    }
}


