###Environment
* Added methods to accommodate multiple turtles
###Parser
* Changed parser to algorithm to simultaneous parsing and executing, instead of parsing the entire thing and executing later. 
###CanvasView started with only one command `getView()` to return the canvas' view to the GUI. Since then, there have been added features that continued to grow as the program grew
* `addImage()`, `setImage()`, `setShape()` and `getShape()` were methods added so that the front end could add an image/shape to the "image palette" or set the image/shape of a particular turtle (though this wasn't implemented)
* `clearScreen()` was implemented to be able to clear the lines on the screen
* `setBackground()` and `getBackground()` were implemented to be able to change the background color for the turtle environment. The arguments changed from Color to Index when the colors became indexed in the front end
* `setPenColor()`, `getPenColor()` and `setPenSize()` were implemented to be able to affect the pen color/width in the turtle environment. The arguments changed from Color to Index when the colors became indexed in the front end
* `setPalette()` and `getPalette()` were implemented so that the user could add new colors and see current colors saved in the front end
* `getColorMap()` and `getImageMap()` were implemented so that the GUI could get the image and color maps when they were changed to reflect that change in the front view of those respective maps