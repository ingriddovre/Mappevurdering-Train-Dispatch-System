# Portfolio project IDATT1003 - 2023

STUDENT NAME = Ingrid Midtmoen Døvre  
STUDENT ID = 11780

## Project description

This project is a program that manages on specific train station, and all the registered trains departing from this station. 
The program uses a text based menu for the user to interact with the program. 
The user can choose between various operations such as:
- Registering a new departure
- Set track to a departure
- Set a new departure time for a departure 
- Set delay to a departure 
- Search for a departure by train number
- Search for departures by destination
- Update the system time
- Show all departures
- Show departures leaving after a chosen time
- Exit the application

Before the user can use the program, the user has to register a system time. The program only takes place within a 24- hour time period. 
The user can choose to update the system time at any time, but it cannot be updated backwards in time. 

## Project structure

The project is divided into two packages:
- _**main**_ contains the entity class, the register, the time class, the user interface class and the application class.
- _**test**_ contains the JUnit test classes for the entity class, the register class and the time class. 


## Link to repository

[Mappevurdering-Train-Dispatch-System](https://github.com/ingriddovre/Mappevurdering-Train-Dispatch-System)

## How to run the project

The main class is the _TrainDispatchApp_ class. The main method initializes the _UserInterface_ class and calls the _start(_) method.
The input of the program is first the time the user wants to set as the system time. 

After that, the user can choose between number 1-10 in the menu. 
Depending on the number in the menu, the user will be asked to input different information about train departures to get the wanted output. 

The expected behaviour of the program is that the user can choose between different operations, and often get an overview of the train departures registered in the system.
The user will always get a verification message, or output after a process is completed about what the program has done.

## How to run the tests

The test classes are located in the test package.
They are named after what class they are testing. And the inner classes are mostly named after the methods they are testing.

The tests can be run by right-clicking on the wanted test class, or package, and choose 'Run _TestClassName_'.
The tests can also be run one by one, by clicking the green arrow next to the test method. 
You can also run the test classes or methods with coverage by either:

- Right-click on the **file/package** and choose 'More Run/Debug' and then choose 'Run _TestClassName_ with Coverage'
- Right-click on the green arrow next to the **method** and choose 'Run _TestMethodName_ with Coverage'

Running tests with coverage will give you an overview of how much of the code the tests covers. Such as how many lines of code, how many methods in each class and how many classes in each package.

## References

This project used code from a method in another project made by the same author.
The method is called _showListOfDepartures()_ in the _DepartureRegister_ class, and is inspired by the _showAllProperties()_ method in the _PropertyRegister_ class in this project:

[Kommunalt-Tomtregister](https://github.com/ingriddovre/Kommunalt-Tomtregister)
