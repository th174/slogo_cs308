# SLogo Help


#### Basic Syntax

NOTE: All predefined variables and commands are case-insensitive, except for USE. All user-defined variables and commands are case-sensitive.

<table border="1">

<tbody>

<tr>

<th>Token</th>

<th>Syntax</th>

<th>Semantics</th>

<th>Examples</th>

</tr>

<tr>

<td>**Constant**</td>

<td>

<div align="center">`-?[0-9]+\.?[0-9]*`</div>

</td>

<td>any real valued number  
note, to avoid potential ambiguity in parsing there should not be a space between the negative sign and the value</td>

<td>

<div align="right">`50`  
`-1.3`</div>

</td>

</tr>

<tr>

<td>**String Literal**</td>

<td>

<div align="center">`".*"|'.*'`</div>

</td>

<td>Any string literal enclosed by single or double quotes  
</td>

<td>

<div align="right">`"Hello world!"`  
`'25'`</div>

</td>

</tr>

<tr>

<td>**Boolean**</td>

<td>

<div align="center">`TRUE|true|FALSE|false`</div>

</td>

<td>A boolean literal evaluating to TRUE or FALSE  
</td>

<td>

<div align="right">`TRUE`  
`false`</div>

</td>

</tr>

<tr>

<td>**Variable**</td>

<td>

<div align="center">`[^"'\s,\(\)\[\]]+`</div>

</td>

<td>Any non-numerical word can be a variable name  
note, built-in commands and built-in variables are given below and user-defined variables cannot reuse those names</td>

<td>

<div align="right">`x`  
`32.a`  
`:side`</div>

</td>

</tr>

<tr>

<td>**Command**</td>

<td>

<div align="center">`[^"'\s,\(\)\[\]]+`</div>

</td>

<td>any non-numerical word can be a command-name  
note, built-in commands and built-in variables are given below and user-defined commands cannot reuse those names</td>

<td>

<div align="right">`forward`  
`fd`</div>

</td>

</tr>

<tr>

<td>**List**</td>

<td>

<div align="center">`[  
]`</div>

</td>

<td>these brackets enclose a list of zero or more commands or variables  
</td>

<td>

<div align="right">`[ fd 50 rt 90 ]`</div>

</td>

</tr>

<tr>

<td>**Group**</td>

<td>

<div align="center">`(  
)`</div>

</td>

<td>these parentheses enclose allow commands to take variable additional arguments  
</td>

<td>

<div align="right">`(+ 1 2 3)`</div>

</td>

</tr>

<tr>

<td>**Comment**</td>

<td>

<div align="center">`#.*\n`</div>

</td>

<td>Ignore any text following a comment character on the same line</td>

<td>

<div align="right">`fd 50 # a comment`  
</div>

</td>

</tr>

</tbody>

</table>

#### Variable dynamic typing

All variables are weakly typed, and use Javascript-style runtime dynamic typing.  

Boolean TRUE evaluates to 1 in numerical context, and "true" in string context. Boolean FALSE evaluates to 0 in numerical context, and "false" in string context. Booleans evaluate to singleton lists in list context.  

<table border="1">

<tbody>

<tr>

<th>SLogo Expression</th>

<th>Evaluation Result</th>

</tr>

<tr>

<td>`(+ TRUE 3)`</td>

<td>

<div align="right">`=> 4`</div>

</td>

</tr>

<tr>

<td>`(+ TRUE "FALSE")`</td>

<td>

<div align="right">`=> "trueFalse"`</div>

</td>

</tr>

</tbody>

</table>

Numbers evaluate to their string representation in string context. 0 evaluates to FALSE in boolean context, while all other numbers evaluate to true. Numbers evaluate to singleton lists in list context  

<table border="1">

<tbody>

<tr>

<th>SLogo Expression</th>

<th>Evaluation Result</th>

</tr>

<tr>

<td>`(+ 1 2 '3' 4 5)`</td>

<td>

<div align="right">`=> "3345"`</div>

</td>

</tr>

<tr>

<td>`(AND .5 2 3 0 4)`</td>

<td>

<div align="right">`=> 0`</div>

