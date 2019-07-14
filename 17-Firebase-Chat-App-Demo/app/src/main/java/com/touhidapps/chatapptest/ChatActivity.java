package com.touhidapps.chatapptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ListView listViewChatMessage;
    EditText editTextMessage;
    ImageButton imageButtonSendMessage;

    ArrayAdapter<String> chatListAdapter;
    ArrayList<String> chatList = new ArrayList<>();

    private DatabaseReference databaseReference;
    String tempKey, chatMessage, chatUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUI();

        final String roomName = getIntent().getStringExtra("ROOM_NAME");
        final String userName = getIntent().getStringExtra("USER_NAME");

        setTitle("Chat room: " + roomName);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(roomName);

        imageButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextMessage.getText().toString().isEmpty()) {
                    Map<String, Object> map = new HashMap<>();
                    tempKey = databaseReference.push().getKey();  // push will not work until updateChildren called
                    databaseReference.updateChildren(map);

                    DatabaseReference dbRef = databaseReference.child(tempKey);
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("NAME", userName);
                    map2.put("MESSAGE", editTextMessage.getText().toString());
                    dbRef.updateChildren(map2);
                } else {
                    Toast.makeText(ChatActivity.this, "Write something", Toast.LENGTH_SHORT).show();
                }

                editTextMessage.setText("");
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addChildConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                addChildConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initUI() {
        listViewChatMessage = findViewById(R.id.listViewChatMessage);
        editTextMessage = findViewById(R.id.editTextMessage);
        imageButtonSendMessage = findViewById(R.id.imageButtonSendMessage);

        chatListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatList);
        listViewChatMessage.setAdapter(chatListAdapter);
    }

    private void addChildConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            chatMessage = ((DataSnapshot) i.next()).getValue().toString();
            chatUserName = ((DataSnapshot) i.next()).getValue().toString();

            chatList.add("" + chatUserName + ": " + chatMessage);
        }
        chatListAdapter.notifyDataSetChanged();
    }


}
