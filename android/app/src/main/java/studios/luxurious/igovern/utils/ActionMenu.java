package studios.luxurious.igovern.utils;

import android.graphics.drawable.Drawable;

public class ActionMenu {
    String actionName, actionDesc, tag;
    Drawable actionIcon;

    public ActionMenu(String actionName, String actionDesc, String tag, Drawable actionIcon) {
        this.actionName = actionName;
        this.actionDesc = actionDesc;
        this.actionIcon = actionIcon;
        this.tag = tag;
    }

    public String getActionName() {
        return actionName;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public Drawable getActionIcon() {
        return actionIcon;
    }

    public String getTag() {
        return tag;
    }
}
