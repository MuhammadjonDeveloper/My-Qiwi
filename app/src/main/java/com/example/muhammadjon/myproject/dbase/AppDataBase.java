package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {
        Categories.class,
        Merchants.class,
        Fields.class,
        Values.class},
        version = 4, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instaince;
    public abstract CategoriesDao getCategoryDao();
    public abstract MerchantsDao getMerchantDao();
    public abstract FieldsDao getFielsDao();
    public abstract ValuesDao getValuesDao();

    public static AppDataBase getInstaince(Context context) {
        if (instaince == null) {
            instaince = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "dbqiwi")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instaince;
    }

    public static void distroyInstaince() {
        instaince = null;
    }
}
