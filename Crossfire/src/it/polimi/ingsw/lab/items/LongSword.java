package it.polimi.ingsw.lab.items;

import java.util.*;
import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;
import it.polimi.ingsw.lab.utils.NotValidParameterException;

public /*@ pure @*/  class LongSword extends Sword {
	
	// Variabili ereditate
	
	

	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(this.getName().equals("LongSword") 
				&& ((Integer)this.requisiti.get(Ability.Forza)).intValue() == 65
				&& this.requisiti.get(Ability.Destrezza).equals(20)
				&& this.modificator.get(Ability.Forza).equals(20)
				&& this.modificator.get(Ability.Destrezza).equals(-20)
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
	
	// Costruttore 
	
	//@ requires true;
	//@ ensures this.getName().equals("LongSword") 
	//@ && this.modificator.get(Ability.Forza).equals(20)
	//@ && this.modificator.get(Ability.Destrezza).equals(-20)
	//@ && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
	//@ && this.bonusAttack.get("AttaccoADistanza").equals(0)
	//@ && this.bonusAttack.get("AttaccoMagico").equals(0);
	public LongSword() {
		super("LongSword");
		this.modificator.put(Ability.Forza,new Integer(20));
		this.modificator.put(Ability.Destrezza,new Integer(-20));
		this.requisiti.put(Ability.Forza,new Integer (65));
		this.requisiti.put(Ability.Destrezza,new Integer(20));
		this.bonusAttack.put("attaccoRavvicinato",new Integer(0));
		this.bonusAttack.put("attaccoADistanza",new Integer(0));
		this.bonusAttack.put("attaccoMagico",new Integer(0));
	}
	// Metodo che controlla se un determinato oggetto 
	// pu essere equipaggiato da un personaggio
	//@ also
	//@ ensures 
	//@ (\result==true)<==>
	//@ ((c.getAbility(Ability.Forza).getValue()>
	//@ (Integer)requisiti.get(Ability.Forza)
	//@ && c.getAbility(Ability.Destrezza).getValue()>
	//@ (Integer)requisiti.get(Ability.Destrezza)));
	//@  also
	//@ ensures
	//@ (\result==false)<==>
	//@ ((c.getAbility(Ability.Forza).getValue()<
	//@ (Integer)requisiti.get(Ability.Forza)
	//@ || c.getAbility(Ability.Destrezza).getValue()<
	//@ (Integer)requisiti.get(Ability.Destrezza)));
	public boolean isEquipable(Character c) throws NotFoundException{
		
		int f = 0;
		try {
			f = c.getAbility(Ability.Forza).getValue();
		} catch (NotFoundException e1) {
			
			e1.printStackTrace();
		}
		int d = 0;
		try {
			d = c.getAbility(Ability.Destrezza).getValue();
		} catch (NotFoundException e) {
		
			e.printStackTrace();
		}
		int n = (Integer)requisiti.get(Ability.Forza);
		int g = (Integer)requisiti.get(Ability.Destrezza);
		if (f < n || d < g ){
			return false;	
		}
		return true;
	}
	
	
	// Continua la Tostring di Sword specificando requisiti,
	// modificatori e bonus propri della longSword 
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "Richiede forza > di"+ this.requisiti.get(Ability.Forza) 
		+", aumenta la forza di "+this.modificator.get(Ability.Forza)
		+",aumenta la destrezza di"+this.modificator.get(Ability.Destrezza) 
		+System.getProperty("line.separator");
		return toRet;
	}
}
