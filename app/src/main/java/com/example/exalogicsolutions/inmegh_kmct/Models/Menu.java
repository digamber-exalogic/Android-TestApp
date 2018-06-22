package com.example.exalogicsolutions.inmegh_kmct.Models;

public class Menu {

    /*
     * Default menu item name
     */

    private String mDefaultMenuItem;
    private int mImageResourceId;

    public Menu(String defaultMenuItem, int imageResourceId) {

        mDefaultMenuItem = defaultMenuItem;
        mImageResourceId = imageResourceId;
    }

    /*
     * Get default menu item name
     */


    public String getDefaultMenuItem() {
        return mDefaultMenuItem;
    }

    public int getmImageResourseId() {
        return mImageResourceId;
    }
}
