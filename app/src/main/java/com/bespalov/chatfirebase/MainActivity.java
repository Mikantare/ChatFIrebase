package com.bespalov.chatfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView messageListView;
    private MessageAdapter adapter;
    private ProgressBar progressBar;
    private Button sendMessageButtont;
    private ImageButton sentImageButton;
    private EditText messageEditText;

    private String userName;

    FirebaseDatabase database;
    DatabaseReference messagesDataBaseReference;

    ChildEventListener messagesChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        messagesDataBaseReference = database.getReference().child("messeges");
        userName = "default name";

        messageListView = findViewById(R.id.messageListView);
        progressBar = findViewById(R.id.progressBar);
        sendMessageButtont = findViewById(R.id.sendMessageButtont);
        sentImageButton = findViewById(R.id.sentImageButton);
        messageEditText = findViewById(R.id.messageEditText);
        List<Message> messages = new ArrayList<>();
        adapter = new MessageAdapter(this, R.layout.message_item, messages);
        messageListView.setAdapter(adapter);
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length()>0) {
                        sendMessageButtont.setEnabled(true);
                    } else {
                        sendMessageButtont.setEnabled(false);
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        messageEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(500)});

        sendMessageButtont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = messageEditText.getText().toString().trim();
                Message message = new Message();
                message.setText(text);
                message.setName(userName);
                message.setImageUrl(null);
                messagesDataBaseReference.push().setValue(message);
                messageEditText.setText("");
            }
        });

        sentImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        messagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                adapter.add(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        messagesDataBaseReference.addChildEventListener(messagesChildEventListener);
    }
}