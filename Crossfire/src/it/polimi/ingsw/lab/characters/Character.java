package it.polimi.ingsw.lab.characters;

import it.polimi.ingsw.lab.items.*;
import it.polimi.ingsw.lab.utils.*;
import java.util.*;

//import org.omg.CosNaming.NamingContextPackage.NotFound; ?

public abstract class Character {
	/**
	 * Definizione delle costanti per validare il nome
	 * della Razza
	 */
	public static final String Umano = "umano";
	public static final String Orco = "orco";
	public static final String Elfo = "elfo";
	public static final String Mago = "mago";
	
	/**
	 * Consideriamo la Vita del personaggio come una Ability
	 */
	private /*@ spec_public non_null @*/ String name;
	private /*@ spec_public non_null @*/ Ability life;
	private /*@ spec_public non_null @*/ Integer bonus;
	private /*@ spec_public non_null @*/ Map abilities; 
	private /*@ spec_public non_null @*/ List inventory;
	private /*@ spec_public non_null @*/ Set equipped;
	
	//@ private invariant repOk();
	public /*@ pure @*/ boolean repOk() {
		/**
		 * il nome non deve essere una stringa vuota!
		 */
		if(this.name == null || name.equals(""))	return false;
		/**
		 * verifico che le chiavi di abilities siano String
		 */
		Set keys = abilities.keySet();
		Iterator overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof String)) {
				return false;
			}
		}
		/**
		 * verifico che gli oggetti di abilities siano Ability
		 */
		keys = abilities.entrySet();
		overKeys = keys.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Ability)) {
				return false;
			}
		}
		/**
		 * verifico che la vita sia in un range di valori ben definito:
		 * Un personaggio ha 100 punti vita, quando questi vengono
		 * esauriti il personaggio muore e viene rimosso dal gioco
		 * (quindi non devo controllare che life sia minore di 0 perchè
		 * è una condizione valida che indica la fine del gioco!).
		 */
		if(this.life.getValue() > 100) {
			return false;
		}
		if(this.bonus.intValue() < 0) {
			return false;
		}
		/**
		 * verifico che tutti gli oggetti in inventory e equipped siano
		 * degli oggetti Item
		 */
		overKeys = inventory.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Item)) {
				return false;
			}
		}
		overKeys = equipped.iterator();
		while(overKeys.hasNext()) {
			Object o = overKeys.next();
			if(!(o instanceof Item)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Costruttori:
	 * Un personaggio ha un nome, una razza(data dal tipo
	 * di oggetto istanziato, vedi la gerarchia di Character) e una serie di abilità(classe Ability).
	 * All'inizio della partita il giocatore:
	 * - crea il suo personaggio
	 * - oppure ne carica uno precedentemente salvato 
	 */
	
	/**
	 * Costruttore: creazione di un personaggio
	 *  Quando il giocatore crea il suo personaggio, sceglie una delle razze,
	 * ricevendo un valore casuale di ogni abilità, inoltre ha dei punti bonus
	 * per incrementare delle abilità a sua scelta fino ad aver speso tutto il bonus.
	 *  Un personaggio ha 100 punti vita, quando questi vengono esauriti il personaggio
	 * muore e viene rimosso dal gioco.
	 * @param name nome del personaggio creato
	 */
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name)
	//@ && this.getLife() == 100;
	public Character(String name) {
		this.name = name;
		this.life = new Ability("Life",100);
		this.abilities = new HashMap();
		/**
		 * genero un valore casuale di ogni abilità
		 * e dei punti bonus
		 */
		Random rand = new java.util.Random();
		do {
			this.bonus = new Integer(rand.nextInt(6));	//random su 6 bit
			Ability ab = new Ability(Ability.Forza, rand.nextInt(6));
			this.abilities.put(Ability.Forza, ab);
			ab = new Ability(Ability.Abilitamagiche, rand.nextInt(6));
			this.abilities.put(Ability.Abilitamagiche, ab);
			ab = new Ability(Ability.Destrezza, rand.nextInt(6));
			this.abilities.put(Ability.Destrezza, ab);
			ab = new Ability(Ability.Fortuna, rand.nextInt(6));
			this.abilities.put(Ability.Fortuna, ab);
			ab = new Ability(Ability.Intelligenza, rand.nextInt(6));
			this.abilities.put(Ability.Intelligenza, ab);
		} while(!this.repOk());
		
		this.inventory = new ArrayList();
		this.equipped = new HashSet();
	}
	
	/**
	 * Getter
	 */
	//@ requires true;
	//@ ensures \result.getName().equals(this.getName());
	public String getName() {
		return name;
	}
	
	//@ requires true;
	//@ ensures \result == this.life.getValue();
	public int getLife() {
		return this.life.getValue();
	}
	
	//@ requires true;
	//@ ensures \result == this.bonus.intValue();
	public int getBonus() {
		return this.bonus.intValue();
	}
	
	/**
	 * questo metodo restituisce l'abilità corrispondente
	 * alla stringa passata
	 */
	//@ requires this.abilities.containsKey(a);
	//@ ensures \result instanceof Ability
	//@ && \result.getName() == a;
	//@ signals(NotFoundException e)
	//@ (!this.abilities.containsKey(a));
	public Ability getAbility(String a) throws NotFoundException {
		if(!this.abilities.containsKey(a)) {
				throw new NotFoundException();
			}
			else {
				return new Ability((Ability)this.abilities.get(a));
			}
	}
	
	/**
	 * Restituisce un HashSet di abilità contenute in questo Character
	 */
	//@ assignable nothing;
	//@ ensures (\forall Ability i; this.abilities.keySet().contains(i) ; \result.containsKey(i));
	public Set getAbilities(){
		return new HashSet(this.abilities.entrySet());
	}
	
	/**
	 * Restituisce una ArrayList dell'inventario corrente
	 */
	public List getInventory() {
		return new ArrayList(this.inventory);
	}
	
	/**
	 * Restituisce un Set di Item che sto usando
	 */
	public Set getEquipped() {
		return new HashSet(this.equipped);
	}

	/**
	 * Setter
	 */
	//@ requires newname!=null && !newname.equals("");
	//@ ensures this.name.equals(newname);
	public void setName(String newname) {
		this.name = new String(name);
	}
	
	/**
	 * Assegna una abilità al personaggio sfruttando
	 * il metodo Ability.produceAlteredAbility
	 * e reinserendo la nuova abilità nella HashMap abilities.
	 * Viene usato nell'assegnare i bonus rimanenti nel personaggio.
	 * @param abName String il nome dell'abilità da modificare
	 * @param addvalue int di quanto viene modificata l'abilità
	 */
	//@ requires this.abilities.keySet().contains(abName)
	//@ && !abName.equals(this.life.getName())
	//@ && this.bonus.intValue() >= addvalue;
	//@ ensures this.getAbility(abName).getValue() >= addvalue;
	//@ signals IllegalArgumentException
	//@ addvalue < 0;
	//@ signals NotFoundException
	//@ !this.abilities.keySet().contains(abName);
	public void assignBonus(String abName, int addvalue) throws Exception {
		if(addvalue < 0 || this.bonus.intValue()-addvalue < 0) throw new IllegalArgumentException("Il valore del bonus assegnato deve essere positivo!");
		if(!this.abilities.keySet().contains(abName)) throw new NotFoundException("L'abilità "+abName+" non è stata trovata!");
		//aggiungo il valore passato, all'abilità corrispondente
		Ability temp = this.getAbility(abName).produceAlteredAbility(addvalue);
		if(!repOk()) {
			throw new IllegalArgumentException("Il nuovo valore assegnato non rispetta la RepOk di questa Razza!");
		}
		else {
			this.abilities.put(temp.getName(),temp);
			//rimuovo i punti bonus che sto assegnando
			this.bonus = new Integer(this.bonus.intValue() - addvalue);
		}
	}
	
	//Producers
	/**
	 * @param itName String il nome dell'item da cercare nell'inventario
	 * @return boolean se l'oggetto è nel mio inventario o meno
	 */
	protected boolean hasItem(String itName) {
		boolean toRet = false;
		if(this.isEquipped(itName)) {
			toRet = toRet | true;
		}
		Iterator iter = this.inventory.iterator();
		Item myItem;
		while(iter.hasNext()) {
			myItem = (Item)iter.next();
			if(myItem.getName() == itName) {
				toRet = toRet | true;
			}
		}
		return toRet;
	}
	
	/**
	 * @param itName String il nome dell'oggetto da cercare
	 * @return boolean se l'oggetto è equipaggiato o meno
	 */
	protected boolean isEquipped(String itName) {
		boolean toRet = false;
		Iterator iter = this.equipped.iterator();
		Item myItem;
		while(iter.hasNext()) {
			myItem = (Item)iter.next();
			if(myItem.getName() == itName) {
				toRet = toRet | true;
			}
		}
		return toRet;
	}
	
	/**
	 * metodo che "raccoglie un oggetto" e lo mette in inventory
	 * @param newItem l'oggetto appena raccolto!
	 * @result boolean dice se l'oggetto è stato o meno equipaggiato 
	 */
	//@ requires true;
	//@ ensures this.inventory.contains(newItem);
	//@ signals NotValidObjectException
	//@ !(newItem instanceof Item);
	public boolean pick(Item newItem) throws NotValidObjectException {
		if(!(newItem instanceof Item)) throw new NotValidObjectException();
		return this.inventory.add(newItem);
	}
	
	/**
	 * metodo che "scarta un oggetto" e lo toglie dall'inventory
	 * (nel caso lo disequippa)
	 * @param i Item
	 * @return Item l'oggetto appena tolto dal personaggio
	 */
	//@ requires this.hasItem(i.getName());
	//@ ensures !(this.hasItem(i.getName());
	//@ signals NotValidObjectException
	//@ !(i instanceof Item);
	//@ signals NotFoundException
	//@ !this.hasItem(i);
	public Item drop(Item i) throws Exception {
		if(!(i instanceof Item)) throw new NotValidObjectException();
		if(!this.hasItem(i.getName()))	throw new NotFoundException();
		
		if(this.isEquipped(i.getName())) {
			this.unEquip(i.getName());
		}
		this.inventory.remove(i);
		return i;
	}

	/**
	 * metodo che "scarta un oggetto" e lo toglie dall'inventory
	 * sfruttando la funzione Item drop(Item);
	 * @param itName nome dell'item da rimuovere
	 * @return Item l'oggetto appena tolto dal personaggio
	 * @throws NotFoundException non trovo l'oggetto!
	 */
	//@ requires true;
	//@ ensures !(this.inventory.contains(\old itName));
	//@ signals NotFoundException
	//@ !(\old this.hasItem(nome));
	public Item drop(String itName) throws Exception {
		if(this.isEquipped(itName)) {
			this.unEquip(itName);
		}
		Iterator myItem = this.inventory.iterator();
		Item i;
		while(myItem.hasNext()) {
			i = (Item)myItem.next();
			if(i.getName().equals(itName)) {
				return this.drop(i);
			}
		}
		throw new NotFoundException(System.getProperty("line.separator")+"L'oggetto "+itName+" non è stato trovato in questo personaggio. "+this.toString());
	}
	
	/**
	 * @param nome Nome dell'oggetto da equipaggiare, l'oggetto deve già essere nell'inventario.
	 * @return boolean se l'oggetto è stato equipaggiato con successo
	 * @throws NotFoundException viene lanciata se non trovo l'oggetto richiesto nell'inventario.
	 */
	//@ requires this.hasItem(itName)
	//@ ensures this.isEquipped(itName)
	public boolean equip(String itName) throws Exception {
		if(this.isEquipped(itName)) {
			//Ho già equipaggiato l'oggetto, non devo fare nulla
			return true;
		}
		Item myItem;
		try {
			myItem = this.drop(itName);
		}
		catch(NotFoundException e) {
			throw new NotFoundException(e+System.getProperty("line.separator")+"non possiedi questo oggetto! come fai ad equipaggiarlo?!");
		}
		
		if(!myItem.isEquipable(this)) {
			return false;
		}
		
		HashMap modify = (HashMap)myItem.getModificator();
		//scorro le mie abilità e vedo applico i modificatori
		Iterator myAbilities = this.abilities.keySet().iterator();
		Ability myAbility;
		while(myAbilities.hasNext()) {
			myAbility = (Ability)myAbilities.next();
			if(modify.containsKey(myAbility.getName())) {
				try {
					myAbility = myAbility.produceAlteredAbility( ((Integer)modify.get( myAbility.getName() )).intValue() );
				} catch(Exception e) {
					//per qualche ragione non può essere equipaggiato, isEquipped ha fatto cilecca?!
					return false;
				}
				this.abilities.put(myAbility.getName(),myAbility);
			}
		}
		if(!(myItem instanceof Potions)) {
			this.equipped.add(myItem);
		}
		return this.repOk();
	}
	
	/**
	 * metodo per togliere un item dall'equipaggiamento.
	 * se un oggetto non è presente, ritorno true!
	 * @param itName String nome dell'item da cercare e rimettere nell'inventario
	 * @return boolean esito dell'operazione
	 */
	//@ requires true;
	//@ ensures !this.isEquipped(itName);
	public boolean unEquip(String itName){
		if(!this.hasItem(itName)) {	//implicito controllo di equipped!
			return true;
		}
		if(!this.isEquipped(itName)) {
			return true;
		}
		Iterator myIt = this.equipped.iterator();
		Item myItem;
		while(myIt.hasNext()) {
			myItem = (Item)myIt.next();
			if(myItem.getName() == itName) {
				return this.equipped.remove(myItem) && this.inventory.add(myItem); 
			}
		}
		///devo togliere i modificatori
		return false;
	}
	
	/**
	 * Funzione di Astrazione:
	 * AF(c) = (* ritorna la descrizione di questo oggetto *);
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String toRet = "Un personaggio di nome " + this.getName() + System.getProperty("line.separator");
		//ciclo sulle abilità sfruttando la toString di Ability
		/////
		return toRet;
	}
}