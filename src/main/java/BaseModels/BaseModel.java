package BaseModels;

import java.io.Serializable;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public class BaseModel implements Serializable{
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