</td>

</tr>

</tbody>

</table>

Strings evaluate to their numerical value if possible in numerical context, else throwing a NaN exception. The empty string ("") evaluates to FALSE in boolean context, while all other strings evaluate to TRUE. Strings evaluate to singleton lists in list context  

<table border="1">

<tbody>

<tr>

<th>SLogo Expression</th>

<th>Evaluation Result</th>

</tr>

<tr>

<td>`(sin "30")`</td>

<td>

<div align="right">`=> 0.5`</div>

</td>

</tr>

<tr>

<td>`(AND "hello" "false" "true" "FALSE" "" "world")`</td>

<td>

<div align="right">`=> ""`</div>

</td>

</tr>

</tbody>

</table>

Lists evaluate to their final element in scalar context.  

<table border="1">

<tbody>

<tr>

<th>SLogo Expression</th>

<th>Evaluation Result</th>

</tr>

<tr>

<td>`(+ 1 2 3 (4 5 6))`</td>

<td>

<div align="right">`=> (+ 1 2 3 6)  
=> 9`</div>

</td>

</tr>

</tbody>

</table>

#### Turtle Commands

<table border="1">

<tbody>

<tr>

<th>Name(s)</th>

<th>Description</th>

</tr>

<tr>

<td>FORWARD _pixels_  
FD _pixels_</td>

<td>moves turtle forward in its current heading by _pixels_ distance  
returns the value of _pixels_</td>

</tr>

<tr>

<td>BACK _pixels_  
BK _pixels_</td>

<td>moves turtle backward in its current heading by _pixels_ distance  
returns the value of _pixels_</td>

</tr>

<tr>

<td>LEFT _degrees_  
LT _degrees_</td>

<td>turns turtle counterclockwise by _degrees_ angle  
returns the value of _degrees_</td>

</tr>

<tr>

<td>RIGHT _degrees_  
RT _degrees_</td>

<td>turns turtle clockwise by _degrees_ angle  
returns the value of _degrees_</td>

</tr>

<tr>

<td>SETHEADING _degrees_  
SETH _degrees_</td>

<td>turns turtle to an absolute heading  
returns number of degrees moved</td>

</tr>

<tr>

<td>TOWARDS _x_ _y_</td>

<td>turns turtle to face the point (_x_, _y_), where (0, 0) is the center of the screen  
returns the number of degrees turtle turned</td>

</tr>

<tr>

<td>SETXY _x_ _y_  
GOTO _x_ _y_</td>

<td>moves turtle to an absolute screen position, where (0, 0) is the center of the screen  
returns the distance turtle moved</td>

</tr>

<tr>

<td>PENDOWN  
PD</td>

<td>puts pen down such that when the turtle moves, it leaves a trail  
returns TRUE</td>

</tr>

<tr>

<td>PENUP  
PU</td>

<td>puts pen up such that when the turtle moves, it does not leave a trail  
returns FALSE</td>

</tr>

<tr>

<td>SHOWTURTLE  
ST</td>

<td>makes turtle visible  
returns TRUE</td>

</tr>

<tr>

<td>HIDETURTLE  
HT</td>

<td>makes turtle invisible  
returns FALSE</td>

</tr>

<tr>

<td>HOME</td>

<td>moves turtle to the center of the screen (0 0)  
returns the distance turtle moved</td>

</tr>

<tr>

<td>CLEARSCREEN  
CS</td>

<td>erases turtle's trails and sends it to the home position  
returns the distance turtle moved</td>

</tr>

<tr>

<td>ID</td>

<td>returns current active turtle's ID number  
ID values typically start at 1 and increase by 1 with each new turtle created  
note, there is technically only one "active turtle" at any given time since each command is run once for each active turtle, i.e., this value can always be used to identify the current turtle running the command</td>

</tr>

<tr>

<td>TURTLES  

</td>

<td>returns number of turtles created so far</td>

</tr>

<tr>

<td>`TELL` `[` _turtle(s)_ `]`</td>

<td>sets _turtles_ that will follow commands hereafter  
returns last value in _turtles_ listnote, if turtle has not previously existed, it is created and placed at the home location  
note, if more than one turtle is active, commands run return value associated with the last active turtle</td>

