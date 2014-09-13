package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;

import java.util.Calendar;


public class Input extends Activity {

    int mYear;
    int mMonth;
    int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button btn_los = (Button)findViewById(R.id.btn_leaveinput);

        btn_los.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Input.this, Suggestions_.class);
                startActivity(intent);

            }
        });

        final Button btn_DateStart = (Button)findViewById(R.id.btn_StartDate);
        final Button btn_DateEnd = (Button)findViewById(R.id.btn_EndDate);
        final Button btn_VisitCount = (Button)findViewById(R.id.btn_VisitCount);
        final Button btn_BudgetClass = (Button)findViewById(R.id.btn_BudgetClass);
        final Button btn_VisitIntensity = (Button)findViewById(R.id.btn_VisitIntensity);
        final EditText btn_location = (EditText)findViewById(R.id.btn_Location);

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_location.setText("");
            }
        });

        btn_DateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(Input.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                btn_DateStart.setText("vom " + dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                                MyMind.startDate=dayOfMonth + "-" + (monthOfYear+1) + "-" + year;

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        btn_DateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(Input.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                btn_DateEnd.setText("bis zum " + dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                                MyMind.endDate=dayOfMonth + "-" + (monthOfYear+1) + "-" + year;

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });


        btn_VisitCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        btn_BudgetClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbudget();
            }
        });

        btn_VisitIntensity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIntensity();
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {

            Intent intent = new Intent(Input.this, AboutScreen.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void show()
    {

        final Button btn_VisitCount = (Button)findViewById(R.id.btn_VisitCount);

        final Dialog d = new Dialog(Input.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.budgetslider);
        np.setMaxValue(20);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                btn_VisitCount.setText("Bereits " + String.valueOf(np.getValue()) + " Besuche");
                MyMind.earlierVisits=np.getValue();
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }




    public void showbudget()
    {

        final Button btn_BudgetClass = (Button)findViewById(R.id.btn_BudgetClass);

        final Dialog d = new Dialog(Input.this);
        d.setTitle("Budget");
        d.setContentView(R.layout.budget_slider_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final SeekBar np = (SeekBar) d.findViewById(R.id.budgetslider1);
        np.setMax(3);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (np.getProgress()==0) btn_BudgetClass.setText("Preisklasse: €");
                if (np.getProgress()==1) btn_BudgetClass.setText("Preisklasse: €€");
                if (np.getProgress()==2) btn_BudgetClass.setText("Preisklasse: €€€");
                if (np.getProgress()==3) btn_BudgetClass.setText("Preisklasse: €€€€");

                MyMind.budget=np.getProgress();

                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }



    public void showIntensity()
    {

        final Button btn_VisitIntensity = (Button)findViewById(R.id.btn_VisitIntensity);

        final Dialog d = new Dialog(Input.this);
        d.setTitle("Intensität");
        d.setContentView(R.layout.budget_slider_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final SeekBar np = (SeekBar) d.findViewById(R.id.budgetslider1);
        np.setMax(10);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                btn_VisitIntensity.setText("Intensität " + String.valueOf(np.getProgress()) +
                        " von" + String.valueOf(np.getMax()));

                MyMind.intensity=np.getProgress();

                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
}
