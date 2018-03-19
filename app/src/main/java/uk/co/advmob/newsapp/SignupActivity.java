package uk.co.advmob.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final TextView etEmail = findViewById(R.id.etEmail);
        final TextView etPassword = findViewById(R.id.etPassword);
        final TextView etRepeatPassword = findViewById(R.id.etRepeatPassword);
        final TextView etFullName = findViewById(R.id.etFullName);
        final CheckBox cbJournalist = findViewById(R.id.cbJournalist);
        final ImageView imgProfile = findViewById(R.id.imgviewProfileimage);

        //Save
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check email
                if (TextUtils.isEmpty(etEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()) {
                    Toast.makeText(SignupActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check password
                if (!TextUtils.equals(etPassword.getText(), etRepeatPassword.getText())) {
                    Toast.makeText(SignupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check name
                if (TextUtils.isEmpty(etFullName.getText())) {
                    Toast.makeText(SignupActivity.this, "Please type your full name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Get userType
                String userType;
                if (cbJournalist.isChecked()) {
                    userType = "JOURNALIST";
                } else {
                    userType = "READER";
                }

                //Get bitmap from imageview
                BitmapDrawable drawable = (BitmapDrawable) imgProfile.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                new ApiConnection(SignupActivity.this).register(etEmail.getText().toString(), etPassword.getText().toString(), etFullName.getText().toString(), userType, bitmap, new ApiCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject jsonObject) {
                        //Convert json to response object
                        Response response = new Gson().fromJson(jsonObject.toString(), Response.class);

                        if (response.isError()) {
                            Toast.makeText(SignupActivity.this, response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            Toast.makeText(SignupActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Select picture
        Button btnUploadPicture = findViewById(R.id.btnUploadPicture);
        btnUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ImageView imgProfile = findViewById(R.id.imgviewProfileimage);
                imgProfile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
