package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mplayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange)
                {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
                    {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                        // Pause playback
                        mplayer.pause();
                        mplayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mplayer.start();
                    }
                }
            };

    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mplayer.stop();
            releaseMediaPlayer();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        numbers.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        numbers.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        numbers.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        numbers.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        numbers.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        numbers.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        numbers.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        numbers.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        numbers.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdapter wa = new WordAdapter(this,numbers,R.color.category_phrases);

        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(wa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = numbers.get(i);
                releaseMediaPlayer();
                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    // Start playback
                    mplayer = MediaPlayer.create(PhrasesActivity.this, currentWord.getMusicfileId());
                    mplayer.start();
                    mplayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
