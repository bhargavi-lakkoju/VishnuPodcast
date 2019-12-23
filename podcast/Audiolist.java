package campus.podcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akhila on 9/6/2018.
 */
public class Audiolist extends Activity {
    ListView listView;
    ArrayList<Detail> namelist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView=(ListView)findViewById(R.id.list);
        Custom adapter=new Custom(this,R.layout.songdetails,namelist);
        namelist.add(new Detail("Inkem Inkem Kavale","04:20","https://firebasestorage.googleapis.com/v0/b/podcast-ff9d5.appspot.com/o/%5BiSongs.info%5D%2001%20-%20Inkem%20Inkem%20Inkem%20Kaavaale.mp3?alt=media&token=a599c046-d1b5-4b51-91f1-720bb4cf5d19"));
        namelist.add(new Detail("Last Bench","05:00","https://firebasestorage.googleapis.com/v0/b/podcast-ff9d5.appspot.com/o/Last%20Bench%20--%20Naasongs.Me.mp3?alt=media&token=5949921f-1990-4a5c-b453-900ea4110aa2"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url=namelist.get(position).getUrl();
                Intent intent=new Intent(Audiolist.this,Newaudio.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });


    }
}
