package com.touhidapps.chatapptest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatRoomActivity extends AppCompatActivity {

    ListView listViewChatRoom;
    EditText editTextRoomName;
    Button buttonAddChatRoom;

    ArrayAdapter<String> roomListAdapter;
    ArrayList<String> roomList = new ArrayList<>();
    String userName;


    /**


    {
        "rules": {
            ".read": true,
            ".write": true
        }
    }

    **/



    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listViewChatRoom = findViewById(R.id.listViewChatRoom);
        editTextRoomName = findViewById(R.id.editTextRoomName);
        buttonAddChatRoom = findViewById(R.id.buttonAddChatRoom);

        roomListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);
        listViewChatRoom.setAdapter(roomListAdapter);

        requestUserName();

        buttonAddChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put(editTextRoomName.getText().toString(), "");
                databaseReference.updateChildren(map);

                editTextRoomName.setText("");
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                roomList.clear();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    roomList.add(((DataSnapshot) i.next()).getKey());
                }
                roomListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewChatRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("ROOM_NAME", ((TextView) view).getText().toString());
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });

    }

    private void requestUserName() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name: ");
        final EditText editTextInputName = new EditText(this);
        builder.setView(editTextInputName);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = editTextInputName.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                requestUserName();
            }
        });
        builder.show();
    }
}
