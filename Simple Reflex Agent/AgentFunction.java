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
import java.util.Set;

class AgentFunction {
	
	// string to store the agent's name
	// do not remove this variable
	private String agentName = "Agent Smith";
	private int action = 1;                              //DECLARED ACTION VARIABLE
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

		if (glitter == true) {					//If Glitter Pick gold
			action = 6;
		}
		else if (bump == true || glitter == true || breeze == true || stench == true || scream == true) {
			if (breeze == true || bump == true) {							// If feel bump or breeze if random
				double prob = rand.nextDouble()*(0.1+(1.0-0.1));		// probability greater than 80 then move left
				if(prob > 0.8)
					action = 5;
				else
					action = 0;
			}
			else if (stench == true) {										// If Wumpus nearer if random probability
				double prob = rand.nextDouble()*(0.1+(1.0-0.1));			// greater than 80 then Shoot
				if(prob > 0.8)
					action = 7;
				else
					action = 0;
			}
		}
		else if (scream == true) {											//If Scream then we can move forward!
			action = 1;
		}

		// return action to be performed
	    return actionTable[action];
	}
	
	// public method to return the agent's name
	// do not remove this method
	public String getAgentName() {
		return agentName;
	}
}