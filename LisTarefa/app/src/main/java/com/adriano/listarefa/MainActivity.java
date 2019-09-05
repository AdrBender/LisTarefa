package com.adriano.listarefa;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import java.util.ArrayList;
import android.widget.*;
import android.view.*;
import android.database.sqlite.*;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Tarefa> arrTarefa;
	CustomAdapter mAdapter;
    DataBaseHelper dbh;
	SQLiteDatabase mDatabase;
	
	Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        mListView = (ListView)findViewById(R.id.listView);
		
		dbh = new DataBaseHelper(this);
		arrTarefa = (ArrayList<Tarefa>) dbh.getTarefas();
		
        mAdapter = new CustomAdapter(this, arrTarefa, dbh);
        mListView.setAdapter(mAdapter);
		/*
		for (Tarefa tarefa : arrTarefa) {		
			StringBuilder mensagem = new StringBuilder();
			mensagem.append("ID "+tarefa.getId()).append(" - ")
		 	.append(tarefa.getTarefa()).append(" - ")
		 	.append(tarefa.getData()).append(" - ")
			.append(tarefa.getHora());
			Toast.makeText(MainActivity.this, mensagem.toString(), Toast.LENGTH_SHORT).show(); 
		}*/
		
		FloatingActionButton mainfab = (FloatingActionButton)findViewById(R.id.mainfab);
		mainfab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(MainActivity.this, NovaTarefa.class);
					startActivity(intent);
				}
			});
			
		if(arrTarefa.size()==0){
            TextView txtListaVazia = (TextView)findViewById(R.id.txt_empty_list);
			txtListaVazia.setText("Nenhuma tarefa salva!");
        }
    }
}
