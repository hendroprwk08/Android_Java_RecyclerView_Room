package com.example.android_cilodong_latihan05_room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    MyDatabase db;
    String nama, kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final EditText et_nama = (EditText) findViewById(R.id.det_et_nama);
        //final EditText et_kelas = (EditText) findViewById(R.id.det_et_kelas);
        final Spinner sp_kelas = (Spinner) findViewById(R.id.sp_det_kelas);
        final Button bt_ubah = (Button) findViewById(R.id.det_bt_ubah);
        final Button bt_hapus = (Button) findViewById(R.id.det_bt_hapus);

        //tangkap bundle
        Bundle bundle = null;
        bundle = this.getIntent().getExtras();

        //letakkan isi bundle
        final int id = bundle.getInt("b_id", 0);
        et_nama.setText(bundle.getString("b_nama"));

        //set spinner
        int idx_spinner = setSpinner(sp_kelas, bundle.getString("b_kelas"));
        sp_kelas.setSelection(idx_spinner);

        getSupportActionBar().setTitle("Data Peserta"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action back

        bt_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama =  et_nama.getText().toString();
                kelas =  sp_kelas.getSelectedItem().toString();//et_kelas.getText().toString();

                MainActivity.db.siswaDAO().update(new Siswa(id, nama, kelas));
                Toast.makeText(getApplicationContext(), "Data " + nama + " berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bt_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama =  et_nama.getText().toString();
                kelas =  sp_kelas.getSelectedItem().toString();//et_kelas.getText().toString();

                MainActivity.db.siswaDAO().delete(new Siswa(id, nama, kelas));
                Toast.makeText(getApplicationContext(), "Data " + nama + " dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private int setSpinner(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
