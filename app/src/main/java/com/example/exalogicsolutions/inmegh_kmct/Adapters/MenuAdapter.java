package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.Models.Menu;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter {

    private static final String LOG_TAG = MenuAdapter.class.getSimpleName();

    public MenuAdapter(Activity context, ArrayList<Menu> menuArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, menuArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_item, parent, false);
        }

        // Get the object located at this position in the list
        Menu currentItem = (Menu) getItem(position);

        // Find the TextView in the menu_item.xml layout with the ID version_name
        TextView myItem = listItemView.findViewById(R.id.text_list);
        // Get the version name from the current object and
        // set this text on the name TextView
        myItem.setText(currentItem.getDefaultMenuItem());

        // Find the ImageView in the list_item.xml layout with the ID version_number
        ImageView myItemImage = listItemView.findViewById(R.id.image_list);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number ImageView
        myItemImage.setImageResource(currentItem.getmImageResourseId());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
