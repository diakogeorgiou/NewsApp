package uk.co.advmob.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                } else {
                    //Login
                    new ApiConnection(getApplicationContext()).login(etUsername.getText().toString(), etPassword.getText().toString(), new ApiCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject jsonObject) {
                            //Convert json to response object
                            Response response = new Gson().fromJson(jsonObject.toString(), Response.class);

                            if (response.isError()) {
                                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            } else {
                                setResult(RESULT_OK);
                                finish();

                                LinkedTreeMap<String, String> data = (LinkedTreeMap<String, String>) response.getDataObject();
                                String email = data.get("email");
                                Toast.makeText(LoginActivity.this, "You are logged in as " + email, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//
//                    finish();
                }
            }
        });
    }
}
