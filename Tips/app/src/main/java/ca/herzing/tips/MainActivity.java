package ca.herzing.tips;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * variable text fields
     */
    private EditText amount;
    private EditText percent_tip;
    private EditText num_people;
    private EditText txt_tipPerPersonBT;

    /**
     * variable buttons
     */
    private ImageView btn_caclTotal;
    private ImageView btn_calcTip;
    private ImageView btn_tipPerPerson;
    private Button btn_resetAll;
    private Button btn_submitAll;
    private ImageView btn_tipBeforeTax;
    private ImageView btn_amountPeople;
    private ImageView btn_BT;

    /**
     * bottom variable text fields
     */
    private EditText txt_total;
    private EditText txt_calcTip;
    private EditText txt_tipPerPerson;
    private EditText txt_amountPeople;
    private EditText txt_beforeTax;

    /**
     * declared global for use of other methods
     */
    private double tmp;
    private double totalTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initializing our variables
         */
        amount = findViewById(R.id.amount);
        percent_tip = findViewById(R.id.percent_tip);
        num_people = findViewById(R.id.num_people);
        btn_caclTotal = findViewById(R.id.btn_caclTotal);
        btn_calcTip = findViewById(R.id.btn_calcTip);
        btn_tipPerPerson = findViewById(R.id.btn_tipPerPerson);
        txt_total = findViewById(R.id.txt_total);
        txt_calcTip = findViewById(R.id.txt_calcTip);
        txt_tipPerPerson = findViewById(R.id.txt_tipPerPerson);
        txt_amountPeople = findViewById(R.id.txt_amountPeople);
        txt_beforeTax = findViewById(R.id.txt_beforeTax);
        btn_resetAll = findViewById(R.id.btn_resetAll);
        btn_tipBeforeTax = findViewById(R.id.btn_tipBeforeTax);
        btn_amountPeople = findViewById(R.id.btn_amountPeople);
        btn_submitAll = findViewById(R.id.btn_submitAll);
        txt_tipPerPersonBT = findViewById(R.id.txt_tipPerPersonBT);
        btn_BT = findViewById(R.id.btn_BT);


        /**
         * Listener for the calculated amount button
         */
        btn_caclTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "AMOUNT NOT GIVEN.", Toast.LENGTH_LONG).show();
                } else {
                    calculateTotal();
                }

            }
        });


        /**
         * Listener for the total tip giving
         */
        btn_calcTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (percent_tip.getText().toString().equals("")) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Tip percentage not given.", Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast1.show();
                } else {
                    if (Integer.parseInt(percent_tip.getText().toString()) <= 12) {
                        Toast toast2 = Toast.makeText(getApplicationContext(), "That's a low tip amount.", Toast.LENGTH_LONG);
                        toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                        toast2.show();
                        calculateTip();
                    } else if (Integer.parseInt(percent_tip.getText().toString()) == 15) {
                        Toast toast3 = Toast.makeText(getApplicationContext(), "That's a good tip.", Toast.LENGTH_LONG);
                        toast3.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                        toast3.show();
                        calculateTip();
                    } else if (Integer.parseInt(percent_tip.getText().toString()) >= 18 && Integer.parseInt(percent_tip.getText().toString()) < 20) {
                        Toast toast4 = Toast.makeText(getApplicationContext(), "That's a great tip!", Toast.LENGTH_LONG);
                        toast4.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                        toast4.show();
                        calculateTip();
                    } else if (Integer.parseInt(percent_tip.getText().toString()) >= 20) {
                        calculateTip();
                        Toast toast5 = Toast.makeText(getApplicationContext(), "That's an incredible tip!", Toast.LENGTH_LONG);
                        toast5.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                        toast5.show();
                        calculateTip();
                    } else
                        calculateTip();
                }
            }
        });

        /**
         * Listener for the tip per person
         */
        btn_tipPerPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num_people.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "number of people paying tip not given.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                } else
                    tipSplitting();
            }
        });


        /**
         * Listener to divide total depended on number of people
         */
        btn_amountPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num_people.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Amount not given or total not calculated!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                }else {
                    divideTotalPerPerson();
                }

            }
        });

        /**
         * Listener for calculating tip amount before tax
         */
        btn_tipBeforeTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().equals("") || percent_tip.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Must enter the amount and tip percentage!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                } else {
                    tipBeforeTax();
                }
            }
        });

        /**
         * Listener to calculate tip for each person before tax is implemented
         */
        btn_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_beforeTax.getText().toString().equals("") || num_people.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Must calculate tip before tax first!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                } else {
                    tipBeforeTaxPerPerson();
                }

            }
        });

        /**
         * Listener to create all calculations instantly
         */
        btn_submitAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amount.getText().toString().equals("") || percent_tip.getText().toString().equals("") || num_people.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "One or more fields are empty.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                } else {
                    calculateTotal();
                    calculateTip();
                    tipSplitting();
                    divideTotalPerPerson();
                    tipBeforeTax();
                    tipBeforeTaxPerPerson();
                }

            }
        });

        /**
         * Listener to reset all fields
         */
        btn_resetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!amount.getText().toString().equals("") || !percent_tip.getText().toString().equals("") || !num_people.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "All fields have been reset.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                    toast.show();
                    resetAll();
                }else
                    resetAll();

            }
        });

    }


    /**
     * Function to calculate the total amount plus Quebec Taxes
     */
    public void calculateTotal() {
        double gst = Double.parseDouble(amount.getText().toString()) * 5d / 100d;
        double qst = Double.parseDouble(amount.getText().toString()) * 9.975d / 100d;
        double tax = qst + gst;
        tmp = Double.parseDouble(amount.getText().toString());
        tmp += tax;
        double roundedTmp = Math.round(tmp * 100d) / 100d;

        txt_total.setText(roundedTmp + "");
    }


    /**
     * Function to calculate the total tip
     */
    public void calculateTip() {
        double tipGiving = Double.parseDouble(percent_tip.getText().toString());
        totalTip = tmp * tipGiving / 100d;
        double roundTmp = Math.round(totalTip * 100d) / 100d;
        txt_calcTip.setText(roundTmp + "");
    }

    /**
     * Function to split the tip distribution
     */
    public void tipSplitting() {
        double tip;
        tip = totalTip / Integer.parseInt(num_people.getText().toString());
        double roundTmp = Math.round(tip * 100d) / 100d;
        txt_tipPerPerson.setText(roundTmp + "");
    }


    /**
     * Function to reset all the fields
     */
    public void resetAll() {
        amount.setText("");
        percent_tip.setText("");
        num_people.setText("");
        txt_total.setText("");
        txt_calcTip.setText("");
        txt_tipPerPerson.setText("");
        txt_amountPeople.setText("");
        txt_beforeTax.setText("");
        txt_tipPerPersonBT.setText("");
    }

    /**
     * Function to divide total per person
     */
    public void divideTotalPerPerson() {
        double amount = Double.parseDouble(txt_total.getText().toString());
        int people = Integer.parseInt(num_people.getText().toString());
        double amountShared = amount / people;
        double tmp = Math.round(amountShared * 100d) / 100d;
        txt_amountPeople.setText(tmp + "");

    }


    /**
     * Function to give tip before tax
     */
    public void tipBeforeTax() {
        double subTotal = Double.parseDouble(amount.getText().toString());
        double percent = Double.parseDouble(percent_tip.getText().toString());
        double tmp = subTotal * percent / 100d;
        double tmp2 = Math.round(tmp * 100d) / 100d;
        txt_beforeTax.setText(tmp2 + "");
    }

    /**
     * Function to give tip amount per person before tax
     */
    public void tipBeforeTaxPerPerson() {
        double beforeTax = Double.parseDouble(txt_beforeTax.getText().toString());
        int numOfPeople = Integer.parseInt(num_people.getText().toString());
        double totalPerPersonBT = beforeTax / numOfPeople;
        double rounded = Math.round(totalPerPersonBT * 100d) / 100d;
        txt_tipPerPersonBT.setText(rounded + "");
    }
}
