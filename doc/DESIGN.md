#SLogo Design Document
##Team 14
##Timmy Huang, Stone Mathers, Riley Nisbet, Alex Salas
##Completed: February 20th, 2017

____


###Introduction
For the SLogo program, this team is trying to create an IDE where a user can input a command on the command line to be executed in the backend and reflected in the View window (where the turtle lives) and/or in the variables. The secondary design goal of this project is to allow for modularity between the 5 APIs. In doing this, minimal data/commands should be passed between the front-end and the back-end to ensure each part can be extensible while still being closed. It is flexible when it comes to the types and amounts of commands that are executed, the languages/strings that can be parsed from the command line and the features possible in the GUI. The Invokables, the Expressions and the Variables are open to extension, whereas the APIs are fairly closed to much modification.

###Design Overview
The project is going to be divided into 5 main API's: Scope, REPL, Parser, GUI and View. Scope stores all user-defined and default variables and methods that can be used in commands. REPL is the read-evaluate-print loop which does as the name suggests. The Parser maintains a mapping from command strings (in any language) to the particular invokable. GUI houses the graphical components (including the View). View updates, using JavaFX, the turtle and the image it prints. The GUI sends user-input commands to REPL. The command string is sent to the Parser, which returns an `Expression` object that REPL executes (most likely affecting the variables in Scope or View). A map that indicates the how these API's interact with each other can be seen in the Doc folder as `SLogo_Map.JPG`

[Variable Class:](SLogo/FunctionEvaluate/Variables/Variable.html) The Variable object (such as Number, String, boolean, etc.) that is stored in the Variables/Methods class. These Variable objects are executed upon by the Invokables to change its value. The main abstract Variable class has default methods for how the variables react to certain operations, while subclasses of Variable has methods to override the superclass's methods. They are updated by the invokables and displayed in the GUI. 

[Invokable Interface:](SLogo/FunctionEvaluate/Functions/Invokable.html) The Invokable object is a single operation (forward, plus, divide, etc.) that has instructions for how to execute that operation with given flags and arguments (Variables). The interface defines one method: invoke(), which will be called by Parser to invoke whatever command the Invokable defines. 

[Expression Class:](SLogo/Parse/Expression.html) The Expression class contains an Invokable, a list of `flag` Strings, a list of `argument` Strings, and a list of other Expressions. This class connects the Invokable, which is simply a wrapped method reference with its arguments and parameters. Expressions can be executed, which runs all subexpressions recursively, and returns a variable.

###User Interface
[SLogoGUI:](SLogo/View/SLogoGUI.html) The User Interface consists of several components: the View which is a large box that displays the turtle and the path that it's drawn. Below that, there is a Command box where commands are typed and previous commands can be seen. The previous command can be clicked on to be pasted in the Command box as a new command. There is another box to the right of the View box that displays currently-defined variables and functions. These functions can be clicked on to be pasted into the Command box as a new command. The menu bar is going to have all the settings functionality, such as Background Color or Change Language. A mock-up of the User Interface can be seen in the Doc folder as `SLogo_GUI.JPG`.

###API Details
Many details, such as specific methods and exception handling, are contained within the linked APIs.

