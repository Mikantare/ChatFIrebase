package com.bespalov.chatfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    private List<Message> messages;
    private Activity activity;

    public MessageAdapter(@NonNull Activity context, int resource, List<Message> messages) {
        super(context, resource, messages);

        this.messages = messages;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        Message message = getItem(position);
        int layoutRecorce = 0;
        int viewType = getItemViewType(position);
        if (viewType ==0 ) {
            layoutRecorce = R.layout.my_message_item;
        } else {
            layoutRecorce = R.layout.your_message_item;
        }
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
         } else {
            convertView = layoutInflater.inflate(layoutRecorce,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        boolean isText = message.getImageUrl() == null;

        if (isText) {
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.messageTextView.setVisibility(View.VISIBLE);
            viewHolder.messageTextView.setText(message.getText());
        } else {
            viewHolder.photoImageView.setVisibility(View.VISIBLE);
            viewHolder.messageTextView.setVisibility(View.GONE);
            Glide.with(viewHolder.photoImageView.getContext()).load(message.getImageUrl()).into(viewHolder.photoImageView);
        }
//        if (convertView == null) {
//            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_item, parent, false);
//        }
//
//        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
//        TextView textTextVIewSender = convertView.findViewById(R.id.textTextViewSender);
//        TextView nameTextVIewSender = convertView.findViewById(R.id.nameTextViewSender);
//        TextView textTextViewRecipient = convertView.findViewById(R.id.textTextViewRecipient);
//        TextView nameTextViewRecipient = convertView.findViewById(R.id.nameTextViewRecipient);
//        CardView cardViewRecipient = convertView.findViewById(R.id.cardViewRecipient);
//        CardView cardViewSender = convertView.findViewById(R.id.cardViewSender);
//
//
//
//        Boolean isText = message.getImageUrl() == null;
//        String id = message.getSender();
//        if (isText) {
//            textTextVIewSender.setVisibility(View.VISIBLE);
//            textTextViewRecipient.setVisibility(View.VISIBLE);
//            photoImageView.setVisibility(View.GONE);
//
//            if (message.isMine()) {
//                cardViewRecipient.setVisibility(View.VISIBLE);
//                cardViewSender.setVisibility(View.GONE);
//                textTextViewRecipient.setText(message.getText());
//                nameTextViewRecipient.setText(message.getName());
//            } else {
//                cardViewRecipient.setVisibility(View.GONE);
//                cardViewSender.setVisibility(View.VISIBLE);
//                textTextVIewSender.setText(message.getText());
//                nameTextVIewSender.setText(message.getName());
//            }
//        } else {
//            textTextVIewSender.setVisibility(View.GONE);
//            textTextViewRecipient.setVisibility(View.GONE);
//            photoImageView.setVisibility(View.VISIBLE);
//            Glide.with(photoImageView.getContext()).load(message.getImageUrl()).into(photoImageView);
//            if (message.isMine()) {
//                cardViewRecipient.setVisibility(View.VISIBLE);
//                cardViewSender.setVisibility(View.GONE);
//                nameTextViewRecipient.setText(message.getName());
//            } else {
//                cardViewRecipient.setVisibility(View.GONE);
//                cardViewSender.setVisibility(View.VISIBLE);
//                nameTextVIewSender.setText(message.getName());
//            }
//        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        int flag;
        Message message = messages.get(position);
        if (message.isMine()) {
            flag = 0;
        } else {
            flag =1;
        } return flag;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private class ViewHolder  {

        private ImageView photoImageView;
        private TextView messageTextView;

        public ViewHolder(View view) {
            photoImageView = view.findViewById(R.id.photoImageView);
            messageTextView = view.findViewById(R.id.messageTextView);
        }
    }


}
