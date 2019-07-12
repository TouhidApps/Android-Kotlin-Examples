package com.touhidapps.firestorechatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private EditText editTextMessage;
    private Button buttonSend;

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> stringList = new ArrayList<>();
    private static String tempUserName = "T";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);

        listView.setAdapter(arrayAdapter);

        // Demo data
//        stringList.add("lkdjfs");
//        stringList.add("urur");
//        stringList.add("ukit7urdf");
//        stringList.add("lkdjfs");
//        stringList.add("urur");
//        stringList.add("ukit7urdf");

        showAlertForUserName();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editTextMessage.getText().toString();
                if (!msg.isEmpty()) {
//                    stringList.add(msg);
//                    arrayAdapter.notifyDataSetChanged();
                    editTextMessage.setText(""); // clear text after send message

                    // insert to firestore
                    addDataToFirestore(new MessageModel(tempUserName, msg));
                }
            }
        });


    } // onCreate


    private void showAlertForUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert username");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton("Start chat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fireStoreCode();
                tempUserName = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        builder.show();
    } // showAlertForUserName


    private CollectionReference dbCollRef;
    private DocumentReference dbDocRef;

    private void fireStoreCode() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        dbCollRef = db.collection("ChatDB");
        dbDocRef = dbCollRef.document("ChatRooms");

        dbDocRef.collection("PublicChat").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // if error is not null then everything is not ok
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                    Log.d(TAG, "Current data: " + queryDocumentSnapshots.getDocumentChanges());

                    List<DocumentChange> dc = queryDocumentSnapshots.getDocumentChanges();
                    for (DocumentChange d : dc) {
                        Log.d(TAG, "onEvent: " + d.getDocument().getData());

                        String uName = String.valueOf(d.getDocument().getData().get("user"));
                        String uMessage = String.valueOf(d.getDocument().getData().get("message"));

                        MessageModel mm = new MessageModel(uName, uMessage); // If we have custom recyclerView and list of pojo then we need this

                        stringList.add(mm.user + ": " + mm.message);
                    }

                    arrayAdapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


    } // fireStoreCode

    private void addDataToFirestore(MessageModel messageModel) {

        dbDocRef.collection("PublicChat") // ex. PublicChat or PrivateChat or any path name
                .add(messageModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    } // addDataToFirestore


}
