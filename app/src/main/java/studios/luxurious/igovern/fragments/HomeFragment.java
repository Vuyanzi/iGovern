package studios.luxurious.igovern.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.ArrayList;
import java.util.List;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.activities.MainActivity;
import studios.luxurious.igovern.adapters.ActionMenuAdapter;
import studios.luxurious.igovern.adapters.HomeMenuAdapter;
import studios.luxurious.igovern.utils.ActionMenu;
import studios.luxurious.igovern.utils.HomeMenu;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView, recyclerViewGrid;
    private List<HomeMenu> homeMenus;
    private List<ActionMenu> actionMenus;
    private ActionMenuAdapter actionMenuAdapter;
    private HomeMenuAdapter homeMenuAdapter;

    private Vibrator vibrator;

    private CFAlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initViews(view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(getActivity(), 3));


        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(actionMenuAdapter);

        recyclerViewGrid.setItemAnimator(null);
        recyclerViewGrid.setAdapter(homeMenuAdapter);

        setItemClickListeners();

        loadMoreInfo();
        loadMenuItems();

        return view;
    }

    private void setItemClickListeners() {

        homeMenuAdapter.setOnItemClickListener(new HomeMenuAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String tag = homeMenus.get(position).getTag();

                if (vibrator != null) {
                    vibrator.vibrate(50);
                }

                switch (tag) {

                    case "report_problem":
                        ((MainActivity)getActivity()).openBottomSheet();

                        break;
                    case "send_money":
//                        startActivity(new Intent(getActivity(), ContactsActivity.class));
                        break;
                    default:
                        Toast.makeText(getActivity(), tag, Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void loadMoreInfo() {

        actionMenus.add(new ActionMenu("Feedback", "Give us a feedback", getResources().getDrawable(R.drawable.ic_metrics)));
        actionMenus.add(new ActionMenu("FAQs", "Read our Frequently Asked Questions", getResources().getDrawable(R.drawable.ic_help)));
        actionMenus.add(new ActionMenu("About Us", "Know more about " + getString(R.string.app_name), getResources().getDrawable(R.drawable.ic_world)));
        actionMenus.add(new ActionMenu("Logout", "Logout from " + getString(R.string.app_name), getResources().getDrawable(R.drawable.ic_contact)));

        actionMenuAdapter.notifyDataSetChanged();
    }

    private void loadMenuItems() {

        homeMenus.add(new HomeMenu("Report a problem", "report_problem", getResources().getDrawable(R.drawable.ic_send_money), getResources().getColor(R.color.menu_purple)));
        homeMenus.add(new HomeMenu("Send a suggestion", "tag", getResources().getDrawable(R.drawable.ic_send_money), getResources().getColor(R.color.menu_orange)));
        homeMenus.add(new HomeMenu("View History", "tag", getResources().getDrawable(R.drawable.ic_credits), getResources().getColor(R.color.menu_pink)));
//        homeMenus.add(new HomeMenu("Buy Airtime", "tag", getResources().getDrawable(R.drawable.ic_send_money), getResources().getColor(R.color.menu_sky_blue_dark)));
//        homeMenus.add(new HomeMenu("Lipa stima", "tag", getResources().getDrawable(R.drawable.ic_credits), getResources().getColor(R.color.menu_yellow)));
//        homeMenus.add(new HomeMenu("iuji ", "tag", getResources().getDrawable(R.drawable.ic_credits), getResources().getColor(R.color.menu_green)));

        homeMenuAdapter.notifyDataSetChanged();

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewGrid = view.findViewById(R.id.recyclerViewGrid);
        homeMenus = new ArrayList<>();
        actionMenus = new ArrayList<>();
        actionMenuAdapter = new ActionMenuAdapter(actionMenus);
        homeMenuAdapter = new HomeMenuAdapter(homeMenus);



        if(getActivity() !=null)

    {
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

}


    private void showCFDialog() {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(getActivity());
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET);

        // Title and message
        builder.setTitle("Report a problem");
        builder.setMessage("In order to function properly, " + getString(R.string.app_name) + " needs the following permissions:\nPermission to access your Phone and Contacts.\nPermission to write to your phone storage.");


        builder.setTextGravity(Gravity.CENTER);

        builder.addButton("Allow Access", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                requestPermissions(permissions, 1);
                alertDialog.dismiss();
            }
        });

        builder.setHeaderView(R.layout.dialog_header_permission_layout);
//            headerVisibility = true;

        // Cancel on background tap
        builder.setCancelable(false);

        alertDialog = builder.show();

    }


}