package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mplayer;

    private AudioManager maudiomanager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                mplayer.pause();
                mplayer.seekTo(0);
            }
            // Pause playback
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mplayer.start();
            }
            // Resume playback
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }


    };


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.word_list,container,false);
        maudiomanager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        WordAdapter adapter=new WordAdapter(getActivity(),words,R.color.category_colors);
        ListView list=(ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word curword= words.get(position);
                int result = maudiomanager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // mplayer.setOnCompletionListener(mListener);

                    releaseMediaPlayer();
                    mplayer= MediaPlayer.create(getActivity(),curword.getAd());
                    mplayer.start();
                    mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }

                    });


                }


            }
        });

        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mplayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            maudiomanager.abandonAudioFocus(mOnAudioFocusChangeListener);

            mplayer = null;
        }
    }

}
