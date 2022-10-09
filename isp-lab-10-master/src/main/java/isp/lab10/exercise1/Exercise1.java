package isp.lab10.exercise1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Exercise1 {
 public static void main(String[] args) throws Exception {
        
     ATC atc = new ATC();
      while (true) {
       System.out.println("Choose an option: \n1)Add an aircraft.\n2)Show aircrafts.\n3)Takeoff of the aircraft. \n4)Land an aircraft.\n5)Exit.");
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       int option = Integer.parseInt(reader.readLine());
       switch (option) {
          case 1: {
             System.out.println("Insert here ID for an aircraft: ");
             String id = reader.readLine();
             atc.addAircraft(id);
             System.out.println("Aircraft " + id + " has been added.");
             break;
          }
          case 2: {
             atc.showAircrafts();
             break;
          }
          case 3: {
             System.out.println("Please insert id for aircraft to takeoff: ");
             String id = reader.readLine();
             System.out.println("Please insert altitude: ");
             int altitude = Integer.parseInt(reader.readLine());
             atc.sendCommand(id, new TakeoffCommand(altitude));
             break;
          }
          case 4: {
             System.out.println("Please insert the id for an aircraft to land: ");
             String id = reader.readLine();
             atc.sendCommand(id, new LandCommand());
             break;
          }
          case 5: {
             System.out.println("Bye!!");
             System.exit(0);
          }
       }
    }
 }
}

