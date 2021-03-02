All the test cases included in testcases.txt file are automated in this project.
Maven project is built and it is based on java and selenium.


Tools installed:
Java,
Maven

Added dependencies in the POM.XML .
TestNg
Selenium


Drivers (Exist in the project)
Chrome Driver for Mac/Windows,
Gecko Driver for Mac/Windows

Browsers:
Chrome, 
Firefox

IDE: Intellij/Eclipse
Plugins:TestNg


-- Automation framework

Page object model -- created separate pages and included actions and elements inside them.

compiling the project:
mvn clean install

How to Run Scripts:

Navigate to 
/AssignmentSruthi/src/test/java/com/tests/LoginPageTest.java class


-----------
To run Scripts, right click on /AssignmentSruthi/src/test/java/com/tests/LoginPageTest.java and Run as- testNG Test
Or we can click "Run All" in the file /AssignmentSruthi/src/test/java/com/tests/LoginPageTest.java


We can update the browser in which tests should be run in the SelectDriver class.


Reports would be generated under :
/AssignmentSruthi/test-output/index.html
