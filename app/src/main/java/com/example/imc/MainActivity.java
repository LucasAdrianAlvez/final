package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends DebugActivity {
    private EditText altura;
    private EditText peso;
    private Button bt;
    private TextView resultado;
    private TextView alerta;
    private String alert;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altura = (EditText) findViewById(R.id.altura);
        peso = (EditText) findViewById(R.id.peso);
        bt = (Button) findViewById(R.id.bt);
        resultado = (TextView) findViewById(R.id.resultado);
        alerta = (TextView) findViewById(R.id.alerta) ;

        if(savedInstanceState!=null){
            name = savedInstanceState.getString("chaveNome");
            alert = savedInstanceState.getString("chaveNome2");
        }else{
            name = null;
            alert = null;
        }
        resultado.setText(name);
        alerta.setText(alert);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String alt0 = altura.getText().toString();
                float alt1 = Float.parseFloat(alt0);

                String pes0 = peso.getText().toString();
                float pes1 = Float.parseFloat(pes0);

                float alt2 = alt1*alt1;
                float calculo = pes1/alt2;

                String res = String.valueOf(calculo);
                name = "IMC = "+res;
                resultado.setText(name);

                if(calculo<18.5){
                    alert = "Debajo del peso";
                    alerta.setText(alert);
                }

                if(calculo>18.5 && calculo<= 24.9){
                    alert = "Peso normal";
                    alerta.setText(alert);

                }
                if(calculo >= 25.0 && calculo<=29.9){
                    alert = "Peso superior al normal";
                    alerta.setText(alert);

                }

                if(calculo >= 30){
                    alert = "Obesidad";
                    alerta.setText(alert);

                }


            }
        });





    }



        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.overflow, menu);
            return true;

        }
        public boolean onOptionsItemSelected(MenuItem item){
                int id = item.getItemId();

                if(id == R.id.metrico){
                    Toast.makeText(this, "Sistema Metrico", Toast.LENGTH_SHORT).show();

                }else if(id == R.id.pulgadas){

                    Intent intent = new Intent(this, MainActivity2.class);
                    Bundle params = new Bundle();

                    startActivity(intent);

                    Toast.makeText(this, "Sistema Ingles", Toast.LENGTH_SHORT).show();
                }

                return super.onOptionsItemSelected(item);
        }

    protected void onSaveInstanceState(Bundle dados){
        super.onSaveInstanceState(dados);
        dados.putString("chaveNome", name);
        dados.putString("chaveNome2", alert);
    }

}