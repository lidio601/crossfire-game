package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;

import java.util.Map;

public class LifePotion extends Potions {
	private static final int lifereq=0;
	private static final int lifemod=10;
	
	
	
	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(		this.getName()!=null && !this.getName().equals("")
				&& this.requisiti.get(Ability.Life).equals(lifereq)
				&& this.modificator.get(Ability.Life).equals(lifemod))
				
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
	//@ && this.modificator.get(Ability.Life).equals(lifemod)
	//@ && this.requisiti.get(Ability.Life).equals(lifemod);
	public LifePotion() {
		super(Item.LifePotion);
		this.modificator.put(Ability.Life,new Integer(lifemod));
		this.requisiti.put(Ability.Life,new Integer (lifereq));
	}
	
	// Metodo che controlla se un determinato oggetto 
	// può essere equipaggiato da un personaggio
	//@ also
	//@ ensures 
	//@ (\result==true)<==>
	//@ ((c.getAbility(Ability.Life).getValue())>
	//@ (Integer)requisiti.get(Ability.Life));
	//@  also
	//@ ensures
	//@ (\result==false)<==>
	//@ ((c.getAbility(Ability.Life).getValue())<
	//@ (Integer)requisiti.get(Ability.Life));
	public boolean isEquipable(Character c)throws NotFoundException {
		
		int life = 0;
		int req = 0;
		try {
			life=c.getAbility(Ability.Life).getValue();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		req=(Integer)requisiti.get(Ability.Life);
		if (life>req){
			return true;
		}
		else 
			return false;
	}

	
	// modificatori e bonus propri del pozione
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "ed in particolare è un lifepotion, " +
				"richiede che la vita sia > di "+ this.requisiti.get(Ability.Life) 
		      +", aumenta la vita di "+  this.modificator.get(Ability.Life)
		      +System.getProperty("line.separator");
		return toRet;
	}
	
	
	

}
