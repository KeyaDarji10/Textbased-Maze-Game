import java.util.HashMap;
import java.util.Scanner;

public class AdventureModel {
    private Room rooms[];
    private Adventurer adventurer;
    private Wall walls[];

    public AdventureModel() {
    }

    public void initGame() {
        walls = new Wall[4];
        rooms = new Room[9];

        for (int i = 0; i < 4; i++)
            walls[i] = new Wall();

        rooms[0] = new Room("Room 0", "You are currently in room 0. Try moving to room 1");
        rooms[1] = new Room("Room 1", "You are currently in room 1. Try moving to room 2");
        rooms[2] = new Room("Room 2", "You are currently in room 2. Try moving to room 3");
        rooms[3] = new Room("Room 3", "You are currently in room 3. Try moving to room 4");
        rooms[4] = new Room("Room 4", "You are currently in room 4. Try moving to room 5");
        rooms[5] = new Room("Room 5", "You are currently in room 5. Try moving to room 6");
        rooms[6] = new Room("Room 6", "You are currently in room 6. Try moving to room 7");
        rooms[7] = new Room("Room 7", "You are currently in room 7. Try moving to room 8");
        rooms[8] = new Room("Room 8", "You are currently in room 8. Try moving to the exit.");

        // maping wall direction

        walls[0].addDirection("east", walls[1]);
        walls[0].addDirection("south", rooms[0]);

        walls[1].addDirection("east", walls[2]);
        walls[1].addDirection("south", rooms[1]);
        walls[1].addDirection("west", walls[0]);

        walls[2].addDirection("east", walls[3]);
        // walls[2].addDirection("south", waters[1]);
        walls[2].addDirection("west", walls[1]);

        // walls[3].addDirection("west", waters[1]);

        // maping rooms direction

        rooms[0].addDirection("east", rooms[1]);
        rooms[0].addDirection("north", walls[0]);
        rooms[0].addDirection("south", rooms[2]);
        rooms[0].addDirection("west", walls[0]);

        rooms[1].addDirection("east", walls[1]);
        rooms[1].addDirection("west", rooms[0]);
        rooms[1].addDirection("north", walls[1]);
        rooms[1].addDirection("south", rooms[3]);

        rooms[2].addDirection("east", rooms[3]);
        rooms[2].addDirection("north", rooms[0]);

        rooms[3].addDirection("west", rooms[2]);
        rooms[3].addDirection("north", rooms[1]);
        rooms[3].addDirection("south", rooms[4]);

        rooms[4].addDirection("west", rooms[5]);
        rooms[4].addDirection("north", rooms[3]);

        rooms[5].addDirection("east", rooms[4]);
        rooms[5].addDirection("south", rooms[6]);

        rooms[6].addDirection("east", rooms[7]);
        rooms[6].addDirection("north", rooms[5]);

        rooms[7].addDirection("east", rooms[8]);
        rooms[7].addDirection("west", rooms[6]);

        rooms[8].addDirection("east", walls[3]);
        rooms[8].addDirection("west", rooms[7]);

        adventurer = new Adventurer(rooms[0], null, 0);
    }

    public void startGame() {
        initGame();
        Scanner input = new Scanner(System.in);
        String cmd;
        Location currentLoc, newLoc = null;
        // int takeThing;

        boolean b = false;
        do {
            currentLoc = adventurer.getLocation();
            showHelpMenu();
            cmd = input.next();
            if (cmd.equalsIgnoreCase("Go")) {
                do {
                    System.out.println("Current Location : " + currentLoc);
                    System.out.println("Choose direction:");
                    System.out.println("one of north, south, east, west");
                    String direc = input.next();

                    b = checkPathExist(direc, currentLoc);
                    if (b) {
                        newLoc = adventurerNextLocation(direc, currentLoc);
                        adventurer.setLocation((Room) newLoc);
                    }

                    else {
                        System.out.println("Here there is a Wall");
                        System.out.println("You can't Move to this direction. Try choosing a different direction.");
                    }
                } while (b == false);
                // } else if (cmd.equalsIgnoreCase("Look")) {
                // if (currentLoc instanceof Room)
                // ((Room) currentLoc).viewRoomContent();
                // adventurer.viewAdventurerInventory();
                // } else if (cmd.equalsIgnoreCase("Take")) {
                // if (currentLoc instanceof Room) {
                // ((Room) currentLoc).viewRoomContent();
                // System.out.println("Take the thing 0/1/2/3 .. from the room : ");
                // takeThing = Integer.parseInt(input.next());
                // Thing t = ((Room) currentLoc).getRoomItem(takeThing);
                // adventurer.take(t);
                // adventurer.viewAdventurerInventory();
                // }

                // } else if (cmd.equalsIgnoreCase("Drop")) {
                // adventurer.viewAdventurerInventory();
                // System.out.println("Drop the thing 0/1/2/3 .. from the Inventory to room :
                // ");
                // takeThing = Integer.parseInt(input.next());
                // if (currentLoc instanceof Room) {
                // Thing t = adventurer.getInventoryItem(takeThing);
                // ((Room) currentLoc).addObject(t);
                // ((Room) currentLoc).viewRoomContent();
                // }

                // } else if (cmd.equalsIgnoreCase("Use")) {
                // adventurer.viewAdventurerInventory();
                // System.out.println("Use the thing 0/1/2/3 .. from the Inventory : ");
                // takeThing = Integer.parseInt(input.next());
                // Thing t = adventurer.getInventoryItem(takeThing);
                // adventurer.drop(t);
            } else if (cmd.equalsIgnoreCase("Exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }

        } while (true);
    }

    public void showHelpMenu() {
        System.out.println("Choose command: ");
        System.out.println("Go");
        // System.out.println("Look");
        // System.out.println("Take");
        // System.out.println("Drop");
        // System.out.println("Use");
        System.out.println("Exit ");
        System.out.println();
    }

    public boolean checkPathExist(String path, Location loc) {

        HashMap<String, Location> hm = loc.getRoomDir();
        if (hm.containsKey(path)) {
            if (hm.get(path) instanceof Room)
                return true;
        }
        return false;
    }

    public Location adventurerNextLocation(String path, Location loc) {

        HashMap<String, Location> hm = loc.getRoomDir();
        if (hm.containsKey(path)) {
            if (hm.get(path) instanceof Room)
                return hm.get(path);
        }
        return loc;

    }
}