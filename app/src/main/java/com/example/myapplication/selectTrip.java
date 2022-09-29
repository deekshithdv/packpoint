package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class selectTrip extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trip);
        Button submit = (Button)findViewById(R.id.bsubmit);
        CheckBox business = (CheckBox) findViewById(R.id.formal);
        CheckBox leisure = (CheckBox) findViewById(R.id.working);
        EditText editText = (EditText) findViewById(R.id.textView3);
        TextView date = (TextView)findViewById(R.id.dateset);
        Button datePicker = (Button) findViewById(R.id.datepicker);
        ArrayList<String> trip = new ArrayList<String>();
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });
        /** Called when the user taps the Send button */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                ArrayList<String> trip = new ArrayList<String>();
                Intent intent = new Intent(getApplicationContext(), commonContent.class);
                if(business.isChecked()) {
                    trip.add("business");
                }
                if(leisure.isChecked()) {
                   trip.add("leisure");
                }
                intent.putExtra("Destination", editText.getText().toString());
                intent.putExtra("Date", date.getText());
                intent.putExtra("selected", trip);


                if(business.isChecked() || leisure.isChecked()) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        TextView lol = (TextView) findViewById(R.id.dateset);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        lol.setText(selectedDate);
    }
}