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
<td><b>Constant</b></td>
<td>
<div align="center"><code>-?[0-9]+\.?[0-9]*</code></div>
</td>
<td>any real valued number<br>
note, to avoid potential ambiguity in parsing there should not be a space between the negative sign and the
value
</td>
<td>
<div align="right">
<code>50</code><br>
<code>-1.3</code>
</div>
</td>
</tr>
<tr>
<td><b>String Literal </b></td>
<td>
<div align="center"><code>".*"|'.*'</code></div>
</td>
<td>Any string literal enclosed by single or double quotes<br></td>
<td>
<div align="right">
<code>"Hello world!"</code><br>
<code>'25'</code>
</div>
</td>
</tr>
<tr>
<td><b>Boolean </b></td>
<td>
<div align="center"><code>TRUE|true|FALSE|false</code></div>
</td>
<td>A boolean literal evaluating to TRUE or FALSE<br></td>
<td>
<div align="right">
<code>TRUE</code><br>
<code>false</code>
</div>
</td>
</tr>
<tr>
<td><b>Variable</b></td>
<td>
<div align="center"><code>[^"'\s,\(\)\[\]]+</code></div>
</td>
<td>Any non-numerical word can be a variable name<br>
note, built-in commands and built-in variables are given below and user-defined variables cannot reuse those
names
</td>
<td>
<div align="right">
<code>x</code><br>
<code>32.a</code><br>
<code>:side</code></div>
</td>
</tr>
<tr>
<td><b>Command</b></td>
<td>
<div align="center"><code>[^"'\s,\(\)\[\]]+</code></div>
</td>
<td>any non-numerical word can be a command-name<br>
note, built-in commands and built-in variables are given below and user-defined commands cannot reuse those
names
</td>
<td>
<div align="right"><code>forward</code><br>
<code>fd</code></div>
</td>
</tr>
<tr>
<td><strong>List</strong></td>
<td>
<div align="center"><code>[ <br>
]</code></div>
</td>
<td>these brackets enclose a list of zero or more commands or variables<br></td>
<td>
<div align="right"><code>[ fd 50 rt 90 ]</code></div>
</td>
</tr>
<tr>
<td><strong>Group</strong></td>
<td>
<div align="center"><code>( <br>
)</code></div>
</td>
<td>these parentheses enclose allow commands to take variable additional arguments<br></td>
<td>
<div align="right"><code>(+ 1 2 3)</code></div>
</td>
</tr>

<tr>
<td><strong>Comment</strong></td>
<td>
<div align="center"><code>#.*\n</code></div>
</td>
<td>Ignore any text following a comment character on the same line</td>
<td>
<div align="right"><code>fd 50 # a comment</code><br></div>
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
<tr>
<td><code>(+ TRUE 3)</code></td>
<td>
<code>=> 4</code>
</td>
</tr>
<tr>
<td><code>(+ TRUE "FALSE")</code></td>
<td>
<code>=> "trueFalse" </code>
</td>
<tr>
</tbody>
</table>

Numbers evaluate to their string representation in string context. 0 evaluates to FALSE in boolean context, while all other numbers evaluate to true. Numbers evaluate to singleton lists in list context  

<table border="1">
<tbody>
<tr>
<th>SLogo Expression</th>
<th>Evaluation Result</th>
<tr>
<td><code>(+ 1 2 '3' 4 5)</code></td>
<td>
<code> => "3345" </code>
</td>
</tr>
<tr>
<td><code>(AND .5 2 3 0 4)</code></td>
<td>
<code> => 0 </code>
</td>
<tr>
</tbody>
</table>

Strings evaluate to their numerical value if possible in numerical context, else throwing a NaN exception. The empty string ("") evaluates to FALSE in boolean context, while all other strings evaluate to TRUE. Strings evaluate to singleton lists in list context  

<table border="1">
<tbody>
<tr>
<th>SLogo Expression</th>
<th>Evaluation Result</th>
<tr>
<td><code>(sin "30")</code></td>
<td>
<code> => 0.5 </code>
</td>
</tr>
<tr>
<td><code>(AND "hello" "false" "" "world")</code></td>
<td>
<code> => "" </code>
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
<tr>
<td><code>(+ 1 2 3 (4 5 6))</code></td>
<td>
<code>=> (+ 1 2 3 6)<br> => 9</code>
</td>
<tr>
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
<td><code>FORWARD <em>pixels</em></code><br>
<code>FD <em>pixels</em></code></td>
<td>moves turtle forward in its current heading by <em>pixels</em> distance<br>
returns the value of <em>pixels</em></td>
</tr>
<tr>
<td><code>BACK <em>pixels</em></code><br>
BK <em>pixels</em></td>
<td>moves turtle backward in its current heading by <em>pixels</em> distance<br>
returns the value of <em>pixels</em></td>
</tr>
<tr>
<td><code>LEFT <em>degrees</em></code><br>
<code>LT <em>degrees</em></code></td>
<td>turns turtle counterclockwise by <em>degrees</em> angle<br>
returns the value of <em>degrees</em></td>
</tr>
<tr>
<td><code>RIGHT <em>degrees</em></code><br>
<code>RT <em>degrees</em></code></td>
<td>turns turtle clockwise by <em>degrees</em> angle<br>
returns the value of <em>degrees</em></td>
</tr>
<tr>
<td><code>SETHEADING <em>degrees</em></code><br>
<code>SETH <em>degrees</em></code></td>
<td>turns turtle to an absolute heading<br>
returns number of degrees moved
</td>
</tr>
<tr>
<td><code>TOWARDS <em>x</em> <em>y </em></code></td>
<td>turns turtle to face the point (<em>x</em>, <em>y</em>), where (0, 0) is the center of the screen<br>
returns the number of degrees turtle turned
</td>
</tr>
<tr>
<td><code>SETXY <em>x</em> <em>y</em></code><br>
<code>GOTO <em>x</em> <em>y</em></code></td>
<td>moves turtle to an absolute screen position, where (0, 0) is the center of the screen<br>
returns the distance turtle moved
</td>
</tr>
<tr>
<td><code>PENDOWN</code><br>
<code>PD</code></td>
<td>puts pen down such that when the turtle moves, it leaves a trail<br>
returns TRUE
</td>
</tr>
<tr>
<td><code>PENUP</code><br>
<code>PU</code></td>
<td>puts pen up such that when the turtle moves, it does not leave a trail<br>
returns FALSE
</td>
</tr>
<tr>
<td><code>SHOWTURTLE</code><br>
<code>ST</code></td>
<td>makes turtle visible<br>
returns TRUE
</td>
</tr>
<tr>
<td><code>HIDETURTLE</code><br>
<code>HT</code></td>
<td>makes turtle invisible<br>
returns FALSE
</td>
</tr>
<tr>
<td><code>HOME</code></td>
<td>moves turtle to the center of the screen (0 0)<br>
returns the distance turtle moved
</td>
</tr>
<tr>
<td><code>CLEARSCREEN</code><br>
<code>CS</code></td>
<td>erases turtle's trails and sends it to the home position<br>
returns the distance turtle moved
</td>
</tr>
<tr>
<td><code>ID</code></td>
<td>returns current active turtle's ID number<br>
ID values typically start at 1 and increase by 1 with each new turtle created<br>
note, there is technically only one "active turtle" at any given time since each command is run once for
each active turtle, i.e., this value can always be used to identify the current turtle running the command
</td>
</tr>
<tr>
<td><code>TURTLES</code><br>
<br></td>
<td>returns number of turtles created so far</td>
</tr>
<tr>
<td><code>TELL[ <em>turtle(s)</em> ]</code></td>
<td>sets <em>turtles</em> that will follow commands hereafter<br>
returns last value in <em>turtles</em> list<em><br>
</em>note, if turtle has not previously existed, it is created and placed at the home location<br>
note, if more than one turtle is active, commands run return value associated with the last active turtle
</td>
</tr>
<tr>
<td><code>ASK [ <em>turtle(s)</em> ]</code> <br>
<code>[<em> command(s)</em> ]</code></td>
<td>only the <i>turtles</i> given in first list all run <em>commands</em> given in the second list<em><br>
</em>returns result of last command run by the last turtle<br>
note, after commands are run, currently active list of turtles returns to that set by the last TELL command
(or default active turtle if TELL never given)<br>
note, if more than one turtle is active, commands run return value associated with the last active turtle
</td>
</tr>
<tr>
<td><code>ASKWITH[ <em>condition</em> </code>]<br>
<code>[ <em>command(s)</em> ]</code></td>
<td>tell <i>turtles</i> matching given <em>condition</em> to run <em>commands</em> given in the second
list<em><br>
</em>returns result of last command run<br>
note, after commands are run, currently active list of turtles returns to that set by the last TELL command
(or default active turtle if TELL never given)<br>
note, if more than one turtle is active, commands run return value associated with the last active turtle
</td>
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
<td><code>XCOR</code></td>
<td>returns the turtle's X coordinate from the center of the screen</td>
</tr>
<tr>
<td><code>YCOR</code></td>
<td>returns the turtle's Y coordinate from the center of the screen</td>
</tr>
<tr>
<td><code>HEADING</code></td>
<td>returns the turtle's heading in degrees</td>
</tr>
<tr>
<td><code>PENDOWN?</code><br>
<code>PENDOWNP</code>
</td>
<td>returns TRUE if turtle's pen is down, FALSE if it is up</td>
</tr>
<tr>
<td><code>SHOWING?</code><br>
<code>SHOWINGP</code></td>
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
<td><code>SUM <em>expr1</em> <em>expr2</em></code><br>
<code>+ <em>expr1</em> <em>expr2</em></code></td>
<td>returns sum of the values of <i>expr1</i> and <i>expr2</i></td>
</tr>
<tr>
<td><code>DIFFERENCE <em>expr1</em> <em>expr2</em></code><br>
<code>- <em>expr1</em> <em>expr2</em></code></td>
<td>returns difference of the values of <i>expr1</i> and <i>expr2</i></td>
</tr>
<tr>
<td><code>PRODUCT <em>expr1</em> <em>expr2</em></code><br>
<code>* <em>expr1</em> <em>expr2</em></code></td>
<td>returns product of the values of <i></i> <i>expr1</i> and <i>expr2</i></td>
</tr>
<tr>
<td><code>QUOTIENT <em>expr1</em> <em>expr2</em></code><br>
<code>/ <em>expr1</em> <em>expr2</em></code></td>
<td>returns quotient of the values of <i></i> <i>expr1</i> and <i>expr2</i></td>
</tr>
<tr>
<td><code>REMAINDER <em>expr1</em> <em>expr2</em></code><br>
<code>% <em>expr1</em> <em>expr2</em></td>
<td>returns remainder on dividing the values of <i></i> <i> expr1</i> by <i>expr2</i></td>
</tr>
<tr>
<td><code>MINUS <em>expr</em></code><br>
<code>~ <em>expr</em></td>
<td>returns negative of the values of <i></i> <i>expr</i></td>
</tr>
<tr>
<td><code>RANDOM <em>max</em></code></td>
<td>returns random non-negative number strictly less than <em>max</em></td>
</tr>
<tr>
<td>
<code>SIN <em>degrees</em></code></td>
<td>
returns sine of <em>degrees</em></td>
</tr>
<tr>
</tr>
<tr>
<td><code>COS <em>degrees</em></code></td>
<td>
return cosine of <em>degrees</em></td>
</tr>
<tr>
</tr>
<tr>
<td>
<code>TAN <em>degrees</em></code></td>
<td>
returns tangent of <em>degrees</em></td>
</tr>
<tr>
</tr>
<tr>
<td>
<code>ATAN <em>degrees</em></code></td>
<td>
returns arctangent of <em>degrees</em></td>
</tr>
<tr>
<td>
<code>OG <em>expr</em></code></td>
<td>
returns natural log of <em>expr</em></td>
</tr>
<tr>
<td>
<code>POW <em>base exponent</em></code></td>
<td>
returns <em>base</em> raised to the power of the <em>exponent</em></td>
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
<td><code>LESS? <em>expr1</em> <em>expr2</em></code><br>
<code>LESSP <em>expr1</em> <em>expr2</em></code></td>
<td>returns TRUE if the value of <i></i> <em>expr1</em> is strictly less than the value of <i></i> <em></em>
<em>expr2</em>, otherwise FALSE
</td>
</tr>
<tr>
<td><code>GREATER? <em>expr1</em> <em>expr2</em></code><br>
<code>GREATERP <em>expr1</em> <em>expr2</em></code></td>
<td>returns TRUE if the value of <i></i> <em></em> <em>expr1</em> is strictly greater than the value of <i></i>
<em></em> <em>expr2</em>, otherwise FALSE
</td>
</tr>
<tr>
<td><code>EQUAL? <em>expr1</em> <em>expr2</em></code><br>
<code>EQUALP <em>expr1</em> <em>expr2</em></code></td>
<td>returns TRUE if the value of <i></i> <em></em> <em>expr1 </em>and the value of <i></i> <em></em>
<em>expr2</em> are equal, otherwise FALSE
</td>
</tr>
<tr>
<td><code>NOTEQUAL? <em>expr1</em> <em>expr2</em></code><br>
<code>NOTEQUALP <em>expr1</em> <em>expr2</em> </code></td>
<td>returns TRUE if the value of <i></i> <em></em> <em>expr1</em> and the value of <i></i>
<em></em><em>expr2</em> are not equal, otherwise FALSE
</td>
</tr>
<tr>
<td><code>AND <em>test1</em> <em>test2</em></code></td>
<td>returns <i>test1</i> if <i>test1</i> evaluates to TRUE, otherwise <i>test2</i><br>
when used with multiple tests, effectively returns the first FALSE test
</td>
</tr>
<tr>
<td><code>OR <em>test1</em> <em>test2</em></code></td>
<td>returns <i>test1</i> if <i>test1</i> evaluates to FALSE, otherwise <i>test2</i><br>
when used with multiple tests, effectively returns the first TRUE test
</td>
</tr>
<tr>
<td><code>NOT <em>test</em></code></td>
<td>returns TRUE if <i>test</i> evaluates to FALSE and FALSE if <i>test</i> evaluates to TRUE</td>
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
<td><code>MAKE <em>variable</em> <em>expr</em></code><br>
<code>SET <em>variable</em> <em>expr</em></code></td>
<td>assigns the value of <i></i> <em></em> <em>expr</em> to <em>variable</em>, creating the variable if
necessary<em><br>
</em>returns <em>expr</em></td>
</tr>
<tr>
<td><code>REPEAT <em>expr</em> [ <em>command(s)</em> ]</code></td>
<td>runs <em>command(s</em>) given in the list the value of <i></i> <em></em> <em>expr</em> number of times<br>
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)<br>
note, the final value of the current iteration, starting at 1, is automatically assigned to the variable
<em>:repcount</em>
so that it can be accessed by the <em>command(s)</em></td>
</tr>
<tr>
<td><code>DOTIMES [ <em>variable</em> <em>limit</em> ] [ <em>command(s)</em> ]</code></td>
<td>runs <em>command(s)</em> for each value specified in the range, i.e., from (1 - <em>limit</em>) inclusive
<br>
returns the final value of each of the commands executed as a list (or FALSE if no commands are
executed)<br>
note, <em>variable</em> is assigned to each succeeding value so that it can be accessed by the <em>command(s)</em>
</td>
</tr>
<tr>
<td><code>FOR [ <em>variable</em> <em>start end expr</em> ] [ <em>command(s)</em> ]</code></td>
<td>initializes the value of <em>variable</em> to <em>start</em>, and loops while <em>variable</em> is between
<em>start</em> and <em>end</em><br>
executes <em>expr</em> at the end of each iteration, and adds the result to <em>variable</em><br>
returns the final value of each of the commands executed as a list (or FALSE if no commands are
executed)<br>
note, <em>variable</em> is assigned to each succeeding value so that it can be accessed by the <em>command(s)</em>
</td>
</tr>
<tr>
<td><code>IF <em>expr</em> [ <em>command(s)</em> ]</code></td>
<td>if <em>expr</em> is TRUE, runs the <em>command(s)</em> given in the list<br>
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)
</td>
</tr>
<tr>
<td><code>IFELSE <em>expr</em> [ <em>trueCommand(s)</em> ] [ <em>falseCommand(s)</em> ]</code>
</td>
<td>if <i> expr</i> is TRUE, runs the <em>trueCommands</em> given in the first list, otherwise runs the <em>falseCommands</em>
given in the second list<br>
returns the value of each of the commands executed as a list (or FALSE if no commands are executed)
</td>
</tr>
<tr>
<td><code> TO <em>commandName </em> [ <em>variable(s)</em> ] [ <em>command(s)</em> ] </code> </td>
<td>assigns <em>command(s)</em> in the second list to <em>commandName</em> using <em>params</em> given in first
list as variables<br>
when <em>commandName</em> is called, any given values are assigned to variables that can be accessed. <br>
the command list is run and the value of each command is returned as a list (or FALSE if no commands were
executed)<br>
returns TRUE
</td>
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
<td><code>SETBACKGROUND <em>index</em></code> <br>
<code>SETBG <em>index</em></code> </td>
<td>sets background color of screen to that represented by <em>index</em><br>
returns given <em>index</em></td>
</tr>
<tr>
<td><code>SETPENCOLOR <em>index</em></code><br>
<code>SETPC <em>index</em></code>
</td>
<td>sets color of the pen to that represented by <em>index</em><br>
returns given <em>index</em></td>
</tr>
<tr>
<td><code>SETPENSIZE <em>pixels</em></code<br>
<code>SETPS <em>pixels</em></code>
</td>
<td>sets size of the pen to be <em>pixels</em> thickness<br>
returns given <em>pixels</em></td>
</tr>
<tr>
<td><code>SETSHAPE <em>index</em></code><br>
<code>SETSH <em>index</em></code></td>
<td>sets shape of turtle to that represented by <em>index</em><br>
returns given <em>index</em></td>
</tr>
<tr>
<td><code>SETPALETTE <em>index</em> <em>r</em> <em>g</em> <em>b</em></code></td>
<td>sets color corresponding at given <em>index</em> to given <em>r</em> <em>g</em> <em>b</em> color values<br>
returns given <em>index</em><br>
note, color component values are nonnegative integers less than 256 specifying an amount of red, green, and
blue
</td>
</tr>
<tr>
<td><code>PENCOLOR<br>
</code><code>PC</code></td>
<td>returns turtle's current color index</td>
</tr>
<tr>
<td><code>SHAPE</code><br>
<code>SH</code><em></em></td>
<td>returns turtle's current shape index</td>
</tr>
</tbody>
</table>


