package ca.herzing.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class DashBoard extends AppCompatActivity {

    //declared variable buttons
    ImageButton aboutMe_btn;
    ImageButton btn_tipCalculator;
    ImageButton btn_colorSwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMe_btn = findViewById(R.id.btn_aboutMe);
        btn_tipCalculator = findViewById(R.id.btn_tipCalc);
        btn_colorSwi = findViewById(R.id.btn_colorSwitches);


        aboutMe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout();
            }
        });

        btn_tipCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipCalc();
            }
        });

        btn_colorSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorSwitch();
            }
        });


    }



    public void openAbout(){
        Intent intent1 = new Intent(this, aboutme.class);
        startActivity(intent1);
    }

    public void tipCalc(){
        Intent intent2 = new Intent(this, TipCalculator.class);
        startActivity(intent2);
    }

    public void colorSwitch(){
        Intent intent3 = new Intent(this, ColorSwitcher.class);
        startActivity(intent3);
    }
}
