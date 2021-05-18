package sample;

import java.util.Scanner;

public class Elevator {
    private int currentLevel, minLevel, maxLevel;
    private boolean available = true;
    private String name;

    // Constructor
    public Elevator(String name) {
        this.name = name;
    }

    public Elevator(String name, int currentLevel) {
        this.currentLevel = currentLevel;
        this.name = name;
    }

    public Elevator(String name, int minLevel, int maxLevel) {
        this( name, 0 );
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public Elevator(String name, int currentLevel, int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.name = name;
        this.currentLevel = currentLevel;
        if ( currentLevel < minLevel )
            this.currentLevel = minLevel;
        else if ( currentLevel > maxLevel )
            this.currentLevel = maxLevel;
    }

    // getter & setter
    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void printAvailable() {
        if ( this.isAvailable() ) {
            System.out.println( this.getName() + " is available" );
        } else {
            System.out.println( this.getName() + " is not available" );
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Elevator " + getName() + " is at level "
                + currentLevel + ".";
    }




}

