package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;

public /*@ pure @*/ class Bow extends Weapon {
	private static final int req=100;
	private static final int bonusdist=10;
	
	
	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(     this.getName()!=null && !this.getName().equals("") 
				&& this.requisiti.get("requisito").equals(req)
				&& this.bonusAttack.get("AttaccoRavvicinato").equals(0)
				&& this.bonusAttack.get("AttaccoADistanza").equals(bonusdist)
				&& this.bonusAttack.get("AttaccoMagico").equals(0))	
			
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name)
	//@ && this.requisiti.get("requisito").equals(req)
	//@ && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
	//@ && this.bonusAttack.get("AttaccoADistanza").equals(bonusdist)
	//@ && this.bonusAttack.get("AttaccoMagico").equals(0);
	/**
	 * 
	 * 
	 */
	public Bow(String nome) 
	{
		super(nome);
		this.bonusAttack.put("AttaccoRavvicinato",new Integer(0));
		this.bonusAttack.put("AttaccoADistanza",new Integer(bonusdist));
		this.bonusAttack.put("AttaccoMagico",new Integer(0));
		this.requisiti.put("requisito",new Integer (req));
	}

	
	// Metodo che controlla se un determinato oggetto 
	// può essere equipaggiato da un personaggio
	//@ also
	//@ ensures 
	//@ (\result==true)<==>
	//@ ((c.getAbility(Ability.Fortuna).getValue()+
	//@ c.getAbility(Ability.Destrezza).getValue())>
	//@ (Integer)requisiti.get("requisito"));
	//@ also
	//@ ensures
	//@ (\result==false)<==>
	//@ ((c.getAbility(Ability.Fortuna).getValue()+
	//@  c.getAbility(Ability.Destrezza).getValue())<
	//@ (Integer)requisiti.get("requisito"));
	/**
	 * 
	 * 
	 */
	public boolean isEquipable(Character c) {
		
		int f = 0;
		try {
			f = c.getAbility(Ability.Fortuna).getValue();
		} catch (NotFoundException e) {
			
			e.printStackTrace();
		}
		int d = 0;
		try {
			d = c.getAbility(Ability.Destrezza).getValue();
		} catch (NotFoundException e) {
			
			e.printStackTrace();
		}
		
		int val=f+d;
		int req=((Integer)requisiti.get("requisito"));
		
		if(val<=req)
			return false;
		return true;
	}
	
	
	// Continua la Tostring di weapon specificando requisiti,
	// modificatori e bonus propri del bow 
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "ed in particolare è un arco, " +
				"richiede che la somma di Fortuna e Destrezza sia > di "+ this.requisiti.get("requisito") 
		+", aumenta l'attacco a distanza di "+  this.bonusAttack.get("AttaccoADistanza")
		+System.getProperty("line.separator");
		return toRet;
	}

	
	
	
	public static void main(String[] args) {
		
		Bow arco = new Bow("arco");
		
	}


	
	
	
	
	
	
}



