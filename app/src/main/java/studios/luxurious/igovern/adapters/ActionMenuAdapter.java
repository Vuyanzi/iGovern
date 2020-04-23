package studios.luxurious.igovern.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studios.luxurious.igovern.activities.FaqActivity;
import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.ActionMenu;


public class ActionMenuAdapter extends RecyclerView.Adapter<ActionMenuAdapter.ViewHolder> {

    private List<ActionMenu> actionMenus;

    Context context;

    public ActionMenuAdapter(List<ActionMenu> actionMenus, Context c) {
        this.actionMenus = actionMenus;
        context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_holder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        ActionMenu actionMenu = actionMenus.get(position);
        holder.actionName.setText(actionMenu.getActionName());
        holder.actionDesc.setText(actionMenu.getActionDesc());
        holder.actionIcon.setImageDrawable(actionMenu.getActionIcon());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = actionMenus.get(position).getTag();


                switch (tag){

                    case "faqs":
                        context.startActivity(new Intent(context, FaqActivity.class));

                        break;

                    default:

                        Toast.makeText(context, tag, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return actionMenus.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView actionName,actionDesc;
        private ImageView actionIcon;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actionName = itemView.findViewById(R.id.actionName);
            actionDesc = itemView.findViewById(R.id.actionDesc);
            actionIcon = itemView.findViewById(R.id.actionIcons);
            linearLayout = itemView.findViewById(R.id.lineae);
        }

    }

}
