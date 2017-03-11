###Environment
* Added methods to accommodate multiple turtles. These methods were `getActiveTurtleList()`, `getAllTurtles()`, `filterTurtles(Predicate<Turtle> filter)`, `selectTurtles(List<Integer> turtleIDs)`, and `clearTurtles()`.
*Changed `getUserVars()` and `getUserFunctions()` to `getLocalVars()` and `getLocalFunctions()`, respectively. These still return only the user-created variables and functions, however, the method name change reflects the effects of scope. Thus, only functions and variables within a particular Environment's scope can be accessed. This accounts for the creation of variable or functions within a TO method or a recursive method, to prevent reading or writing of variables that should not be accessible beyond the appropriate scope.
* Added methods `addUserVariable(String name, Variable var)` and `addUserFunction(String name, Invokable function)` to create new variables and functions. This alteration does not reflect a major design change, but merely an oversight in the initial design process.
###Parser
* Changed parser to algorithm to simultaneous parsing and executing, instead of parsing the entire thing and executing later. 
###CanvasView started with only one command `getView()` to return the canvas' view to the GUI. Since then, there have been added features that continued to grow as the program grew
* `addImage()`, `setImage()`, `setShape()` and `getShape()` were methods added so that the front end could add an image/shape to the "image palette" or set the image/shape of a particular turtle (though this wasn't implemented)
* `clearScreen()` was implemented to be able to clear the lines on the screen
* `setBackground()` and `getBackground()` were implemented to be able to change the background color for the turtle environment. The arguments changed from Color to Index when the colors became indexed in the front end
* `setPenColor()`, `getPenColor()` and `setPenSize()` were implemented to be able to affect the pen color/width in the turtle environment. The arguments changed from Color to Index when the colors became indexed in the front end
* `setPalette()` and `getPalette()` were implemented so that the user could add new colors and see current colors saved in the front end
* `getColorMap()` and `getImageMap()` were implemented so that the GUI could get the image and color maps when they were changed to reflect that change in the front view of those respective maps
###SLogoGUI 
*Started with just `getNode()` and did not add anything to its API. Subclasses contained within all communicate with each other within SLogoGUI and do not change exterior things.
