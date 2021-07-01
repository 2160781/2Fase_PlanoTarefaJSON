package com.example.android_resource;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_tarefas extends AppCompatActivity {

    boolean estadoBoton;
    Button boton;
    TextView textV;
    ImageView ver_imagen;
    int numeroPlano = 0;
    int posicao = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        estadoBoton=true;
        boton=findViewById(R.id.Button);
        ver_imagen=findViewById(R.id.imageView3);
        textV= findViewById(R.id.textView);

        numeroPlano = getIntent().getIntExtra("NumeroPlano",0);
        textV.setText(PlayActivity.Main.dadosApp_.getTextTarefa(numeroPlano,posicao)+":"+PlayActivity.Main.dadosApp_.getFeito(numeroPlano,0));

        if(PlayActivity.Main.dadosApp_.getFeito(numeroPlano,0) == false){
            ver_imagen.setImageResource(R.drawable.errado);
        }else{
            ver_imagen.setImageResource(R.drawable.certo);
        }


    }

    //Metodos
    @Override
    public boolean dispatchKeyEvent( KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        //int PassosFeitos1 = PlayActivity.Main.dadosApp.getPassosFeitos();
        switch (keyCode) {
            //Seguinte
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (action == KeyEvent.ACTION_DOWN) {
                    if(posicao<PlayActivity.Main.dadosApp_.getNumeroTarefasDePlano(numeroPlano)-1){
                        posicao++;
                        textV.setText(PlayActivity.Main.dadosApp_.getTextTarefa(numeroPlano,posicao)+":");

                        if(PlayActivity.Main.dadosApp_.getFeito(numeroPlano,posicao) == false){
                            ver_imagen.setImageResource(R.drawable.errado);
                        }else{
                            ver_imagen.setImageResource(R.drawable.certo);
                        }
                        estadoBoton= false;

                    }


                }
                return true;
            //Anterior
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (action == KeyEvent.ACTION_DOWN) {
                    if(posicao>0) {
                        posicao--;
                        textV.setText(PlayActivity.Main.dadosApp_.getTextTarefa(numeroPlano,posicao) + ":" );
                        estadoBoton = false;
                        if (PlayActivity.Main.dadosApp_.getFeito(numeroPlano,posicao) == false) {
                            ver_imagen.setImageResource(R.drawable.errado);
                        } else {
                            ver_imagen.setImageResource(R.drawable.certo);
                        }
                    }


                }

                return true;
            //Marcar passo como "Feito"
            case KeyEvent.KEYCODE_DPAD_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    //Colocar a posição da tarefa "Feito"

                    int position = posicao;
                    if(PlayActivity.Main.dadosApp_.getFeito(numeroPlano,position) == false){
                        PlayActivity.Main.dadosApp_.marcarFeito(numeroPlano,position);
                        ver_imagen.setImageResource(R.drawable.certo);
                    }


                }

                return true;

            //Marcar passo como "Por fazer"
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {

                    int position = posicao;
                    if(PlayActivity.Main.dadosApp_.getFeito(numeroPlano,position) == true){
                        PlayActivity.Main.dadosApp_.marcarErrado(numeroPlano,position);
                        ver_imagen.setImageResource(R.drawable.errado);
                    }
                }

                return true;
            case KeyEvent.KEYCODE_ENTER:
                if (action == KeyEvent.ACTION_DOWN) {

                    Intent inicio = new Intent(this,
                            MainActivity.class);
                    startActivity(inicio);
                }

                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }




}