#### Predefined Variables

<table border="1">
<tbody>
<tr>
</tr>
<tr>
<td><code>PI</code></td>
<td>Reports the number Pi</td>
</tr>
<tr>
<td><code>E</code></td>
<td>Reports the number E</td>
</tr>
<tr>
<td><code>TRUE</code></td>
<td>Returns TRUE</td>
</tr>
<tr>
<td><code>FALSE</code></td>
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
<td><code>ECHO <em>expr</em> </code> <br>
<code>PRINT <em>expr</em></code><br>
<code>CONSOLE <em>expr</em></code><br>
<code>PUT <em>expr</em> </code></td>
<td> evalutates <em>expr</em> and prints the result to STDOUT<br>
returns the result of <em>expr</em></td>
</tr>
<tr>
<td><code>EXIT <em>expr</em></code><br>
</td>
<td>exits the application with exit code <em>expr</em><br>
returns <em>expr</em></td>
</tr>
<tr>
<td><code>USE <em>language</em></code><br>
</td>
<td>sets the language to <em>language</em> for all future SLogo commands<br>
returns <em>language</em><br>
note: this command is case sensitive, and does not change with the language configuration
</td>
</tr>
<tr>
<td><code>SETBOUNDSWRAPPING <em>expr</em></code><br>
<code>SETBW <em>expr</em></code></td>
<td>if <em>expr</em> is TRUE, sets bounds behavior to toroidal<br>
if <em>expr</em> is FALSE, sets bounds behavior to infinite
</td>
</tr>
<tr>
<td><code>BOUNDSWRAPPING</code><br>
<code>BW</code>
</td>
<td>
returns TRUE if bounds behavior is current set to toroidal, otherwise FALSE
</td>
</tr>
<tr>
<td><code>HISTORY</code>
</td>
<td>
returns all previously executed commands as a string, delimited by line break
</td>
</tr>
<tr>
<td><code>LASTCOMMAND</code><br>
<code>!!</code>
</td>
<td>
returns last executed command as a string
</td>
</tr>
<tr>
<td><code>EXECUTE <em>string</em></code><br>
<code>EXEC <em>string</em></code>
</td>
<td>
reads a String as a SLogo code, and executes it<br>
returns the result
</td>
</tr>
<tr>
<td><code>WRITE <em>path</em> <em>string</em></code></td>
<td>opens the file located at <em>path</em>, and overwrites it with <em>string</em><br>
the file is created if it doesn't exist<br>
returns the <em>string</em> that was written<br>
uses relative path from working directory
</td>
</tr>
<tr>
<td><code>OPEN <em>path</em></code><br>
<code>READ <em>path</em></code><br>
</td>
<td>returns a string containing the contents of the file located at <em>path</em><br>
uses relative path from working directory
</td>
</tr>
<tr>
<td><code>CD< <em>path</em>/code></td>
<td>changes current working directory to <em>path</em><br>
uses relative path from working directory
</td>
</tr>
<tr>
<td><code>LS</code><br>
</td>
<td>returns names of all files in working directory as a String, delimited by line break<br>
uses relative path from working directory
</td>
</tr>
<tr>
<td><code>PWD</code><br>
</td>
<td>returns absolute path of current working directory as a String</td>
</tr>
</tbody>
</table>