package campus.podcast;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Akhila on 9/8/2018.
 */
public class Custom extends ArrayAdapter<Detail>{
    List<Detail> details;
    LayoutInflater inflater;
    public Custom(Context context,int resource,List<Detail> objects){
        super(context,resource,objects);
        details=objects;
        inflater=LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(R.layout.songdetails,parent,false);
        }
        Detail currentsong=details.get(position);
        TextView t1=(TextView)convertView.findViewById(R.id.name);
        t1.setText(currentsong.getSongname());
        TextView t2=(TextView)convertView.findViewById(R.id.duration);
        t2.setText(currentsong.getDuration());
        t1.setText(details.get(position).getSongname());
        t2.setText(details.get(position).getDuration());
        return convertView;
    }
}
