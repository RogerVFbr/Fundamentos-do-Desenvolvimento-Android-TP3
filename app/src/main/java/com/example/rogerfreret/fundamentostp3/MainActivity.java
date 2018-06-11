package com.example.rogerfreret.fundamentostp3;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    //   /--------------------------------------------------------------------------------------\
    //   |                              PROPERTIES DECLARATION                                  |
    //   \--------------------------------------------------------------------------------------/

    private Button       btnAdd10, btnSubtract10, btnAdd5, btnSubtract5;
    private TextView     txvDisplayResult;
    private MediaPlayer  mPlayer;


    //   /--------------------------------------------------------------------------------------\
    //   |                                LIFECYCLE: ON CREATE                                  |
    //   \--------------------------------------------------------------------------------------/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViews();
        setListeners();
    }


    //   /--------------------------------------------------------------------------------------\
    //   |                            VIEWS & LISTENERS DEFINITIONS                             |
    //   \--------------------------------------------------------------------------------------/

    // ---> Connect Views to Variables
    private void findAllViews() {
        btnAdd10           = (Button)   findViewById(R.id.btnAdd10);
        btnSubtract10      = (Button)   findViewById(R.id.btnDecrease10);
        btnAdd5            = (Button)   findViewById(R.id.btnAdd5);
        btnSubtract5       = (Button)   findViewById(R.id.btnDecrease5);
        txvDisplayResult   = (TextView) findViewById(R.id.numberDisplay);
    }

    // ---> Sets Listeners to Buttons
    private void setListeners () {
        attachOnClickMethod(btnAdd5,       "genericButtonActions", 5,    R.raw.som8);
        attachOnClickMethod(btnSubtract5,  "genericButtonActions", -5,   R.raw.som1);
        attachOnClickMethod(btnAdd10,      "genericButtonActions", 10,   R.raw.som8);
        attachOnClickMethod(btnSubtract10, "genericButtonActions", -10,  R.raw.som1);
    }

    //   /--------------------------------------------------------------------------------------\
    //   |                                 ON CLICK METHODS                                     |
    //   \--------------------------------------------------------------------------------------/

    protected void genericButtonActions (final int value, final int note) {

        String newValue = String.valueOf(Integer.valueOf(txvDisplayResult.getText().toString()) + value);
        txvDisplayResult.setText(newValue);
        mPlayer = MediaPlayer.create(getApplicationContext(), note);
        mPlayer.start();
    }

    //   /--------------------------------------------------------------------------------------\
    //   |                                    ABSTRACTIONS                                      |
    //   \--------------------------------------------------------------------------------------/

    private void attachOnClickMethod (View v, final String methodname, final int valueA, final int valueB) {

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Class[] parameterTypes = new Class[] {int.class, int.class};

                try {
                    Method sentMethod = MainActivity.class.getMethod(methodname, parameterTypes);
                    sentMethod.invoke(MainActivity.this, valueA, valueB);
                }

                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}


/*

    /--------------------------------------------------------------------------------------\
    |                   ESTUDOS DE CASO: FORMAS DE APLICAR OS LISTENERS                    |
    \--------------------------------------------------------------------------------------/

    A - IN-LINE
    ^^^^^^^^^^^

    exemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instruções

            }
    });


    B - DECLARACAO DO OBJETO VIEW.ONCLICKLISTENER ATRAVES DE VARIAVEL
    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    Declarar no escopo da instância do Activity:

        private View.OnClickListener exemploObj = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Instruções

                }
        };

    Declarar no escopo do metodo OnCreate:

        exemplo.setOnClickListener(exemploObj);


    C - DECLARAR ONCLICK POR INTERFACE
    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    Declarar Activity como implementação da interface OnClickListener:

        public class MainActivity extends AppCompatActivity implements View.OnClickListener

    Declarar no escopo do metodo OnCreate:

        exemplo1.setOnClickListener(this);
        exemplo2.setOnClickListener(this);
        exemplo3.setOnClickListener(this);

    Definir OVERRIDE do metodo OnClick da instância da activity para conter
    um SWITCH CASE que identifique o elemento em questão e anexe a ele a função contendo
    suas funcionalidades.

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.exemplo1:     action_exemplo1();     break;
                case R.id.exemplo2:     action_exemplo2();     break;
                case R.id.exemplo3:     action_exemplo3();     break;
            }
        }

    Declarar na instancia da Activity os metodos com as acoes do botao:

        private void action_exemplo1 () {

           // Instruções

        }



*/





