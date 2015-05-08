package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;

public class Shield extends Item{
	
	private static final int destrezzamod=-10; 
	private static final int bonusdef=-10;
	
	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(  this.getName()!=null && !this.getName().equals("")
			 && this.bonusAttack.get("Difesa").equals(bonusdef)
			 && this.modificator.get(Ability.Destrezza).equals(destrezzamod)
			 && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
			 && this.bonusAttack.get("AttaccoADistanza").equals(0)
			 && this.bonusAttack.get("AttaccoMagico").equals(0))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	/**
	 * 
	 * 
	 */
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name) 
	//@ && this.modificator.get(Ability.Destrezza).equals(destrezzamod)
	//@ && this.bonusAttack.get("Difesa").equals(-10)
	//@ && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
	//@ && this.bonusAttack.get("AttaccoADistanza").equals(0)
	//@ && this.bonusAttack.get("AttaccoMagico").equals(0);
	public Shield(String name) {
		super(name);
		this.modificator.put(Ability.Destrezza,new Integer(destrezzamod));
		//diminuisce attacchi subiti di dieci bonus
		this.bonusAttack.put("Difesa",new Integer(bonusdef));
		this.bonusAttack.put("attaccoRavvicinato",new Integer(0));
		this.bonusAttack.put("attaccoADistanza",new Integer(0));
		this.bonusAttack.put("attaccoMagico",new Integer(0));
	}
	
	//*@ requires true;
	//*@ ensures true;
	public boolean isEquipable(Character c) {
		return true;
	}



	// Continua la Tostring di armor specificando requisiti,
	// modificatori e bonus propri dello shield 
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "è uno scudo e non ha requisiti. " 
		+", Diminuisce gli attacchi subiti di "+  this.bonusAttack.get("Difesa")
		+"e la destrezza di "+ this.modificator.get(Ability.Destrezza)
		+System.getProperty("line.separator")  ;
		return toRet;
	}

}
