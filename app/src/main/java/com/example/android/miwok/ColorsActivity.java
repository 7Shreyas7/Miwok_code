package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mplayer;

    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        numbers.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        numbers.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        numbers.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        numbers.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        numbers.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

    WordAdapter wa = new WordAdapter(this,numbers,R.color.category_colors);

    ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(wa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = numbers.get(i);
                releaseMediaPlayer();
                mplayer = MediaPlayer.create(ColorsActivity.this,currentWord.getMusicfileId());
                mplayer.start();
                mplayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer()
    {
        if(mplayer!=null)
        {
            mplayer.release();
            mplayer=null;
        }
    }
}
