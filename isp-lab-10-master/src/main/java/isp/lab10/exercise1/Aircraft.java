/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp.lab10.exercise1;

import java.util.Objects;

/**
 *
 * @author andreeabardasan
 */
public class Aircraft implements Runnable{
    private String id;
    private int altitude;
    private STATE state;
    private int isLanded=0;
    private long cruiseStart;

    public Aircraft(){

    }
     public Aircraft(String id) {
        this.id = id;
        this.state=STATE.ON_STAND;
    }

    public String getId() {
        return id;
    }
    public Integer getAltitude(){
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
    public void run(){
        while (this.isLanded==0) {
            switch (this.state) {
                case ON_STAND: {
                    System.out.println("Aircraft - " + this+ " is on standing");
                    synchronized (this) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case TAXING: {
                    System.out.println("Aircraft - " + this+ " is in taxing mode");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.state = STATE.TAKING_OFF;
                    break;
                }
                case TAKING_OFF: {
                    System.out.println("Aircraft - " + this+ " is taking off");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.state = STATE.ASCENDING;
                    break;
                }
                case ASCENDING: {
                    System.out.println("Aircraft - " + this+ " is ascending");
                    for (int i = 1; i <=this.altitude; i++) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    this.state = STATE.CRUISING;
                    break;
                }
                case CRUISING: {
                    System.out.println("Aircraft - " + this +" is cruising");
                    this.cruiseStart = System.currentTimeMillis();
                    synchronized (this) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case DESCENDING: {
                    System.out.println("Aircraft - " + this+ " is descending");
                    while (this.altitude > 0) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.altitude--;
                    }
                    this.state = STATE.LANDED;
                    break;
                }
                case LANDED: {
                    System.out.println("Aircraft - " + this+ " has landed");
                    System.out.println("The aircraft " + this + " spent " + (System.currentTimeMillis() - cruiseStart) / 1000 + " seconds in the cruising mode.");
                    this.isLanded = 1;
                    break;
                }
                default: {
                    System.out.println("Wrong state!!");
                }
            }
        }

    }
    public void recieveAtcMessage(AtcCommand msg) {
        synchronized (this) {
            if (msg instanceof LandCommand) {
                if (this.state.equals(STATE.CRUISING)) {
                    this.state = STATE.DESCENDING;
                    this.notify();
                } else {
                    System.out.println("Aircraft isn't on CRUISING mode.");
                }
            } else if (msg instanceof TakeoffCommand) {
                if (this.state.equals(STATE.ON_STAND)) {
                    this.altitude = ((TakeoffCommand) msg).getAltitude();
                    this.state = STATE.TAXING;
                    this.notify();
                } else {
                    System.out.println("Aircraft isn't on ON_STAND mode.");
                }
            } else {
                System.out.println("Bad state.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return altitude == aircraft.altitude && isLanded == aircraft.isLanded && cruiseStart == aircraft.cruiseStart && Objects.equals(id, aircraft.id) && state == aircraft.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, altitude, state, isLanded, cruiseStart);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id='" + id + '\'' +
                ", altitude=" + altitude +
                ", state=" + state +
                ", isLanded=" + isLanded +
                ", cruiseStart=" + cruiseStart +
                '}';
    }
}

