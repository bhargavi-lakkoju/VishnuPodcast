package campus.podcast;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Akhila on 9/6/2018.
 */
public class Newaudio extends Activity {
    Button b1, b2,btn5;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    double starttime = 0;
    double finaltime = 0;
    Handler handler = new Handler();
    int forwardtime = 5000;
    int backwardtime = 5000;
    SeekBar seekBar;
    TextView t1, t2, t3;
    static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        btn5=(Button) findViewById(R.id.playbtn);
        t1 = (TextView) findViewById(R.id.txt1);
        t2 = (TextView) findViewById(R.id.txt2);
        t3 = (TextView) findViewById(R.id.txt3);
        imageView = (ImageView) findViewById(R.id.image);
        t3.setText("song.mp3");
        seekBar = (SeekBar) findViewById(R.id.seek);
        seekBar.setClickable(false);

    }


    public void play_song(View v) {

        mediaPlayer = new MediaPlayer();
        //btn5.setOnClickListener(new View.OnClickListener() {
        //public void onClick(View v) {
        try {
            String url1=getIntent().getStringExtra("url");
            mediaPlayer.setDataSource(url1);
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



        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btn5.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                } else {
                    mediaPlayer.start();
                    btn5.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                }

            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=(int) starttime;
                int seekForwardtime=5000;
                if(mediaPlayer!=null){
                    int currentPosition=mediaPlayer.getCurrentPosition();
                    if(currentPosition+seekForwardtime<=mediaPlayer.getDuration()){
                        mediaPlayer.seekTo(currentPosition+seekForwardtime);
                    }
                    else{
                        mediaPlayer.seekTo(mediaPlayer.getDuration());
                    }
                    Toast.makeText(getApplicationContext(), "5 seconds forwaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            int seekbackwardtime=5000;
            public void onClick(View v) {
                if(mediaPlayer!=null) {
                    int currentposition = mediaPlayer.getCurrentPosition();
                    if(currentposition-seekbackwardtime>=0){
                        mediaPlayer.seekTo(currentposition-seekbackwardtime);
                    }
                }
                Toast.makeText(getApplicationContext(), "5 seconds backwarded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private Runnable UpadateSongTime = new Runnable() {
        @Override
        public void run() {
            starttime = mediaPlayer.getCurrentPosition();
            t1.setText(String.format("%d min,%d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) starttime),
                    TimeUnit.MILLISECONDS.toSeconds((long) starttime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) starttime))));
            seekBar.setProgress((int) starttime);
            handler.postDelayed(this, 100);
        }
    };
}
