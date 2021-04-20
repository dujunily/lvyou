package com.bishe.me;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bishe.me.bean.Raider;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    Context context;
    List<Raider> notepadList;


    public NoteAdapter(Context context, List<Raider> notepadList) {
        this.context = context;
        this.notepadList = notepadList;
    }

    @Override
    public int getCount() {
        return notepadList.size();
    }

    @Override
    public Object getItem(int position) {
        return notepadList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder = new ViewHolder();
//            convertView = View.inflate(context, R.layout.note_item, null);
//            viewHolder.tvContent=	convertView.findViewById(R.id.content);
//            viewHolder.tvTile=convertView.findViewById(R.id.title);
//            viewHolder.tvTime=convertView.findViewById(R.id.my_tv_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTime.setText(notepadList.get(position).getTime());
        viewHolder.tvContent.setText(notepadList.get(position).getContent());
        viewHolder.tvTile.setText(notepadList.get(position).getName() );

        Log.e("time",notepadList.get(position).getTime());

        return convertView;
    }

    static class  ViewHolder{
        TextView tvTile;
        TextView tvTime;
        TextView tvContent;
    }
}
