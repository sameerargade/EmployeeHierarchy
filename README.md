# EmployeeHierarchy
Repository for Employee Hierarchy

This project uses Java 8 and Maven 3.6.

The project is packaged into a jar on compilation


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

java (JDK 8.0 and above)
maven 3.6
Make sure JAVA_HOME and MVN_HOME are pointing to the correct locations.

clone the code to your local directory
you will get one parent project named EmployeeHierarchy-master which willl have 
a pom.xml for the project , this readme.md file and src folder with the source code in it.

### Building 

Package Jar.

		1. Go to the EmployeeHierarchy-master project directory and run mvn clean package.(This creates the jar employeeHierarchy-0.0.1.jar inside the target folder of the EmployeeHierarchy-master-project) 
    2. run-> java -jar /target/hierarchy-1.0.jar
    3. It will ask to enter the file name containing the employeedata.
    4. Sample Data can be found in the src/main/java folder.


### Output

	Enter the absolute file path /home/sameer/input-employee2.txt

	Jamie
	 		Alan
       		Martin
	    		Alex
	 		Steve
	    		David
	 		Malcom
	invalid employees
	
	Jason
			Chantelle
			Julia
	Will

