package studios.luxurious.igovern.utils;

import android.graphics.drawable.Drawable;

public class ActionMenu {
    String actionName, actionDesc;
    Drawable actionIcon;

    public ActionMenu(String actionName, String actionDesc, Drawable actionIcon) {
        this.actionName = actionName;
        this.actionDesc = actionDesc;
        this.actionIcon = actionIcon;
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
}
