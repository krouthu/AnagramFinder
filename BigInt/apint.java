// Keerthana Routhu
// 24 April 2018
// krouthu@ucsc.edu
// PA1
// apint.java -- abstract precision integer class

public class apint {
	String val;

	// default constructor
	public apint () {
		val = "0";
	}
		
	// inputs a numerical String; constructs an apint() object
	public apint (String v) {
		val = v;
		if (val.charAt(0) == '+')	val = val.substring(1);
	}

	// inputs an int; converts into String and constructs an apint() object
	public apint (int v) {
		val = Integer.toString (v);
	}

	// inputs a double; casts to an int and converts the int into a String and then constructs an apint() object
	public apint (double v) {
		val = Integer.toString ((int)(v));
	}

	// print method
	public String print () {
		return val;
	}
		
	// addition method
	public apint addition (apint a) {
		String ans = "";
		apint ansA = null;
		int aLen = (a.val).length();
		int bLen = (val).length();
		apint aTemp = new apint(a.val);
		apint bTemp = new apint(val);
		int carry = 0;
		String neg = "";
			
		if (aTemp.val.charAt(0) == '-' && bTemp.val.charAt(0) == '-') {
			aTemp.val = aTemp.val.substring(1);
			bTemp.val = bTemp.val.substring(1);
			neg = "-";
			aLen--;
			bLen--;
		}
			
		else if (aTemp.val.charAt(0) == '-' || bTemp.val.charAt(0) == '-') {
			if (aTemp.val.charAt(0) == '-') {
				String negative = "";
				aTemp.val = aTemp.val.substring(1);
				ansA = aTemp.subtraction(bTemp);
				if (ansA.val.charAt(0) == '-') {
					ansA.val = ansA.val.substring(1);
					negative = "-";
				}
				int count = 0;
				
				for (int i = 0; i < ansA.val.length(); i++) {
					if (ansA.val.charAt(i) == '0') {
						count++;
					}
					else break;
				}
					
				ansA.val = ansA.val.substring(count);
				ansA.val = negative + ansA.val;
				if (ansA.val.equals("") || ansA.val.equals("-"))		ansA.val = "0";
				return ansA;
			}
			if (bTemp.val.charAt(0) == '-') {
				String negative = "";
				bTemp.val = bTemp.val.substring(1);
				ansA = bTemp.subtraction(aTemp);
				if (ansA.val.charAt(0) == '-') {
					ansA.val = ansA.val.substring(1);
					negative = "-";
				}
				int count = 0;
					
				for (int i = 0; i < ansA.val.length(); i++) {
					if (ansA.val.charAt(i) == '0') {
						count++;
					}
					else break;
				}
				
				ansA.val = ansA.val.substring(count);
				ansA.val = negative + ansA.val;
				if (ansA.val.equals("") || ansA.val.equals("-"))		ansA.val = "0";
				return ansA;
			}
		}
			
		else ;
			
		while (aLen > 0 || bLen > 0) {
			int dig = 0;
			if (aLen == 0 || bLen == 0) {
				if (aLen == 0) {
					dig = Integer.parseInt((bTemp.val).substring(bLen-1, bLen)) + carry;
					carry = dig / 10;
					dig = dig % 10;
				}
				else	 {
					dig = Integer.parseInt((aTemp.val).substring(aLen-1, aLen)) + carry;
					carry = dig / 10;
					dig = dig % 10;
				}
			}
			else {
				int tempA = Integer.parseInt((aTemp.val).substring(aLen-1, aLen));
				int tempB = Integer.parseInt((bTemp.val).substring(bLen-1, bLen));
				dig = tempA + tempB + carry;
				carry = dig/10;
				dig = dig % 10;
			}
			ans = Integer.toString(dig) + ans;
			if (aLen > 0)	aLen--;
			if (bLen > 0)	bLen--;
			if (aLen == 0 && bLen == 0)	ans = Integer.toString(carry) + ans;
		}
		ansA = new apint (ans);
		int count = 0;
			
		for (int i = 0; i < ansA.val.length(); i++) {
			if (ansA.val.charAt(i) == '0') {
				count++;
			}
			else break;
		}
			
		ansA.val = ansA.val.substring(count);
		ansA.val = neg + ansA.val;
		if (ansA.val.equals("") || ansA.val.equals("-"))	ansA.val = "0";
		return ansA;
	}
		
