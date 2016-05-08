package simulator.agent.zoo;

import simulator.Simulator;
import simulator.geometry.ContinuousVector;
import utils.XMLParser;

public class RoamingBacterium extends Bacterium 
{
	/**
	 * Boolean noting whether any EPS particles have been declared as part of
	 * this Bacterium.
	 */
	protected Boolean _Roaming = false;
	
	
	/**
	 * \brief Constructor used to generate progenitor and initialise an object to store relevant parameters
	 * 
	 * Constructor used to generate progenitor and initialise an object to store relevant parameters
	 */
	public RoamingBacterium() {
		super();
		_speciesParam = new RoamBactParam();
	}
	
	/**
	 * \brief Creates a Bacterium agent from the parameters specified in the
	 * XML protocol file.
	 * 
	 * @param aSim	The simulation object used to simulate the conditions
	 * specified in the protocol file.
	 * @param aSpeciesRoot	A species mark-up within the specified protocol
	 * file.
	 */
	@Override
	public void initFromProtocolFile(Simulator aSim, XMLParser aSpeciesRoot) 
	{
		// Initialisation of the Active agent
		super.initFromProtocolFile(aSim, aSpeciesRoot);
		
		init();
	}
	
	/**
	 * \brief Create a new Bacterium agent (who a priori is registered in at
	 * least one container).
	 * 
	 * This agent is located on the relevant grid.
	 */
	@Override
	public void createNewAgent(ContinuousVector position) 
	{
		
			
		super.createNewAgent(position);
			
	}
	
	/**
	 * \brief Return the set of parameters that is associated with the object
	 * of this species.
	 * 
	 * @return Object of BacteriumParam that stores the parameters associated
	 * with this species.
	 */
	@Override
	public RoamBactParam getSpeciesParam()
	{
		return (RoamBactParam) _speciesParam;
	}
	
	/**
	 * \brief Initialises any new agent (progenitor or daughter cell), setting
	 * cell radius, generation, and genealogy.
	 */
	@Override
	public void init() 
	{
		// after being created, determine roaming.
		double detachmentChance = getSpeciesParam().detachmentChance;
		_Roaming = (Math.random() < detachmentChance);

	}
	
	/**
	 * \brief With it determined that cell division will occur, create a new
	 * agent from the existing one.
	 * 
	 * @throws CloneNotSupportedException Thrown if the agent cannot be cloned.
	 */
	@Override
	public void makeKid() throws CloneNotSupportedException
	{
		super.makeKid();
		
		// after kid is created, determine roaming.
		double detachmentChance = getSpeciesParam().detachmentChance;
		_Roaming = (Math.random() < detachmentChance);

		this.relocate(false);

		
	}
	
	
	/**
	 * \brief Called by Bacterium.createAgent and to obtain another instance
	 * of the same species (totally independent).
	 * 
	 * The returned agent is NOT registered.
	 * 
	 * @throws CloneNotSupportedException	Exception thrown if the object
	 * cannot be cloned
	 */
	@Override
	public RoamingBacterium sendNewAgent() throws CloneNotSupportedException 
	{

		RoamingBacterium baby = (RoamingBacterium)super.sendNewAgent();
		
		double detachmentChance = getSpeciesParam().detachmentChance;
		baby._Roaming = (Math.random() < detachmentChance);

		baby.relocate(false);
		return baby;
	}
	
	public void relocate(Boolean isParent) {
		double length_X = _species.domain.length_X;
		double length_Y = _species.domain.length_Y;

		ContinuousVector position = this.getLocation();
		position.x = (position.x + 10) % length_X; 
		position.y = (10.0 * this._generation)  % length_Y;; 
		this.setLocation(position);
		_agentGrid.registerMove(this);
		
	}
	
	
	/**
	 * \brief Called at each time step of the simulation to compute mass
	 * growth and update radius, mass, and volume.
	 * 
	 * Under the control of the method Step of the class Agent to avoid
	 * multiple calls. Also determines whether the agent has reached the size
	 * at which it must divide, and monitors agent death.
	 */
	@Override
	protected void internalStep()
	{
		
		if (false && _Roaming) {

			double length_X = _species.domain.length_X;
			double length_Y = _species.domain.length_Y;
			double length_Z = _species.domain.length_Z;
			
			double nextX = length_X * (0.0 + 1.0 * Math.random());
			double nextY = length_Y * (0.0 + 1.0 * Math.random());
			double nextZ = length_Z * (0.0 + 1.0 * Math.random());
	
			ContinuousVector cc = new ContinuousVector(nextX, nextY, nextZ);		
			this.setLocation(cc);
			_agentGrid.registerMove(this);
		}
		super.internalStep();
	}
}
