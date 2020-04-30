package studios.luxurious.igovern;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RetrofitActivity6 extends AppCompatActivity {
    private Button submit;
    private ProgressDialog progressDialog;
    private String baseUrl;

    TextView textRetro;
    JsonObject suggestionJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit6);
        submit = findViewById(R.id.submit);
        textRetro = findViewById(R.id.textRetro);

        baseUrl = "https://igovern.herokuapp.com/";

        suggestionJson = new JsonObject();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", "ihiljho");
        jsonObject.addProperty("device", "test");
        jsonObject.addProperty("county", "ujikli");

        suggestionJson.add("suggestion", jsonObject);

        textRetro.setText(suggestionJson.toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                submitData(suggestionJson);
            }
        });
    }

    private void submitData(JsonObject jsonObject) {
        progressDialog = new ProgressDialog(RetrofitActivity6.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService service = retrofit.create(ApiService.class);
        Call<JsonObject> call = service.postData(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Post submitted Message: " +response.message(), Toast.LENGTH_LONG).show();

                    String response_string = response.body().toString();
                    try {
                        JSONObject obj = new JSONObject(response_string);

                        JSONObject jsonObject1 = obj.getJSONObject("data");

                        int id = jsonObject1.getInt("id");

                        textRetro.setText(id+"  "+ response.body().toString());


                    } catch (JSONException e) {
                        e.printStackTrace();

                        textRetro.setText("hr    "+e.toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                textRetro.setText("error "+t.getMessage());

            }

        });
    }

    private interface ApiService {
        @POST("api/suggestions")
        Call<JsonObject> postData(@Body JsonObject body);
    }

}