	public apint subtraction (apint a) {
		String ans = "";
		apint ansA = null;
		int aLen = (a.val).length();
		int bLen = (val).length();
		int carry = 0;
		String neg = "";
		apint aTemp = new apint(a.val);
		apint bTemp = new apint(val);
			
		if (aLen == bLen) {
			int tempLen = aLen-1;
				
			while (tempLen >= 0) {
				if (bTemp.val.charAt(tempLen) > aTemp.val.charAt(tempLen)) {
					String temp = bTemp.val;
					bTemp.val = aTemp.val;
					aTemp.val = temp;
					int tempInt = aLen;
					aLen = bLen;
					bLen = tempInt;
					neg = "-";
					break;
				}
				tempLen--;
			}
		}
			
		if (bLen > aLen) {
			String temp = bTemp.val;
			bTemp.val = aTemp.val;
			aTemp.val = temp;
			int tempInt = aLen;
			aLen = bLen;
			bLen = tempInt;
			neg = "-";
		}
			
		if (aTemp.val.charAt(0) == '-' && bTemp.val.charAt(0) != '-') {
			bTemp.val = "-" + bTemp.val;
			ansA = bTemp.addition(aTemp);
			return ansA;
		}
			
		if (aTemp.val.charAt(0) != '-' && bTemp.val.charAt(0) == '-') {
			bTemp.val = bTemp.val.substring(1);
			ansA = bTemp.addition(aTemp);
			return ansA;
		}
			
		if (aTemp.val.charAt(0) == '-' && bTemp.val.charAt(0) == '-') {
			bTemp.val = bTemp.val.substring(1);
		}
			
		while (aLen > 0 || bLen > 0) {
			int dig = 0;
			if (aLen == 0 || bLen == 0) {
				if (aLen == 0) {
					dig = Integer.parseInt((bTemp.val).substring(bLen-1, bLen)) - carry;
				}
				else	 {
					dig = Integer.parseInt((aTemp.val).substring(aLen-1, aLen)) - carry;
					neg = "-";
				}
				carry = 0;
			}
				
			else {
				int tempA = Integer.parseInt((aTemp.val).substring(aLen-1, aLen));
				int tempB = Integer.parseInt((bTemp.val).substring(bLen-1, bLen));
				if (tempA-carry < tempB) {
					dig = tempA + 10 - tempB - carry;
					carry = 1;
				}
				else {
					dig = tempA - tempB - carry;
					carry = 0;
				}
			}
			ans = Integer.toString(dig) + ans;
			if (aLen > 0)	aLen--;
			if (bLen > 0)	bLen--;
		}
			
		ansA = new apint (ans);
		int count = 0;
		
		for (int i = 0; i < ansA.val.length(); i++) {
			if (ansA.val.charAt(i) == '0') {
				count++;
			}
			else break;
		}
			
		ansA.val = ansA.val.substring(count);
		ansA.val = neg + ansA.val;
		if (ansA.val.equals("") || ansA.val.equals("-"))  ansA.val = "0";
		ansA = new apint (neg + ans);
		return ansA;
	}
		
