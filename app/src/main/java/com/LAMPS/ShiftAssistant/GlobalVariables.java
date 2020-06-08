package com.LAMPS.ShiftAssistant;

public class GlobalVariables {

    //Setup
    private String Protocol = "http://";
    private String IP = "192.168.1.6";//Change with your IP

    //Employees

    private String LoginEmployees = "/Shifts/LoginEmployee.php";
    private String RegisterEmployees = "/Shifts/RegisterEmployees.php";
    private String FetchEmployees = "/Shifts/FetchEmployees.php";

    //Owners

    private String LoginOwners ="/Shifts/LoginOwner.php";
    private String RegisterOwners = "/Shifts/RegisterOwners.php";
    private String FetchOwners = "0";

    //Teams

    private String DeleteTeams = "/shifts/DeleteTeams.php";
    private String SearchTeams = "/shifts/SearchTeam.php";
    private String NextTeam = "/shifts/NextTeam.php";
    private String PreviousTeam = "/Shifts/PrevTeam.php";
    private String NewTeams = "/Shifts/RegisterTeams.php";

    //Assistants

    private String DeleteAssistants = "/Shifts/DeleteAssistant.php";
    private String SearchAssistants = "/Shifts/SearchAssistant.php";
    private String NextAssistant = "/Shifts/NextAssistant.php";
    private String PreviousAssistant = "/Shifts/PrevAssistant.php";
    private String NewAssistant = "/Shifts/RegisterOwners.php";

    //Worker

    private String DeleteWorker = "/Shifts/DeleteWorkers.php";
    private String SearchWorker = "/Shifts/SearchWorker.php";
    private String NextWorker = "/Shifts/NextWorker.php";
    private String PreviousWorker = "/Shifts/PrevWorker.php";
    private String NewWorker = "/Shifts/RegisterEmployees.php";

    //Program

    private String ProgramGenerate = "/Shifts/ProgramGen.php";
    private String Vacation = "/Shifts/Vacation.php";
    private String EndVacation = "/Shifts/EndVacation.php";
    private String UploadProgramToDB = "/Shifts/RegisterProgram.php";
    private String DownloadProgramFromDB = "/Shifts/viewProgram.php";
    private String DropLastProgram = "/Shifts/deleteProgram.php";

    //Files

    private String FileProgram = "Program.json";
    private String FileEmployees = "Employees.json";

    //=================Output======================================================================

    //Employees
    private String LoginEmployees_URL = Protocol + IP + LoginEmployees;
    private String RegisterEmployees_URL = Protocol + IP + RegisterEmployees;
    private String FetchEmployees_URL = Protocol + IP + FetchEmployees;

    //Owners

    private String LoginOwners_URL = Protocol + IP + LoginOwners;
    private String RegisterOwners_URL = Protocol + IP + RegisterOwners;
    private String FetchOwners_URL = Protocol + IP + FetchOwners;

    //Teams

    private String DeleteTeams_URL = Protocol + IP + DeleteTeams;
    private String SearchTeams_URL = Protocol + IP + SearchTeams;
    private String NextTeam_URL = Protocol + IP + NextTeam;
    private String PreviousTeam_URL = Protocol + IP + PreviousTeam;
    private String NewTeams_URL = Protocol + IP + NewTeams;

    //Assistants

    private String DeleteAssistants_URL = Protocol + IP + DeleteAssistants;
    private String SearchAssistants_URL = Protocol + IP + SearchAssistants;
    private String NextAssistant_URL = Protocol + IP + NextAssistant;
    private String PreviousAssistant_URL = Protocol + IP + PreviousAssistant;
    private String NewAssistant_URL = Protocol + IP + NewAssistant;

    //Worker

    private String DeleteWorker_URL = Protocol + IP + DeleteWorker;
    private String SearchWorker_URL = Protocol + IP + SearchWorker;
    private String NextWorker_URL = Protocol + IP + NextWorker;
    private String PreviousWorker_URL = Protocol + IP + PreviousWorker;
    private String NewWorker_URL = Protocol + IP + NewWorker;

    //Program
    private String ProgramGenerate_URL = Protocol + IP + ProgramGenerate;
    private String Vacation_URL = Protocol + IP + Vacation;
    private String EndVacation_URL = Protocol + IP + EndVacation;
    private String UploadProgramToDB_URL = Protocol + IP + UploadProgramToDB;
    private String DownloadProgramFromDB_URL = Protocol + IP + DownloadProgramFromDB;
    private String DropLastProgram_URL = Protocol + IP + DropLastProgram;

    //==================================================Getters=====================================
    //Employees

    protected String getLoginEmployees_URL() {
        return LoginEmployees_URL;
    }

    protected String getRegisterEmployees_URL() {
        return RegisterEmployees_URL;
    }

    protected String getFetchEmployees_URL() {
        return FetchEmployees_URL;
    }

    //Owners

    protected String getLoginOwners_URL() {
        return LoginOwners_URL;
    }

    protected String getRegisterOwners_URL() {
        return RegisterOwners_URL;
    }

    protected String getFetchOwners_URL(){return FetchOwners_URL;}

    //Teams

    protected String getDeleteTeams_URL() {
        return DeleteTeams_URL;
    }

    protected String getSearchTeams_URL() {
        return SearchTeams_URL;
    }

    protected String getNextTeam_URL() {
        return NextTeam_URL;
    }

    protected String getPreviousTeam_URL() {
        return PreviousTeam_URL;
    }

    protected String getNewTeams_URL() {
        return NewTeams_URL;
    }

    //Assistants

    protected String getDeleteAssistants() {
        return DeleteAssistants_URL;
    }

    protected String getSearchAssistants() {
        return SearchAssistants_URL;
    }

    protected String getNextAssistant() {
        return NextAssistant_URL;
    }

    protected String getPreviousAssistant() {
        return PreviousAssistant_URL;
    }

    protected String getNewAssistant() {
        return NewAssistant_URL;
    }

    //Workers

    protected String getDeleteWorker_URL() {
        return DeleteWorker_URL;
    }

    protected String getSearchWorker_URL() {
        return SearchWorker_URL;
    }

    protected String getNextWorker_URL() {
        return NextWorker_URL;
    }

    protected String getPreviousWorker_URL() {
        return PreviousWorker_URL;
    }

    protected String getNewWorker_URL() {
        return NewWorker_URL;
    }

    //Program

    protected String getProgramGenerate_URL() {
        return ProgramGenerate_URL;
    }

    protected String getVacation_URL() {
        return Vacation_URL;
    }

    protected String getEndVacation_URL() {
        return EndVacation_URL;
    }

    protected String getUploadProgramToDB_URL() {
        return UploadProgramToDB_URL;
    }

    protected String getDownloadProgramFromDB_URL() {
        return DownloadProgramFromDB_URL;
    }

    protected String getDropLastProgram_URL() { return DropLastProgram_URL; }

    //Files

    protected String getFileProgram() {
        return FileProgram;
    }

    protected String getFileEmployees() {
        return FileEmployees;
    }
}
