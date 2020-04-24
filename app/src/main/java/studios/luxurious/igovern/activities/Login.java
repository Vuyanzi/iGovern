package studios.luxurious.igovern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import studios.luxurious.igovern.R;
import studios.luxurious.igovern.utils.SharedPref;

public class Login extends AppCompatActivity {

    SharedPref sharedPref;

    TextView loginTextview;
    EditText usernameEdt;
    Button continueBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPref = new SharedPref(this);

        loginTextview = findViewById(R.id.loginTextView);
        usernameEdt = findViewById(R.id.username);
        loginTextview.setText(getString(R.string.usernameText));
        continueBtn = findViewById(R.id.next);

        usernameEdt.setText(sharedPref.getUserName());

        usernameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 2){
                    continueBtn.setEnabled(true);
                }else {
                    continueBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.setUserName(usernameEdt.getText().toString());
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }
        });

    }
}
