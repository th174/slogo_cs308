SLogo
===================
###By Riley Nisbet, Stone Mathers, Alex Salas, Timmy Huang
######Started February 16, 2017
######Finished March 10, 2017
####Estimated hours worked:
* Riley: 30
* Stone: 40
* Alex:
* Timmy: idk way too many

####Roles in developing project:
* Riley: CanvasView (turtle environment)
* Stone: Function Implementation, Backend Turtle design, file-handling
* Alex: GUI
* Timmy: Backend parser implementation, Turtle implementation, Function Evaluation, Dynamic type system implementation, Debugging

####Files to start project:
* `Main.java` must be run to start the application.

####Files used to test the project:
* `ConfigurationWriterTester.java` to test the ConfigurationWriter (results are in "ConfigWriterTestFile.txt")
* `FileHandlerTester.java` to test the FileHandler's ability to pull commands from a file

####Data or Resource Files:
* Language property files contain translations from English to Foreign Languages
* Variable and View property files contain respective properties
*File property files contain information pertinent to file-handling. `turtle.properties` contains the tags needed to save a Turtle's state in an XML file. `writing.properties` contains strings needed to write the XML files.

####Information to use the program:
* Type commands in the command line and press Run to run the commands. The top menu can be used to load configurations, change the language, and get help with commands. On the left the tabs keep running lists of different things in slogo (variables, functions, color, and turtle images). Clicking on turtles and the background allows you to change their properties.

####Known bugs/problems with functionality:
* There is currently back end framework established to take in configuration data and write it to a file in XML format. However, it has neither been implemented for all possible data (only the ObservableTurtle class has implemented the required `toProperty()` method), nor connected to the front end.
* GUI formatting is different on different machines.

####Extra features:
* CanvasView: When clicking on a turtle, a pop-up display appears for the user to change the turtle’s properties. When clicking on the canvas, a pop-up display appears for the user to change the turtle environment's properties.
* Clicking on items in the right side pane 
* [Additional commands:](CommandList.html)
    * `USE` changes language
    * `READ` reads file
    * `SETBW` changes toroidal vs infinite bounds mode
    * `BW` gets current bounds
    * `EXIT` exits the program with a custom exit code
    * 

####Impressions of the assignment:
Having the team design API's at the beginning of the project and making changes to those API's throughout gave us a good understanding of the importance of API's. Especially when working in larger teams, it is vital to establish what methods are available between portions of the project. Any change to the API's may effect how other portions work, thus a change in one area can elicit change throughout the program. 
This project also revealed the difficulties associated with communication between front end and back end. Resolving the issues that arose required learning how to design and implement Observers and Observables.
As always, increasing the complexity of the project made it more difficult to stay true to an intended design. For example, certain separations of responsibilities and knowledge between portions of the front end and back end had to be reworked.
We were made more comfortable with not know eachothers' specific implementations

