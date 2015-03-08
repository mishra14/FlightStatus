package com.example.ankit.flightstatus;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class HomePage extends Activity
{
    private GregorianCalendar selectedDate;
    private GregorianCalendar flightDate;
    private String status="On Time";
    private boolean statusType=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_home_page);
        selectedDate=new GregorianCalendar();
        flightDate=new GregorianCalendar();
        flightDate.set(Calendar.HOUR_OF_DAY,18);
        flightDate.set(Calendar.MINUTE, 30);
        updateStatus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void updateStatus()
    {
        ((TextView)findViewById(R.id.status)).setText(status);
        ((TextView)findViewById(R.id.statusTime)).setText((status.equals("Canceled"))?"":(flightDate.get(Calendar.HOUR_OF_DAY)+":"+flightDate.get(Calendar.MINUTE)+" Departure"));
        ((ImageView) findViewById(R.id.foreGround)).setImageResource((statusType)?R.drawable.positive:R.drawable.negitive);
    }

    private TimePickerDialog showTimePickerDialog(int initialHour, int initialMinutes, boolean is24Hour, TimePickerDialog.OnTimeSetListener listener)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, listener, initialHour, initialMinutes, is24Hour);
        dialog.show();
        return dialog;
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            selectedDate.set(Calendar.HOUR_OF_DAY,hourOfDay);
            selectedDate.set(Calendar.MINUTE,minute);
            if(!status.equals("Canceled"))
            {
                long diff = flightDate.getTimeInMillis() - selectedDate.getTimeInMillis();
                if ((diff / (1000 * 60) > 120))
                {
                    status = (statusType) ? "On Time" : "Delayed";
                }
                else if (diff / (1000 * 60) > 60)
                {
                    status = "Delayed";
                    statusType = false;
                    flightDate.set(Calendar.HOUR_OF_DAY, 19);
                }
                else if ((diff / (1000 * 60) < 60) && (diff / (1000 * 60) > 0))
                {
                    if (diff / (1000 * 60) < 20)
                    {
                        status = "Gate Closed";
                    }
                    else
                    {
                        status = "Boarding";
                    }
                }
                else
                {
                    status = "Departed";
                }
                updateStatus();
            }
        }
    };
    public void changeTime(View view)
    {
        showTimePickerDialog(0,0,true,timeSetListener);
    }

    public void cancelFlight(View view)
    {
        if(status.equals("Canceled"))
        {
            status="On Time";
            flightDate.set(Calendar.HOUR_OF_DAY,18);
            statusType=true;
            ((Button)findViewById(R.id.cancelFlight)).setText("Cancel Flight");
            ((Button)findViewById(R.id.changeTime)).setVisibility(View.VISIBLE);
        }
        else
        {
            status="Canceled";
            flightDate.set(Calendar.HOUR_OF_DAY,18);
            statusType=false;
            ((Button)findViewById(R.id.cancelFlight)).setText("Reset Flight");
            ((Button)findViewById(R.id.changeTime)).setVisibility(View.INVISIBLE);
        }
        updateStatus();
    }
}
