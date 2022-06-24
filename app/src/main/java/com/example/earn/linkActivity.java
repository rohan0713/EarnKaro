package com.example.earn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class linkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        ImageView back = findViewById(R.id.profile);
        Button make = findViewById(R.id.get_link);

        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog;
                AlertDialog.Builder alert = new AlertDialog.Builder(linkActivity.this);
                View v = getLayoutInflater().inflate(R.layout.link_dialog, null);
                alert.setView(v);
                alertDialog = alert.create();
                alertDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.WHITE)
                );
                RelativeLayout share = v.findViewById(R.id.share_button);
                RelativeLayout copy = v.findViewById(R.id.copy_button);

                alertDialog.show();

                alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                Window window = alertDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("text", "Copied from earn karo");
                        clipboardManager.setPrimaryClip(clipData);

                        Toast.makeText(linkActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                    }
                });

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri imageUri = (new Uri.Builder())
                                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                                .authority(getResources().getResourcePackageName(R.drawable.flipkart))
                                .appendPath(getResources().getResourceTypeName(R.drawable.flipkart))
                                .appendPath(getResources().getResourceEntryName(R.drawable.flipkart))
                                .build();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            sendBitmap(bitmap);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            Toast.makeText(linkActivity.this, "error occurred", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void sendBitmap(Bitmap bitmap) {

        Uri imageToShare = Uri.parse(MediaStore.Images.Media.insertImage(
                getContentResolver(), bitmap, "Share_app" + Calendar.getInstance().getTime(), null));   // in case of fragment use [context].getContentResolver()
        String shareMessage = "Shop Now!!\nClick on the link below\nearn karo link";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_TEXT, shareMessage);
        share.putExtra(Intent.EXTRA_STREAM, imageToShare);
        startActivity(Intent.createChooser(share, "Share via"));
    }
}