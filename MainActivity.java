package com.facebook.bianca.emprestando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.FirebaseApp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static com.facebook.bianca.emprestando.ActionActivity.Bianca;
import static com.facebook.bianca.emprestando.ActionActivity.Dami;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Firebase objRef, objRef2;
    TextView saldoB, saldoD;
    TextView nome1, nome2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Colentando dados locais", Toast.LENGTH_LONG).show();

        Firebase.setAndroidContext(this);

        objRef = new Firebase("https://lender-9b3ab.firebaseio.com/pessoa/0/saldo");

        saldoB = (TextView) findViewById(R.id.din);
        saldoD = (TextView) findViewById(R.id.din2);
        nome1 = (TextView) findViewById(R.id.textBianca);
        nome2 = (TextView) findViewById(R.id.textDami);

        objRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String messege = dataSnapshot.getValue(String.class);
                saldoB.setText(messege);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        objRef2 = new Firebase("https://lender-9b3ab.firebaseio.com/pessoa/1/saldo");

        saldoB = (TextView) findViewById(R.id.din);
        saldoD = (TextView) findViewById(R.id.din2);
        nome1 = (TextView) findViewById(R.id.textBianca);
        nome2 = (TextView) findViewById(R.id.textDami);

        objRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String messege = dataSnapshot.getValue(String.class);
                saldoD.setText(messege);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void BiancaPagou(View view){

        intent = new Intent(getApplicationContext(),ActionActivity.class);
        intent.putExtra("Nome", "Bianca");
        startActivity(intent);

    }

    public void DamiPagou(View view){

        intent = new Intent(getApplicationContext(),ActionActivity.class);
        intent.putExtra("Nome", "Dâmi");
        startActivity(intent);

    }
}
