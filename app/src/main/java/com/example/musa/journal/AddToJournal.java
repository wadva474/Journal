package com.example.musa.journal;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.musa.journal.AppDataBase.Mood;
import com.example.musa.journal.ImageUriUtils.BitmapUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddToJournal extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE=2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String mood;
    String description;
    String videouri;
    String imageuri;
    static Uri videoUri;
    static Uri imageUri;
    private String mTempPhotoPath;
    private static final String FILE_PROVIDER_AUTHORITY = "com.example.android.fileprovider";




    @BindView(R.id.addMood) Button AddTaskButton;
    @BindView(R.id.mood) android.support.design.widget.TextInputEditText moodEditText;
    @BindView(R.id.description) android.support.design.widget.TextInputEditText DescriptionEditText;
    @BindView(R.id.uploadImage) Button uploadImageButton;
    @BindView(R.id.uploadVideo) Button uploadVideoButton;
    @BindView(R.id.videoView) VideoView playVideo;
    @BindView(R.id.imageView) ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_journal);
        ButterKnife.bind(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("moods").child(firebaseAuth.getCurrentUser().getUid());
        }

        @OnClick(R.id.uploadVideo)
        public void uploadVideo(View v) {
               Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
        }

        @OnClick(R.id.uploadImage)
        public void uploadImage(View v) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the temporary File where the photo should go
                File photoFile = null;
                try {
                    photoFile = BitmapUtils.createTempImageFile(this);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    ex.printStackTrace();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {

                    // Get the path of the temporary file
                    mTempPhotoPath = photoFile.getAbsolutePath();

                    // Get the content URI for the image file
                    Uri photoURI = FileProvider.getUriForFile(this,
                            FILE_PROVIDER_AUTHORITY,
                            photoFile);

                    // Add the URI so the camera can store the image
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                    // Launch the camera activity
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                }
            }
        }

        //puting into DataBase
        @OnClick(R.id.addMood)
        public void addMood(View v){
           Getters();
       if (!(TextUtils.isEmpty(mood))&& !(TextUtils.isEmpty(description))){
           String id =databaseReference.push().getKey();
           Mood mMood=new Mood(id,mood,description,imageuri,videouri);
           assert id != null;
           databaseReference.child(id).setValue(mMood);
           Toast.makeText(AddToJournal.this,"Mood Added",Toast.LENGTH_SHORT).show();
           finish();
        }
        else {
           Toast.makeText(AddToJournal.this, "Enter a valid Mood", Toast.LENGTH_SHORT).show();
       }
        }
        public void Getters(){
        if (moodEditText.getText()!=null && imageUri!=null ) {
            mood = moodEditText.getText().toString();
            description = DescriptionEditText.getText().toString();
            videouri = videoUri.toString();
            imageuri = imageUri.toString();
        }else {
            Toast.makeText(AddToJournal.this,"Please fill in the fields",Toast.LENGTH_SHORT).show();
        }
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();
            uploadVideoButton.setVisibility(View.GONE);
            playVideo.setVisibility(View.VISIBLE);
            playVideo.setVideoURI(videoUri);
            playVideo.setMediaController(new MediaController(this));
            playVideo.canPause();
        }
        else if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            imageUri=intent.getData();
            uploadImageButton.setVisibility(View.GONE);
            showImage.setVisibility(View.VISIBLE);
            Log.d("isImage",Boolean.toString(intent.getData() == null));
            Glide.with(this).load(imageUri).into(showImage);
        }
    }

}
