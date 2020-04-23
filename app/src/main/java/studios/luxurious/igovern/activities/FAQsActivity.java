package studios.luxurious.igovern.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.expandablelayout.ExpandableLayout;
import com.skydoves.expandablelayout.OnExpandListener;

import studios.luxurious.igovern.R;

public class FAQsActivity extends AppCompatActivity {

    ExpandableLayout expandableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expandable);

        expandableLayout1 = findViewById(R.id.expandable1);

        setExpandableLayout1(expandableLayout1);

    }

    private void setExpandableLayout1(final ExpandableLayout expandableLayout) {

        expandableLayout.setOnExpandListener(new OnExpandListener() {
            @Override
            public void onExpand(boolean b) {

                if (b)
                    Toast.makeText(FAQsActivity.this, "Expanded", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(FAQsActivity.this, "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableLayout.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.isExpanded()){
                    expandableLayout.collapse();
                }else {
                    expandableLayout.expand();
                }
            }
        });

    }
}
