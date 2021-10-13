package com.bespalov.chatfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter <Message> {

    public MessageAdapter(@NonNull Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.message_item,parent,false);
        }

        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        TextView textTextVIew = convertView.findViewById(R.id.textTextView);
        TextView nameTextVIew = convertView.findViewById(R.id.nameTextView);

        Message message = getItem(position);

        Boolean isText = message.getImageUrl() == null;
        if (isText) {
            textTextVIew.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            textTextVIew.setText(message.getText());
        } else {
            textTextVIew.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext()).load(message.getImageUrl()).into(photoImageView);
        }
        nameTextVIew.setText(message.getName());

        return convertView;
    }
}
