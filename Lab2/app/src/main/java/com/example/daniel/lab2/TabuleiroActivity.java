package com.example.daniel.lab2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;


public class TabuleiroActivity extends ActionBarActivity {

    char[][] tabuleiro = new char[3][3];

    int cont=0;
    Button[] btns;

    char jogada = 'X';
//    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);

        Resources res = getResources();
        Intent intent = getIntent();
        final int id = intent.getIntExtra("tabuleiro_id", R.id.btStart);


        final Button[] btns = new Button[9];


        btns[0] = (Button) findViewById(R.id.btTabuleiro1);
        btns[1] = (Button) findViewById(R.id.btTabuleiro2);
        btns[2] = (Button) findViewById(R.id.btTabuleiro3);

        btns[3] = (Button) findViewById(R.id.btTabuleiro4);
        btns[4] = (Button) findViewById(R.id.btTabuleiro5);
        btns[5] = (Button) findViewById(R.id.btTabuleiro6);

        btns[6] = (Button) findViewById(R.id.btTabuleiro7);
        btns[7] = (Button) findViewById(R.id.btTabuleiro8);
        btns[8] = (Button) findViewById(R.id.btTabuleiro9);

        for (int i = 0; i < 9; i++) {
            btns[i].setTag(i);
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {
                        atribuiValores(v);
                        resolucao(v);
                        checar();
                }
            });
        }
    }



    private void checar() {



        TextView ress = (TextView) findViewById(R.id.resultado);

        for (int linha = 0; linha < 3; linha++) {

            if ((tabuleiro[linha][0] == 'X' && tabuleiro[linha][1] == 'X' && tabuleiro[linha][2] == 'X')) {
                ress.setText ("Player 1 Venceu");

                break;


            } else if ((tabuleiro[linha][0] == 'O' && tabuleiro[linha][1] == 'O' && tabuleiro[linha][2] == 'O')) {
                ress.setText ("Player 2 Venceu");

                break;
            }
        }




        for (int coluna = 0; coluna < 3; coluna++) {


            if ((tabuleiro[0][coluna] == 'X' && tabuleiro[1][coluna] == 'X' && tabuleiro[2][coluna] == 'X')) {
                ress.setText ("Player 1 Venceu");

                break;

            } else if ((tabuleiro[0][coluna] == 'O' && tabuleiro[1][coluna] == 'O' && tabuleiro[2][coluna] == 'O')) {
                ress.setText ("Player 2 Venceu");

                break;
            }
        }

        if ((tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X')) {
            ress.setText ("Player 1 Venceu");



        } else if ((tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O')) {
            ress.setText ("Player 2 Venceu");


        }

        if ((tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == 'X')) {
            ress.setText ("Player 1 Venceu");


        }else if ((tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][0] == 'O')) {
                     ress.setText ("Player 2 Venceu");


        }

        cont=cont++;
        if(cont==9){
            ress.setText ("Empate");
        }

    }



    private void atribuiValores(View v) {
        Button botao = (Button) v;
        int indexOfPressedButton = Integer.parseInt(botao.getTag().toString());

        int row = indexOfPressedButton / 3;
        int col = indexOfPressedButton % 3;

        tabuleiro[row][col] = jogada;

        String turnAsString = String.valueOf(jogada);

        botao.setText(turnAsString);
    }

    private void resolucao(View v) {



        Button botao = (Button) v;

          if (jogada == 'X') {
              botao.setBackgroundColor(Color.RED);
              botao.setText("X");
              jogada = 'O';
              botao.setEnabled(false);

          }else  {
              botao.setBackgroundColor(Color.GREEN);
              botao.setText("O");
              jogada = 'X';
              botao.setEnabled(false);
          }
    }


}





