package studios.luxurious.igovern.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.adapters.ReportingAdapter;
import studios.luxurious.igovern.utils.Post;

public class ExploreFragment extends Fragment {

    RecyclerView recyclerView;
    private ReportingAdapter reportingAdapter;
    private List<Post> postItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        postItems = new ArrayList<>();
        reportingAdapter = new ReportingAdapter(getActivity(),postItems);
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(reportingAdapter);
        loadAllMyPosts();
        return view;
    }

    private void loadAllMyPosts() {
        postItems.add(new Post("Garbage Collection", "Kefinco KAKAMEGA", "This is a sample message here jher rutg erhgurg rhguier erue","problem","received"));
        postItems.add(new Post("Garbage Collection", "Kefinco KAKAMEGA", "This is a sample message here jher rutg erhgurg rhguier erue","problem","received"));
        postItems.add(new Post("Garbage Collection", "Kefinco KAKAMEGA", "This is a sample message here jher rutg erhgurg rhguier erue","problem","received"));
        postItems.add(new Post("Garbage Collection", "Kefinco KAKAMEGA", "This is a sample message here jher rutg erhgurg rhguier erue","problem","received"));

        reportingAdapter.notifyDataSetChanged();
    }

}
