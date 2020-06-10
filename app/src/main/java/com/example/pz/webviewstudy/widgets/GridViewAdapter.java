package com.example.pz.webviewstudy.widgets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pz.webviewstudy.R;


/**
 * 站点信息管理营业彩种选择容器容器
 */
public class GridViewAdapter extends BaseAdapter {

    private final int[] imgs;
    private final int[] names;
    private Context context;


    /**
     * @param context 上下文 , 最好是activity
     */
    public GridViewAdapter(Context context, int[] imgs, int [] names) {
        super();
        this.context = context;
        this.imgs = imgs;
        this.names = names;
    }

    @Override
    public int getCount() {
        return Math.min(names.length, imgs.length);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_grid, null);
            holder.img = convertView.findViewById(R.id.img);
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(context.getString(names[position]));
        holder.img.setImageResource(imgs[position]);
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView img;
    }

}
