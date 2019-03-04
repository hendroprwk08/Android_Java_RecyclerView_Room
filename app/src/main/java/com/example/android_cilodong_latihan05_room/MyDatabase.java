package com.example.android_cilodong_latihan05_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Siswa.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SiswaDAO siswaDAO();
}
