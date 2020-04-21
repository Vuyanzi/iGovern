package studios.luxurious.igovern.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.adapters.ReportingAdapter;
import studios.luxurious.igovern.utils.DBAdapter;
import studios.luxurious.igovern.utils.Post;

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReportingAdapter reportingAdapter;
    private List<Post> postItems;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        searchView = view.findViewById(R.id.searchView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        postItems = getAllPosts();
        reportingAdapter = new ReportingAdapter(getActivity(),postItems);
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(reportingAdapter);

        setLayoutsVisibility(view, postItems.size());
        return view;
    }

    private ArrayList<Post> getAllPosts() {

        ArrayList<Post> fetchedPosts = new ArrayList<>();
        DBAdapter db = new DBAdapter(getActivity());
        db.open();
        Cursor cur = db.getAllPostData();

        cur.moveToFirst();
        if (cur.getCount() > 0) {
            while (!cur.isAfterLast()) {
                Post post = new Post(cur.getString(cur.getColumnIndexOrThrow(DBAdapter.TITLE_POSTS)),cur.getString(cur.getColumnIndexOrThrow(DBAdapter.LOCATION_POST)),cur.getString(cur.getColumnIndexOrThrow(DBAdapter.MESSAGE_POST)), cur.getInt(cur.getColumnIndexOrThrow(DBAdapter.TYPE_POST)), cur.getInt(cur.getColumnIndexOrThrow(DBAdapter.STATUS_POST)));
                fetchedPosts.add(post);
                cur.moveToNext();
            }
            db.close();

        }

        return fetchedPosts;

    }

    private void setLayoutsVisibility(View view, int size){

        LinearLayout noPostLayout = view.findViewById(R.id.noPostLayout);

        if (size > 0){
            noPostLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
        }else {

            noPostLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
        }
    }
}
