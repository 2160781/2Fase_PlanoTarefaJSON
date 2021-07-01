package com.example.android_resource;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean estadoBotao;
    Button botao;

    private List<Tarefas> listaPassos;
    private List<Plano> listaPlano;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listviewTarefas();

    }

    public boolean listviewTarefas(){

        setContentView(R.layout.activity_listview);
        
        listView = findViewById(R.id.listview);

        String[] values = new String[] {
                "1. Receita de bolo - "+PlayActivity.Main.dadosApp_.getNumeroPassosFeitos(0)+" de " + PlayActivity.Main.dadosApp_.getSizeListPassos(0)
                , "2. Plantação de trigo - "+PlayActivity.Main.dadosApp_.getNumeroPassosFeitos(1)+"  de "
                + PlayActivity.Main.dadosApp_.getSizeListPassos(1)
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,values);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position=position;
                if(position == 0){
                    Intent Tarefa1 = new Intent(view.getContext(),
                            activity_tarefas.class);
                    Tarefa1.putExtra("NumeroPlano",position);
                    startActivity(Tarefa1);
                }

                if (position == 1) {

                    Intent Tarefa2 = new Intent(view.getContext(),
                            activity_tarefas.class);
                    Tarefa2.putExtra("NumeroPlano",position);
                    startActivity(Tarefa2);
                }
            }
        });
        return true;
    }


}

