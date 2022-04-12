package main;

import java.util.Collection;

import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.AJSLista;

public class JSLista extends AJSLista {

	public void dodaj(CvorJSListe novi)
	{
		if(prvi==null)
		{
			prvi=novi;
			return;
		}
		novi.sledeci=prvi;
		prvi=novi;
	}
	
	public void dodajNaKraj(CvorJSListe novi)
	{
		if(prvi==null)
		{
			prvi=novi;
			return;
		}
		CvorJSListe pom=prvi;
		while(pom.sledeci!=null)
		{
			pom=pom.sledeci;
		}
		pom.sledeci=novi;
		if(novi==prvi)
		{
			prvi=prvi.sledeci;
		}
		novi.sledeci=null;
	}
	public void UkloniSaPocetka()
	{
		if(prvi!=null)
		{
			prvi=prvi.sledeci;
		}
	}
	
	public void ispisi()
	{
		CvorJSListe pom=prvi;
		while(pom!=null)
		{
			System.out.println(pom.podatak);
			pom=pom.sledeci;
		}
	}
	public int brojClanova()
	{
		CvorJSListe pom=prvi;
		int br=0;
		while(pom!=null)
		{
			pom=pom.sledeci;
			br++;
		}
		return br;
	}
	
	@Override
	public int izbaciTrenutni(CvorJSListe t) throws LabisException
	{
		if(t==null)throw new LabisException("element je null");
		if(prvi==null)throw new LabisException("lista je prazna");
		CvorJSListe pom=prvi;
		if(prvi==t)
		{
			int p=prvi.podatak;
			prvi=prvi.sledeci;
			return p;
		}
		while(pom.sledeci!=null)
		{
			if(pom.sledeci==t)
			{
				int p=pom.sledeci.podatak;
				pom.sledeci=pom.sledeci.sledeci;
				return p; 
			}
			pom=pom.sledeci;
		}
		throw new LabisException("element se ne nalazi u listi");
	}
	public CvorJSListe invertuj(CvorJSListe p1){
		CvorJSListe p2=null;
		while(p1!=null)
		{
			p2=new CvorJSListe(p1.podatak,p2);
			p1=p1.sledeci;
		}
		return p2;
	}
	  public void invertovanjeBezPomocne() throws labis.exception.LabisException
	  {
		 if(prvi==null)return;
		  CvorJSListe p=prvi;
		  CvorJSListe pok=prvi;
		  while(p.sledeci!=null)
		  {
			  pok=p.sledeci;
			  p.sledeci=pok.sledeci;
			  pok.sledeci=prvi;
			  prvi=pok;
		  }
	  }
	  
	  public int zbirElemenataUCiklicnoj() throws LabisException
	  {
		if(prvi==null)throw new LabisException("lista je prazna");
		CvorJSListe pom=prvi.sledeci;
		int br=prvi.podatak;
		while(pom!=prvi)
		{
			br+=pom.podatak;
			pom=pom.sledeci;
		}
		return br;
	  }
	  public void ispisiObrnuto(CvorJSListe prvi) throws LabisException
	  {
		  if(prvi.sledeci!=null)
		  ispisiObrnuto(prvi.sledeci);
		  if(prvi!=null)
		  System.out.println(prvi.podatak);
	  }
	  
	  public CvorJSListe klonirajRekurzivno(CvorJSListe pom) throws LabisException
	  {
		  CvorJSListe unos=null;
		  if(pom!=null)
		  {
			  unos=new CvorJSListe(pom.podatak,klonirajRekurzivno(pom.sledeci));
		  }
		  return unos;
	  }
		
	
}
