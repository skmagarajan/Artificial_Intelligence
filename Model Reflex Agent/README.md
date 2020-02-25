# Model Reflex Agent

This project is to build the reflex agent with state in the wumpus world.

# Model Description:

* Created a separate class as Model in the wumpus world and added parameters, functions to keep track of internal state which is adjusted by each percept and action.

* This model will keep updating the world for each percept and actions. 

* It keeps track of which cell are not safe-0, partially safe-0.3 and safe-1

* Agent location and direction will be kept updated for every action.

* If the two cells possess the same cell as partially safe then that cell is confirmed as pit and remaining cell related to that two cells are safe zones.
 
* For eg: (1,0) feels breeze then (1,0) contains (1,1) and (2,0) value as 0.3 which is partially safe and my model will make agent to return in visited cell and traverse through other cells if it feels breeze once again at (0,1) then it update near cell (0,2) and (1,1) value 0.3 as partially safe

* From the above example different cells contain common positions that contain partial safe so it has a higher probability to have pits. And assigned (1,1) value 1 and other cells (1,1) and (0,2) value 0 which is safe.

# Parameters:

* Assigned agent position of X and Y is initially Zero.
* Create a world matrix which holds the probability of Safe(1) or Not Safe(0) for every cell
* Initial direction of agent is assigned as “East”
* Initial ShootFlag is “Not Yet”
* Maintaining a hashMap to find pits

# Functions:

### updateLocation(direction) - Whenever agent move forward this function is called

* If direction is east, moving forward will increase agent position of X by 1
* If direction is north, moving forwards will increase agent position of Y by 1
* If direction is west, moving forward will decrement agent position of X by 1
* If direction is south, moving forwards will decrement agent position of Y by 1

### SafeUpdate() - Whenever there is no percept this function is called

* Particular position value will be Safe (1)
* And adding near location of agent is all Safe (1)

### updateTurn() - Whenever agent turn to left or right this function update the direction of agent

#### Turn Right:
* If agent in East, then direction changes to North
* If agent in North, then direction changes to West
* If agent in West, then direction changes to South
* If agent in South, then direction changes to East

#### Turn Left:
* If agent in East, then direction changes to South
* If agent in North, then direction changes to East
* If agent in West, then direction changes to North
* If agent in South, then direction changes to West

### UpdateWorld() - This function called whenever breeze is appeared

* Agent Nearer locations are updated to chance of Pits (0.3).
* Add key as agent location and values as list of chances of pit location.

### Boolean checkSafeZone() - It will whether the moving forward location is safe or not

* If moving forward state value is 1, then function will return true
* Else return false i.e moving forward is not safe

### UpdatePits() - This function to confirm pits from chances of pits

* If two cell possess same location chances of pits then it marked as pits
* And remaining location regarding that two cell is made into safe zone

# Condition Action Rules: 


 > actionTable = new int[8];

>  actionTable[0] = Action.NO_OP;

>  actionTable[1] = Action.GO_FORWARD;

>  actionTable[2] = Action.GO_FORWARD;

>  actionTable[3] = Action.GO_FORWARD;

>  actionTable[4] = Action.TURN_RIGHT;

>  actionTable[5] = Action.TURN_LEFT;

>  actionTable[6] = Action.GRAB;

>  actionTable[7] = Action.SHOOT;

* If glitter is present then action should be grabbed.

* If all the percept is none, then model will update all near location as safe, update the agent location and agent will move forward

* If bump is present, getting random turn i.e left or right turn and model will update agent direction

* If breeze is present,
  * At starting position, then agent do no operation
  * Else model will update the world that nearer locations are not safe.
  * If any pits are confirmed by the model, then it will be updated.
  * Finally, checks moving forward is a safe zone or not, if yes then move forward else it will turn and model will update these actions.

* If Stench is true,
  * If model has shootFlag “not yet” then it will shoot
  * Else check wumpus is killed or not if yes then move forward or else no operation

* If Scream is present, then the shootFlag is assigned as “Wumpus Killed” and action will be moving forward.

* If both breeze and stench are present then no operation will be performed by the agent.




