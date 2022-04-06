package main;

import labis.cvorovi.CvorDSListe;
import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;

public class Nizovi {

	//Nizovi  
	public static void  InsertSortArray(int value, int[] niz,int brElemenata) throws LabisException
	{
		if(brElemenata==niz.length) throw new LabisException();
		for(int i=0;i<niz.length;i++)
		{
			if(niz[i]>value)
			{
				for(int j=brElemenata+1;j>i;j--)
				{
					niz[j]=niz[j-1];
				}
				niz[i]=value;
				brElemenata++;
				return;
			}
		}
		niz[brElemenata]=value;
		brElemenata++;
	}

	public static  double prosekParnih(int[] niz,int brElemenata)
	{
		int zbir=0,brojac=0;
		for(int i=0;i<brElemenata;i++)
		{
			if(niz[i]%2==0)
			{
				brojac++;
				zbir+=niz[i];
			}
		}
		if(brojac>0) return (double)zbir/brojac;
		else return 0;
	}

	public static int sekvencijalno(int podatak, int[] n)
	{
		for(int i=0;i<n.length;i++)
		{
			if(n[i]==podatak) return i;
		}
		return -1;
	}

	public static int sekvencijalnoR(int podatak, int[] n, int i)
	{
		if(i==n.length)return -1;
		if(n[i]==podatak)return i;
		return sekvencijalnoR(podatak, n,i+1);
	}

	public static int binarno(int podatak, int[] n)
	{
		int l=0;
		int d=n.length-1;
		while(l<=d)
		{
			int s=(l+d)/2;
			if(n[s]==podatak)return s;
			if(n[s]>podatak)d=s-1;	
			else l=s+1;
		}
		return -1;
		
	}

	public static int binarnoR(int podatak, int[] n, int l, int d)
	{
		if(l>d) return -1;
		int s=(l+d)/2;
		if(n[s]==podatak) return s;
		if(n[s]>podatak)return binarnoR(podatak, n, l, s-1);
		else return binarnoR(podatak, n,s+1,d);
	}

	public static int interpolaciono(int podatak, int[] n)
	{
		int l=0;
		int d=n.length-1;
		while(l<=d)
		{
			int index=l+(d-l)*(podatak-n[l])/(n[d]-n[l]);
			if(n[index]==podatak)return index;
			if(n[index]>podatak) d=index-1;
			else l=index+1;
		}
		return -1;
	}

	public static int interpolacionoR(int podatak, int[] n, int l, int d)
	{
		if(l>d)return -1;
		int index=l+(d-l)*(podatak-n[l])/(n[d]-n[l]);
		if(n[index]==podatak) return index;
		if(n[index]>podatak) return interpolacionoR(podatak, n, l, index-1);
		else return interpolacionoR(podatak, n, index+1, d);
	}

	//LISTE
	
	public static int prebroj(CvorJSListe pocetak)
	{
		if(pocetak==null)return 0;
		CvorJSListe pom=pocetak;
		int podatak=pocetak.podatak;
		int br=0;
		while(pom!=null)
		{
			if(pom.podatak>podatak)br++;
			pom=pom.sledeci;
		}
		return br;
	}
	
	public static int brPonavljanja(CvorJSListe prvi, int pod)
	{
		if(prvi==null)return 0;
		CvorJSListe pom=prvi;
		int br=0;
		while(pom!=null)
		{
			if(pom.podatak==pod)br++;
			pom=pom.sledeci;
		}
		return br;
	}
	
	private static int nadjiMin(CvorJSListe prvi)
	{
		CvorJSListe pom=prvi;
		int min=prvi.podatak;
		while(pom!=null)
		{
			if(pom.podatak<min)min=pom.podatak;
			pom=pom.sledeci;
		}
		return min;
	}
	
	public static int posleNajmanjeg(CvorJSListe prvi) throws LabisException
	{
		if(prvi==null) throw new LabisException();
		CvorJSListe pom=prvi;
		int min=nadjiMin(prvi);
		while(pom!=null)
		{
			if(pom.podatak==min)
			{
				if(pom.sledeci!=null)
				{
					int p=pom.sledeci.podatak;
					pom.sledeci=pom.sledeci.sledeci;
					return p;
				}
			}
		}
		return Integer.MIN_VALUE;
		
	}

	public static int izbrojPozitivneR(CvorJSListe pom)
	{
		if(pom==null) return 0;
		else
		{
			if(pom.podatak>0)return 1+izbrojPozitivneR(pom.sledeci);
			else return izbrojPozitivneR(pom.sledeci);
		}
		
	}

	public static CvorJSListe invertuj(CvorJSListe p1){
		CvorJSListe p2=null;
		while(p1!=null)
		{
			p2=new CvorJSListe(p1.podatak,p2);
			p1=p1.sledeci;
		}
		return p2;
	}

