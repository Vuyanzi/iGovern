package studios.luxurious.igovern.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.adapters.ReportingAdapter;
import studios.luxurious.igovern.utils.Constants;
import studios.luxurious.igovern.utils.Post;

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReportingAdapter reportingAdapter;
    private List<Post> postItems;
    private TextView date_txt;

    private LinearLayout noPostLayout;
    private RelativeLayout recyclerHolder;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerHolder = view.findViewById(R.id.recyclerHolder);

        date_txt = view.findViewById(R.id.date);

        progressBar = view.findViewById(R.id.progressBar);

        date_txt.setText(Constants.getDate());

         noPostLayout = view.findViewById(R.id.noPostLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        postItems = new ArrayList<>();
        reportingAdapter = new ReportingAdapter(getActivity(),postItems);
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(reportingAdapter);
        fetchData();
        return view;
    }


    private void setLayoutsVisibility( int size){


        if (size > 0){
            noPostLayout.setVisibility(View.GONE);
            recyclerHolder.setVisibility(View.VISIBLE);
        }else {

            noPostLayout.setVisibility(View.VISIBLE);
            recyclerHolder.setVisibility(View.GONE);
        }
    }


    private void fetchData(){

        progressBar.setVisibility(View.VISIBLE);
        setLayoutsVisibility(1);

        String device_id = Constants.getUniqueDeviceId(getActivity());
        String URLline = Constants.BASE_URL+ "api/suggestions?device="+device_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response == null) {
                    Toast.makeText(getActivity(), "Response was null", Toast.LENGTH_LONG).show();
                    return;
                }
                parseData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                dialogError("Error", "Error connecting to the server. Please check your internet connection and try again.");

            }
        });


        if (getActivity() != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }
    }

    private void parseData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);

                    JSONArray suggestions = jsonObject.getJSONArray("data");


            setLayoutsVisibility(suggestions.length());

                    for (int i = 0; i < suggestions.length(); i++) {
                        JSONObject un = suggestions.getJSONObject(i);

                        String id = un.getString("id");
                        String content = un.getString("content");
                        String county = un.getString("county");
                        String device = un.getString("device");
                        String status = un.getString("status");
                        String title = un.getString("title");
                        String type = un.getString("type");

                        postItems.add(new Post(id,content,county, device,status,title,type));



                    }

                    reportingAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void dialogError(String title, String message) {

        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    ExploreFragment.this.fetchData();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

}
