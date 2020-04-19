package studios.luxurious.igovern.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.HomeMenu;

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private List<HomeMenu> homeMenus;
    private static ClickListener clickListener;


    public HomeMenuAdapter(List<HomeMenu> homeMenus) {
        this.homeMenus = homeMenus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_menu_holder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeMenu homeMenu = homeMenus.get(position);

        holder.logoIcon.setImageDrawable(homeMenu.getDrawable());
        holder.titleext.setText(homeMenu.getTitle());
        holder.gridBackGround.setCardBackgroundColor(homeMenu.getColor());




    }

    @Override
    public int getItemCount() {
        return homeMenus.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleext;
        private ImageView logoIcon;
        private CardView gridBackGround;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            logoIcon = itemView.findViewById(R.id.menuIcon);
            titleext = itemView.findViewById(R.id.menu_title);
            gridBackGround = itemView.findViewById(R.id.gridView_cardView);

        }

        @Override
        public void onClick(View v) {

            clickListener.onItemClick(getAdapterPosition(), v);

        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        HomeMenuAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
