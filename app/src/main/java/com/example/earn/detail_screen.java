package com.example.earn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class detail_screen extends AppCompatActivity {

    private void sendBitmap(Bitmap bitmap) {

        Uri imageToShare = Uri.parse(MediaStore.Images.Media.insertImage(this.
                getContentResolver(), bitmap, "Share_app" + Calendar.getInstance().getTime(), null));   // in case of fragment use [context].getContentResolver()
        String shareMessage = "Shop Now!!\nClick on the link below\nearn karo link";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_TEXT, shareMessage);
        share.putExtra(Intent.EXTRA_STREAM, imageToShare);
      startActivity(Intent.createChooser(share, "Share via"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        TextView profit = findViewById(R.id.profit);
        RelativeLayout copy = findViewById(R.id.copy_button);
        RelativeLayout share = findViewById(R.id.share_button);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", "Copied from earn karo");
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(detail_screen.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(detail_screen.this, "error occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView back = findViewById(R.id.profile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(detail_screen.this, offer_detail.class);
                startActivity(i);
            }
        });

    }
}