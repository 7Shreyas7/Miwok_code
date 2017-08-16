package com.example.android.miwok;

public class Word {
    private String MiwokTranslation;
    private String DefaultTranslation;
    private int ImageResourceId;
    private static final int NO_IMAGE_PROVIDED=-1;
    private int musicfileId;

    public Word(String defaultTranslation,String miwokTranslation,int ImageResourceId,int music)
    {
        MiwokTranslation=miwokTranslation;
        DefaultTranslation=defaultTranslation;
        this.ImageResourceId=ImageResourceId;
        this.musicfileId=music;
    }

    public Word(String defaultTranslation,String miwokTranslation,int music)
    {
        MiwokTranslation=miwokTranslation;
        DefaultTranslation=defaultTranslation;
        ImageResourceId=NO_IMAGE_PROVIDED;
        this.musicfileId=music;
    }

    public String getDefaultTranslation()
    {
        return DefaultTranslation;
    }

    public String getMiwokTranslation()
    {
        return MiwokTranslation;
    }

    public int getImageResourceId()
    {
        return ImageResourceId;
    }

    public boolean hasImageResource()
    {
        return ImageResourceId!=NO_IMAGE_PROVIDED;
    }

    public int getMusicfileId()
    {
        return musicfileId;
    }
}
