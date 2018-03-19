package uk.co.advmob.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    new ApiConnection(LoginActivity.this).login(etUsername.getText().toString(), etPassword.getText().toString(), new ApiCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject jsonObject) {
                            //Convert json to response object

                            try {
                                if (jsonObject.getBoolean("error")) {
                                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                } else {
                                    JSONObject response = jsonObject.getJSONObject("dataObject");
                                    setResult(RESULT_OK);
                                    finish();

                                    Toast.makeText(LoginActivity.this, "You are logged in as " + response.getString("email"), Toast.LENGTH_SHORT).show();

                                    //Set user in singleton
                                    SingleSignOn.setUser_id(response.getInt("id"));
                                    SingleSignOn.setEmail(etUsername.getText().toString());
                                    SingleSignOn.setPassword(etPassword.getText().toString());
                                    SingleSignOn.setFullName(response.getString("fullName"));
                                    SingleSignOn.setUserType(response.getString("userType"));
                                    SingleSignOn.setProfilePicture(response.getString("profilePicture"));
                                }
                            } catch (Exception e) {

                            }
                        }
                    });
                }
            }
        });
    }
}
