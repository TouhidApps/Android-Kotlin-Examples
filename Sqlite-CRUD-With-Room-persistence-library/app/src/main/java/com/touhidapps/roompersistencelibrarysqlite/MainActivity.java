package com.touhidapps.roompersistencelibrarysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.touhidapps.roompersistencelibrarysqlite.room.UserEntity;
import com.touhidapps.roompersistencelibrarysqlite.room.UsersDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonSave, buttonUpdate, buttonDelete, buttonShow;
    EditText editTextSaveName, editTextSavePhone, editTextUpdateId,
            editTextUpdateName, editTextUpdatePhone, editTextDeleteId;
    ListView listView;
    List<String> users = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    UsersDatabase usersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        usersDatabase = UsersDatabase.getInstance(this);

    }

    private void initUI() {
        buttonSave = findViewById(R.id.buttonSave);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonShow = findViewById(R.id.buttonShow);

        buttonSave.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonShow.setOnClickListener(this);

        editTextSaveName = findViewById(R.id.editTextSaveName);
        editTextSavePhone = findViewById(R.id.editTextSavePhone);
        editTextUpdateId = findViewById(R.id.editTextUpdateId);
        editTextUpdateName = findViewById(R.id.editTextUpdateName);
        editTextUpdatePhone = findViewById(R.id.editTextUpdatePhone);
        editTextDeleteId = findViewById(R.id.editTextDeleteId);

        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                usersDatabase.userDao().insertUser(new UserEntity(editTextSaveName.getText().toString(), editTextSavePhone.getText().toString()));
                break;

            case R.id.buttonUpdate:
                UserEntity ue = new UserEntity(Integer.valueOf(editTextUpdateId.getText().toString()), editTextUpdateName.getText().toString(), editTextUpdatePhone.getText().toString());
                usersDatabase.userDao().updateUser(ue);
                break;

            case R.id.buttonDelete:
                UserEntity userEntity = new UserEntity(Integer.valueOf(editTextDeleteId.getText().toString()));
                usersDatabase.userDao().deleteUser(userEntity);
                break;

            case R.id.buttonShow:
                users.clear();
                List<UserEntity> userEntities = usersDatabase.userDao().loadAllUsers();
                for (UserEntity u : userEntities) {
                    users.add(u.getId() + ". " + u.getName() + " - " + u.getPhone());
                }
                arrayAdapter.notifyDataSetChanged();
                break;

        }
    }
}
