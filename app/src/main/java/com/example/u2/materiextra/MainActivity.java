package com.example.u2.materiextra;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    VideoView myVid;
    Uri uri;
    TextView result;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myVid = (VideoView) findViewById(R.id.vid_baru);

        final TextView teksview = (TextView) findViewById(R.id.tv_rating);
        final RatingBar rate = (RatingBar) findViewById(R.id.rb_rating);

//        set aksi rating bar
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                final int numStars = rate.getNumStars();
                rate.getRating() ;
                final float ratingBarStepSize = rate.getStepSize();

                teksview.setText("Rating : "+ rate.getRating()+" / "+ratingBarStepSize +" / "+numStars);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.boom_sound);
                mediaPlayer.start();
            }
        });

        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ads_vid);
        mediaController = new MediaController(this);

        myVid.setMediaController(mediaController);

//        menngantisipasi perubahan ukuran video
        myVid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.setAnchorView(myVid);
                    }
                });
            }
        });
    }

//    atur konfigurasi perubahan orientasi
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void playVideo(View view){
        myVid.setVideoURI(uri);
        myVid.start();
    }

}
