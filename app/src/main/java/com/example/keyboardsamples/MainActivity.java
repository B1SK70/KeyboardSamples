package com.example.keyboardsamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnShow;
    Button btnTime;
    EditText keyboard;
    Spinner selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button) findViewById(R.id.showText);
        btnTime = (Button) findViewById(R.id.btnTime);
        keyboard = (EditText) findViewById(R.id.phone);
        selector = (Spinner) findViewById(R.id.selector);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.selectorOptions,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector.setAdapter(adapter);



        selector.setOnItemSelectedListener(this);
        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showWroted();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment  = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), null);
            }
        });
    }

    public void showWroted() {
        Toast toast = Toast.makeText(this.getApplicationContext(), keyboard.getText(), Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selected = (String) parent.getItemAtPosition(pos);
        switch (selected) {
            case "Home":
                keyboard.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case "Work":
                keyboard.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case "Other":
                keyboard.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case "Custom":
                keyboard.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        public TimePickerFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        }
    }

}

