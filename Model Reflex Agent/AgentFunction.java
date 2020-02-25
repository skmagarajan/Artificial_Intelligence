/*
 * Class that defines the agent function.
 * 
 * Written by James P. Biagioni (jbiagi1@uic.edu)
 * for CS511 Artificial Intelligence II
 * at The University of Illinois at Chicago
 * 
 * Last modified 2/19/07 
 * 
 * DISCLAIMER:
 * Elements of this application were borrowed from
 * the client-server implementation of the Wumpus
 * World Simulator written by Kruti Mehta at
 * The University of Texas at Arlington.
 * 
 */

import java.util.Random;

class AgentFunction extends Model{
	
	// string to store the agent's name
	// do not remove this variable
	private String agentName = "Agent Smith";
	
	// all of these variables are created and used
	// for illustration purposes; you may delete them
	// when implementing your own intelligent agent
	private int[] actionTable;
	private boolean bump;
	private boolean glitter;
	private boolean breeze;
	private boolean stench;
	private boolean scream;
	private Random rand;
	private Model model;
	public AgentFunction()
	{
		// for illustration purposes; you may delete all code
		// inside this constructor when implementing your 
		// own intelligent agent

		// this integer array will store the agent actions
		actionTable = new int[8];

		actionTable[0] = Action.NO_OP;
		actionTable[1] = Action.GO_FORWARD;
		actionTable[2] = Action.GO_FORWARD;
		actionTable[3] = Action.GO_FORWARD;
		actionTable[4] = Action.TURN_RIGHT;
		actionTable[5] = Action.TURN_LEFT;
		actionTable[6] = Action.GRAB;
		actionTable[7] = Action.SHOOT;
		
		// new random number generator, for
		// randomly picking actions to execute
		rand = new Random();
		model = new Model();
	}

	public int process(TransferPercept tp)
	{
		// To build your own intelligent agent, replace
		// all code below this comment block. You have
		// access to all percepts through the object
		// 'tp' as illustrated here:
		
		// read in the current percepts
		bump = tp.getBump();
		glitter = tp.getGlitter();
		breeze = tp.getBreeze();
		stench = tp.getStench();
		scream = tp.getScream();

		model.AgentLocation();
		model.printWorld();

		if(glitter == true){
			return actionTable[6];
		}
		
		if (bump != true && breeze != true && stench != true) {
			model.SafeUpdate();
			model.updateLocation(model.direction);
			return actionTable[1];
		}

		if (bump == true) {
			int turn = rand.nextInt(5-4)+4;
			model.updateTurn(turn);
			return actionTable[turn];
		}

		if(breeze == true && stench != true){
			if(model.agent_pos_x == 0 && model.agent_pos_y == 0)			//If Breeze at Starting position, No operation performed
				return actionTable[0];

			model.UpdateWorld();
			if(model.FindingPits.size()>0){
				model.UpdatePits();
			}
			//System.out.println(model.Pits);
			if(model.checkSafeZone()){
				model.updateLocation(model.direction);
				return actionTable[1];
			}
			else{
				int turn = rand.nextInt(5-4)+4;
				model.updateTurn(turn);
				return actionTable[turn];
			}
		}

		if(stench == true){			//If stench is present and check whether wumpus killed or not. if not, shoot
			if(model.ShootFlag == "Not Yet"){
				model.ShootFlag = "Shot";
				return actionTable[7];
			}
			if(model.ShootFlag == "Wumpus Killed") {
				model.updateLocation(model.direction);
				return actionTable[1];
			}
		}

		if(scream == true){					//If scream is true update wumpus killed and move forward
			model.ShootFlag = "Wumpus Killed";
			model.updateLocation(model.direction);
			return actionTable[1];
		}

		// return no action to be performed
	    return actionTable[0];
	}
	
	// public method to return the agent's name
	// do not remove this method
	public String getAgentName() {
		return agentName;
	}
}