package GerätePackage;

/**
 * @author Kevin Rabe
 *
 */
public class PC extends GeräteTyp2{

	public PC() {
		super(340.0, 97.5, 132.3, 125.5, 1.5);
		
		this.verbrauchsWerte.add(99.0);		//Modus 0: Leerlauf
		this.schwankungsWerte.add(1.5);
		
		this.verbrauchsWerte.add(112.0);	//Modus 1: Videos schauen (YouTube)
		this.schwankungsWerte.add(6.0);
		
		this.verbrauchsWerte.add(120.0);	//Modus 2: aktives browsen im Internet (Facebook und andere, komplexe Seiten)
		this.schwankungsWerte.add(20.0);
		
		this.verbrauchsWerte.add(310.0);	//Modus 3: Anwendung (komplexes Spiel)
		this.schwankungsWerte.add(30.0);
	}

}
