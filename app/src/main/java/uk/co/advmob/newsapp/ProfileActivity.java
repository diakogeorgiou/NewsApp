package uk.co.advmob.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final EditText etName = findViewById(R.id.editboxName);
        final EditText etEmail = findViewById(R.id.editboxEmail);
        final EditText etPassword = findViewById(R.id.editboxPassword);
        final EditText etAccType = findViewById(R.id.editboxAccType);
        final ImageView imgviewProfileimage = findViewById(R.id.imgviewProfileimage);

        final Button btnEditDetails = findViewById(R.id.btnEditprofile);

        etName.setText(SingleSignOn.getFullName());
        etEmail.setText(SingleSignOn.getEmail());
        etPassword.setText("");
        etAccType.setText(SingleSignOn.getUserType());

        Picasso.with(this)
                .load("http://newsapi.dkode.co.uk/uploads/" + SingleSignOn.getProfilePicture())
                .into(imgviewProfileimage);


        final ApiCallback cbApiCallbackLogin = new ApiCallback() {
            @Override
            public void onSuccessResponse(JSONObject jsonObject) {
                //Convert json to response object
                Response response = new Gson().fromJson(jsonObject.toString(), Response.class);

                if (response.isError()) {
                    Toast.makeText(ProfileActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                } else {


//                    LinkedTreeMap<String, String> data = (LinkedTreeMap<String, String>) response.getDataObject();
//
//                    etName.setText(data.get("fullName"));
//                    etEmail.setText(data.get("email"));
//                    etPassword.setText("");
//                    etAccType.setText(data.get("userType"));


                }
            }
        };

        final ApiConnection conn = new ApiConnection(this);
        conn.login(SingleSignOn.getEmail(), SingleSignOn.getPassword(), cbApiCallbackLogin);
        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEditDetails.getText().toString() != "Apply") {
                    etName.setEnabled(true);
                    etEmail.setEnabled(true);
                    etPassword.setEnabled(true);
                    btnEditDetails.setText("Apply");
                } else {
                    etName.setEnabled(false);
                    etEmail.setEnabled(false);
                    etPassword.setEnabled(false);
                    btnEditDetails.setText("Edit Details");
                    conn.update_user(SingleSignOn.getEmail(), SingleSignOn.getPassword(), etEmail.getText().toString(), etPassword.getText().toString(), etName.getText().toString(), new ApiCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject jsonObject) {
                            SingleSignOn.setEmail(etEmail.getText().toString());
                            SingleSignOn.setPassword(etPassword.getText().toString());
                            conn.login(SingleSignOn.getEmail(), SingleSignOn.getPassword(), cbApiCallbackLogin);
                        }
                    });

                }
            }
        });
    }

}

