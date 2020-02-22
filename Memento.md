The memento design pattern has three classes: 
The Memento class - contains four Cells for each character(and player) location. Contains getters for these locations
The Orignator class - contains getMemento and setMemento
The CareTaker class - Implements KeyObserver. Contains Memento and Stage instance variables

The program uses the Stage.java as its orignator and has seperate class for the memento and caretaker. 

#The memento class stores the neccesary fields to store an internal state of the stage(orignator) class. This program stores 
the locations of each character and also the player as these are the only fields that changes as the program runs.
It also has a get method for each field that returns the location(Cell) of the perticular field. 

#The orignator(stage.java) creates a momento object when it is needed to by the caretaker. Here, the getMomento method creates and returns
the new memento using the location of each character and player at that state.
It has a setMomento method that accepts a momento object, and using that momento it changes the location of characters

#The CareTaker class uses a notify method implemented from the KeyObserver, to store a Memento by clicking the r key
 this calls the getMemento of the stage class and saves that Memento.
Using the same method the character reverts back to that saved Memento when the space key is pressed. This is done by calling
setMemento method of the stage class. 

The Memento Design pattern may not be the optimial solution for saving game as it can only revert back to one state of the game.
Also, once a state restored(undo) it would not possible to get back to the previous state(redo). In that case, Command Pattern would be
more optimal.