</tr>

<tr>

<td>`ASK` `[` _turtle(s)_ `]`  
`[`_  command(s)_ `]`</td>

<td>only the _turtles_ given in first list all run _commands_ given in the second listreturns result of last command run by the last turtle  
note, after commands are run, currently active list of turtles returns to that set by the last TELL command (or default active turtle if TELL never given)  
note, if more than one turtle is active, commands run return value associated with the last active turtle</td>

</tr>

<tr>

<td>`ASKWITH` `[` _condition_ `]  
``[`_  command(s)_ `]`</td>

<td>tell _turtles_ matching given _condition_ to run _commands_ given in the second listreturns result of last command run  
note, after commands are run, currently active list of turtles returns to that set by the last TELL command (or default active turtle if TELL never given)  
note, if more than one turtle is active, commands run return value associated with the last active turtle</td>

</tr>

</tbody>

</table>

#### Turtle Queries

<table border="1">

<tbody>

<tr>

<th>Name</th>

<th>Description</th>

</tr>

<tr>

<td>XCOR</td>

<td>returns the turtle's X coordinate from the center of the screen</td>

</tr>

<tr>

<td>YCOR</td>

<td>returns the turtle's Y coordinate from the center of the screen</td>

</tr>

<tr>

<td>HEADING</td>

<td>returns the turtle's heading in degrees</td>

</tr>

<tr>

<td>PENDOWN?  
PENDOWNP</td>

<td>returns TRUE if turtle's pen is down, FALSE if it is up</td>

</tr>

<tr>

<td>SHOWING?  
SHOWINGP</td>

<td>returns TRUE if turtle is showing, FALSE if it is hiding</td>

</tr>

</tbody>

</table>

#### Math Operations

<table border="1">

<tbody>

<tr>

<th>Name</th>

<th>Description</th>

</tr>

<tr>

<td>SUM _expr1_ _expr2_  
+ _expr1_ _expr2_</td>

<td>returns sum of the values of _expr1_ and _expr2_</td>

</tr>

<tr>

<td>DIFFERENCE _expr1_ _expr2_  
- _expr1_ _expr2_</td>

<td>returns difference of the values of _expr1_ and _expr2_</td>

</tr>

<tr>

<td>PRODUCT _expr1_ _expr2_  
* _expr1_ _expr2_</td>

<td>returns product of the values of _expr1_ and _expr2_</td>

</tr>

<tr>

<td>QUOTIENT _expr1_ _expr2_  
/ _expr1_ _expr2_</td>

<td>returns quotient of the values of _expr1_ and _expr2_</td>

</tr>

<tr>

<td>REMAINDER _expr1_ _expr2_  
% _expr1_ _expr2_</td>

<td>returns remainder on dividing the values of _expr1_ by _expr2_</td>

</tr>

<tr>

<td>MINUS _expr_  
~ _expr_</td>

<td>returns negative of the values of _expr_</td>

</tr>

<tr>

<td>RANDOM _max_</td>

<td>returns random non-negative number strictly less than _max_</td>

</tr>

<tr>

<td>SIN _degrees_</td>

<td>returns sine of _degrees_</td>

</tr>

<tr>

<td>COS _degrees_</td>

<td>return cosine of _degrees_</td>

</tr>

<tr>

<td>TAN _degrees_</td>

<td>returns tangent of _degrees_</td>

</tr>

<tr>

<td>ATAN _degrees_</td>

<td>returns arctangent of _degrees_</td>

</tr>

<tr>

<td>LOG _expr_</td>

<td>returns natural log of _expr_</td>

</tr>

<tr>

<td>POW _base exponent_</td>

<td>returns _base_ raised to the power of the _exponent_</td>

</tr>

</tbody>

</table>

#### Boolean Operations

<table border="1">

<tbody>

<tr>

<th>Name</th>

<th>Description</th>

</tr>

<tr>

<td>LESS? _expr1_ _expr2_  
LESSP _expr1_ _expr2_</td>

<td>returns TRUE if the value of _expr1_ is strictly less than the value of _expr2_, otherwise FALSE</td>

