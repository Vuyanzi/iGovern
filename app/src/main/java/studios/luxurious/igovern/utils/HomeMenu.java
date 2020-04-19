package studios.luxurious.igovern.utils;

import android.graphics.drawable.Drawable;

public class HomeMenu {

    private String title,tag;
    private Drawable drawable;
    private int color;

    public HomeMenu(String title, String tag, Drawable drawable, int color) {
        this.title = title;
        this.tag = tag;
        this.drawable = drawable;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public int getColor() {
        return color;
    }
}
