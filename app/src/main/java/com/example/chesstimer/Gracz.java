package com.example.chesstimer;

import android.os.CountDownTimer;
import android.widget.Button;

public class Gracz {
    private int ileSekund;
    private boolean czyAktywny;
    private CountDownTimer countDownTimer;
    private Button button;

    public Gracz(int ileSekund, boolean czyAktywny, Button button) {
        this.ileSekund = ileSekund;
        this.czyAktywny = czyAktywny;
        this.button = button;
        button.setText(preetyText());
        if(czyAktywny){
            uruchomZegar();
        }
    }
    private void uruchomZegar(){
        countDownTimer = new CountDownTimer(ileSekund * 1000L,100) {
            @Override
            public void onFinish() {

            }

            @Override
            public void onTick(long l) {
                ileSekund = (int) l/1000;
                button.setText(preetyText());
            }
        };
        countDownTimer.start();
    }
    private void zatrzymajZegar(){
        countDownTimer.cancel();
    }
    public void odwrocAktywnosc(){
        czyAktywny = !czyAktywny;
        if(czyAktywny) uruchomZegar();
        else zatrzymajZegar();
    }

    public boolean isCzyAktywny() {
        return czyAktywny;
    }
    public void dodajCzas(){
        ileSekund += 5;
    }
    private String preetyText(){
        int sekund = ileSekund%60;
        int minut  = (ileSekund/60) % 60;
        return String.format("%02d:%02d", minut, sekund);
    }

    public int getIleSekund() {
        return ileSekund;
    }
}
