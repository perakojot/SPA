package main;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

	
	@Override
	public boolean daLiPostojiIsti(CvorStabla k, CvorStabla cvor) throws LabisException
	{
		if(cvor==null)return false;
		boolean output=false;
		if(k.podatak==cvor.podatak&& k!=cvor)
		{
			return true;
		}
		output=daLiPostojiIsti(k, cvor.levo);
		if(output==false)output=daLiPostojiIsti(k, cvor.desno);
		return output;
	}
	
	@Override
	public CvorStabla vratiCvorNaNajvecojDubini(CvorStabla k) throws LabisException
	{
		if(k==null)return null;
		if(k.desno==null&&k.levo==null)return k;
		CvorStabla output=null;
		if(visinaStabla(k.levo)>visinaStabla(k.desno))
		{
		 output=vratiCvorNaNajvecojDubini(k.levo);
		}
		else
		{
		output=vratiCvorNaNajvecojDubini(k.desno);
		}		
		return output;
	}
	
	@Override
	public CvorStabla vratiListNaNajmanjojDubini(CvorStabla k) throws LabisException
	{
		if(k==null)return null;
		if(k.desno==null&&k.levo==null)return k;
		CvorStabla output=k;
		if(k.levo==null)output=vratiListNaNajmanjojDubini(k.desno);
		else if(k.desno==null)output=vratiListNaNajmanjojDubini(k.levo);
		else if(visinaStabla(k.levo)<visinaStabla(k.desno))
		output=vratiListNaNajmanjojDubini(k.levo);
		else output=vratiListNaNajmanjojDubini(k.desno);
		return output;
	}
	
	@Override
	public CvorStabla vratiNajveciNaPutanji(CvorStabla k, CvorStabla neki) throws LabisException
	{
		if(neki==k)return k;
		CvorStabla max=neki;
		CvorStabla p= vratiNajveciNaPutanji(k,vrednostRoditeljaZadate(k,max.podatak));
		if(max.podatak<p.podatak)max=p;
		return max;
	}
	
	@Override
	public int vratiBrojCvorovaVecihOdSvojihSledbenika(CvorStabla k) throws LabisException
	{		
		if(k==null)return 0;
		int br=0;
		if(k.levo==null&& k.desno==null)br++;
		else if (k.levo!=null && k.podatak>k.levo.podatak &&k.desno!=null && k.podatak>k.desno.podatak)br++;
		else if(k.levo==null && k.desno!=null && k.podatak>k.desno.podatak)br++;
		else if (k.desno==null && k.levo!=null && k.podatak>k.levo.podatak)br++;
		br=br+vratiBrojCvorovaVecihOdSvojihSledbenika(k.levo);
		br=br+vratiBrojCvorovaVecihOdSvojihSledbenika(k.desno);
		return br;
	}
	
	public void ispisiStabloPrefiks(CvorStabla k) throws LabisException
	{
		if(k==null)throw new LabisException("Stablo je prazno");
		System.out.println(k.podatak); 
		if(k.levo!=null)ispisiStabloPrefiks(k.levo);
		if(k.desno!=null)ispisiStabloPrefiks(k.desno);
	}
	
	public void ispisiStabloInfiks(CvorStabla k) throws LabisException
	{
		if(k==null)throw new LabisException("Stablo je prazno");
		if(k.levo!=null)ispisiStabloInfiks(k.levo);
		System.out.println(k.podatak);
		if(k.desno!=null)ispisiStabloInfiks(k.desno);
	}
	
	public void ispisiStabloPostfiks(CvorStabla k) throws LabisException
	{
		if(k==null)throw new LabisException("Stablo je prazno");
		if(k.levo!=null)ispisiStabloPostfiks(k.levo);
		if(k.desno!=null)ispisiStabloPostfiks(k.desno);
		System.out.println(k.podatak);
	}
	
	public int maxStabla(CvorStabla k) throws LabisException
	{
		if(k==null) throw new LabisException("stablo je prazno");
		int max=k.podatak;
		if(k.levo!=null)
		if(maxStabla(k.levo)>max)max=maxStabla(k.levo);
		if(k.desno!=null)
		if(maxStabla(k.desno)>max)max=maxStabla(k.desno);
		return max;
	}
	
	public int minStabla(CvorStabla k)throws LabisException
	{
		if(k==null) throw new LabisException("stablo je prazno");
		int min=k.podatak;
		if(k.levo!=null)
		if(minStabla(k.levo)<min)min=minStabla(k.levo);
		if(k.desno!=null)
		if(minStabla(k.desno)<min)min=minStabla(k.desno);
		return min;
	}
	
	public int zbirCvorova(CvorStabla k) throws LabisException
	{
		if(k==null) throw new LabisException("stablo je prazno");
		int zbir=k.podatak;
		if(k.levo!=null) zbir+=zbirCvorova(k.levo);
		if(k.desno!=null) zbir+=zbirCvorova(k.desno);
		return zbir;
	}
	
	public int brojElemenata(CvorStabla k) throws LabisException
	{
		if(k==null) throw new LabisException("stablo je prazno");
		int br=1;
		if(k.levo!=null)br=br+brojElemenata(k.levo);
		if(k.desno!=null)br=br+brojElemenata(k.desno);
		return br;
	}
	
	public double prosekElemenata(CvorStabla k) throws LabisException
	{
		return (double)zbirCvorova(k)/brojElemenata(k);
	}
	
	public CvorStabla vrednostRoditeljaZadate(CvorStabla tekuci,int podatak) throws LabisException
	{
		CvorStabla output=null;
		if(tekuci.levo!=null)
		{
			if(tekuci.levo.podatak==podatak)output= tekuci;
			else if(tekuci.desno!=null&&tekuci.desno.podatak==podatak)output= tekuci;
			else output=vrednostRoditeljaZadate(tekuci.levo,podatak);
		}
		if(tekuci.desno!=null&&output==null)
		{
			if(tekuci.desno.podatak==podatak)output= tekuci;
			else if(tekuci.levo!=null&&tekuci.levo.podatak==podatak)output= tekuci;
			else output=vrednostRoditeljaZadate(tekuci.desno,podatak);
		}
		return output;
	}
	
	public void ispisiPutanjuCvor(CvorStabla k, CvorStabla tekuci) throws LabisException
	{
		if(k==null||tekuci==null)return;
		System.out.println(tekuci.podatak);
		ispisiPutanjuCvor(k,vrednostRoditeljaZadate(k,tekuci.podatak));
	}
	
	public void ispisiPutanjuKoren(CvorStabla kraj, CvorStabla tekuci) throws LabisException
	{
		if(k==null||tekuci==null)return;
		ispisiPutanjuCvor(k,vrednostRoditeljaZadate(k,tekuci.podatak));
		System.out.println(tekuci.podatak);
	}
	public int visinaStabla(CvorStabla k) throws LabisException
	{
		if(koren==null) throw new LabisException();
		if(k==null)return 0;
		return 1+Math.max(visinaStabla(k.levo),visinaStabla(k.desno));
	}
	
	public CvorStabla najdubljiCvor(CvorStabla k) throws LabisException
	{
		if(k==null)return null;
		if(k.levo==null&&k.desno==null)return k;
		if(visinaStabla(k.levo)>visinaStabla(k.desno))return najdubljiCvor(k.levo);
		else return najdubljiCvor(k.desno);
	}
	//Jun 2021
	
	public int Jun2021metoda1(CvorStabla k,int v) throws LabisException
	{
		if(koren==null)throw new LabisException();
		if(k==null)return 0;
		int br=0;
		if(k.levo!=null && k.levo.podatak==v)br++;
		else if(k.desno!=null && k.desno.podatak==v)br++;
		br=br+Jun2021metoda1(k.levo, v);
		br=br+Jun2021metoda1(k.desno, v);
		return br;
	}
	
	public int Jun2021metoda2(CvorStabla k) throws LabisException
	{
		if(koren==null)throw new LabisException();
		if(k==null)return 0;
		int zbir=k.podatak;
		if(Jun2021metoda2(k.levo)>Jun2021metoda2(k.desno))
			zbir=zbir+Jun2021metoda2(k.levo);
		else zbir=zbir+Jun2021metoda2(k.desno);
		return zbir;
	}
	
	//Jul 2021
	
	public int Jul2021metoda1(CvorStabla k) throws LabisException
	{
		if(koren==null)throw new LabisException();
		if(k==null)return 0;
		int br=0;
		if(k.levo!=null&&k.desno!=null)
		{
			if((k.levo.podatak%2==0&& k.desno.podatak%2!=0)||(k.levo.podatak%2!=0&& k.desno.podatak%2==0))
				br++;
		}
		else if(k.levo==null&&k.desno!=null)br++;
		else if(k.levo!=null&&k.desno==null)br++;
		else br++;
		br=br+Jul2021metoda1(k.levo);
		br=br+Jul2021metoda1(k.desno);
		return br;
	}
	
	// Jul2021metoda 2 idk tf hoce ovde
	
	//Septembar 2021
	
	public void Septembar2021metoda1(CvorStabla k,CvorStabla neki) throws LabisException
	{
		//mrzi me da radim verzija
		if(k==null||neki==null)return;
		Septembar2021metoda1(k,vrednostRoditeljaZadate(k,neki.podatak));
		if(neki.podatak%2==0)System.out.println(neki.podatak);
	}
	
	public void Septembar2021metoda1V2(CvorStabla k,CvorStabla neki)
	{
		//verzija sa da li postoji
		if(k==null||neki==null)return;
		if(k.podatak%2==0)System.out.println(k.podatak);
		if(postoji(k.levo,neki))Septembar2021metoda1V2(k.levo,neki);
		else Septembar2021metoda1V2(k.desno,neki);
		
	}
	
	public boolean postoji(CvorStabla k,CvorStabla neki)
	{
		if(k==null||neki==null)return false;
		if(k==neki)return true;
		if(postoji(k.levo,neki))return true;
		else return postoji(k.desno,neki);
	}
	
	public void Septembar2021metoda2(CvorStabla k,int nivo)
	{
		if(k==null)return;
		if(nivo(koren,k)==nivo)
		{
			if((k.levo==null&&k.desno!=null)||(k.levo!=null&&k.desno==null))
			{
				System.out.println(k.podatak);
			}
		}
		else 
		{
			Septembar2021metoda2(k.levo,nivo);
			Septembar2021metoda2(k.desno,nivo);
		}
		
	}
	
	public int nivo(CvorStabla k, CvorStabla neki)
	{
		if(k==null)return 0;
		if(k==neki)return 1;
		int br=1;
		if(postoji(k.levo,neki))br+=nivo(k.levo,neki);
		else br+=nivo(k.desno,neki);
		return br;
	}
	
	
	//Oktobar 2021
	
	public int Oktobar2021metoda1(CvorStabla k)
	{
		if(k==null)return 0;
		int zbir=0;
		zbir=zbir+Oktobar2021metoda1(k.levo);
		zbir=zbir+Oktobar2021metoda1(k.desno);
		if(k.levo==null&&k.desno==null)return k.podatak;
		else return zbir;
	}
	
	// Metoda 2 pogledati Jun2021metoda2
	
	//Oktobar2_2021
	
	public int Oktobar2_2021metoda1(CvorStabla k,int dg,int gg)
	{
		if(k==null)return 1;
		int p=1;
		if(k.podatak>=dg&&k.podatak<=gg)p=p*k.podatak;
		p=p*Oktobar2_2021metoda1(k.levo,dg,gg);
		p=p*Oktobar2_2021metoda1(k.desno,dg,gg);
		return p;
	}
	
	
	public int Oktobar2_2021metoda2(CvorStabla k) throws LabisException
	{
		if(k==null)throw new LabisException();
		int max=Integer.MIN_VALUE;
		if(nivo(koren,k)%2==0)max=k.podatak;
		if(k.levo!=null)
		{
			if(Oktobar2_2021metoda2(k.levo)>max)max=Oktobar2_2021metoda2(k.levo);
		}
		if(k.desno!=null)
		{
			if(Oktobar2_2021metoda2(k.desno)>max)max=Oktobar2_2021metoda2(k.desno);
		}
		return max;
	}
	//Klk2020_g1
	
	public int Klk2020metoda1_g1(CvorStabla k) throws LabisException
	{
		if(k==null)throw new LabisException("lista je prazna");
		int p=1;
		if(k.levo==null&&k.desno==null)p=p*k.podatak;
		if(k.levo!=null)p=p*Klk2020metoda1_g1(k.levo);
		if(k.desno!=null)p=p*Klk2020metoda1_g1(k.desno);
		return p;
	}
	
	// zad2 i dalje nzm sta tf znaci udaljen od lista 
	
	public int Klk2020metoda1_g2(CvorStabla k)throws LabisException
	{
		if(k==null )throw new LabisException();
		int br=0;
		if(visinaStabla(k.levo)>visinaStabla(k.desno))br++;
		if(k.levo!=null)br+=Klk2020metoda1_g2(k.levo);
		if(k.desno!=null)br+=Klk2020metoda1_g2(k.desno);
		return br;
	}
	
	//mrzi me da proveravam mislim da radi ono sto treba
	public int Klk2020metoda2_g2(CvorStabla k)throws LabisException
	{
		
		if(koren==null)throw new LabisException();//jer me mrzi da radim exceptione		
		if(k==null)return Integer.MIN_VALUE;
		int levo=0,desno=0;
		if(k.levo!=null)levo=zbirCvorova(k.levo);
		if(k.desno!=null)desno=zbirCvorova(k.desno);
		int razlika=levo-desno;
		if(Klk2020metoda2_g2(k.levo)>razlika)razlika=Klk2020metoda2_g2(k.levo);
		if(Klk2020metoda2_g2(k.desno)>razlika)razlika=Klk2020metoda2_g2(k.desno);
		return razlika;
	}
	
	//Klk 2021 
	
	//trebalo bi da se radi isto za BST i obicno pretrage moze da bude efikasnija ali...
	public CvorStabla Klk2021metoda1(CvorStabla k,CvorStabla neki) throws LabisException
	{
		if(k==null)throw new LabisException();//ako ga ne nadjemo ide exception
		CvorStabla output=null;
		if(k.levo==neki)output=k.desno;
		if(k.desno==neki)output=k.levo;
		if(output==null&&k.levo!=null)output=Klk2021metoda1(k.levo,neki);
		if(output==null&&k.desno!=null)output=Klk2021metoda1(k.desno,neki);
		return output;
	}
	
	public int Klk2021metoda2(CvorStabla k,CvorStabla p,CvorStabla q)throws LabisException
	{
		if(p==null||q==null)throw new LabisException();
		if(k==null)throw new LabisException();
		if(k==koren && postoji(k.levo,p)&&postoji(k.desno,q))return k.podatak;
		int proizvod=1;
		if(postoji(k.levo,p)&&postoji(k.levo,q))
		{
			proizvod=proizvod*k.podatak;
			if(k.levo!=null)proizvod=proizvod*Klk2021metoda2(k.levo,p,q);
		}
		else if(postoji(k.desno,p)&&postoji(k.desno,q))
		{
			proizvod=proizvod*k.podatak;
			if(k.desno!=null)proizvod=proizvod*Klk2021metoda2(k.desno,p,q);
		}
		else if((postoji(k.levo,p)&&postoji(k.desno,q))||(postoji(k.desno,p)&&postoji(k.levo,q)))
		{
			proizvod=proizvod*k.podatak;
			return proizvod;
		}
		return proizvod;
	}
	
}
