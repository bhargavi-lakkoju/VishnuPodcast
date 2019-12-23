package campus.podcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Akhila on 9/10/2018.
 */
public class Audiolist2 extends Activity {
    ListView listView;
    ArrayList<Detail> namelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView=(ListView)findViewById(R.id.list);
        Custom adapter=new Custom(this,R.layout.songdetails,namelist);
        namelist.add(new Detail("Chilipiga","04:20","https://firebasestorage.googleapis.com/v0/b/podcast-ff9d5.appspot.com/o/02%20%20-%20Chilipiga%20-%20%5Bwww.MazaMp3.com%5D.mp3?alt=media&token=d8457e5b-0c80-44f5-be21-0cb9fe3b90f6"));
        namelist.add(new Detail("mira mira meesam","05:00","https://firebasestorage.googleapis.com/v0/b/podcast-ff9d5.appspot.com/o/%5BiSongs.info%5D%2001%20-%20Mira%20Mira%20Meesam.mp3?alt=media&token=8631fc25-594a-45bf-87ae-aaf15d8f9907"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url=namelist.get(position).getUrl();
                Intent intent=new Intent(Audiolist2.this,Newaudio.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });


    }
}
