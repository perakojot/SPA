package main;

import labis.exception.LabisException;
import labis.niz.ANiz;

public class Niz extends ANiz {

	 public int[] spojiDva(int[] a, int[] b)throws LabisException
	 {
		 if(a.length<1||b.length<1) throw new LabisException("Nizovi su prazni");
		 int n=a.length+b.length;
		 int[] output=new int[n];
		 int pom=1,brA=0,brB=0;
		 for(int i=0;i<n;i++)
		 {
			if(brA<a.length-1&&a[brA]<b[brB]&&pom==0)
			{
				output[i]=a[brA];
				brA++;
				pom=1;
			}
			else
			{
				output[i]=b[brB];
				brB++;
				pom=0;
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
