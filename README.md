# L01_02

## teamSetup:
 
    Jassy.pdf:          Contains all of the information relating to the members of the team as well
                        as the team itself.
                        
    TEAM AGREEMENT.pdf: Contains the plan as to how the team will work together on this project
                        during the span of the course.

## ProductBacklog:
    
    Personas:           Contains the type users for the application that we are developing.

    UserStories:        Contains the requirements that reflect each type of users described in 
                        Personas.

    Where # denotes the sprint number. Sprints where no changes are made are omitted.
                        
## SprintBacklog:

    This folder contains organized files and folders of all backlogs for each sprint. In each sprint
	folder contains the burndown chart and sprint plan for that sprint.


    PlanAndBurndown#:   Contains the plan of distribution of tasks as well as the burndown chart.
    
    SprintBacklog#:     Contains information on how user stories were divided into tasks.

    Where # denotes the sprint number.


## JAR_Files:

    The various JAR files that we may need for the project.

    * sqlite-jdbc-3.18.0.jar: SQLite, an easy to use SQL-esque package for java.

## JWorks/src:              
    
    This is the Java application that the team is building, it contains the code for the entire 
    application. This application is a still in progress and in its early stages.
    
### Build Instructions: 
Add JAR_Files/sqlite-jdbc-3.18.0.jar as an external dependency to the project.

#### Running the Command Line Version:
1. Compile and run the project using JWorks/src/driver/JWorks.java as the main class. 

In the command line version, the following commands can be typed into the command line:

To add a new question:

```
1 "Question" answer
```

Where Question is a string wrapped by double quotes, and answer is a string without a space.

To view all saved questions:

```
2
```

#### Running the GUI Version:
1. Compile and run the project using JWorks/src/gui/JWorksGUI.java as the main class.

To login, enter any non-empty username and press the Login button.
Currently, a user can create a new question, return to the home page, or log out.

