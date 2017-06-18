package com.example.android.miwok;

/**
 * Created by Pulkit on 30-03-2017.
 */


public class Word {

   private String meword;

    private String mword;



    private int mid=NO_IMAGE;

    private int maid;

    private static final int NO_IMAGE=-1;

    public Word(String s1,String s2,int id,int ad)
    {
        meword=s1;
        mword=s2;
        mid=id;
        maid=ad;
    }

    public Word(String s1,String s2,int ad)
    {
        meword=s1;
        mword=s2;
        maid=ad;
    }
    public String getEword()
    {
        return meword;
    }

    public String getMword()
    {
        return mword;
    }

    public boolean hasImage(){
        return mid!=NO_IMAGE;
    }

    public int getId(){return mid;}

    public int getAd(){return maid;}

    @Override
    public String toString() {
        return "Word{" +
                "meword='" + meword + '\'' +
                ", mword='" + mword + '\'' +
                ", mid=" + mid +
                ", maid=" + maid +
                '}';
    }
}
