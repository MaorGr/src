package simulator.agent.zoo;

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
		super.internalStep();
	}
}
