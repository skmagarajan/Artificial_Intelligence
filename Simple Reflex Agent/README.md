#### Nick Name: Thanos 
 
## Simple Reflex Agent 
 
This assignment is to build the simple reflex agent in the wumpus world. 
 
#### Changes Made in Agent function 
 
* Added action instance variable. 
 
* Added No operation action in the action table array of index 0. 
 
* If glitter is found, grab it (action 6). 
 
* Check if any percepts are found or not, if not then action is move forward (action 1). 
 
* Else, Check whether breeze or bump is present if so get the random probability. If the random probability is greater than 0.8 then Turn left (or Right - action 5 or 4). Otherwise no operation (action 0). 
 
* Check whether Stench is present if so get the random probability and it is greater than 0.8 shoot the wumpus (action 7) else no operation (action 0). 
 
* If wumpus is killed and scream percept is true then agent can move forward (action 1). 
 
#### To Run 
 
I’ve used IntelliJ IDEA application and imported the project files. 
 
Added some command line arguments (RUN -> EDIT CONFIGURATIONS)  
 
Program Arguments:  -t 10000 -a false -n false  
 
-t  ~ Number of Trials. -a ~ Agent Location. -n ~ Non-Deterministic. 
 
 
 
 
 
 
 
#### Results 
 
Wumpus-Lite v0.18a 
 
Dimensions: 4x4 Maximum number of steps: 50 Number of trials: 10000 Random Agent Location: false Random number seed: -96565918 Output filename: wumpus_out.txt Non-Deterministic Behavior: false 
 
 
Total Score: 1285167 Average Score: 128.5167 
 
 
Total Score: 1246273 Average Score: 124.6273 
 
 
Total Score: 1286756 Average Score: 128.6756 
