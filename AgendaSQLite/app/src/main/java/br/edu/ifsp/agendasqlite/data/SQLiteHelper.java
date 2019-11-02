package br.edu.ifsp.agendasqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    static final String TABLE_NAME = "contatos";

    static final String KEY_ID = "id";
    static final String KEY_NOME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_CEL = "cel";
    static final String KEY_EMAIL = "email";
    static final String KEY_FAV = "favorito";
    static final String KEY_DT_NASC = "dataNascimento";

    private static final int DATABASE_VERSION = 4;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NOME + " TEXT, "
            + KEY_FONE + " TEXT, "
            + KEY_CEL + " TEXT, " + KEY_EMAIL + " TEXT, "
            + KEY_DT_NASC + " TEXT, "
            + KEY_FAV + " INTEGER DEFAULT 0);";

    private static final String ALTER_TABLE_FAV_COLUMN =
            "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_FAV + " INTEGER DEFAULT 0;";

    private static final String ALTER_TABLE_CEL_COLUMN =
            "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_CEL + " TEXT;";

    private static final String ALTER_TABLE_DT_NASC_COLUMN =
            "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_DT_NASC + " TEXT;";


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case 1:
                ExecuteUpdateTwo(db);
                ExecuteUpdateThree(db);
                ExecuteUpdateFour(db);
                break;
            case 2:
                ExecuteUpdateThree(db);
                ExecuteUpdateFour(db);
                break;
            case 3:
                ExecuteUpdateFour(db);
                break;
        }
    }

    private void ExecuteUpdateTwo(SQLiteDatabase db) {
        db.execSQL(ALTER_TABLE_FAV_COLUMN);
    }

    private void ExecuteUpdateThree(SQLiteDatabase db) {
        db.execSQL(ALTER_TABLE_CEL_COLUMN);
    }

    private void ExecuteUpdateFour(SQLiteDatabase db) {
        db.execSQL(ALTER_TABLE_DT_NASC_COLUMN);
    }
}
