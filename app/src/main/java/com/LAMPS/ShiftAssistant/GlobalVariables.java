package com.LAMPS.ShiftAssistant;

public class GlobalVariables {

    //Setup
    private String Protocol = "http://";
    private String IP = "192.168.1.8";//Change with your IP


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
    private String NewAssistant = "/Shifts/NewAssistant.php";

    //Worker

    private String DeleteWorker = "/Shifts/";
    private String SearchWorker = "/Shifts/SearchWorker.php";
    private String NextWorker = "/Shifts/NextWorker.php";
    private String PreviousWorker = "/Shifts/PrevWorker.php";
    private String NewWorker = "/Shifts/";

    //Vacation

    private String ProgramGenerate = "/Shifts/ProgramGen.php";
    private String Vacation = "/Shifts/Vacation.php";

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


    //TestPilot
    private String Empty = "0";
    private String Empty_URL = Protocol + IP + Empty;

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
        return DeleteAssistants;
    }

    protected String getSearchAssistants() {
        return SearchAssistants;
    }

    protected String getNextAssistant() {
        return NextAssistant;
    }

    protected String getPreviousAssistant() {
        return PreviousAssistant;
    }

    protected String getNewAssistant() {
        return NewAssistant;
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

    //Files

    public String getFileProgram() {
        return FileProgram;
    }

    public String getFileEmployees() {
        return FileEmployees;
    }

    //toliptseT
    protected String getEmpty_URL() {
        return Empty_URL;
    }

}