</tr>

<tr>

<td>GREATER? _expr1_ _expr2_  
GREATERP _expr1_ _expr2_</td>

<td>returns TRUE if the value of _expr1_ is strictly greater than the value of _expr2_, otherwise FALSE</td>

</tr>

<tr>

<td>EQUAL? _expr1_ _expr2_  
EQUALP _expr1_ _expr2_</td>

<td>returns TRUE if the value of _expr1_ and the value of _expr2_ are equal, otherwise FALSE</td>

</tr>

<tr>

<td>NOTEQUAL? _expr1_ _expr2_  
NOTEQUALP _expr1_ _expr2_  </td>

<td>returns TRUE if the value of _expr1_ and the value of _expr2_ are not equal, otherwise FALSE</td>

</tr>

<tr>

<td>AND _test1_ _test2_</td>

<td>returns _test1_ if _test1_ evaluates to TRUE, otherwise _test2_  
when used with multiple tests, effectively returns the first FALSE test</td>

</tr>

<tr>

<td>OR _test1_ _test2_</td>

<td>returns _test1_ if _test1_ evaluates to FALSE, otherwise _test2_  
when used with multiple tests, effectively returns the first TRUE test</td>

</tr>

<tr>

<td>NOT _test_</td>

<td>returns TRUE if _test_ evaluates to FALSE and FALSE if _test_ evaluates to TRUE</td>

</tr>

</tbody>

</table>

#### Variables, Control Structures, and User-Defined Commands

<table border="1">

<tbody>

<tr>

<th>Name</th>

<th>Description</th>

</tr>

<tr>

<td>MAKE _variable_ _expr_  
SET _variable_ _expr_</td>

<td>assigns the value of _expr_ to _variable_, creating the variable if necessaryreturns _expr_</td>

</tr>

<tr>

<td>REPEAT _expr_ [ _command(s)_ ]</td>

<td>runs _command(s_) given in the list the value of _expr_ number of times  
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)  
note, the final value of the current iteration, starting at 1, is automatically assigned to the variable _:repcount_ so that it can be accessed by the _command(s)_</td>

</tr>

<tr>

<td>DOTIMES [ _variable_ _limit_ ] [ _command(s)_ ]  
</td>

<td>runs _command(s)_ for each value specified in the range, i.e., from (1 - _limit_) inclusive  
returns the final value of each of the commands executed as a list (or FALSE if no commands are executed)  
note, _variable_ is assigned to each succeeding value so that it can be accessed by the _command(s)_</td>

</tr>

<tr>

<td>FOR [ _variable_ _start end expr_ ] [ _command(s)_ ]</td>

<td>initializes the value of _variable_ to _start_, and loops while _variable_ is between _start_ and _end_  
executes _expr_ at the end of each iteration, and adds the result to _variable_  
returns the final value of each of the commands executed as a list (or FALSE if no commands are executed)  
note, _variable_ is assigned to each succeeding value so that it can be accessed by the _command(s)_</td>

</tr>

<tr>

<td>IF _expr_ [ _command(s)_ ]</td>

<td>if _expr_ is TRUE, runs the _command(s)_ given in the list  
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)</td>

</tr>

<tr>

<td>IFELSE _expr_ [ _trueCommand(s)_ ] [ _falseCommand(s)_ ]</td>

<td>if _expr_ is TRUE, runs the _trueCommands_ given in the first list, otherwise runs the _falseCommands_ given in the second list  
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)</td>

</tr>

<tr>

<td>TO _commandName_ [ _variable(s)_ ] [ _command(s)_ ]</td>

<td>assigns _command(s)_ in the second list to _commandName_ using _params_ given in first list as variables  
when _commandName_ is called, any given values are assigned to variables that can be accessed.  
the command list is run and the value of each command is returned as a list (or FALSE if no commands were executed)  
returns TRUE</td>

</tr>

</tbody>

</table>

#### Display Commands

<table border="1">

<tbody>

<tr>

<th>Command</th>

<th>Description</th>

</tr>

<tr>

<td>`SETBACKGROUND` _index_  
`SETBG` _index_</td>

