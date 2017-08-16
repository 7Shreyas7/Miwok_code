package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    int color;

    public WordAdapter(Activity context,ArrayList<Word> wordFlavours,int colour)
    {
        super(context,0,wordFlavours);
        color=colour;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView= convertView;

        if(convertView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        /*View li = listItemView.findViewById(R.id.second_linear);
        int clr = ContextCompat.getColor(getContext(),color);
        li.setBackgroundColor(clr);*/

        Word currentword=getItem(position);

        TextView tv1 = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        tv1.setText(currentword.getMiwokTranslation());

        TextView tv2 = (TextView)listItemView.findViewById(R.id.default_text_view);
        tv2.setText(currentword.getDefaultTranslation());

        ImageView iv = (ImageView)listItemView.findViewById(R.id.image_text);

        LinearLayout li = (LinearLayout)listItemView.findViewById(R.id.second_linear);
        int clr = ContextCompat.getColor(getContext(),color);
        li.setBackgroundColor(clr);

        if(currentword.hasImageResource())
        {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(currentword.getImageResourceId());
        }
        else
        {
            iv.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
