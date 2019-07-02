package com.example.cordplayer;

import android.net.Uri;

public class SongInfo {

    String mSongName;
    String mArtistName;
    Uri mUrl;

    public SongInfo(String mSongName, String mArtistName, Uri mUrl ) {
        this.mSongName = mSongName;
        this.mArtistName = mArtistName;
        this.mUrl = mUrl;
    }


    public String getmArtistName() {
        return mArtistName;
    }

    public String getmSongName() {
        return mSongName;
    }

    public Uri getmUrl() {
        return mUrl;
    }
}
