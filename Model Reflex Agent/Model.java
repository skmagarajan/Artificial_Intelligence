import java.util.*;

public class Model {
    protected int agent_pos_x = 0;      //Initial Position x
    protected int agent_pos_y = 0;      //Initial Position y
    protected double world[][] = new double[4][4];       //World State are preserved here
    protected String direction = "East";            //Initial Direction of Agent Facing
    protected String ShootFlag = "Not Yet";

    HashMap<Integer, List<Integer>> FindingPits = new HashMap<Integer, List<Integer>>();

    protected void updateLocation(String dir){
        if(dir == "East" && agent_pos_x < 3)
            agent_pos_x = agent_pos_x+1;
        if(dir == "North" && agent_pos_y < 3)
            agent_pos_y = agent_pos_y+1;
        if(dir == "West" && agent_pos_x > 0)
            agent_pos_x = agent_pos_x-1;
        if(dir == "South" && agent_pos_y > 0)
            agent_pos_y = agent_pos_y-1;
    }

    protected void SafeUpdate(){
        world[agent_pos_x][agent_pos_y] = 1;
        if(agent_pos_x -1 >= 0 && agent_pos_x -1 <=3){
            world[agent_pos_x-1][agent_pos_y] = 1.0;
        }
        if(agent_pos_x +1 >= 0 && agent_pos_x +1 <=3){
            world[agent_pos_x+1][agent_pos_y] = 1.0;
        }
        if(agent_pos_y -1 >= 0 && agent_pos_y -1 <=3){
            world[agent_pos_x][agent_pos_y-1] = 1.0;
        }
        if(agent_pos_y +1 >= 0 && agent_pos_y +1 <=3){
            world[agent_pos_x][agent_pos_y+1] = 1.0;
        }
    }

    protected void updateTurn(int turn){
        if(turn == 5){
            if(direction == "East"){
                direction = "North";
            }
            else if(direction == "North"){
                direction = "West";
            }
            else if(direction == "West"){
                direction = "South";
            }
            else if(direction == "South"){
                direction = "East";
            }
        }
        else{
            if(direction == "East"){
                direction = "South";
            }
            else if(direction == "North"){
                direction = "East";
            }
            else if(direction == "West"){
                direction = "North";
            }
            else if(direction == "South"){
                direction = "West";
            }
        }
    }

