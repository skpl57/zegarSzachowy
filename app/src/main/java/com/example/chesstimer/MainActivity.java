package com.example.chesstimer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button redBtn, purpleBtn;
    private Gracz gracz1, gracz2;

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
        int gracz1Sekund = 180;
        int gracz2Sekund = 180;
        if(savedInstanceState != null){
            gracz1Sekund = savedInstanceState.getInt("GRACZ1", 180);
            gracz2Sekund = savedInstanceState.getInt("GRACZ2", 180);
        }
        redBtn = findViewById(R.id.redBtn);
        purpleBtn = findViewById(R.id.purpleBtn);

        gracz1 = new Gracz(gracz1Sekund, true, redBtn);
        gracz2 = new Gracz(gracz2Sekund, false, purpleBtn);

        Handler handler = new Handler();

        handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(savedInstanceState != null){
                            savedInstanceState.putInt("GRACZ1", gracz1.getIleSekund());
                            savedInstanceState.putInt("GRACZ2", gracz2.getIleSekund());
                        }
                        handler.postDelayed(this, 100);
                    }});

        redBtn.setOnClickListener(
                    v ->{
                        if(!gracz1.isCzyAktywny()) return;
                        gracz1.dodajCzas();
                        odwrocCzas();
                    }
        );
        purpleBtn.setOnClickListener(
                v ->{
                    if(!gracz2.isCzyAktywny()) return;
                    gracz2.dodajCzas();
                    odwrocCzas();
                }
        );
    }
    private void odwrocCzas(){
        gracz1.odwrocAktywnosc();
        gracz2.odwrocAktywnosc();
    }
}