package com.example.android_cilodong_latihan05_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SiswaDAO {
    @Query("SELECT * FROM siswa")
    List<Siswa> getAll(); //harus pake list karena akan di konversi menjadi cursor

    @Query("SELECT * FROM siswa WHERE nama LIKE :nama")
    List<Siswa> findByName(String nama);

    @Insert
    void insertAll(Siswa siswa); //tanpa id (karena id otomatis)

    @Update
    void update(Siswa siswa); //dengan id

    @Delete
    void delete(Siswa siswa); //dengan id
}
