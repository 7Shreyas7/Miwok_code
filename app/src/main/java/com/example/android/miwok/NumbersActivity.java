package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {


    private MediaPlayer mplayer;
    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        numbers.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        numbers.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        numbers.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        numbers.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        numbers.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter wa = new WordAdapter(this,numbers,R.color.category_numbers);

        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(wa);

        ListView lv = (ListView)findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord=numbers.get(i);
                releaseMediaPlayer();
                mplayer = MediaPlayer.create(NumbersActivity.this,currentWord.getMusicfileId());
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
