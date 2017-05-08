#ADDITION

I had already done all of the backend extensions for SLogo, and one of my teammates already did the additional extension described on the Vooga Analysis page. I ended up refactoring a bit of my code to make adding new functions more closed to modification and open to extension.
I added the ability to automatically load functions into the environment by instantiating new classes with reflection, and implemented one class to try it out, WriteAppend. This was my TA's main criticism of my design when we met and talked over SLogo.

###Estimation
* I expect this change to take around 10 minutes. I should only have to add 2 lines to one method in the environment. I may need an additional 5 minutes to actually create a new function and add a new file in order to test it out though.

###Review
* It really did end up taking 15 minutes. I originally wanted to extend EnvironmentImpl and override a single method in order to implement the change. However I decided against it because that would mean making a previously private method protected, and I'm pretty sensitive with my access modifiers. I just instead opened up the class and added in the few extra lines.
* I was able to get it right on the first try, since it ended up being such a small change. However, I was surprised at how much I had to look back at my previous chain of method calls in order to figure out how to write it. I guess I don't remember my SLogo interface as well as I thought I did.

###Analysis
* It ended up being about what I expected. This was a part of the program I had complete and total control over, so I immediately knew what how to do it after skimming through my old classes. I was actually much less familiar with functional interface commands. I didn't immediately know which one to implement at first, and had to take a look at what my previous functions were implementing. My teammate had the same problem, and I assume someone unfamiliar with the code would too. I guess I just need to rename them better so it's clear which classes do what, and which ones to extend for what purpose.