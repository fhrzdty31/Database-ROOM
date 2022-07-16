package www.fhrzdty31.sch.id.personprofile_room;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import www.fhrzdty31.sch.id.personprofile_room.helper.DBHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText edtName, edtNomor, edtEmail;
    private Integer id;
    private DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        CircleImageView image = findViewById(R.id.avatar);
        edtName = findViewById(R.id.edt_name);
        edtNomor = findViewById(R.id.edt_nomor);
        edtEmail = findViewById(R.id.edt_email);
        Button btnSave = findViewById(R.id.btn_save);
        id = getIntent().getIntExtra("id", 0);
        String nama = getIntent().getStringExtra("name");
        String nomor = getIntent().getStringExtra("nomor");
        String email = getIntent().getStringExtra("email");

        database = DBHelper.getInstance(getApplicationContext());

        if (id == 0) {
            setTitle("Tambah User");
        } else {
            setTitle("Edit User");
            Glide.with(getApplicationContext())
                    .load("https://picsum.photos/id/7"+id+"/200")
                    .into(image);
            edtName.setText(nama);
            edtNomor.setText(nomor);
            edtEmail.setText(email);
        }

        btnSave.setOnClickListener(view -> {
            try {
                if (id == 0) {
                    save();
                } else {
                    update();
                }
            } catch (Exception e) {
                Log.e("Saving", e.getMessage());
            }
        });
    }

    private void save() {
        if (String.valueOf(edtName.getText()).equals("") || String.valueOf(edtNomor.getText()).equals("") || String.valueOf(edtEmail.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            database.userDao().insertAll(
                    edtName.getText().toString(),
                    edtNomor.getText().toString(),
                    edtEmail.getText().toString()
            );
            Toast.makeText(getApplicationContext(), "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void update() {
        if (String.valueOf(edtName.getText()).equals("") || String.valueOf(edtNomor.getText()).equals("") || String.valueOf(edtEmail.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            database.userDao().update(
                    id,
                    edtName.getText().toString(),
                    edtNomor.getText().toString(),
                    edtEmail.getText().toString()
            );
            Toast.makeText(getApplicationContext(), "Berhasil mengubah data", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}