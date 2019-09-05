package com.adriano.listarefa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "tarefas_db";
    private static final String TABLE_NAME = "tarefas";
    private static final String KEY_ID = "id";
    private static final String KEY_TAREFA = "tarefa";
    private static final String KEY_DATE = "data";
    private static final String KEY_TIME = "hora";
	
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

	@Override 
	public void onCreate(SQLiteDatabase db) { 
		db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
				   "(id INTEGER PRIMARY KEY AUTOINCREMENT,tarefa TEXT,data TEXT,hora TEXT)"); 
	} 
	
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
	
   public void insertData(Tarefa tarefa){
		SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
		cValues.put(KEY_TAREFA, tarefa.getTarefa());
        cValues.put(KEY_DATE, tarefa.getData());
        cValues.put(KEY_TIME, tarefa.getHora());

        db.insert(TABLE_NAME,null, cValues);
		db.close();
		
    }
	
    public List<Tarefa> getTarefas() {
        List<Tarefa> mList = new ArrayList<Tarefa>();
  
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(Integer.parseInt(cursor.getString(0)));
                tarefa.setTarefa(cursor.getString(1));
                tarefa.setData(cursor.getString(2));
				tarefa.setHora(cursor.getString(3));
				
                mList.add(tarefa);
            } while (cursor.moveToNext());
        }
        return mList;
    }

    public void DeletaTarefa(Tarefa tarefa){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",new String[]{
					  String.valueOf(tarefa.getId())
			});
		db.close();
    }
	
	public int updateTarefa(Tarefa tarefa) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("KEY_TAREFA", tarefa.getTarefa());
		values.put("KEY_DATE", tarefa.getData());
		values.put("KEY_TIME", tarefa.getHora());

		return db.update(TABLE_NAME, values, KEY_ID+" = ?"
		, new String[] { String.valueOf(tarefa.getId()) });
	}
}
