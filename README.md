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
                        
## SprintBacklog:

    PlanAndBurndown1:   Contains the plan of distribution of tasks as well as the burndown chart.
    
    SprintBacklog1:     Contains information on how user stories were divided into tasks.


## JAR_Files:

    The various JAR files that we may need for the project.

    * sqlite-jdbc-3.18.0.jar: SQLite, an easy to use SQL-esque package for java.

## JWorks/src:              
    
    This is the Java application that the team is building, it contains the code for the entire 
    application. This application is a still in progress and in its early stages.
    
<<<<<<< HEAD
    Instruction: Compile JWorks.java under JWorks/src/driver/ along with sqlite-jdbc-3.18.0.jar
                 found under JAR_Files/
                 
                 The current working option is option 1, the input format is the following:
                 
                 1 "Question" answer
                 
                 Where Question must be a string warped around by double quote and answer must
                 be a string without space.
    
### action:
    
### database: 

    Interacts directly with the database file.
    
    * DatabaseDeleter.java: Deletes entries in the database.
    * DatabaseDriver.java: Create or connect to the database file.
    * DatabaseInserter.java: Inserts data into the database.
    * DatabaseSelecter.java: Retrives data from the database.
    * DatabaseUpdater.java: Modifies data in the database.
    
### databaseAPI:

    API for classes in Database objects
    
    * DatabaseAPI.java: Interface that defines responsibility shared by all API classes
    * DatabaseDriverAPI: Creates and returns connection to the database
    * DatabseStoreAPI: Parses information supplied by Action object and sends to be stored on database

### driver:
    
    The drivers for JWorks
    
    * JWorks.java: The main class of JWorks.
    * UIParser.java: Parse user's input into readable parameters for the interpreter.
    * Interpreter.java: interpret user’s input and execute the corresponding action.

### exceptions:

    Contains exceptions.
    
    * ConnectionFailedException.java: Thrown if a connection to the database file was corrupt.
    * DatabaseInsertException.java: Thrown if a problem arises while trying to insert data into the database.
    * DatabaseSelectException.java: Thrown if a problem arises while tyring to retrieve data from the database.
=======
### Instruction: 
Compile JWorks.java under JWorks/src/driver/ along with sqlite-jdbc-3.18.0.jar found under JAR_Files/
>>>>>>> origin/dev

The current working option is option 1, the input format must match the following:

```
1 "Question" answer
```

Where Question must be a string warped around by double quote and answer must be a string without any space.
