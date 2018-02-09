package biz.vumobile.contentprovider;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<String> arrayList = new ArrayList();
    List<ContactModel> contactModels = new ArrayList();
    ArrayAdapter<String> arrayAdapter;

    public static final int RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "" + contactModels.get(i).getId(), Toast.LENGTH_SHORT).show();
            }
        });

        // Call operation method
        operationAfterPermission();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RESULT_CODE) {
            operationAfterPermission();
        }

    }

    public void operationAfterPermission() {
        // Runtime permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, RESULT_CODE);
        } else {
            contentOperation();
        }
    }

    public void contentOperation() {
        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  // Custom: "content://com.example.MyApplication.StudentsProvider" // sms: Uri.parse("content://sms/inbox")
                null, // Which column to select, null means all column // new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                null, // WHERE clause // ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=? OR " + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?",
                null, // Additional arguments // new String[]{"Amma", "Abba"},
                null // ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC LIMIT 4"
        );

        if (c != null) {
            while (c.moveToNext()) {
                String id = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                ContactModel contactModel = new ContactModel(id, name, number);
                contactModels.add(contactModel);

                arrayList.add(contactModel.getId() + " - " + contactModel.getName() + " - " + contactModel.getNumber());


            }
            c.close();
            arrayAdapter.notifyDataSetChanged();

        }
    }

    // TODO
    public void updateContact(ContentValues contentValues, String id) {
        getContentResolver().update(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, contentValues, ContactsContract.CommonDataKinds.Phone._ID + "=?", new String[]{id});

    }


}
