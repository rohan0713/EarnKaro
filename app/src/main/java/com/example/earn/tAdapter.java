package com.example.earn;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class tAdapter extends RecyclerView.Adapter<tAdapter.tViewHolder> {

    List<offers> list;
    public tAdapter(List<offers> list){
        this.list = list;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_offers, parent, false);
        return new tViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {

        holder.Bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class tViewHolder extends RecyclerView.ViewHolder{

        public tViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void Bind(offers offers) {

            ImageView imageView = itemView.findViewById(R.id.logo);
            imageView.setBackgroundResource(offers.image);
            RelativeLayout copy = itemView.findViewById(R.id.copy_button);
            RelativeLayout share = itemView.findViewById(R.id.share_button);
            ImageView sh = itemView.findViewById(R.id.share);

            sh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri imageUri = (new Uri.Builder())
                            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                            .authority(itemView.getResources().getResourcePackageName(offers.image))
                            .appendPath(itemView.getResources().getResourceTypeName(offers.image))
                            .appendPath(itemView.getResources().getResourceEntryName(offers.image))
                            .build();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(itemView.getContext().getContentResolver(), imageUri);
                        sendBitmap(bitmap);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        Toast.makeText(itemView.getContext(), "error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri imageUri = (new Uri.Builder())
                            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                            .authority(itemView.getResources().getResourcePackageName(offers.image))
                            .appendPath(itemView.getResources().getResourceTypeName(offers.image))
                            .appendPath(itemView.getResources().getResourceEntryName(offers.image))
                            .build();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(itemView.getContext().getContentResolver(), imageUri);
                        sendBitmap(bitmap);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        Toast.makeText(itemView.getContext(), "error occurred", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ClipboardManager clipboardManager = (ClipboardManager) itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("text", "Copied from earn karo");
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(itemView.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), detail_screen.class);
                    itemView.getContext().startActivity(i);
                }
            });
        }

        private void sendBitmap(Bitmap bitmap) {

            Uri imageToShare = Uri.parse(MediaStore.Images.Media.insertImage(itemView.getContext().
                    getContentResolver(), bitmap, "Share_app" + Calendar.getInstance().getTime(), null));   // in case of fragment use [context].getContentResolver()
            String shareMessage = "Shop Now!!\nClick on the link below\nearn karo link";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_TEXT, shareMessage);
            share.putExtra(Intent.EXTRA_STREAM, imageToShare);
            itemView.getContext().startActivity(Intent.createChooser(share, "Share via"));
        }

    }
}
