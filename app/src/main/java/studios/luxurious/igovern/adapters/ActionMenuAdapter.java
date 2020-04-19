package studios.luxurious.igovern.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.ActionMenu;


public class ActionMenuAdapter extends RecyclerView.Adapter<ActionMenuAdapter.ViewHolder> {

    private List<ActionMenu> actionMenus;


    public ActionMenuAdapter(List<ActionMenu> actionMenus) {
        this.actionMenus = actionMenus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_holder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ActionMenu actionMenu = actionMenus.get(position);
        holder.actionName.setText(actionMenu.getActionName());
        holder.actionDesc.setText(actionMenu.getActionDesc());
        holder.actionIcon.setImageDrawable(actionMenu.getActionIcon());

    }

    @Override
    public int getItemCount() {
        return actionMenus.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView actionName,actionDesc;
        private ImageView actionIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actionName = itemView.findViewById(R.id.actionName);
            actionDesc = itemView.findViewById(R.id.actionDesc);
            actionIcon = itemView.findViewById(R.id.actionIcons);
        }
    }
}
