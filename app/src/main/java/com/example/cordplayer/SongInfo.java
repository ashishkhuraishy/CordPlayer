package com.example.cordplayer;

import android.net.Uri;

public class SongInfo {

    String mSongName;
    String mArtistName;
    String mAlbum;
    String mUrl;

    public SongInfo(String mSongName, String mArtistName, String mAlbum ,String mUrl ) {
        this.mSongName = mSongName;
        this.mArtistName = mArtistName;
        this.mAlbum = mAlbum;
        this.mUrl = mUrl;
    }


    public String getmArtistName() {
        return mArtistName;
    }

    public String getmSongName() {
        return mSongName;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public String getmUrl() {
        return mUrl;
    }
}
