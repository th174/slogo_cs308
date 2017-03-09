##Riley's API

###Conversation
* Borderpane
* Arc for all movements
* Force the file browser to only take in certain types of files
* Bindings can make updating the View alot easier (location, etc.)
* MVC pattern could help the overall organization of the application
* I'm most excited to work on the `move()` function because it's the only design problem that I can work on. 

###Use Cases
1. User types in "arc 30"
2. User changes the background color
3. User changes the image file
4. GUI calls `getView()`
5. View is instantialized 

##Jesse's API

###Conversation
My API encapsulates implementation decisions through the hierarchy of classes. Features can be easily extended through creating subclasses without affecting the core code of our program. It is very flexible in that you can create a new subclass of `Tool` and add it to the toolbar and it will be there without affecting any of the previous tools. An error that will need to be handled is an invalid command. This will be handled by displaying an alert to the user and the interpreter throwing the errror.

The interpreter design pattern is currently being used in our interpreter class to interpret commands. Also the MVC design is used to connect the front end and back end. One pattern that can be used to improve our design is the Observer. Bindings and generics can be useful for this project, to bind inputs from the console to actions elsewhere. I am most excited to work on the movement of the turtle, like having it do fancy arcs and stuff. I am also most worried about this since I'm not sure how to create the arcs.

###Use cases:
1. User wants to change the image of the turtle
2. User wants to change the color of the pen
3. User inputs a command
4. User inputs an invalid command
5. User loads a file