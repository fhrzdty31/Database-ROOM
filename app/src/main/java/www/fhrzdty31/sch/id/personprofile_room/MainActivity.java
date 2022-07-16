package www.fhrzdty31.sch.id.personprofile_room;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import www.fhrzdty31.sch.id.personprofile_room.adapter.Adapter;
import www.fhrzdty31.sch.id.personprofile_room.helper.DBHelper;
import www.fhrzdty31.sch.id.personprofile_room.helper.entitas.User;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private final List<User> list = new ArrayList<>();
    private Adapter adapter;
    private DBHelper database;
    AlertDialog.Builder dialog;


    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Profile Data");


        database = DBHelper.getInstance(getApplicationContext());
        adapter = new Adapter(MainActivity.this, list);

        Button btnAdd = findViewById(R.id.btn_add);
        listView = findViewById(R.id.tv_item);

        btnAdd.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, EditorActivity.class)));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final int id = list.get(i).id;
            final String name = list.get(i).name;
            final String nomor = list.get(i).nomor;
            final String email = list.get(i).email;
            CharSequence[] dialogItem = {"Detail", "Edit", "Hapus"};
            CharSequence[] dialogDetail = {"\tNama", name, "\tNomor", nomor, "\tEmail", email};
            dialog = new AlertDialog.Builder(this);
            dialog.setItems(dialogItem, (dialogInterface, i1) -> {
                switch (i1) {
                    case 0:
                        dialog.setItems(dialogDetail, (dialogInterface1, i2) -> {
                            if (i2 == 2 || i2 == 3) {
                                Uri tel = Uri.parse("tel:" + nomor);
                                Intent intent = new Intent(Intent.ACTION_DIAL, tel);
                                startActivity(intent);
                            }
                        }).show();
                        break;
                    case 1:
                        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("nomor", nomor);
                        startActivity(intent);
                        break;
                    case 2:
                        User user = list.get(i);
                        database.userDao().delete(user);
                        list.clear();
                        getData();
                        break;
                }
            }).show();
        });
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        getData();
    }

    private void getData() {
        list.addAll(database.userDao().getAll());
        adapter.notifyDataSetChanged();
    }
}