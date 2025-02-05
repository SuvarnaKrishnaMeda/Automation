package codes;

import java.util.Arrays;
import java.util.HashMap;

import com.google.common.primitives.Ints;

public class Practice1 {

	public static void main(String[] args) {
//	HashMap<Character,Integer> hm=new HashMap<Character,Integer>();
//	String str="hethaswi";
//	int count=0,i=0;
//	char a=' ';
//	for(i=0;i<str.length()-1;i++) {
//		if(hm.containsKey(str.charAt(i))) {
//			hm.put(str.charAt(i), hm.get(str.charAt(i))+1);
//		}
//		else {
//			hm.put(str.charAt(i),1);
//		}
//		if(count<hm.get(str.charAt(i))) {
//			a=str.charAt(i);
//			count=hm.get(str.charAt(i));
//		}
//		
//		}
//	
//		
//	System.out.println(hm+" "+a+" "+count);
//
//	}
//
//}
//int a=0,b=1,c=0;
//System.out.println(a+" "+b);
//for(int i=0;i<=10;i++) {
//c=a+b;
//a=b;
//b=c;
//System.out.println(c);
//}
//String str="hethaswi";
//for(int i=str.length()-1;i>=0;i--) {
//	System.out.print(str.charAt(i));
//}
//String str="suvarnakrishna";
//for(int i=0;i<str.length();i++) {
//	if(str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u') {
//		System.out.println(str.charAt(i)+" "+"is vowel");
//	}
//	else {
//		System.out.println(str.charAt(i)+" "+"is consonant");
//	}
//}
//int n=969;
//int c=0,rem=0,temp=n;
//while(n!=0) {
//	c=n%10;
//	rem=rem*10+c;
//	n=n/10;
//}
//System.out.println(rem);
//if(temp==rem) {
//	System.out.println("given number is palindrome");
//}
//else {
//	System.out.println("given number is not palindrome");
//}
//int[] arr= {40,30,50,20,60};
//int temp=0;
//for(int i=0;i<arr.length;i++) {
//	for(int j=i;j<arr.length;j++) {
////	Arrays.sort(arr);
////	
////}
////System.out.println(Arrays.toString(arr));
//	if(arr[i]>arr[j]) {
//		temp=arr[i];
//		arr[i]=arr[j];
//		arr[j]=temp;
//		}
//	}
//	}
//System.out.println(Arrays.toString(arr));
//		int a=30,b=40;
////		temp=a;
////		a=b;
////		b=temp;
//		a=a+b;
//		b=a-b;
//		a=a-b;		
//		System.out.println(a+" "+b);
//		int n=7,count=0;
//		for(int i=1;i<=n;i++) {
//			if(n%i==0) {
//				count++;
//			}
//		}
//		if(count==2) {
//			System.out.println("given number is prime");
//		}
//		else {
//			System.out.println("given number is not prime");
//		}
//		int[] arr= {10,20,30,40,50};
//		int high=arr[0];
//		int low=arr[0];
//		for(int i=0;i<arr.length;i++) {
//			if(high>arr[i]) {
//				high=arr[i];
//			}
//			else if(low<arr[i]) {
//				low=arr[i];
//			}
//		}
//		System.out.println(low+" "+high);
//		String str="ghtijh12345$$$%%%%%pkjgcdxr5656uyvhiu";
//		StringBuffer alpha=new StringBuffer();
//		StringBuffer num=new StringBuffer();
//		StringBuffer special=new StringBuffer();
//		for(int i=0;i<str.length();i++) {
//			if(Character.isAlphabetic(str.charAt(i))) {
//				alpha.append(str.charAt(i));
//			}
//			else if(Character.isDigit(str.charAt(i))) {
//				num.append(str.charAt(i));
//			}
//			else {
//				special.append(str.charAt(i));
//			}
//		}
//		System.out.println(alpha+""+num+""+special);
		int[] arr = {  30, 0, 60,70,0,0,0,0,46 };

		int[] arr1 = new int[arr.length];
		int a = arr.length-1, b =0;
		for (int i = arr.length-1; i >=0 ; i--) {
			if (arr[i] == 0) {
				
				arr[i]=arr[a];
				arr[a--]=0;
			}

				System.out.println(a+""+i+" iteration "+Ints.asList(arr));	
			
			}
//			

		
System.out.println(Ints.asList(arr));
	}
}
