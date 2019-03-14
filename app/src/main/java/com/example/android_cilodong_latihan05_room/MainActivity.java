package com.example.android_cilodong_latihan05_room;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    EditText met_nama, et_cari;
    Button bt_cari;//, bt_refresh;
    Spinner msp_kelas;

    static MyDatabase db;

    List<Siswa> siswas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_cari = (EditText) findViewById(R.id.et_cari);
        bt_cari = (Button) findViewById(R.id.bt_cari);

        //load data palsu
        //defineData();

        //bentuk database
        db = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class,
                "db-siswa")
                .allowMainThreadQueries()
                .build();

        viewRecyclerView(null);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(MainActivity.this);

                inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.form_input, null);

                final View diagView = view;

                dialog.setView(view);
                dialog.setCancelable(true);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("Form Biodata");

                met_nama = (EditText) view.findViewById(R.id.et_nama);
                msp_kelas = (Spinner) view.findViewById(R.id.sp_kelas);

                dialog.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String nama, kelas;

                        nama = met_nama.getText().toString();
                        kelas = msp_kelas.getSelectedItem().toString();

                        //simpan data
                        db.siswaDAO().insertAll(new Siswa(nama, kelas));

                        viewRecyclerView(null);
                    }
                });

                dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        bt_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRecyclerView(et_cari.getText().toString());
            }
        });


//        bt_refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewRecyclerView(null);
//            }
//        });
    }

//    private void defineData()
//    {
//        for (int i = 1; i < 5; i++) {
//            siswaArrayList.add(new Siswa("Nama - "+ i, "Kelas - "+ i));
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();

        viewRecyclerView(null);
    }

    public void viewRecyclerView(String cari) {
        //definisi
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_container);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        if (cari == null || cari.isEmpty()){
            siswas = db.siswaDAO().getAll();
        }else{
            siswas = db.siswaDAO().findByName(cari);
        }

        RvAdapter rvAdapter = new RvAdapter(this);
        rvAdapter.setListSiswa(siswas); //ambil nilai array dari main activity
        rv.setAdapter(rvAdapter);
    }

}
