package com.LAMPS.ShiftAssistant;

public class ProgramGetterSetter {

    private String Date = "0";
    private String ShiftID = "0";

    private int WorkerID = 0;
    private int TeamID = 0;
    private int TilVAC = 0;

    protected ProgramGetterSetter(String date, String shiftID, int workerID, int teamID, int tilVAC) {
        Date = date;
        ShiftID = shiftID;
        WorkerID = workerID;
        TeamID = teamID;
        TilVAC = tilVAC;
    }

//==================================================Getters=========================================

    protected String getDate() {
        return Date;
    }

    protected String getShiftID() {
        return ShiftID;
    }

    protected int getWorkerID() {
        return WorkerID;
    }

    protected int getTeamID() {
        return TeamID;
    }

    protected int getTilVAC() {
        return TilVAC;
    }

//==================================================Setters=========================================

    public void setDate(String date) {
        Date = date;
    }

    public void setShiftID(String shiftID) {
        ShiftID = shiftID;
    }

    public void setWorkerID(int workerID) {
        WorkerID = workerID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public void setTilVAC(int tilVAC) {
        TilVAC = tilVAC;
    }

}
