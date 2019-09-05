package com.adriano.listarefa;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.design.widget.*;
import java.util.*;
import android.app.*;

public class NovaTarefa extends AppCompatActivity {

    EditText edtTarefa;
	TextView tvData, tvHora;
	private int mAno, mMes, mDia, mHora, mMinuto;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_tarefa);
		
		final DataBaseHelper dbh = new DataBaseHelper(NovaTarefa.this);
		
		final Calendar c = Calendar.getInstance();
		mAno = c.get(Calendar.YEAR);
		mMes = c.get(Calendar.MONTH);
		mDia = c.get(Calendar.DAY_OF_MONTH);
		mHora = c.get(Calendar.HOUR_OF_DAY);
		mMinuto = c.get(Calendar.MINUTE);

		edtTarefa = (EditText)findViewById(R.id.edt_tarefa);
		tvData = (TextView)findViewById(R.id.tv_data);
		tvHora = (TextView)findViewById(R.id.tv_hora);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        
		fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					try {	
						String nometarefa = edtTarefa.getText().toString();
						String datatarefa = tvData.getText().toString();
						String horatarefa = tvHora.getText().toString();
						Tarefa tarefa = new Tarefa(nometarefa, datatarefa, horatarefa);
						dbh.insertData(tarefa);
						Toast.makeText(NovaTarefa.this, "Salvo!", Toast.LENGTH_SHORT).show();
						
						Intent intent = new Intent(NovaTarefa.this, MainActivity.class);
						startActivity(intent);
						finish();
						}
					catch (Exception e){
						e.printStackTrace();
					}
				}
			});
    }
	public void showDataPicker(View v){

		DatePickerDialog datePickerDialog = new DatePickerDialog(this,
			new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year,
									  int month, int day) {
					tvData.setText(day + "/" + (month + 1) + "/" + year);

				}
			}, mAno, mMes, mDia);
		datePickerDialog.show();
	}

	public void showHoraPicker(View v){

		TimePickerDialog timePickerDialog = new TimePickerDialog(this,
			new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hour,
									  int minute) {
					tvHora.setText(hour + ":" + minute);
				}
			}, mHora, mMinuto, false);
		timePickerDialog.show();
	}
}
