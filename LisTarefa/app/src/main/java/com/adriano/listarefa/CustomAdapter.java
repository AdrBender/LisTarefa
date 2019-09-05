package com.adriano.listarefa;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.*;

import java.util.ArrayList;
import android.app.*;
import java.util.*;
import android.content.*;

public class CustomAdapter extends BaseAdapter {

    private Context context;
   	private ArrayList<Tarefa> arrTarefa;
	private DataBaseHelper dbh;

    public CustomAdapter(Context context, ArrayList<Tarefa> arrTarefa, DataBaseHelper dbh) {
        this.context = context;
        this.arrTarefa = arrTarefa;
		this.dbh = dbh;
    }

    @Override
    public int getCount() {
        return arrTarefa.size();
    }

    @Override
    public Object getItem(int i) {
        return arrTarefa.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;

        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_list, null);
			
			holder = new ViewHolder();
			holder.txtId = view.findViewById(R.id.id);
            holder.txtTarefa = view.findViewById(R.id.tarefa);
            holder.txtData = view.findViewById(R.id.data);
            holder.txtHora = view.findViewById(R.id.hora);
			holder.deleteButton = view.findViewById(R.id.del_btn);
			
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        
		holder.txtId.setText("id "+arrTarefa.get(i).getId());
		holder.txtTarefa.setText(arrTarefa.get(i).getTarefa());
        holder.txtData.setText(arrTarefa.get(i).getData());
		holder.txtHora.setText(arrTarefa.get(i).getHora());
		
		final int position = i;
		
		holder.deleteButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
					dialogDelete.setTitle("Opa!");
					dialogDelete.setMessage("Quer mesmo deletar?");
					dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								try{
									dbh.DeletaTarefa(arrTarefa.get(position));
									arrTarefa = (ArrayList<Tarefa>) dbh.getTarefas();notifyDataSetChanged();
									notifyDataSetChanged();
								}catch(Exception e){
									Toast.makeText(context, "Exception: " + e, Toast.LENGTH_LONG).show();
								}
							}
						});
					dialogDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								dialogInterface.dismiss();
							}
						});
					dialogDelete.show();
				}
			});
        return view;
    }
	
	private class ViewHolder{
        TextView txtTarefa, txtId, txtData, txtHora;
		Button deleteButton;
    }
}
