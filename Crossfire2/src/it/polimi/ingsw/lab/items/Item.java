package it.polimi.ingsw.lab.items;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;
import it.polimi.ingsw.lab.utils.NotValidObjectException;
import it.polimi.ingsw.lab.utils.NotValidParameterException;

public /*@ pure @*/ abstract class Item {
	
	// Variabili
	
	/**
	 * Valori accettati per il campo nome degli oggetti
	 */
	// Variabili costanti che indicano il nome degli item
	public static final String LongSword="LongSword";
	public static final String LifePotion="LifePotion";
	public static final String MagicWond="MaginWond";
	public static final String Shield="Shield";
	public static final String Bow="Bow";
	/**
	 * Mappe dei requisiti, dei modificatori e dei bonus di ciascuno oggetto
	 */
	// Hashmap dei requisiti dell'oggetto e dei modificatori
	protected /*@ spec_public non_null @*/Map requisiti;
	protected /*@ spec_public non_null @*/Map modificator;
	protected /*@ spec_public non_null @*/Map bonusAttack;
	
	// Variabile della stringa nome
	private String name;
	
	//REP INVARIANT
	//@ private invariant repOk();
	public /*@ pure @*/ boolean repOk() {
		
		
		 // Verifico la validità del nome
		if(this.name == null || name.equals(""))	return false;
		
		// Verifico che le chiavi di requisiti siano String
		Set keys = requisiti.keySet();
		Iterator overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof String)) {
				return false;
			}
		}
		
		// Verifico che le chiavi di modificator siano String
		keys = modificator.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Ability)) {
				return false;
			}
		}
		
		 // Verifico che le chiavi di bonusAttack siano String
		keys = bonusAttack.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Ability)) {
				return false;
			}
		}
		
		 // Verifico che gli oggetti di requisiti siano Integer
		keys = requisiti.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Integer)) {
				return false;
			}
		}
		
		// Verifico che gli oggetti di modificator siano Integer
		keys = modificator.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Integer)) {
				return false;
			}
		}
		
		// Verifico che gli oggetti di bonusAttack siano Integer
		keys = bonusAttack.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Integer)) {
				return false;
			}
		}
		return true;
	}

	
	// Costruttore
	
	/**
	 * I costruttori dei figli devono inserire i propri requisiti,modificatori,e 
	 * bonus attacco nella Map
	 * @param name
	 */
	// Costruttore di item
	//@ requires name!=null 
	//@ && !name.equals("");
	//@ ensures this.name.equals(name) 
	//@ && name!=null 
	//@ && !name.equals("");
	public /*@ pure @*/  Item(String name) {
		this.name = name;
		this.requisiti = new HashMap();
		this.modificator = new HashMap();
		this.bonusAttack = new HashMap();
	}
	
	// Getters & Setters
	// Setters non necessari essendo Item pure
	
	/**
	 * Getter del nome
	 * @return the name
	 */
	// Getter del nome dell'Item
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name);
	public /*@ pure @*/  String getName(){	
		return new String(name);
	}

	
	/**
	 * Metodo che restituisce la mappa dei modificatori 
	 * di un certo oggetto se equipaggiato
	 * @return
	 */
	/*
	  Getter della mappa dei modificatori di un oggetto		
	*/
	//@ requires true;
	//@ ensures \result.equals(modificator);
	public Map getModificator(){
		Map m= new HashMap();
		m.putAll(modificator);
		return m;
	}
	
	/**
	 * Metodo che restituisce la mappa dei bonus di attacco
	 * generati da un determanto oggetto al momento dell'attacco
	 * @return
	 */
	/*
	  Getter della mappa dei requisiti di un oggetto		
	*/
	//@ requires true;
	//@ ensures \result.equals(bonusAttack);
	public Map getbonusAttack(){
		Map m= new HashMap();
		m.putAll(bonusAttack);
		return m;
	}
	
	/**
	 * Metodo che restituisce la mappa dei requisiti 
	 * per equipaggiare un certo oggetto
	 * @return
	 */
	/*
	  Getter della mappa dei requisiti di un oggetto		
	*/
	//@ requires true;
	//@ ensures \result.equals(requisiti);
	public Map getRequisiti(){
		Map m= new HashMap();
		m.putAll(requisiti);
		return m;
	}
	
	
	// Metodi
	
	/**
	 * Ritorna un booleano se il Character passato possiede le Ability
	 * necessarie per questo oggetto (il figlio!)
	 * @param c
	 * @return boolean
	 * @throws NotFoundException 
	 */
	// Controlla se un determinato oggetto 
	// può essere equipaggiato da un personaggio
	//@ requires true;
	//@ ensures (\result instanceof boolean) 
	//@ && (c instanceof Character);
	//@ signals (NotValidObjectException e)
	//@ (!(c instanceof Character));
	public /*@ pure @*/  abstract boolean isEquipable(Character c) throws NotFoundException;
	
	// Ridefinizione del metodo equals di object
	/**
	 * Devo raffinare la specifica del metodo ereditato da Object
	 */
	//@ also
	//@ requires o instanceof Item;
	//@ ensures \result == (((Item) o).getName().equals(this.getName()) && ((Item) o).getValue() == this.value());
	/**
	 * Cosa succede se non è rispettata la prima requires?
	 * Deve ritornare <false>
	 */
	//@ also
	//@ requires !(o instanceof Item) || o == null;
	//@ ensures false;
	public boolean equals(/*@ nullable @*/Object o) {
		if( o != null && o instanceof Item ) {
			Item a = (Item)o;
			if(a.getName().equals(this.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * AF(c) = (* ritorna la descrizione di questo oggetto *);
	 * 
	 * @@see java.lang.Object#toString()
	 */
	/* ToString che visualizza solo il nome dell'Item.
	   le altre sottoclassi implementano il resto della toString in modo
	   da darne una descrizione completa delle caratteristiche specifiche
	*/
	// AF(c) = (* ritorna la descrizione di questo oggetto *);
	// AF(c) = "L'oggeto è "name", appartiene alla categoria "target" 
	//         ed in particolare alla sottocategoria "sottocategoria"
	//         "descrizione delle caratterisctiche(requisiti,modificatori,bonus attacco)""
	public String toString() {
		String toRet = null;
		toRet = "L'oggetto è " + this.getName() + 
		System.getProperty("line.separator");
		return toRet;
	}

}
