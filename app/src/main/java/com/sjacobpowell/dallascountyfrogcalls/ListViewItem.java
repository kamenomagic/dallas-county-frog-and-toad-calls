package com.sjacobpowell.dallascountyfrogcalls;

import android.media.MediaPlayer;

/**
 * Created by S Jacob Powell on 5/2/2016.
 */
public class ListViewItem {
    private String text;
    private MediaPlayer sound;
    private int type;

    public ListViewItem(String text, MediaPlayer sound, int type) {
        this.text = text;
        this.sound = sound;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public MediaPlayer getSound() {
        return sound;
    }

    public int getType() {
        return type;
    }
}
