/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp.lab10.exercise1;

/**
 *
 * @author andreeabardasan
 */
public class TakeoffCommand extends AtcCommand {
    private int altitude;

    public TakeoffCommand(int altitude) {
        this.altitude = altitude;
    }

    public int getAltitude() {
        return altitude;
    }
}
