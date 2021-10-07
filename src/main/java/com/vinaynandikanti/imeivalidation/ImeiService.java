package com.vinaynandikanti.imeivalidation;


import org.springframework.stereotype.Service;

@Service
public class ImeiService {
	 public int getCheckDigit(String imei) {
	     int sum = 0;
	     for(int i = 13;i>=0;i=i-1) {
	         String sDigit = imei.substring(i,i+1);
	         int digit = Integer.valueOf(sDigit);
	         if(i%2==0) {
	             sum = sum + digit;
	         }else {
	             sum = sum + sumOfDigits(digit*2);
	         }
	     }
	     sum = sum * 9;
	     return sum%10;         
	 }
	  
	 public static int sumOfDigits(int number) {
	     int sum=0;
	     while(number > 0) {
	         sum += number%10;
	         number = number/10;
	     }
	     return sum;
	 }


    public boolean validateTheNumber(int checkImei,int lastDigit) {
    	
    	if(checkImei==lastDigit) 
        return true;
    	
    	else 
    		
    		return false;
    
    	
    }
}

	
