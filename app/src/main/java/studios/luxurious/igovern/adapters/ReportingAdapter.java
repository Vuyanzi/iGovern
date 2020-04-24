package studios.luxurious.igovern.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.activities.FullPost;
import studios.luxurious.igovern.utils.Constants;
import studios.luxurious.igovern.utils.Post;
import studios.luxurious.igovern.utils.SharedPref;


public class ReportingAdapter extends RecyclerView.Adapter<ReportingAdapter.ViewHolder> {

    private List<Post> postItems;
    private Context context;
    SharedPref sharedPref;


    public ReportingAdapter(Context context,List<Post> postItems) {
        this.postItems = postItems;
        this.context = context;
        sharedPref = new SharedPref(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_single_item_holder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Post postItem = postItems.get(position);


        String title = postItem.getTitle();

        if (postItem.getType().equals(Constants.PROBLEM_TYPE_STRING)){
            title = title + "(Problem)";
        }else {
            title = title + "(Suggestion)";
        }
        holder.post_title.setText(title);
        holder.post_location.setText(postItem.getCounty());
        holder.post_message.setText(postItem.getContent());

        holder.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent full_post = new Intent(context, FullPost.class);
                full_post.putExtra("author", sharedPref.getUserName() );
                full_post.putExtra("date", "date_not_set");
                full_post.putExtra("content", postItem.getContent());
                full_post.putExtra("county", postItem.getCounty());
                full_post.putExtra("title", postItem.getTitle());
                full_post.putExtra("type", postItem.getType());
                full_post.putExtra("status", postItem.getStatus());
                context.startActivity(full_post);

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
