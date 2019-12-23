package campus.podcast;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Akhila on 9/4/2018.
 */
public class Audio extends Activity{
    Button b1,b2,b3,b4;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    double starttime=0;
    double finaltime=0;
    Handler handler=new Handler();
    int forwardtime=5000;
    int backwardtime=5000;
    SeekBar seekBar;
    TextView t1,t2,t3;
    static int oneTimeOnly=0;
    boolean isplaying =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        t1 = (TextView) findViewById(R.id.txt1);
        t2 = (TextView) findViewById(R.id.txt2);
        t3 = (TextView) findViewById(R.id.txt3);
        imageView = (ImageView) findViewById(R.id.image);
        t3.setText("song.mp3");
        seekBar = (SeekBar) findViewById(R.id.seek);
        seekBar.setClickable(false);
        b3.setEnabled(false);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"playing...",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                finaltime=mediaPlayer.getDuration();
                starttime=mediaPlayer.getCurrentPosition();
                if(oneTimeOnly==0){
                    seekBar.setMax((int)finaltime);
                    oneTimeOnly=1;
                }
                t2.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finaltime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finaltime)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finaltime))));
                t1.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) starttime),
                        TimeUnit.MILLISECONDS.toSeconds((long) starttime)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finaltime))));
                seekBar.setProgress((int)starttime);
                handler.postDelayed(UpadateSongTime,100);
                b3.setEnabled(true);
                b4.setEnabled(false);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=(int) starttime;
                if((temp+forwardtime)<=finaltime){
                    starttime=starttime+forwardtime;
                    mediaPlayer.seekTo((int)starttime);
                    Toast.makeText(getApplicationContext(), "5 seconds forwaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=(int) starttime;
                if((temp-backwardtime)<=finaltime){
                    starttime=starttime-backwardtime;
                    mediaPlayer.seekTo((int)starttime);
                    Toast.makeText(getApplicationContext(), "5 seconds backwarded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Runnable UpadateSongTime=new Runnable() {
        @Override
        public void run() {
            starttime=mediaPlayer.getCurrentPosition();
            t1.setText(String.format("%d min,%d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long)starttime),
                    TimeUnit.MILLISECONDS.toSeconds((long)starttime)-
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)starttime))));
            seekBar.setProgress((int)starttime);
            handler.postDelayed(this,100);
        }
    };
    public void play_song(View v){
        mediaPlayer = new MediaPlayer();
        String urlString="https://firebasestorage.googleapis.com/v0/b/podcast-ff9d5.appspot.com/o/%5BiSongs.info%5D%2001%20-%20Inkem%20Inkem%20Inkem%20Kaavaale.mp3?alt=media&token=a599c046-d1b5-4b51-91f1-720bb4cf5d19";
        try {
            mediaPlayer.setDataSource(urlString);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

