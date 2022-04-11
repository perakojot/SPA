package main;

import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;

public class DSLista extends ADSLista {

	@Override
	  public void popuniListu() throws LabisException
	  {
		CvorDSListe pom=prvi;
		CvorDSListe pok=null;
		while(pom.sledeci!=null)
		{
			if(pom.sledeci.podatak-pom.podatak>1)
			{
				CvorDSListe novi=null;
				for(int i=pom.podatak+1;i<pom.sledeci.podatak;i++)
				{					
					if(i==pom.podatak+1)
					{
						 novi=new CvorDSListe(i,pom,pom.sledeci);
						 pok=pom.sledeci;
						 pom.sledeci=novi;
					}
					else
					{
						novi.sledeci=new CvorDSListe(i,novi,null);
						novi=novi.sledeci;
					}
				}
				novi.sledeci=pok;
				pok.prethodni=novi;
			}
			pom=pom.sledeci;
		}
	  }
	  public CvorDSListe elementUSredini() throws LabisException
	  {
		  CvorDSListe pom=prvi;
		  CvorDSListe spori=prvi;
		  int brojac=0;
		  if(prvi==null)return null;
		  while(pom.sledeci!=null)
		  {
			  brojac++;
			  if(brojac%2==0)
			  {
				  spori=spori.sledeci;
			  }
			  pom=pom.sledeci;
		  }
		  return spori;
	  }
}
