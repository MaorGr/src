package simulator.agent.zoo;

import java.math.BigInteger;

import simulator.Simulator;
import simulator.geometry.ContinuousVector;
import simulator.World;

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
	 * \brief Create a new Bacterium agent (who a priori is registered in at
	 * least one container).
	 * 
	 * This agent is located on the relevant grid.
	 */
	@Override
	public void createNewAgent(ContinuousVector position) 
	{
		
		if (Math.random() < 0.05) {
			_Roaming = true;
		} else {
			_Roaming = false;
		}
			
		super.createNewAgent(position);
			
	}
	
	/**
	 * \brief Initialises any new agent (progenitor or daughter cell), setting
	 * cell radius, generation, and genealogy.
	 */
	@Override
	public void init() 
	{
		if (Math.random() < 0.05) {
			_Roaming = true;
		} else {
			_Roaming = false;
		}
		super.init();
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
		
		if (_Roaming) {

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
