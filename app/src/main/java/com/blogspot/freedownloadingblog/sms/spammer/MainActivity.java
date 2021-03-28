package com.blogspot.freedownloadingblog.sms.spammer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected EditText phoneNumber;
    protected EditText SmsAmount;
    protected Button start;
    protected Button stop;
    protected TextView sent;
    protected int amount;
    protected String defaultMessage;
    protected boolean running = false;
    protected EditText msg;
    protected TextView msgtosent;
    protected String messagetoSend;
    protected Thread sendthread;
    protected int status;
    private Handler mHandler = new Handler();
    protected Integer numb;
    protected Button pickContact;
    static final int RQS_PICK_CONTACT = 1;


    private void loopInAnotherThread() {
        new Thread(new Runnable() {
            public void run() {


                for (int i = 0; i < amount; i++) {

                  final  ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

                    if (status == 0) {

                        break;


                    }

					/*	
                        if(phoneNumber.getText().toString().equals("03033945050") || phoneNumber.getText().toString().equals("+923033945050") ){
							showToast();
							break;
						}*/

                    SmsManager sms = SmsManager.getDefault();

                    String check = msg.getText().toString();
                    if (check == null) {
                        msg.setText(defaultMessage);
                    }

                    sms.sendTextMessage(phoneNumber.getText().toString(), null, messagetoSend, null, null);

                    numb = i+1;
                   // Log.d("MainActivity", String.valueOf(i));

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // This gets executed on the UI thread so it can safely modify Views
                            sent.setText(String.valueOf(numb));
                            int progamount = (numb * 100 / amount) ;
                            progressBar.setProgress(progamount);


                        }
                    });

                }


            }
        }).start();
    }


    public void showToast() {
        Toast.makeText(this, "I cant spam developer!!!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RQS_PICK_CONTACT){
            if(resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cursor = managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();

                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //contactName.setText(name);
                phoneNumber.setText(number);
                //contactEmail.setText(email);

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-7437698934008715~2473296939");
        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-7437698934008715~2473296939");
        AdView mAdView = (AdView) findViewById(R.id.adView);
       /* AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("4908214C602C3463")
                .build();
        mAdView.loadAd(adRequest);
*/
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        }

        msgtosent = (TextView) findViewById(R.id.msgtosent);
        msg = (EditText) findViewById(R.id.msge);
        phoneNumber = (EditText) findViewById(R.id.phonenumber);
        SmsAmount = (EditText) findViewById(R.id.smsamountbox);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        sent = (TextView) findViewById(R.id.sent);


        defaultMessage = "Are you angry? No? I'm sure you are gonna be mad soon!!";
        msg.setText(defaultMessage);

        sendthread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                if (Thread.interrupted()) {
                    return;
                }

                for (int i = 0; i < amount; i++) {


                    if (status == 0) {

                        break;


                    }

			/*	
				if(phoneNumber.getText().toString().equals("03033945050") || phoneNumber.getText().toString().equals("+923033945050") ){
					showToast();
					break;
				}*/

                    SmsManager sms = SmsManager.getDefault();

                    String check = msg.getText().toString();
                    if (check == null) {
                        msg.setText(defaultMessage);
                    }

                   sms.sendTextMessage(phoneNumber.getText().toString(), null, messagetoSend, null, null);


                  //  Log.d("MainActivity", String.valueOf(i));





                }


            }
        });


        try {
            amount = (Integer.parseInt(SmsAmount.getText().toString()));
        } catch (Exception e) {
            Log.d("MainActivity", "error parsing numbers to int");
        }


        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                status = 1;

                try {

                    if(phoneNumber.getText().toString().contains("+")){
                        phoneNumber.getText().toString().replaceAll("\\+", "");
                    }
                    if(phoneNumber.getText().toString().contains(" ")){
                        phoneNumber.getText().toString().replaceAll(" ", "");
                    }



                    amount = Integer.parseInt(SmsAmount.getText().toString());
                } catch (Exception e) {
                    Log.d("MainActivity", "error parsing numbers to int");
                }

                messagetoSend = msg.getText().toString();
                if (messagetoSend.equals("")) {
                    messagetoSend = defaultMessage;
                }
                loopInAnotherThread();
                //Sender send = new Sender();
				
			/*	if(sendthread.isAlive()){
					sendthread.run();
				}*/
                //	Intent intent = new Intent(MainActivity.this, Running.class);
                //	sendthread.start();
                //Start st = new Start(phoneNumber.getText().toString(), amount, messagetoSend, running);
                //	st.SendSMS();

                //	send.doInBackground(null);
				
				/*for(int i=0;i<amount;i++){
					
					
					
					if  (running = false){
						running = true;
					}
		
					
					if(phoneNumber.getText().toString().equals("03033945050") || phoneNumber.getText().toString().equals("+923033945050") ){
						showToast();
						break;
					}
					
					 SmsManager sms = SmsManager.getDefault();
					 	
						String check = msg.getText().toString();
						if(check == null){
							msg.setText(defaultMessage);
						}
					 
					 sms.sendTextMessage(phoneNumber.getText().toString(), null,messagetoSend, null, null);
					
					 sent.setText(String.valueOf(i));
				}*/


            }
        });


        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                status = 0;
                //sendthread.interrupt();

            }
        });


    pickContact = (Button) findViewById(R.id.pickcontact);

    pickContact.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            startActivityForResult(intent, 1);

        }



    });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


/*class Sender extends AsyncTask<Void, Void, Void>{

	@Override
	protected Void doInBackground(Void... params) {
		
		for(int i=0;i<amount;i++){
			
			
			
			if  (running = false){
				running = true;
				break;
			}

			
			if(phoneNumber.getText().toString().equals("03033945050") || phoneNumber.getText().toString().equals("+923033945050") ){
				showToast();
				break;
			}
			
			 SmsManager sms = SmsManager.getDefault();
			 	
				String check = msg.getText().toString();
				if(check == null){
					msg.setText(defaultMessage);
				}
			 
			 sms.sendTextMessage(phoneNumber.getText().toString(), null,messagetoSend, null, null);
			
			 sent.setText(String.valueOf(i)+1);
		}
		
		
		
		return null;
	}
	
}*/


}