package com.blogspot.freedownloadingblog.sms.spammer;

import android.telephony.SmsManager;

public class Start {
	
	String phoneNumber;
	int amount;
	String msg;
	boolean running;
	public Start(String phoneNumber, int amount, String msgtosend, boolean running) {
		super();
		this.phoneNumber = phoneNumber;
		this.amount = amount;
		this.msg = msgtosend;
		this.running = running;
	}
	
	
	public void SendSMS(){
		
for(int i=0;i<amount;i++){
			
			
			
			if  (running = false){
				running = true;
			}

			
		/*	if(phoneNumber.equals("03033945050") || phoneNumber.equals("+923033945050") ){
				showToast();
				break;
			}*/
			
			 SmsManager sms = SmsManager.getDefault();
			 	
				
			 
			 sms.sendTextMessage(phoneNumber, null,msg, null, null);
			
		
		
		
	}
		
		
		
	}
	
	

}
