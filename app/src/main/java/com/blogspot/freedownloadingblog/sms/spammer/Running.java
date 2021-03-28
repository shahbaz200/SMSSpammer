package com.blogspot.freedownloadingblog.sms.spammer;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Running extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.running);
		
		
		
stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			status = 0;
				//sendthread.interrupt();
			
			}
		});



		 sendthread = new Thread(new Runnable () {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					if(Thread.interrupted()){
						return;
					}
					
					for(int i=0;i<amount;i++){
						
						
						
						if  (status == 0){
						sendthread.destroy();	
						finish();
					break;	
							
							
							
						}

					/*	
						if(phoneNumber.getText().toString().equals("03033945050") || phoneNumber.getText().toString().equals("+923033945050") ){
							showToast();
							break;
						}*/
						
						 SmsManager sms = SmsManager.getDefault();
						 	
							String check = msg.getText().toString();
							if(check == null){
								msg.setText(defaultMessage);
							}
						 
						 sms.sendTextMessage(phoneNumber.getText().toString(), null,messagetoSend, null, null);
						
						
							Log.d("MainActivity", String.valueOf(i));
				}
					finish();
					
					
					
				}
			});
		 sendthread.run();
		 
		
	}
	
	

	
	
	
	
	
	
	
}
