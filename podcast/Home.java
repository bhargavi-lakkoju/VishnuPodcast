package campus.podcast;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Akhila on 9/10/2018.
 */
public class Home extends AppCompatActivity {
    CardView c1,c2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        c1=(CardView)findViewById(R.id.card1);
        c2=(CardView)findViewById(R.id.card2);
        if(havenetwork()){

        }
        else if(!havenetwork()){
            Toast.makeText(Home.this,"NO INTERNET CONNECTION!!!!!",Toast.LENGTH_LONG).show();
        }
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Home.this,Audiolist.class);
                startActivity(intent1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Home.this,Audiolist2.class);
                startActivity(intent2);
            }
        });


    }
    private boolean havenetwork(){
        boolean have_wifi=false;
        boolean mobiledata=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                   have_wifi=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE")){
                if (info.isConnected())
                    mobiledata=true;
            }
        }
        return have_wifi||mobiledata;
    }

}
