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
		CvorStabla output=null;
		if(visinaStabla(k.levo)<visinaStabla(k.desno))
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
		if(tekuci==null||kraj==null) throw new LabisException();
		System.out.println(tekuci.podatak);
		if(kraj.podatak==tekuci.podatak)return;
		else if(kraj.podatak>tekuci.podatak)ispisiPutanjuKoren(kraj,tekuci.desno);
		else ispisiPutanjuKoren(kraj,tekuci.levo);
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
}
