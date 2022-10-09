/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp.lab10.exercise1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreeabardasan
 */
public class ATC {
    private List <Aircraft> aircraftList = new ArrayList<Aircraft>();

    public void sendCommand(String aircraftId, AtcCommand cmd) {
        Aircraft aircraft1 = new Aircraft();
        int isfound = 0;
        for (Aircraft i : aircraftList) {
            if (aircraftId.equals(i.getId())) {
                aircraft1 = i;
                isfound = 1;
            }
        }
        if (isfound!=0) {
            aircraft1.recieveAtcMessage(cmd);
        }
    }
    public void addAircraft(String id) {
        Aircraft aircraft1 = new Aircraft(id);
        if (aircraftList.contains(aircraft1)) {
            System.out.println("This aircraft " + id + " already exists.");
        }
        else {
            aircraftList.add(aircraft1);
            new Thread(aircraft1).start();
        }
    }
    public void showAircrafts() {
        for (Aircraft i : aircraftList) {
            System.out.println(i);
        }
    }

}
