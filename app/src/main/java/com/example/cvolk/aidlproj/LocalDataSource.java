package com.example.cvolk.aidlproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class LocalDataSource<T> extends SQLiteOpenHelper {

    public static final String SELECT_ALL = "SELECT * FROM ";

    public String NAME;
    public String TABLE;
    public String pkName;
    public String pkType;
    public String[] colNames;
    public String[] colTypes;
    public int version;

    public LocalDataSource(Context context, DataContract contract) {
        super(context, contract.name, null, contract.version);

        this.NAME = contract.name;
        this.TABLE = contract.name;
        this.pkName = contract.pkName;
        this.pkType = contract.pkType;
        this.colNames = contract.colNames;
        this.colTypes = contract.colTypes;
        this.version = contract.version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // UNUSED
    }

    private String getCreateQuery() {
        String query = "CREATE TABLE " + TABLE + "(";

        query += pkName + " " + pkType + " PRIMARY KEY, ";

        for (int i = 0; i < colNames.length; i++) {
            query += colNames[i] + " " + colTypes[i];

            if (i != colNames.length - 1) {
                query += ", ";
            }
        }

        query += ");";

        return query;
    }

    public long saveObject(T object) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < colNames.length; i++) {
                values.put(
                        colNames[i],
                        (String)object.getClass().getMethod(
                                "get" + colNames[i],
                                null
                        ).invoke(object)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long rowNum = db.insert(TABLE, null, values);

        db.close();

        return rowNum;
    }

    public List<T> getObjects(Class<T> type) {
        SQLiteDatabase db = getWritableDatabase();
        List<T> objects = new ArrayList<>();
        Cursor cursor = db.rawQuery(SELECT_ALL + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    Class[] argTypes = new Class[colNames.length + 1];
                    Object[] args = new Object[colNames.length + 1];

                    args[0] = cursor.getInt(0);
                    argTypes[0] = int.class;
                    for (int i = 1;i < args.length; i++)
                    {
                        args[i] = cursor.getString(i);
                        argTypes[i] = String.class;
                    }

                    Constructor<T> constructor = type.getConstructor(argTypes);
                    T object =(T)constructor.newInstance(args);//  new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                    objects.add(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        db.close();

        return objects;
    }
}
