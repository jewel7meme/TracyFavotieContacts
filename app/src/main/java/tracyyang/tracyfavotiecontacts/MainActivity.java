package tracyyang.tracyfavotiecontacts;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
View.OnClickListener{

    ImageButton btnCall1, btnCall2, btnCall3, btnSMS1, btnSMS2, btnSMS3;
    Resources res;
    String[] messagesArray;
    String sMessage;
    String[] numbersArray;
    TextView textViewStatus;
    Spinner spinnerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        messagesArray = res.getStringArray(R.array.messages_array);
        numbersArray = res.getStringArray(R.array.numbers_array);

        btnCall1 = (ImageButton) findViewById(R.id.callBtn1);
        btnCall1.setOnClickListener(this);
        btnCall2 = (ImageButton) findViewById(R.id.callBtn2);
        btnCall2.setOnClickListener(this);
        btnCall3 = (ImageButton) findViewById(R.id.callBtn3);
        btnCall3.setOnClickListener(this);

        btnSMS1 = (ImageButton) findViewById(R.id.smsBtn1);
        btnSMS1.setOnClickListener(this);
        btnSMS2 = (ImageButton) findViewById(R.id.smsBtn2);
        btnSMS2.setOnClickListener(this);
        btnSMS3 = (ImageButton) findViewById(R.id.smsBtn3);
        btnSMS3.setOnClickListener(this);

        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        //---------------------------------------------
        spinnerMessage = (Spinner) findViewById(R.id.spinner1);
        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, messagesArray) {

            /*public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                else
                    return true;
            }*/

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerMessage.setAdapter(dataAdapter);


        spinnerMessage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                //On selecting a spinner item
                sMessage = adapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //--------------------------------------------------

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.callBtn1:
                textViewStatus.setText("Code should call: " + numbersArray[0]);
                callPhoneNumber(numbersArray[0]);
                //callPhoneNumber("(218) 123 4567");
                break;

            case R.id.callBtn2:
                //callPhoneNumber("(218) 456 7890");
                textViewStatus.setText("Code should call: " + numbersArray[1]);
                callPhoneNumber(numbersArray[1]);
                break;

            case R.id.callBtn3:
                //callPhoneNumber("(218) 789 1234");
                textViewStatus.setText("Code should call: "+numbersArray[2]);
                callPhoneNumber(numbersArray[2]);
                break;

            case R.id.smsBtn1:
                //sendSMS("(218) 123 4567");
                textViewStatus.setText("Code should send SMS to: " + numbersArray[0]);
                sendSMS(numbersArray[0]);
                break;

            case R.id.smsBtn2:
                //sendSMS("(218) 456 7890");
                textViewStatus.setText("Code should send SMS to: " + numbersArray[1]);
                sendSMS(numbersArray[1]);
                break;

            case R.id.smsBtn3:
                //sendSMS("(218) 789 1234");
                textViewStatus.setText("Code should send SMS to: " + numbersArray[2]);
                sendSMS(numbersArray[2]);
                break;

            default:
                break;
        }
    }


    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendSMS(String phoneNumber){
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("sms:" + phoneNumber));
        sendIntent.putExtra("sms_body", sMessage);
        startActivity(sendIntent);
    }


}
