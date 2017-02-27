package tracyyang.tracyfavotiecontacts;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
View.OnClickListener{

    ImageButton btnCall1, btnCall2, btnCall3, btnSMS1, btnSMS2, btnSMS3;
    //Resources res = getResources();
    //String[] namesArray = res.getStringArray(R.array.names_array);
    //String[] numbersArray = res.getStringArray(R.array.numbers_array);
    TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.callBtn1:
                callPhoneNumber("(218) 123 4567");
                textViewStatus.setText("Code should call the first number");
                break;

            case R.id.callBtn2:
                callPhoneNumber("(218) 456 7890");
                textViewStatus.setText("Code should call the second number");
                break;

            case R.id.callBtn3:
                callPhoneNumber("(218) 789 1234");
                textViewStatus.setText("Code should call the third number");
                break;

            case R.id.smsBtn1:
                sendSMS("(218) 123 4567");
                textViewStatus.setText("Code should send SMS to the first number");
                break;

            case R.id.smsBtn2:
                sendSMS("(218) 456 7890");
                textViewStatus.setText("Code should send SMS to the second number");
                break;

            case R.id.smsBtn3:
                sendSMS("(218) 789 1234");
                textViewStatus.setText("Code should send SMS to the third number");
                break;

            default:
                break;
        }
    }

    /*private void setupButtonClickEvents(){
        /**
         *   Set up button click event listener for the web info button for the first performance


        btnCall1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            callPhoneNumber("(218) 123 4567");
                //callPhoneNumber(numbersArray[0]);
                textViewStatus.setText("Code should call the first number");
            }
        });

    }*/

    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendSMS(String phoneNumber){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + phoneNumber));
        sendIntent.putExtra("sms_body", "Hi");
        startActivity(sendIntent);
    }


}
