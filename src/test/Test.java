package test;

import java.util.ArrayList;
import java.util.List;

import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Karte;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class Test {

	public static void main(String[] args) {
		Lernkartei kartei1 = new Lernkartei(0, "Deutsch -> Englisch", "De", "En", false, false);
		Fach fach1 = new Fach();
		Karte karte1 = new Karte(0, "haus", "house", false, false);
		VokabeltrainerDB.hinzufuegenLernkartei(kartei1);
		VokabeltrainerDB.hinzufuegenFach(0, fach1);
		
		ArrayList<Fach> faecher = new ArrayList<Fach>();
		for(int i = 0; i< 5; i++)
			faecher.add(new Fach());
		faecher.forEach((fach)->VokabeltrainerDB.hinzufuegenFach(VokabeltrainerDB.getLernkartei(0).getNummer(), fach));
	}

}
