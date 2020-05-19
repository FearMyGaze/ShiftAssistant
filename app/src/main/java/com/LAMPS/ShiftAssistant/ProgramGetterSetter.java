package com.LAMPS.ShiftAssistant;

public class ProgramGetterSetter {

    private String Date = "0";
    private String ShiftID = "0";
    private String WorkerID = "0";

    private String TeamID = "0";
    private String TilVAC = "0";

    protected ProgramGetterSetter(String date, String shiftID, String workerID, String teamID, String tilVAC) {
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

    protected String getWorkerID() {
        return WorkerID;
    }

    protected String getTeamID() {
        return TeamID;
    }

    protected String getTilVAC() {
        return TilVAC;
    }

//==================================================Setters=========================================

    public void setDate(String date) {
        Date = date;
    }

    public void setShiftID(String shiftID) {
        ShiftID = shiftID;
    }

    public void setWorkerID(String workerID) {
        WorkerID = workerID;
    }

    public void setTeamID(String teamID) {
        TeamID = teamID;
    }

    public void setTilVAC(String tilVAC) {
        TilVAC = tilVAC;
    }

}
