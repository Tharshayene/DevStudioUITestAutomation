DevStudioUITestAutomation
=========================

The project is undertaken for GSoC 2014 to automate the UI test of WSO2 Develop Studio.

Tharshayene Loganathan
IIT
2014


STEPS Done:

1. Install SWTBot to eclipse: http://download.eclipse.org/technology/swtbot/snapshots

2. Create a plugin project in Eclipse

3. Add these dependencies to the MANIFEST.MF
  org.eclipse.swtbot.eclipse.finder
  org.eclipse.swtbot.junit4_x
  org.hamcrest.core
  org.apache.commons.collections
  org.junit4
  org.apache.log4j
  org.eclipse.ui

Only the org.hamcrest.core.source is avaible not org.hamcreast.core, said to be a minor bug. 

4. Wrote a class called Pilot.java to test Java project creation

Todo list 

5. Run SWTBot tests with Maven

6. Add ESB Features to the Product under test

7. Write test cases

8. Set up a framework



