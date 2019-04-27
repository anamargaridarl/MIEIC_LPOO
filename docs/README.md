# LPOO_32 Don't Die

2D Survival Game, intended to be a recreation of Don't Starve.
With the purpose of basic survival, the player should try its best
to find resources to survive.

Despite the difficulties of sole survival, it is as well needed to
defend yourself from all the enemies on the map, whose purpose 
is to defeat you.

Developed by [André Rocha](https://github.com/andrefmrocha)
and [Ana Margarida](https://github.com/anamargaridarl)


## Implemented Features
//estou a assumir que isto é relativo apenas ao jogo em sim

**Move Character -** By pressing the arrow keys your character will move around in the game.

**Consume food or water -** By pressing the T key you can consume food or water from the ground and restore some health value to your main character.


The person has a backpack to store food and water for later use. There are several option to interact with it: 
 
 -  **Collect food and water-** By pressing the F key the character will collect those elements to its own backpack for later use
 -   **Use current element** - By pressing the E key the current element int the backpack will be used and the respective value in will be restored to the character health
 -  **Choose diferent elements**- The current element to use in the backpack will be display on the screen. Use the keys Q and W to switch the current element to use by searching left or right of the backpack.

**Quit game** - By pressing Z the game will return to the main menu.

**Help menu** - By pressing the ? key the game will be redirected to a small menu with all the rules of the game. 

> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.

## Planned Features
To further improve this game we intend to

> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

## Design

#### Interactable Element
##### Problem in Context
Most elements had some form of interaction with the Player, whether they
were a weapon that could damage to him, or it was an element he could use
to regain back some health and nourishment. 

There was a need to unify all of them, they may have different requests
and interactions with the Player, however they would all interact 
with him.

##### The Pattern
To solve said issue, we used the **Command** pattern due to several facts:
*  It had the ability to encapsulate our requests (in this case, the interactions);
* It had the ability to parameterize different contextual requests
inside the same class.

#### Implementation
With this, we ended up creating and InteractableElement abstract class
that knew its own position, and forced all its children classes to implement
a method to interact with the player. 


<div style="text-align:center">
    <img src="images/InteractableElement.png"/>
</div>


> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts: "Problem in Context", "The Pattern", "Implementation" and "Consequences".

## Known Code Smells and Refactoring Suggestions

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

## Testing Results

> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).

## Self-evaluation

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
