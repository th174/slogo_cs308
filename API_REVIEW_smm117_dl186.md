#API Peer Review
##Stone Mathers and Dennis Ling
###February 23, 2017
_____

###Part 1

1. The Invokable interface allows different types of commands to be added based upon the number and type of arguments that can be taken in. These are also created as interfaces, thus the specific operation of each command can be implemented uniquely. This allows for the addition of new specific commands within a general group of commands. The abstraction of Variable allows for different types of variables to be added that define specific operations differently. In this way, commands can simply take in a Variable, and the specific implementation can differ by subclass of Variable. Lastly, the Environment interface allows the user to create as many new variables and functions as they wish.
2. The specific execution of commands is hidden from all other portions of the project. They simply receive the output and see the data changes that result from the command. 
3. Any mathematical errors, such as divide by zero, are detected and thrown when the command is invoked. This will give an error message to the front end, which will then either handle it or display a message to the user. Beyond this, few errors should reach my portion of the project, and should instead be caught by the parser.
4. Our API design does well to separate responsibilities and encapsulate implementation decision. It also allows for the easy addition of new types of variables and commands, as there are few methods that must be implemented, and the purpose of these is easily understood.

###Part 2

1. We utilize the MVC and REPL data structures in our design. The REPL acts as a facilitator between the different portions of the project. It reads in the user's code, passes it to a parser, executes the parsed commands, and prints the result. In the context of MVC, the REPL is the control, the back end (Parse, Invokable, Environment) handles the model, and the front end (View, GUI) handles the view.
2. The Observer and Observable classes could be very useful for handling the turtle. This would make it easier to alter the turle's data in the back end while keeping implementation hidden from the front end.
3. I am most excited to work on implementing the *TO* command. This will be the most involved and hardest command to implement, and will likely require some creative thinking.
4. I am most worried about the handling of turtle commands. These are the commands that require some level of communication between front end and back end, a design path that we have not yet figured out.

**Use Case 1:** *User creates a new function with TO*
When the user creates a new function in the command window, the REPL loop will grab one line, represented by the String *command*, at a time, and call `Parser.parse(command)`. The Parser will parse the String and create a new Expression containing however many arguments are in the first line, which it will return to the REPl. The REPL will then call `execute()` on the Expression, which will call `invoke()` on the Invokable. In this method, whichever arguments were passed in through the first line will be placed into a List of arguments. Lines will continue to be read in and added to the List until *END* is reached. The first String will then be extracted and set to *functName*. A new UserFunction object, which implements Invokable, will then be initialized as *function*. This object will contain a Map of variable name Strings to Variables and a List of Expressions (the order of which must be maintained). Every *argument* in the List of arguments will then be dealt with. If *argument* contains a variable name (starts with ":"), then *function*'s Variable Map will be filled with `addVariable(name, new Variable())`. Otherwise, *argument* contains an Expression, which will be added to *function*'s Expression list with `addFunction(Parser.parse(argument))`. This new UserFunction will then be added to Scope's Map of user functions using `addUserFunction(functName, function)`. The REPL will then read in the next command.

**Use Case 2:** *User makes a new variable with MAKE*
When the user creates a new variable in the command window, the REPL loop will obtain the String *command* and call `Parser.parse(command)`. The Parser will parse the String and create a new Expression containing the *varName* and *expr* arguments, which it will return to the REPl. If *expr* involves operations, then this will be parsed and put into the Expression's List of Expressions. The REPL will the call `execute()` on the Expression, which will call `invoke()` on the Invokable. The Invokable will then, depending on the value given by the initial *expr*, create a new Variable object *varValue*. After creating this object, it will then call Scope's `addUserVariable(varName, varValue)`. The REPL will then read in the next command.

**Use Case 3:** *User uses CLEARSCREEN*
After the command is parsed, REPL will call `execute()` on the Expression, which will call `invoke()` on the Invokable. The CLEAR Invokable will first calculate the distance between its current position and the home position. Then, the turtle's `reset()` method will then be called, which moves the turtle back to (0,0) and restores initial settings. View will then be notified that all drawings need to be removed. The distance value calculated before will then be returned.

**Use Case 4:** *User uses a REPEAT**
After the command is parsed, REPL will call `execute()` on the Expression, which will call `invoke()` on the Invokable. The REPAET Invokable will first execute *expr* (if it is not a variable). It will then execute the given commands that number of times. When the last command is executed, its value will be returned.