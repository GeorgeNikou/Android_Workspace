package ca.herzing.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class TipCalculator extends AppCompatActivity {

    ImageButton btn_back2;

    TextView tip;
    TextView amount;
    CheckBox include;

    EditText editText_bill;
    EditText editText_gst;
    EditText editText_hst;
    EditText editText_tip;
    EditText editText_numPeople;

    ImageButton btn_calculateTot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //back button redirection (intent) //
        btn_back2 = findViewById(R.id.btn_back2);

        tip = findViewById(R.id.tip);
        amount = findViewById(R.id.amount);
        include = findViewById(R.id.include);

        editText_bill = findViewById(R.id.editText_bill);
        editText_gst = findViewById(R.id.editText_gst);
        editText_hst = findViewById(R.id.editText_hst);
        editText_tip = findViewById(R.id.editText_tip);
        editText_numPeople = findViewById(R.id.editText_numPeople);


        btn_calculateTot = findViewById(R.id.btn_calculateTot);
        btn_calculateTot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double total = 0;
                double hst = 0;
                double gst = 0;
                double tip = 0;

                if (include.isChecked()) {

                    total = Double.parseDouble(editText_bill.getText().toString());

                    total = total + (total * (Double.parseDouble(editText_tip.getText().toString())) / 100);

                    gst = (total * (Double.parseDouble(editText_gst.getText().toString())) / 100);

                    hst = (total * (Double.parseDouble(editText_hst.getText().toString())) / 100);

                    total = total + gst + hst;

                    total = total / Double.parseDouble(editText_numPeople.getText().toString());

                    amount.setText("" + total);

                } else {

                    total = Double.parseDouble(editText_bill.getText().toString());

                    tip = (total * (Double.parseDouble(editText_tip.getText().toString())) / 100);

                    gst = (total * (Double.parseDouble(editText_gst.getText().toString())) / 100);

                    hst = (total * (Double.parseDouble(editText_hst.getText().toString())) / 100);

                    total = total + tip + gst + hst;

                    total = total / Double.parseDouble(editText_numPeople.getText().toString());

                    amount.setText("" + total);
                }

            }
        });

        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backTip();
            }
        });



    }

    public void backTip(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }


}
