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

#### Prerequisite:
Oracle JDK 8 must be installed, OpenJDK is not supported.
* Oracle JDK 8 installer can be found [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
* [How to install Oracle JDK 8 in Linux?](https://docs.oracle.com/javase/8/docs/technotes/guides/install/linux_jdk.html)
* [How to install Oracle JDK 8 in OS X?](https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
* [How to install Oracle JDK 8 in Windows?](https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html)

#### Setup
Clone this repository from Github.
* Type the following git command in the command line:
```
git clone https://github.com/CSCC01F17/L01_02
```
* Alternatively, you can clone this repository using [GitHub Desktop](https://desktop.github.com/), but currently this project can only be compiled using command line interface.

#### Compile and Run
Open a terminal/command prompt/PowerShell, navigate to the src directory under the JWorks directory.

* For Linux/Mac users.
    * To compile and run the command line version, type:
	```
	javac driver/JWorks.java
	java -cp ".:../../JAR_Files/sqlite-jdbc-3.18.0.jar" driver/JWorks
	```
	* To compile and run the GUI version, type:
	```
	javac gui/JWorksGUI.java
	java -cp ".:../../JAR_Files/sqlite-jdbc-3.18.0.jar" gui/JWorksGUI
	```
* For Windows users.
	* To compile and run the command line version, type:
	```
	javac driver\JWorks.java
	java -cp ".;..\..\JAR_Files\sqlite-jdbc-3.18.0.jar" driver\JWorks
	```
	* To compile and run the GUI version, type:
	```
	javac gui\JWorksGUI.java
	java -cp ".;..\..\JAR_Files\sqlite-jdbc-3.18.0.jar" gui\JWorksGUI
	```	

#### Using the Command Line Version:

**Note that the command line version is no longer maintained.**
In the command line version, the following commands can be typed into the command line:

To add a new question:

```
AddSimpleProblemCommand "Question" answer
```

Where Question is a string wrapped by double quotes, and answer is a string without any space.


#### Using the GUI Version:
To login, enter any non-empty username along with a non-empty password and press the Login button.
On the main screen, a user can:
* Add a new question
* View all saved questions
* Logout

