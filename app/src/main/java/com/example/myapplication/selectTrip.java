package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class selectTrip extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ApiInterface apiInterface;
    RelativeLayout mainRl;
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
        recyclerView = findViewById(R.id.recyclerview);
        relativeLayout = findViewById(R.id.no_data_found);
        relativeLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        mainRl = findViewById(R.id.mainRelativeLayout);
        mainRl.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        ArrayList<String> trip = new ArrayList<String>();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                getData(editable.toString());
            }
        });
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


                if((business.isChecked() || leisure.isChecked()) && !editText.getText().toString().isEmpty()) {
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

    private void getData(String text){
        apiInterface.getplace(text, getString(R.string.api_key)).enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                if(response.isSuccessful()){
                    relativeLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(response.body().getPredictions());
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
                else{
                    relativeLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                relativeLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}