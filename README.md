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

## JWorks/src:              
    
    This is the Java application that the team is building, it contains the code for the entire 
    application.
    
### action:
    
### database: 

    Interacts directly with the database file.
    
    * DatabaseDeleter.java: Deletes entries in the database.
    * DatabaseDriver.java: Create or connect to the database file.
    * DatabaseInserter.java: Inserts data into the database.
    * DatabaseSelecter.java: Retrives data from the database.
    * DatabaseUpdater.java: Modifies data in the database.
    
### databaseAPI:

### driver:

### exceptions:

    Contains exceptions.
    
    * ConnectionFailedException.java: Thrown if a connection to the database file was corrupt.
    * DatabaseInsertException.java: Thrown if a problem arises while trying to insert data into the database.
    * DatabaseSelectException.java: Thrown if a problem arises while tyring to retrieve data from the database.

### io:
    
