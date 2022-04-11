package main;

import labis.exception.LabisException;
import labis.niz.ANiz;

public class Niz extends ANiz {

	 @Override
		public int[] spojiDva(int[] a, int[] b) throws LabisException {
			
			int output[]=new int[a.length+b.length];
			int brA=a.length-1;
		int brB=b.length-1;
		for(int i=0;i<a.length+b.length;i++)
		{
			if(brB<0)
			{
				output[i]=a[brA];
				brA--;
			}
			else if(brA>0&& a[brA]>b[brB])
			{
				output[i]=a[brA];
				brA--;
			}
			else
			{
				output[i]=b[brB];
				brB--;
			}
		}
		return output;
	}

	 public static int[] ZameniProizvodomSuseda(int a[])
	 {
		 int p[]=new int[a.length];
		 for(int i=0;i<a.length;i++)
		 {
			 if(i==0)
			 {
				 p[i]=a[i]*a[i]+1;
			 }
			 else if(i==a.length-1)
			 {
				 p[i]=a[i]*a[i-1];
			 }
			 else
			 {
				 p[i]=a[i-1]*a[i+1];
			 }
		 }
		 return p;
		 
	 }

	 public static int[] ZameniProizvodomSusedaBezPom(int a[])
	 {
		 int pre=0,posle=0;
		 for(int i=0;i<a.length;i++)
		 {
			 if(i==0)
			 {
				 pre=a[i];
				 posle=a[i+1];
				 a[i]=pre*posle;
			 }
			 else if(i!=a.length-1)
			 {
				 posle=a[i];
				 a[i]=pre*a[i+1];
				 pre=posle;
			 }
			 else
			 {
				 a[i]=a[i]*pre;
			 }
		 }
		 return a;
	 }
}
