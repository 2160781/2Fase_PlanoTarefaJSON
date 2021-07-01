package com.example.android_resource;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    public static class Main {
        public static DadosApp dadosApp_;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Main.dadosApp_ = new DadosApp();

        getJSON();

        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);

    }

    public void getJSON(){

        String json;

        try{
            InputStream is = PlayActivity.this.getAssets().open("JSONFinal.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);


            for(int i = 0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Tarefas[] listaPassos = new Tarefas[4];
                JSONArray passos = obj.getJSONArray("passos");

                for (int o = 0; o < passos.length(); o++){
                    JSONObject c = passos.getJSONObject(o);

                    Boolean feito = c.getBoolean("feito");
                    listaPassos[o] = new Tarefas(""+c.getString("texto"),feito);
                }

                PlayActivity.Main.dadosApp_.adicionarPlano(""+obj.getString("nome"),
                        listaPassos);

            }



        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}