package com.bespalov.chatfirebase;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    private String senderUserIdFromChat;
    private FirebaseAuth auth;

    public MessageAdapter(@NonNull Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        auth = FirebaseAuth.getInstance();
        senderUserIdFromChat = auth.getCurrentUser().getUid();

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_item, parent, false);
        }

        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        TextView textTextVIewSender = convertView.findViewById(R.id.textTextViewSender);
        TextView nameTextVIewSender = convertView.findViewById(R.id.nameTextViewSender);
        TextView textTextViewRecipient = convertView.findViewById(R.id.textTextViewRecipient);
        TextView nameTextViewRecipient = convertView.findViewById(R.id.nameTextViewRecipient);
        CardView cardViewRecipient = convertView.findViewById(R.id.cardViewRecipient);
        CardView cardViewSender = convertView.findViewById(R.id.cardViewSender);

        Message message = getItem(position);

        Boolean isText = message.getImageUrl() == null;
        String id = message.getSender();
        if (isText) {
            textTextVIewSender.setVisibility(View.VISIBLE);
            textTextViewRecipient.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);

            if (senderUserIdFromChat.equals(id)) {
                cardViewRecipient.setVisibility(View.VISIBLE);
                cardViewSender.setVisibility(View.GONE);
                textTextViewRecipient.setText(message.getText());
                nameTextViewRecipient.setText(message.getName());
                            }  else {
                cardViewRecipient.setVisibility(View.GONE);
                cardViewSender.setVisibility(View.VISIBLE);
                textTextVIewSender.setText(message.getText());
                nameTextVIewSender.setText(message.getName());
            } }

                       else {
                textTextVIewSender.setVisibility(View.GONE);
                textTextViewRecipient.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext()).load(message.getImageUrl()).into(photoImageView);
            if (senderUserIdFromChat.equals(id)) {
                cardViewRecipient.setVisibility(View.VISIBLE);
                cardViewSender.setVisibility(View.GONE);
                nameTextViewRecipient.setText(message.getName());
            }  else {
                cardViewRecipient.setVisibility(View.GONE);
                cardViewSender.setVisibility(View.VISIBLE);
                nameTextVIewSender.setText(message.getName());
            }

            }



        return convertView;
    }


}
