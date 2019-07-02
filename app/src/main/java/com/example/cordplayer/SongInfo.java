package com.example.cordplayer;

public class SongInfo {

    String mSongName;
    String mArtistName;
    String mUrl;

    public SongInfo(String mSongName, String mArtistName, String mUrl ) {
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

    public String getmUrl() {
        return mUrl;
    }
}
