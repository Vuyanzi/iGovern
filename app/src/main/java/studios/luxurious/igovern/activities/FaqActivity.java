package studios.luxurious.igovern.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import studios.luxurious.igovern.FAQ_item;
import studios.luxurious.igovern.R;
import studios.luxurious.igovern.adapters.Faqdapter;

public class FaqActivity extends AppCompatActivity {

    Faqdapter faq_adapter;

    ArrayList<FAQ_item> faq_items;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        faq_items = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        faq_items.add(new FAQ_item("DO you share my information with other people", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Ho do i know whether my suggestion has been worked on", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Can I have multiple account in this app", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Can I use a different language in this app", getString(R.string.text_stars)));

        faq_adapter = new Faqdapter(faq_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(faq_adapter);


    }
}
