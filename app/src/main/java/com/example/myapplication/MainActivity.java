package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText et1 , et2 ,et3;
    TextView tx1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et1 = findViewById(R.id.editTextText);
        et2 = findViewById(R.id.editTextText2);
        et3 = findViewById(R.id.editTextText3);
        tx1 = findViewById(R.id.textView);
        button1 = findViewById(R.id.button);

        et1.addTextChangedListener(watcher);
        et2.addTextChangedListener(watcher);
        et3.addTextChangedListener(watcher);

      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              calculerMensualite();
          }
      });


    }
    private void calculerMensualite() {
        String capitalStr = et1.getText().toString();
        String tauxStr = et2.getText().toString();
        String dureeStr = et3.getText().toString();

        if (!capitalStr.isEmpty() && !tauxStr.isEmpty() && !dureeStr.isEmpty()) {
            double capital = Double.parseDouble(capitalStr);
            double taux = Double.parseDouble(tauxStr) / 100; // Convertir en décimal
            int duree = Integer.parseInt(dureeStr);

            // Calcul de la mensualité
            double tauxMensuel = taux / 12;
            double mensualite = (capital * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -duree));

            // Affichage du résultat
            tx1.setText("Mensualité : " + String.format("%.2f", mensualite) + " €");
        } else {
            tx1.setText("Veuillez remplir tous les champs !");
        }
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,int count) {
            // Appel de la méthode calculerMensualité

            calculerMensualite();


        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };



}