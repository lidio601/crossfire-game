package it.polimi.ingsw.lab.characters;

import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.characters.Character;
import java.util.Iterator;
import java.util.Set;

public class Human extends Character{
	
	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		Set abilities = super.getAbilities();
		Iterator overAbilities = abilities.iterator();
		int sum = 0;
		/**
		 * Da specifica: per la razza Umano la somma delle abilità
		 * deve essere minore di 300!
		 */
		while(overAbilities.hasNext()) {
			Ability nx = (Ability)overAbilities.next();
			sum += nx.getValue();
			if(sum > 300) {
				return false;
			}
		}
		return true;
	}

	//@ requires name!=null && !name.equals(""); 
	public Human(String name) {
		super(name);
	}
	
	// Continuo la toString di Character 
	public String toString() {
		String toRet = super.toString();
		toRet += "Questo è un umano"+ System.getProperty("line.separator");
		return toRet;
	}
}
