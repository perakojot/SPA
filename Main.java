package main;

import labis.cvorovi.CvorDSListe;
import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class Main {

	public static void main(String[] args) {
		JSLista lista=new JSLista();
		DSLista lista2=new DSLista();
		int a[]={1,2,3,4,5,6};
		int b[]= {2,2,4,4,4};
		ListGenerator.napraviJSListuCommon(lista,a,false);
		ListGenerator.napraviDSListuCommon(lista2,b,false);
		//CvorJSListe pom=lista.prvi;
		/*try {
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		JSLista test=new JSLista();
		try {
			//System.out.println("Zbir u ciklicnoj je "+ lista.zbirElemenataUCiklicnoj());
			test.prvi=lista.klonirajRekurzivno(lista.prvi);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListGenerator.ispisiJSListu(test.prvi);
	}

}
