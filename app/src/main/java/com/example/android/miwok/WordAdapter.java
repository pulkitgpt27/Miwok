package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pulkit on 30-03-2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param word A List of AndroidFlavor objects to display in a list
     */

    int mcolor;
    public WordAdapter(Activity context, ArrayList<Word> word,int c) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, word);
        mcolor=c;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        View contain=listItemView.findViewById(R.id.container);
        int color= ContextCompat.getColor(getContext(),mcolor);
        contain.setBackgroundColor(color);

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word curword = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(curword.getMword());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishTextView.setText(curword.getEword());

        ImageView iv=(ImageView)listItemView.findViewById(R.id.image);
        if(curword.hasImage()) {
            iv.setImageResource(curword.getId());
        iv.setVisibility(View.VISIBLE);
        }
        else
        iv.setVisibility(View.GONE);




        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
