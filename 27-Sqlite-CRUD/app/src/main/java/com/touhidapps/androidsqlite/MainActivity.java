package com.touhidapps.androidsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<String> dataList;
    ArrayAdapter<String> arrayAdapter;

    EditText editTextAddName, editTextAddPhone, editTextUpdateId, editTextUpdateName, editTextUpdatePhone, editTextDeleteId;
    Button buttonAdd, buttonUpdate, buttonDelete;
    ListView listView;
    InfoModel infoModel;

    SqliteController sqliteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteController = new SqliteController(this);

        initUI();

    }

    private void initUI() {
        editTextAddName = (EditText) findViewById(R.id.editTextAddName);
        editTextAddPhone = (EditText) findViewById(R.id.editTextAddPhone);
        editTextUpdateId = (EditText) findViewById(R.id.editTextUpdateId);
        editTextUpdateName = (EditText) findViewById(R.id.editTextUpdateName);
        editTextUpdatePhone = (EditText) findViewById(R.id.editTextUpdatePhone);
        editTextDeleteId = (EditText) findViewById(R.id.editTextDeleteId);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonAdd.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        dataList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(arrayAdapter);
        getAllDataFromDb();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonAdd:
                infoModel = new InfoModel(editTextAddName.getText().toString(), editTextAddPhone.getText().toString());
                addToSqliteDatabase(infoModel);
                break;

            case R.id.buttonUpdate:
                infoModel = new InfoModel(editTextUpdateId.getText().toString(), editTextUpdateName.getText().toString(), editTextUpdatePhone.getText().toString());
                updateData(infoModel);
                break;

            case R.id.buttonDelete:
                try {
                    deleteData(Long.parseLong(editTextDeleteId.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void addToSqliteDatabase(InfoModel infoModel) {
        long row = sqliteController.addInfo(infoModel);
        if (row > 0) { // row is which no row is effected
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            editTextAddName.setText("");
            editTextAddPhone.setText("");
            getAllDataFromDb();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(InfoModel infoModel) {
        long row = sqliteController.updateInfo(infoModel);
        if (row > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            getAllDataFromDb();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData(long id) {
        long row = sqliteController.deleteInfo(id);
        if (row > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            getAllDataFromDb();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAllDataFromDb() {
        List<InfoModel> infoModels = sqliteController.getAllInfo();
        dataList.clear();
        for (int i = 0; i < infoModels.size(); i++) {
            dataList.add(infoModels.get(i).getId() + ". " + infoModels.get(i).getName() + " " + infoModels.get(i).getPhone());
        }
        arrayAdapter.notifyDataSetChanged();
    }


}
