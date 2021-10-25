package com.bespalov.chatfirebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHolder>  {

    private ArrayList<User> users;
    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onUserClick(int position);
    }

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public UserAdapter(OnUserClickListener listener) {
    }

    public void setOnUserClickListener(OnUserClickListener listener) {
        this.listener = listener;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)  {
        User currentUser = users.get(position);
        Boolean userHasAPhoto = currentUser.getUserPhotoUri() == null;
        if (userHasAPhoto) {
            holder.avatarImageView.setVisibility(View.VISIBLE);
            holder.avatarImageViewToUserPhoto.setVisibility(View.GONE);
            Glide.with(holder.avatarImageView.getContext()).clear(holder.avatarImageView);
            holder.avatarImageView.setImageResource(currentUser.getAvatarMoskUpResource());
        } else {
            holder.avatarImageView.setVisibility(View.GONE);
            holder.avatarImageViewToUserPhoto.setVisibility(View.VISIBLE);
            Picasso.get().load(currentUser.getUserPhotoUri()).
                    placeholder(R.drawable
                            .ic_baseline_insert_photo_24).
                    fit().into(holder.avatarImageViewToUserPhoto);
                    }
        holder.userNameTextView.setText(currentUser.getName());
        ;

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatarImageView;
        public CircleImageView avatarImageViewToUserPhoto;
        public TextView userNameTextView;

        public UserViewHolder(@NonNull View itemView, final OnUserClickListener listener) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            avatarImageViewToUserPhoto = itemView.findViewById(R.id.avatarImageViewToUserPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onUserClick(position);
                        }
                    }
                }
            });
        }
    }
}
