package studios.luxurious.igovern;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import studios.luxurious.igovern.adapters.Faqdapter;

public class Main3Activity extends AppCompatActivity {

    Faqdapter faq_adapter;

    ArrayList<FAQ_item> faq_items;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        faq_items = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        faq_items.add(new FAQ_item("Tillrerg", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Tillrerg", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Tillrerg", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Tillrerg", getString(R.string.text_stars)));
        faq_items.add(new FAQ_item("Tillrerg", getString(R.string.text_stars)));

        faq_adapter = new Faqdapter(faq_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(faq_adapter);


    }
}
