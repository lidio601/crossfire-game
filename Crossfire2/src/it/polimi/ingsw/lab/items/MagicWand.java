package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;

import java.util.Map;
import it.polimi.ingsw.lab.characters.Character;

public /*@ pure @*/ class MagicWand extends Weapon{

	private static final int abilreq=70; 
	private static final int bonusmagic=10;
	
	
	
	//REP INVARIANT
	//@ private invariant repOK();
	public /*@ pure @*/ boolean repOk() {
		if(!super.repOk()) {
			return false;
		}
		if(		this.getName()!=null && !this.getName().equals("")
				&& this.requisiti.get(Ability.Abilitamagiche).equals(abilreq)
				&& this.bonusAttack.get("AttaccoRavvicinato").equals(0)
				&& this.bonusAttack.get("AttaccoADistanza").equals(0)
				&& this.bonusAttack.get("AttaccoMagico").equals(bonusmagic))
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
	//@ && this.requisiti.get(Ability.Abilitamagiche).equals(abilreq)
	//@ && this.bonusAttack.get("AttaccoRavvicinato").equals(0)
	//@ && this.bonusAttack.get("AttaccoADistanza").equals(0)
	//@ && this.bonusAttack.get("AttaccoMagico").equals(bounusmagic);
	public MagicWand() {
		super("MaqicWand");
		this.requisiti.put(Ability.Abilitamagiche,new Integer (abilreq));
		this.bonusAttack.put("attaccoRavvicinato",new Integer(0));
		this.bonusAttack.put("attaccoADistanza",new Integer(0));
		this.bonusAttack.put("attaccoMagico",new Integer(bonusmagic));
	}

	// Metodo che controlla se un determinato oggetto 
	// può essere equipaggiato da un personaggio
	//@ also
	//@ ensures 
	//@ (\result==true)<==>
	//@ ((c.getAbility(Ability.Abilitamagiche).getValue())>
	//@ (Integer)requisiti.get(Abilitamagiche));
	//@  also
	//@ ensures
	//@ (\result==false)<==>
	//@ ((c.getAbility(Abilitamagiche).getValue())<
	//@ (Integer)requisiti.get(Abilitamagiche)); 
	public boolean isEquipable(Character c) {
		int am = 0;
		try {
			am = c.getAbility(Ability.Abilitamagiche).getValue();
		} catch (NotFoundException e) {
			
			e.printStackTrace();
		}
		int req =  (Integer) requisiti.get(Ability.Abilitamagiche);
		if(am<=req)
			return false;
		return true;
	}
	
	// Continua la Tostring di MagicItem specificando requisiti,
	// modificatori e bonus propri della magicWand 
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "e' una bacchetta magica e richiede abilità magiche > di"+ this.requisiti.get(Ability.Abilitamagiche) 
		+System.getProperty("line.separator")
		+", aumenta l'attacco magico di "+this.bonusAttack.get("AttaccoMagico")
		+System.getProperty("line.separator");
		return toRet;
	}

}