[Scope:](SLogo/FunctionEvaluate/Scope.html)
This interface maintains the variables and commands for the entire program. It contains several getters to be able to access Variables and Expressions (mainly for the Invokable class and the GUI class to access. This can be extended to include more default variables, operations, and user-generated functions.

[REPL:](SLogo/Repl.html)
This interface maintains the read-evaluate-print loop. It gets commands from the GUI, with which it sends to the Parser to deal with. Currently, this class does not have many ways in which it can be extended, as it is relatively set in its functionality. However, with some adjustment, it could be extended to take in files.

[Parser:](SLogo/Parse/Parser.html)
This class converts user command strings into Expressions, identifying matching the arguments of the with their respective commands. It should account for nested commands and control flow. It is open to extension through the addition of new languages, new commands, or new syntax (such as taking in more than two arguments for certain functions, like *SUM*).

[GUI:](SLogo/View/SLogoGUIView.html)
A user interface by which the user can interact with the program. It provides windows for viewing the Canvas containing the turtle, a REPL shell, and a list of defined variables and functions. The GUI should also give the user an interface with which they can customize program configurations such as color, or view pertinent information about the program. GUI can be extended by supporting new settings and functions in the Menu bar. To create new visuals, extra boxes can be added or new stage can be made. The GUI can also make any functions that are displayed clickable (to be pasted in the command window).

[View:](SLogo/View/CanvasView.html)
The window that displays the turtle and the lines that the turtle drew. It is constantly updated by the Invokables dictating how the turtle should move and whether or not the pen is down. The view can be extended to have different background colors, pen colors and turtle images. 

###API Example Code
**Use Case 1:**
When a user types `fd 50` in the command window, the REPL loop will grab that string and call `Parser.parse(fd 50)`. Parser will parse that object and place the relevant information into the respective Expression object and call `Invokable.invoke()` on the Invokable in that Expression. That expression will be placed in the Scope class under history. This Invokable will call `View.move(forward, 30)` which will cause the turtle to move accordingly. The GUI class, after the user pressed enter on the initial command, will move that command to the history of commands.

**Use Case 2 (Stone):** *User creates a new function with TO*
When the user creates a new function in the command window, the REPL loop will grab one line, represented by the String *command*, at a time, and call `Parser.parse(command)`. The Parser will parse the String and create a new Expression containing however many arguments are in the first line, which it will return to the REPl. The REPL will then call `execute()` on the Expression, which will call `invoke()` on the Invokable. In this method, whichever arguments were passed in through the first line will be placed into a List of arguments. Lines will continue to be read in and added to the List until *END* is reached. The first String will then be extracted and set to *functName*. A new UserFunction object, which implements Invokable, will then be initialized as *function*. This object will contain a Map of variable name Strings to Variables and a List of Expressions (the order of which must be maintained). Every *argument* in the List of arguments will then be dealt with. If *argument* contains a variable name (starts with ":"), then *function*'s Variable Map will be filled with `addVariable(name, new Variable())`. Otherwise, *argument* contains an Expression, which will be added to *function*'s Expression list with `addFunction(Parser.parse(argument))`. This new UserFunction will then be added to Scope's Map of user functions using `addUserFunction(functName, function)`. The REPL will then read in the next command.


**Use Case 3 (Stone):** *User makes a new variable with MAKE*
When the user creates a new variable in the command window, the REPL loop will obtain the String *command* and call `Parser.parse(command)`. The Parser will parse the String and create a new Expression containing the *varName* and *expr* arguments, which it will return to the REPl. If *expr* involves operations, then this will be parsed and put into the Expression's List of Expressions. The REPL will the call `execute()` on the Expression, which will call `invoke()` on the Invokable. The Invokable will then, depending on the value given by the initial *expr*, create a new Variable object *varValue*. After creating this object, it will then call Scope's `addUserVariable(varName, varValue)`. The REPL will then read in the next command.

**Use Case 4 (Riley):** 
When the GUI is instantiated, it needs to get a reference to the Scene to be able to display the View. When the Main class instantiates the View, the View will create the root for the Scene in its constructor. At that point, the GUI will be instantiated and call `View.getScene()` to get the reference. From then on, when the View updates the Scene it will automatically update the graphics within the GUI.

**Use Case 5 (Riley):** 
When the user wants to change the turtle image, they will click the "Image" menu bar option on the GUI which will open a file browser for the user to find a file. Once a file is chosen, the GUI will call `View.setImage(filename)` which will change the turtle Image in the View.

**Use Case 6 (Alex):** *User changes the language* 
When a user clicks on the language menu item, the combobox will drop down and the user will be able to click on one of the available languages (determined by available property files). Once the user chooses a new language, the language resource bundle will be changed by the parser’s `setLanguage()` method and therefore the accepted language values in the command line will change.

**Use Case 7 (Alex):** *User types in command* 
When a user types in a command, the string entered will be set to the REPL to process the command. When the processed command returns the GUI will call the `update()` method to update the corresponding history and variables frame to reflect the current state of the program.

###Design Considerations
* A large design concern was how we would be able to read a line that has embedded operations (sum sum 1 2 6), but we decided that it would be easiest and most effective to dictate that parentheses must be written around embedded operations. 
* A design choice that we made was to keep all of the main graphical properties in the GUI class with no interactions with the backend. The settings such as `background color` and `image` are going to be housed in a class embedded within the GUI class. The alternative option was to allow these properties to be changed by the command line, but that would`ve meant extra communication with the backend that wasn`t necessary.
* We decided to have the Parser class house a translator to be able to translate the input commands into English so that the entire program can simply deal with English commands. The alternative idea was to instantiate a Map in Scope which would map the commands in the current language to the appropriate operation. This would then be reinitialized whenever the language was changed. 
* Another design consideration dealt with the handling of user-generated functions that included more than one command. Our initial idea was to create a Function class, which would hold a List of Commands, so as to keep the correct order of instructions. This, however, would require the execution class to know if it was simply calling the *execute* method on a Command, or first extracting the list of Commands from a Function. We then considered containing all default basic Commands within a Function as well, so that the executing class would always deal with a Function. Two problems arose from this idea. First, the executing class would know how many Commands are within each function, which is an undesirable level of access. Second, the majority of Functions would contain only one Command and provide no additional functionality, resulting in a great deal of unnecessary overhead. Instead, we decided to create a List of Commands as an instance variable within the Command class. In this way, executing a user-generated function does not have to be handled differently, the additional Commands that may or may not be called are hidden, and additional overhead is avoided.

###Team Responsibilities

The primary responsibility of every member is to complete implementation of their respective classes (see below). This includes any helper classes that may be required to accomplish the desired functionality. It is every member's goal to accomplish this while adhering to the establish APIs. If they must shift, however, it is the secondary responsibility of every member to communicate any design changes they make. It is as important for each member to also understand the design of their teammates' portions. This is especially important for the three members working on the backend, as any major change in one portion is likely to impact the others. When a team member has finished their primary responsibilities, they may help out with other members' portions. 

**Timmy**: Parser Class, REPL Class

**Riley**: GUI Class (Secondarily will support the Invokable, Expression and Variable components)

**Alex**: View Class (Secondarily will support the Invokable, Expression and Variable components)

**Stone**: Scope Class, Variable class and subclasses, create additional interfaces that implement Invokable to implement all basic commands dealing with the Turtle, variables, control structures, and user-defined commands