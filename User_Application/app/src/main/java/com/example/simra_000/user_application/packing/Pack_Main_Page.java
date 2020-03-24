package com.example.simra_000.user_application.packing;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.simra_000.user_application.R;

public class Pack_Main_Page extends AppCompatActivity {

    CardView business,leisure;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack__main__page);
      //  spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //spinner.setAdapter(adapter);

        //pack_date=(EditText)findViewById(R.id.pack_date);
        business=(CardView) findViewById(R.id.business);
        leisure=(CardView) findViewById(R.id.leisure);

       // business.setTag(i);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Pack_Main_Page.this,Business_Trip.class);
                startActivity(intent);
            }
        });
       // leisure.setTag(i);
        leisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int pos=(int)v.getTag();
                Intent intent = new Intent(Pack_Main_Page.this,Lesiure_Trip.class);
               // intent.putExtra("pid",CardView.get(pos).getId());
                startActivity(intent);
            }
        });

       /* calendar= Calendar.getInstance();
        day=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);

        month=month+1;
        pack_date.setText(day+"/"+month+"/"+year);

        pack_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(Pack_Main_Page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month =month+1;
                        pack_date.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,day);
                pickerDialog.show();


                //new DatePickerDialog(Hotel_Page.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/
    }
}