<td>sets background color of screen to that represented by _index_  
returns given _index_</td>

</tr>

<tr>

<td>`SETPENCOLOR` _index_  
`SETPC` _index_</td>

<td>sets color of the pen to that represented by _index_  
returns given _index_</td>

</tr>

<tr>

<td>`SETPENSIZE` _pixels_  
`SETPS` _pixels_</td>

<td>sets size of the pen to be _pixels_ thickness  
returns given _pixels_</td>

</tr>

<tr>

<td>`SETSHAPE` _index_  
`SETSH` _index_</td>

<td>sets shape of turtle to that represented by _index_  
returns given _index_</td>

</tr>

<tr>

<td>`SETPALETTE` _index_ _r_ _g_ _b_</td>

<td>sets color corresponding at given _index_ to given _r_ _g_ _b_ color values  
returns given _index_  
note, color component values are nonnegative integers less than 256 specifying an amount of red, green, and blue</td>

</tr>

<tr>

<td>`PENCOLOR  
``PC`</td>

<td>returns turtle's current color index</td>

</tr>

<tr>

<td>`SHAPE`  
`SH`</td>

<td>returns turtle's current shape index</td>

</tr>

</tbody>

</table>

#### Predefined Variables

<table border="1">

<tbody>

<tr>

<td>PI</td>

<td>Reports the number Pi</td>

</tr>

<tr>

<td>E</td>

<td>Reports the number E</td>

</tr>

<tr>

<td>TRUE</td>

<td>Returns TRUE</td>

</tr>

<tr>

<td>FALSE</td>

<td>Returns FALSE</td>

</tr>

</tbody>

</table>

#### Additional Implemented Commands

<table border="1">

<tbody>

<tr>

<th>Command</th>

<th>Description</th>

</tr>

<tr>

<td>`ECHO` _expr_  
`PRINT` _expr_  
`CONSOLE` _expr_  
`PUT` _expr_</td>

<td>evalutates _expr_ and prints the result to STDOUT  
returns the result of _expr_</td>

</tr>

<tr>

<td>`EXIT` _expr_  
</td>

<td>exits the application with exit code _expr_  
returns _expr_</td>

</tr>

<tr>

<td>`USE` _language_  
</td>

<td>sets the language to _language_ for all future SLogo commands  
returns _language_  
note: this command is case sensitive, and does not change with the language configuration</td>

</tr>

<tr>

<td>`SETBOUNDSWRAPPING` _expr_  
`SETBW` _expr_</td>

<td>if _expr_ is TRUE, sets bounds behavior to toroidal  
if _expr_ is FALSE, sets bounds behavior to infinite</td>

</tr>

<tr>

<td>`BOUNDSWRAPPING`  
`BW`</td>

<td>returns TRUE if bounds behavior is current set to toroidal, otherwise FALSE</td>

</tr>

<tr>

<td>`HISTORY`</td>

<td>returns all previously executed commands as a string, delimited by line break</td>

</tr>

<tr>

<td>`LASTCOMMAND`  
`!!`</td>

<td>returns last executed command as a string</td>

</tr>

<tr>

<td>`EXECUTE _string_`  
`EXEC _string_`</td>

<td>reads a String as a SLogo code, and executes it  
returns the result</td>

</tr>

<tr>

<td>`WRITE _path_ _string_`</td>

<td>opens the file located at _path_, and overwrites it with _string_  
the file is created if it doesn't exist  
returns the _string_ that was written  
uses relative path from working directory</td>

</tr>

<tr>

<td>`OPEN` _path_  
`READ` _path_  
</td>

<td>returns a string containing the contents of the file located at _path_  
uses relative path from working directory</td>

</tr>

<tr>

<td>`CD` _path_  
</td>

<td>changes current working directory to _path_  
uses relative path from working directory</td>

</tr>

<tr>

<td>`LS`  
</td>

<td>returns names of all files in working directory as a String, delimited by line break  
uses relative path from working directory</td>

</tr>

<tr>

<td>`PWD`  
</td>

<td>returns absolute path of current working directory as a String</td>

</tr>

</tbody>

</table>