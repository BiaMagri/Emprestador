package com.facebook.bianca.emprestando;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by aluno on 25/01/2017.
 */

public class ActionActivity extends AppCompatActivity{

    Intent intent;
    TextView textView;
    EditText editText;
    String nome, oldValueD, oldValueB;
    private DatabaseReference mDatabase;
    public static Pessoa Bianca, Dami;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Firebase.setAndroidContext(this);

        intent = getIntent();
        nome = intent.getStringExtra("Nome");
        textView = (TextView) findViewById(R.id.textEntrada);
        editText = (EditText) findViewById(R.id.editTextConta);

        textView.setText(nome + " pagou:");

    }

    public void pagamento(View view){

        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(Dami == null){
            Dami = new Pessoa("Dâmi", 0.00);
        }
        if(Bianca == null){
            Bianca = new Pessoa("Bianca", 0.00);
        }

        if(nome.equals("Bianca")){

            Double oldDami = Dami.getSaldo();
            Double oldBianca = Bianca.getSaldo();
            Double toSum = Double.parseDouble(editText.getText().toString());
            Double newValue = oldDami + toSum;

            if(newValue>=oldBianca){
                newValue = newValue - oldBianca;
                oldBianca = 0.00;
            }
            else{
                oldBianca = oldBianca - newValue;
                newValue = 0.00;
            }

            Dami.setSaldo(newValue);
            mDatabase.child("pessoa").child("1").setValue(Dami);
            Bianca.setSaldo(oldBianca);
            mDatabase.child("pessoa").child("0").setValue(Bianca);
            finish();

        }
        else{

            Double oldBianca = Bianca.getSaldo();
            Double oldDami = Dami.getSaldo();
            Double toSum = Double.parseDouble(editText.getText().toString());
            Double newValue = oldBianca + toSum;

            if(newValue>=oldDami){
                newValue = newValue - oldDami;
                oldDami = 0.00;
            }
            else{
                oldDami = oldDami - newValue;
                newValue = 0.00;
            }

            Dami.setSaldo(oldDami);
            mDatabase.child("pessoa").child("1").setValue(Dami);
            Bianca.setSaldo(newValue);
            mDatabase.child("pessoa").child("0").setValue(Bianca);
            finish();

        }
    }
}
