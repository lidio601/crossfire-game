package it.polimi.ingsw.lab.items;

import java.util.*;
import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;
import it.polimi.ingsw.lab.utils.NotValidParameterException;

public /*@ pure @*/  class LongSword extends Sword {
	
	// Variabili ereditate +
	private static final int  forzareq=65;
	private static final int  destrezzareq=20; 
    private static final int  forzamod=20;
    private static final int  destrezzamod=-20;
	
    //REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(		this.getName()!=null && !this.getName().equals("")
				&&   this.requisiti.get(Ability.Forza).equals(forzareq)
				&& this.requisiti.get(Ability.Destrezza).equals(destrezzareq)
				&& this.modificator.get(Ability.Forza).equals(forzamod)
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
	
	// Costruttore 
	
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name)
	//@ && this.modificator.get(Ability.Forza).equals(forzamod)
	//@ && this.modificator.get(Ability.Destrezza).equals(destrezzamod)
	//@ && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
	//@ && this.bonusAttack.get("AttaccoADistanza").equals(0)
	//@ && this.bonusAttack.get("AttaccoMagico").equals(0);
	public LongSword(String name) {
		super(name);
		this.modificator.put(Ability.Forza,new Integer(forzamod));
		this.modificator.put(Ability.Destrezza,new Integer(destrezzamod));
		this.requisiti.put(Ability.Forza,new Integer (forzareq));
		this.requisiti.put(Ability.Destrezza,new Integer(destrezzareq));
		this.bonusAttack.put("attaccoRavvicinato",new Integer(0));
		this.bonusAttack.put("attaccoADistanza",new Integer(0));
		this.bonusAttack.put("attaccoMagico",new Integer(0));
	}
	// Metodo che controlla se un determinato oggetto 
	// può essere equipaggiato da un personaggio
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
		toRet += "e' una spada lunga e richiede forza > di"+ this.requisiti.get(Ability.Forza) 
		+System.getProperty("line.separator")
		+", aumenta la forza di "+this.modificator.get(Ability.Forza)
		+System.getProperty("line.separator")
		+",aumenta la destrezza di"+this.modificator.get(Ability.Destrezza) 
		+System.getProperty("line.separator");
		return toRet;
	}
}