	public static CvorJSListe klonirana(CvorJSListe prvi)
	{
		if(prvi!=null)
		{
			CvorJSListe novi=new CvorJSListe(prvi.podatak,klonirana(prvi.sledeci));
			return novi;
		}
		else return null;
	}
	
	public static int brClanova(CvorJSListe prvi)
	{
		int br=0;
		while(prvi!=null)
		{
			prvi=prvi.sledeci;
			br++;
		}
		return br;
	}
	
	public static  int[] transform(JSLista l)
	{
		int output[]=new int[brClanova(l.prvi)];
		CvorJSListe pom=l.prvi;
		for(int i=0;i<output.length;i++)
		{
			output[i]=pom.podatak;
			pom=pom.sledeci;
		}
		return output;
	}
		
	public static CvorJSListe Test(CvorJSListe p1)
	{
		while(p1!=null)
		{
			p1=p1.sledeci;
		}
		return p1;
	}

	public static boolean sadrzi(CvorJSListe c1,int vrednost)
	{
		while(c1!=null)
		{
			if(c1.podatak==vrednost)return true;
			c1=c1.sledeci;
		}
		return false;
	}
	
	public static CvorJSListe unija(CvorJSListe c1, CvorJSListe c2)
	{
		CvorJSListe output=null;
		while(c1!=null)
		{
			if(!sadrzi(output,c1.podatak))
			{
				output=new CvorJSListe(c1.podatak,output);
			}
			c1=c1.sledeci;
		}
		while(c2!=null)
		{
			if(!sadrzi(output,c2.podatak))
			{
				output=new CvorJSListe(c2.podatak,output);
			}
			c2=c2.sledeci;
		}
		return output;
	}

	public static void prebaciMaxElement(CvorDSListe poslednji)
	{
		CvorDSListe pom=poslednji;
		CvorDSListe max=poslednji;
		while(pom.prethodni!=null)
		{
			if(pom.podatak>max.podatak)max=pom;
			pom=pom.prethodni;
		}
		if(max.prethodni==null)return;
		max.prethodni.sledeci=max.sledeci;
		if(max.sledeci!=null)max.sledeci.prethodni=max.prethodni;
		max.prethodni=null;
		max.sledeci=pom;
		pom.prethodni=max;			
	}

	public static void veciZbir(CvorDSListe p)
	{
		CvorDSListe pom=p;
		int z1=0,z2=0;
		while(pom!=null)
		{
			z1=z1+pom.podatak;
			pom=pom.prethodni;
		}
		pom=p;
		while(pom!=null)
		{
			z2=z2+pom.podatak;
			pom=pom.sledeci;
		}
		if(z1>z2)
		{
			pom=p;
			while(pom!=null)
			{
				System.out.println(pom.podatak);
				pom=pom.prethodni;
			}
		}
		else
		{
			pom=p;
			while(pom!=null)
			{
				System.out.println(pom.podatak);
				pom=pom.sledeci;
			}
		}
	}

	public static boolean daLiJePalindromDS(CvorDSListe p)
	{
		if(p==null)return false;
		CvorDSListe pom=p;
		while(pom.sledeci!=null)
		{
			pom=pom.sledeci;
		}
		CvorDSListe poslednji=pom;
		pom=p;
		while(pom!=poslednji)
		{
			if(pom.podatak!=poslednji.podatak)return false;
			pom=pom.sledeci;
			poslednji=poslednji.prethodni;
		}
		return true;
	}

	public static void popuni(CvorDSListe prvi) {
		if (prvi == null || prvi.sledeci == null)
		return;
		if ((prvi.sledeci.podatak - prvi.podatak) > 1) {
		CvorDSListe novi = new CvorDSListe(prvi.podatak + 1, prvi, prvi.sledeci);
		prvi.sledeci.prethodni = novi;
		prvi.sledeci = novi;
		}
		popuni(prvi.sledeci);
		}
	
	public static boolean odgovara(CvorDSListe prvi)
	{
		boolean output=false;
		int p=prvi.podatak;
		CvorDSListe sledi=prvi.sledeci;
		CvorDSListe prethodi=prvi.prethodni;
		while(sledi!=null)
		{
			if(sledi.podatak==p)
			{
				return false;
			}
			sledi=sledi.sledeci;
		}
		while(prethodi!=null)
		{
			if(prethodi.podatak==p)output=true;
			prethodi=prethodi.prethodni;
		}
		return output;
	}
	
	public  static int ZbirDuplih(CvorDSListe p1)
	{
		int zbir=0;
		while(p1!=null)
		{
			if(odgovara(p1))
			{
				zbir+=p1.podatak;
			}
			p1=p1.sledeci;
		}
		return zbir;
	}
	
}
