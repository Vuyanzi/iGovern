package studios.luxurious.igovern.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.Constants;
import studios.luxurious.igovern.utils.Post;


public class ReportingAdapter extends RecyclerView.Adapter<ReportingAdapter.ViewHolder> {

    private List<Post> postItems;
    private Context context;


    public ReportingAdapter(Context context,List<Post> postItems) {
        this.postItems = postItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_single_item_holder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post postItem = postItems.get(position);


        String title = postItem.getTitle();

        if (postItem.getType() == Constants.PROBLEM_TYPE){
            title = title + "(Problem)";
        }else {
            title = title + "(Suggestion)"+postItem.getType()+ Constants.PROBLEM_TYPE;
        }
        holder.post_title.setText(title);
        holder.post_location.setText(postItem.getLocation());
        holder.post_message.setText(postItem.getMessage());

        holder.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Next clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView post_title,post_location,post_message;
        private ImageButton nextButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            post_location = itemView.findViewById(R.id.post_location);
            post_message = itemView.findViewById(R.id.post_message);
            nextButton = itemView.findViewById(R.id.fullSizeStory);
        }
    }
}
