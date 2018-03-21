package uk.co.advmob.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Button btnSelectPicture = findViewById(R.id.btnUploadphoto);
        final EditText etName = findViewById(R.id.editboxName);
        final EditText etEmail = findViewById(R.id.editboxEmail);
        final EditText etPassword = findViewById(R.id.editboxPassword);
        final EditText etAccType = findViewById(R.id.editboxAccType);
        final ImageView imgviewProfileimage = findViewById(R.id.imgviewProfileimage);

        final Button btnEditDetails = findViewById(R.id.btnEditprofile);
        final Button btnLikedArticles = findViewById(R.id.btnLikedArticles);
        final Button btnReportedArticles = findViewById(R.id.btnReportedarticals);

        etName.setText(SingleSignOn.getFullName());
        etEmail.setText(SingleSignOn.getEmail());
        etPassword.setText("");
        etAccType.setText(SingleSignOn.getUserType());

        Picasso.with(this).invalidate("http://newsapi.dkode.co.uk/uploads/" + SingleSignOn.getProfilePicture());
        Picasso.with(this)
                .load("http://newsapi.dkode.co.uk/uploads/" + SingleSignOn.getProfilePicture())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imgviewProfileimage);

        //Liked articles
        btnLikedArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent likedArticles = new Intent(ProfileActivity.this, LikedArticlesActivity.class);
                startActivity(likedArticles);
            }
        });

        //Fake articles
        btnReportedArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fakeArticles = new Intent(ProfileActivity.this, FakeArticlesActivity.class);
                startActivity(fakeArticles);
            }
        });

        //Select picture
        btnSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Select picture
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
            }
        });

        final ApiConnection conn = new ApiConnection(this);
//        conn.login(SingleSignOn.getEmail(), SingleSignOn.getPassword(), cbApiCallbackLogin);
        //Save profile
        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEditDetails.getText().toString() != "Apply") {
                    btnSelectPicture.setEnabled(true);
                    etName.setEnabled(true);
                    etEmail.setEnabled(true);
                    etPassword.setEnabled(true);
                    btnEditDetails.setText("Apply");
                } else {
                    //Check password
                    if (TextUtils.isEmpty(etPassword.getText())) {
                        Toast.makeText(ProfileActivity.this, "Please enter a password.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    btnSelectPicture.setEnabled(false);
                    etName.setEnabled(false);
                    etEmail.setEnabled(false);
                    etPassword.setEnabled(false);
                    btnEditDetails.setText("Edit Details");

                    //Get bitmap from imageview
                    BitmapDrawable drawable = (BitmapDrawable) imgviewProfileimage.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();

                    conn.update_user(SingleSignOn.getEmail(), SingleSignOn.getPassword(),
                            etEmail.getText().toString(), etPassword.getText().toString(),
                            etName.getText().toString(), bitmap, new ApiCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject jsonObject) {
                                    try {
                                        if (!jsonObject.getBoolean("error")) {
                                            SingleSignOn.setFullName(etName.getText().toString());
                                            SingleSignOn.setEmail(etEmail.getText().toString());
                                            SingleSignOn.setPassword(etPassword.getText().toString());

                                            Picasso.with(ProfileActivity.this).invalidate("http://newsapi.dkode.co.uk/uploads/" + SingleSignOn.getProfilePicture());
                                            Toast.makeText(ProfileActivity.this, "Changes successfully saved.", Toast.LENGTH_SHORT).show();
//                            conn.login(SingleSignOn.getEmail(), SingleSignOn.getPassword(), cbApiCallbackLogin);
                                        } else {
                                            Toast.makeText(ProfileActivity.this, jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
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

