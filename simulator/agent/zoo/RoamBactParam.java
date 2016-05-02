package simulator.agent.zoo;

import simulator.Simulator;
import utils.UnitConverter;
import utils.XMLParser;

public class RoamBactParam extends BacteriumParam {

	public double detachmentChance;
	
	/**
	 * \brief Creates a parameter storage object for the Bacterium EPS species type
	 * 
	 * Creates a parameter storage object for the Bacterium EPS species type
	 */
	public RoamBactParam() {
		super();
	}
	
	/**
	 * \brief Initialises Bacterium species parameters, calling the relevant superclasses to initialise common parameters for Bacterium derived species
	 * 
	 * Initialises Bacterium species parameters, calling the relevant superclasses to initialise common parameters for Bacterium derived species
	 * 
	 * @param aSim	The simulation object used to simulate the conditions specified in the protocol file
	 * @param aSpeciesRoot	A species mark-up within the specified protocol file
	 */
	@Override
	public void init(Simulator aSim, XMLParser aSpeciesRoot, XMLParser speciesDefaults)
	{
		super.init(aSim,aSpeciesRoot,speciesDefaults);
		Double value;
		
		value = getSpeciesParameterDouble("detachmentChance", aSpeciesRoot, speciesDefaults);
		detachmentChance = (value == XMLParser.nullDbl) ? 0.05 : value;
	}
	
	
}
