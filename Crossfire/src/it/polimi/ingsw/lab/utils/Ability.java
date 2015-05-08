package it.polimi.ingsw.lab.utils;

import it.polimi.ingsw.lab.utils.Ability;

/**
 * classe Abilità: public, immutabile, pura
 * questa è stata modificata
 *
 **/
public /*@ pure @*/ class Ability {
	/**
	 * Definizione delle costanti per validare il nome
	 * della Abilità
	 */
	public static final String Life = "life";
	public static final String Forza = "forza";
	public static final String Intelligenza = "intelligenza";
	public static final String Fortuna = "fortuna";
	public static final String Destrezza = "destrezza";
	public static final String Abilitamagiche = "abilitamagiche";
	
	/**
	 * @uml.property  name="name"
	 */
	private /*@ spec_public non_null @*/ String name;
	/**
	 * @uml.property  name="value"
	 */
	private /*@ spec_public@*/ int value;
	
	//REP INVARIANT
	//@ private invariant
	//@ this.name != null && !this.name.equals("") &&
	//@ this.value >= 0 && this.value <= 100;
	
	/**
	 * oppure
	 */
	//@ private invariant repOk();
	public /*@ pure @*/ boolean repOk() {
		return (this.name != null && !this.name.equals("") && this.value >= 0 && this.value <= 100);
	}
	
	/**
	 * @param name nome dell'abilità
	 * @param value valore dell'abilità corrente
	 */
	//@ requires name!=null && !name.equals("") && value >=0 && value <=100;
	//@ ensures this.value==value && this.name.equals(name);
	public Ability(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	//@ requires name!=null && !name.equals("") && value >=0 && value <=100;
	//@ ensures this.value==value && this.name.equals(name);
	public Ability(Ability a) {
		super();
		this.name = a.getName();
		this.value = a.getValue();
	}
	
	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	//@ ensures this.name.equals(\result);
	public /*@ pure @*/ String getName() {
		return name;
	}

	/**
	 * @return  the value
	 * @uml.property  name="value"
	 */
	//@ ensures \result == this.value;
	public /*@ pure @*/ int getValue() {
		return value;
	}

	/* produttore */
	//@ requires true;
	//@ ensures \result.getName().equals(this.getName()) && (\result.getValue() == (this.getValue() + difference));
	//@ signals (ValueOutOfBoundException e)
	//@((this.getValue() + difference) > 100 || (this.getValue() + difference < 0));
	public /*@ pure @*/ Ability produceAlteredAbility(int difference) throws ValueOutOfBoundException {
		//if(this.getValue() + difference < 0 || this.getValue() + difference > 100)	throw ValueOutOfBoundException();
		return new Ability(this.name, this.getValue() + difference);
	}
	
	
	/**
	 * Devo raffinare la specifica del metodo ereditato da Object
	 */
	//@ also
	//@ requires o instanceof Ability;
	//@ ensures \result == (((Ability) o).getName().equals(this.getName()) && ((Ability) o).getValue() == this.value);
	/**
	 * Cosa succede se non è rispettata la prima requires?
	 * Deve ritornare <false>
	 */
	//@ also
	//@ requires !(o instanceof Ability) || o == null;
	//@ ensures false;
	
	public boolean equals(/*@ nullable @*/Object o) {
		if( o != null && o instanceof Ability ) {
			Ability a = (Ability)o;
			if(a.getName().equals(this.getName()) && a.getValue()== this.getValue()) {
				return true;
			}
		}
		return false;
	}
}
