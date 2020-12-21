package com.example.betacell.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



        //DB NOMBRE
        public static final String DATABASE_NAME = "betacellDB";
        //DB VERSION
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_USERS = "users";
        //COLUMNAS TABLA USUARIO
        public static final String KEY_ID = "id";
        public static final String KEY_USER_NAME = "username";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_PASSWORD = "password";
        public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
                + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_USER_NAME + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT"
                + " ) ";



        public static final String TABLE_PRESION = "presion";
        //COLUMNAS TABLA PRESION
        public static final String ID = "id";
        public static final String SISTOLA = "sistola";
        public static final String DIASTOLA = "diastola";
        public static final String PULSOS = "pulsos";
        public static final String FECHA = "fecha";
        public static final String USUARIO = "usuario";
        public static final String SQL_TABLE_PRESION = " CREATE TABLE " + TABLE_PRESION
                + " ( "
                + ID + " INTEGER PRIMARY KEY, "
                + SISTOLA + " TEXT, "
                + DIASTOLA + " TEXT, "
                + PULSOS + " TEXT, "
                + FECHA + " TEXT,"
                + USUARIO + " TEXT"

                + " ) ";


        public static final String TABLE_GLUCOSA = "glucosa";
        //COLUMNAS TABLA GLUCOSA
        public static final String ID_GLUCOSA = "id";
        public static final String MEDICION_GLUCOSA = "medicionGlucosa";
        public static final String CONDICION = "condicion";
        public static final String FECHA_GLUCOSA= "fecha";
        public static final String USUARIO_GLUCOSA = "usuario";

        public static final String SQL_TABLE_GLUCOSA = " CREATE TABLE " + TABLE_GLUCOSA
            + " ( "
            + ID_GLUCOSA + " INTEGER PRIMARY KEY, "
            + MEDICION_GLUCOSA + " TEXT, "
            + CONDICION + " TEXT, "
            + FECHA_GLUCOSA + " TEXT,"
            + USUARIO_GLUCOSA + " TEXT"

            + " ) ";


            public DatabaseHelper(Context context) {
                    super(context, DATABASE_NAME, null, DATABASE_VERSION);
                }

            public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL_TABLE_USERS);
            sqLiteDatabase.execSQL(SQL_TABLE_PRESION);
            sqLiteDatabase.execSQL(SQL_TABLE_GLUCOSA);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_GLUCOSA);
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PRESION);
        }





        //Metodo para agregar usuario
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_USER_NAME, user.userName);
            values.put(KEY_EMAIL, user.email);
            values.put(KEY_PASSWORD, user.password);
            long todo_id = db.insert(TABLE_USERS, null, values);
        }

        //Metodo para autentificar usuario
        public User Authenticate(User user) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USERS,// Eligiendo la tabla
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},
                    KEY_EMAIL + "=?",
                    new String[]{user.email},//Where clausula
                    null, null, null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
                //si el cursor tiene valor, entonces en la base de datos del usuario hay un usuario asociado con este correo electrónico dado
                User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                //Comprueba contraseñas
                if (user.password.equalsIgnoreCase(user1.password)) {
                    return user1;
                }
            }

            //si la contraseña del usuario no coincide o no hay registro con ese correo electrónico,
            // devuelva @false
            return null;
        }


        public boolean isEmailExists(String email) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Seleccionar columnas consultadas
                    KEY_EMAIL + "=?",
                    new String[]{email},//Where clausula
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
                //si el cursor tiene valor, entonces en la base de datos del usuario hay un usuario
                // asociado con este correo electrónico dado, así que devuelva true
                return true;
            }
            //si el correo electrónico no existe devolver false
            return false;
        }



}