    protected void printWorld(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.println("x: "+i+"y: "+j+": "+world[i][j]);
            }
        }
    }

    protected void UpdateWorld(){
        int key_pos = agent_pos_x*10+agent_pos_y;
        List<Integer> cell = new ArrayList<>();
        if(agent_pos_x -1 >= 0 && agent_pos_x -1 <=3){
            if(world[agent_pos_x-1][agent_pos_y] != 1.0){
                if(world[agent_pos_x-1][agent_pos_y] == 0.0){
                    world[agent_pos_x-1][agent_pos_y] = 0.3;
                }
//                else if(world[agent_pos_x-1][agent_pos_y] == 0.3){
//                    world[agent_pos_x-1][agent_pos_y] = 0.6;
//                }
                int pos = (agent_pos_x-1)*10+agent_pos_y;
                cell.add(pos);
            }
        }
        if(agent_pos_x +1 >= 0 && agent_pos_x +1 <=3){
            if(world[agent_pos_x+1][agent_pos_y] != 1.0){
                if(world[agent_pos_x+1][agent_pos_y] == 0.0){
                    world[agent_pos_x+1][agent_pos_y] = 0.3;
                }
//                else if(world[agent_pos_x+1][agent_pos_y] == 0.3){
//                    world[agent_pos_x+1][agent_pos_y] = 0.6;
//                }
                int pos = (agent_pos_x+1)*10+agent_pos_y;
                cell.add(pos);
            }
        }
        if(agent_pos_y -1 >= 0 && agent_pos_y -1 <=3){
            if(world[agent_pos_x][agent_pos_y-1] != 1.0){
                if(world[agent_pos_x][agent_pos_y-1] == 0.0){
                    world[agent_pos_x][agent_pos_y-1] = 0.3;
                }
//                else if(world[agent_pos_x][agent_pos_y-1] == 0.3){
//                    world[agent_pos_x][agent_pos_y-1] = 0.6;
//                }
                int pos = (agent_pos_x)*10+agent_pos_y-1;
                cell.add(pos);
            }
        }
        if(agent_pos_y +1 >= 0 && agent_pos_y +1 <=3){
            if(world[agent_pos_x][agent_pos_y+1] != 1.0){
                if(world[agent_pos_x][agent_pos_y+1] == 0.0){
                    world[agent_pos_x][agent_pos_y+1] = 0.3;
                }
//                else if(world[agent_pos_x][agent_pos_y+1] == 0.3){
//                    world[agent_pos_x][agent_pos_y+1] = 0.6;
//                }
                int pos = (agent_pos_x)*10+agent_pos_y+1;
                cell.add(pos);
            }
        }
        FindingPits.put(key_pos,cell);
    }

    protected boolean checkSafeZone(){
        if(direction == "East"){
            if(agent_pos_x +1 >= 0 && agent_pos_x +1 <=3){
                if(world[agent_pos_x+1][agent_pos_y] == 1.0){
                    return true;
                }
            }
        }

        else if(direction == "West"){
            if(agent_pos_x-1 >= 0 && agent_pos_x -1 <=3){
                if(world[agent_pos_x-1][agent_pos_y] == 1.0){
                    return true;
                }
            }
        }

        else if(direction == "North"){
            if(agent_pos_y+1 >= 0 && agent_pos_y +1 <=3){
                if(world[agent_pos_x][agent_pos_y+1] == 1.0){
                    return true;
                }
            }
        }

        else{
            if(agent_pos_y-1 >= 0 && agent_pos_y -1 <=3){
                if(world[agent_pos_x][agent_pos_y-1] == 1.0){
                    return true;
                }
            }
        }
        return false;
    }

    protected void UpdatePits() {
        List<Integer> pits=new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
            Iterator hmIterator = FindingPits.entrySet().iterator();
            while (hmIterator.hasNext()) {                                  //Travelling through every hashMap Keys
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                ArrayList<Integer> arr = new ArrayList<>();
                arr = (ArrayList<Integer>) mapElement.getValue();           //Getting List value of particular Key
                for (int j = 0; j < arr.size(); j++) {
                    int ele = arr.get(j);
                    if (!set.add(ele)) {
                        pits.add(ele);                                      //THis one add pit pos
                    } else {
                        set.add(ele);
                    }
                }
            }

            for(int i=0;i<pits.size();i++) {            //If pits is found make that as Not Safe and
                                                        // then making other position related to that are Safe ZOne
                System.out.println(FindingPits);
                System.out.println(pits);
                int pos = pits.get(i);
                Iterator hmIterator2 = FindingPits.entrySet().iterator();
                while (hmIterator2.hasNext()) {
                    Map.Entry mapElement = (Map.Entry) hmIterator2.next();
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr = (ArrayList<Integer>) mapElement.getValue();
                    if (arr.contains(pos)) {
                        for (int k = 0; k < arr.size(); k++) {
                            int val = arr.get(k);
                            if (val == pos) {
                                int x = 0, y = 0, flag = 0;
                                while (pos > 0) {
                                    if (flag == 0) {
                                        flag++;
                                        y = pos % 10;
                                    } else
                                        x = pos;
                                    pos = pos / 10;
                                }
                                world[x][y] = 0;
                            }
                            else {
                                int x = 0, y = 0, flag = 0;
                                while (pos > 0) {
                                    if (flag == 0) {
                                        flag++;
                                        y = pos % 10;
                                    } else
                                        x = pos;
                                    pos = pos / 10;
                                }
                                world[x][y] = 1;
                            }
                        }
                    }
                }
            }
    }


    protected void AgentLocation(){
        System.out.println("Agent LOc: "+agent_pos_x+agent_pos_y);
    }

}