	public apint multiplication (apint a) {
		String ans = "";
		apint ansA = null;
		int aLen = (a.val).length();
		int bLen = (val).length();
		int carry = 0;
		String neg = "";
		apint aTemp = new apint(a.val);
		apint bTemp = new apint(val);
		int numZeroes = 0;
		String zeroes = "";
			
		if (aTemp.val.charAt(0) == '-' && bTemp.val.charAt(0) == '-') {
			aTemp.val = aTemp.val.substring(1);
			bTemp.val = bTemp.val.substring(1);
			aLen--;
			bLen--;
		}
			
		if (aTemp.val.charAt(0) == '-' || bTemp.val.charAt(0) == '-') {
			if (aTemp.val.charAt(0) == '-') {
				aTemp.val = aTemp.val.substring(1);
				aLen--;
			}
			if (bTemp.val.charAt(0) == '-') {
				bTemp.val = bTemp.val.substring(1);
				bLen--;
			}
			neg = "-";
		}
			
		apint [] parSums = new apint[bLen];
		int aLenTemp = aLen;
		
		while (bLen > 0) {
			int b1 = Integer.parseInt(bTemp.val.substring(bLen-1, bLen));
			aLenTemp = aLen;
			while (aLenTemp > 0) {
				int a1 = Integer.parseInt(aTemp.val.substring(aLenTemp-1, aLenTemp));
				int tempAns = a1 * b1 + carry;
				carry = tempAns / 10;
				tempAns = tempAns % 10;
				zeroes = "";
				for (int i = 0; i < numZeroes; i++) {
					zeroes = zeroes + "0";
				}
				ans = Integer.toString(tempAns) + ans;
				aLenTemp--;
			}
			if (carry != 0)		ans = Integer.toString(carry) + ans;
			parSums [numZeroes] = new apint(ans + zeroes);
			ans = "";
			numZeroes++;
			carry = 0;
			bLen--;
		}
			
		for (int i = 0; i < parSums.length-1; i++) {
			apint sum = parSums[i].addition(parSums[i+1]);
			parSums[i+1] = sum;
		}
		ansA = new apint(parSums[parSums.length-1].val);
			
		int count = 0;
		
		for (int i = 0; i < ansA.val.length(); i++) {
			if (ansA.val.charAt(i) == '0') {
				count++;
			}
			else break;
		}
			
		ansA.val = ansA.val.substring(count);
		ansA.val = neg + ansA.val;
		if (ansA.val.equals("") || ansA.val.equals("-"))		ansA.val = "0";
		return ansA;
	}
	
	public apint division (apint a) {
		String ans = "";
		apint ansA = null;
		int aLen = (a.val).length();
		int bLen = (val).length();
		String neg = "";
		apint aTemp = new apint(a.val);
		apint bTemp = new apint(val);
		int tempLen = 0;
			
		if ((bTemp.val).equals("0")) {
			ansA = new apint ("cannot divide by zero");
			return ansA;
		}
			
		if (aTemp.val.charAt(0) == '-' && bTemp.val.charAt(0) == '-') {
			aTemp.val = aTemp.val.substring(1);
			bTemp.val = bTemp.val.substring(1);
			aLen--;
			bLen--;
		}
			
		if (aTemp.val.charAt(0) == '-' || bTemp.val.charAt(0) == '-') {
			if (aTemp.val.charAt(0) == '-') {
				aTemp.val = aTemp.val.substring(1);
				aLen--;
			}
			if (bTemp.val.charAt(0) == '-') {
				bTemp.val = bTemp.val.substring(1);
				bLen--;
			}
			neg = "-";
		}
			
		int dividend = Integer.parseInt(aTemp.val.substring(tempLen, tempLen+1));
		tempLen++;
		int divisor = Integer.parseInt(bTemp.val);
		int quotient = dividend/divisor;
		int sub = 0;
			
		if (aLen == bLen)	ans = ans + Integer.toString(quotient);
			
		while (tempLen < aLen) {
			dividend = (dividend - sub) * 10 + Integer.parseInt(aTemp.val.substring(tempLen, tempLen+1));
			quotient = dividend/divisor;
			ans = ans + Integer.toString(quotient);
			sub = quotient * divisor;
			tempLen++;
		}
		ansA = new apint (ans);
			
		int count = 0;
			
		for (int i = 0; i < ansA.val.length(); i++) {
			if (ansA.val.charAt(i) == '0') {
				count++;
			}
			else break;
		}
			
		ansA.val = ansA.val.substring(count);
		ansA.val = neg + ansA.val;
		if (ansA.val.equals("") || ansA.val.equals("-"))		ansA.val = "0";
		return ansA;
	}
}
