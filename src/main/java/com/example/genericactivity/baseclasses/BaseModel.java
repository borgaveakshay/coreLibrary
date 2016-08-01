package com.example.genericactivity.baseclasses;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public class BaseModel {
    boolean selected;
    int selectedImageResource;

    public int getSelectedImageResource() {
        return selectedImageResource;
    }

    public void setSelectedImageResource(int selectedImageResource) {
        this.selectedImageResource = selectedImageResource;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
