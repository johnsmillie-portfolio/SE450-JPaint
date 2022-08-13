# Introduction
This is the JPaint application produced for SE450 in Summer 2022 at DePaul Univesity, instructed by Jeff Sharpe.  The substantive additions to the base code in this repository were made by John Smillie and David Zwerdling.

NOTE: Starting 07/29/2022 all submissions to this project 
were written exclusively by John Smillie.

# Grading Notes
## Missing Features

## Bugs
- Painting bugs on closure of popups
    On DZwerdling's system, upon closing the mouse mode selection dialog, there are visual bugs that prevent shapes from being drawn correctly.
- Move and/or select are currently broken

## Extra Credit

## Miscellaneous Notes

## GitHub Repository
https://github.com/zwerdlds/SE450-JPaint

## List of Design Patterns
### 1. Command
The behavior of the user interface needs to be extensible.  While the project starts with limited functionality, more will be added.  Interacting with the logic layer should be decoupled from the interface, but the interface should have knowledge of the functions it is capable of performing.  For these reasons, the project implements a Command pattern.  The UI layer is one client capable of creating commands, which then are passed to the logic layer to be invoked, and kept in a stack, to be revoked in case of an undo.

### 2. Strategy
The behavior of the mouse will fire different actions based on the application state. A mouse down -> move mouse -> mouse up could be used to build a new shape, or to select a current shape. This is relative to the application state. Furthermore, the painting of differernt shapes will require different algorithms based on the type of shape being painted. Different approaches will need to be applied based on the current state, and that is why we have used the Strategy Pattern to provide strategies for these implementations. "The Strategy pattern suggests that you take a class that does something specific in a lot of different ways and extract all of these algorithms into separate classes called strategies" -Refactoring Guru. We have applied this to the drag mouse and paint shape behaviors by programming to an interface and allowing implementing classes to perform our various strategies. 

### 3. Observer
The painting and selection processes both rely on dynamic lists of shapes, which are defined by their follow-on processes based on the list populations.  Because the lists are dynamic and there is dependent logic we do not want to be responsible for at the list-population logic layer, this suggests a decoupling via an observer pattern.  In the case of painting, the list of shapes will be populated by the drawing mechanism invoked by the AddNewShapeCommand.  The PaintCanvas class implements the subscriber interface and subscribe to shape list updates.  Additionally, the ClickHandler will also implement the subscriber interface, so that it may correctly calculate the selection.  In the case of selection, the list of selected shapes will be calculated and updated by the ClickHandler class, to be acted upon by any following subscribers.

### 4. Proxy
### 5. Composite
### 6. 

## Additional Discussion


## Unresolved Issues
- Investigate using observer pattern on selected shape list for updating drawing
- Investigate using strategy pattern for drawing disparate shape types
- Investigate refactoring shape to take paint context as constructor

# To-Do
- Whiteboard exercise/Discussion post

# Completed Features

## Sprint 1
- Draw a filled-in (always green) Rectangle
  - Click and drag while in Draw mode
- Undo/Redo Draw
- Whiteboard exercise/Discussion post 1
- Undo/Redo Move

## Sprint 2
- Select shapes by dragging.
- Move Selected Shapes.
  - Move does not deselect.
- Have at three design patterns implemented


## Sprint 2 (Ends on Week 6)
- Draw Rectangles, Ellipses, and Triangles

## Sprint 3 (Ends on Week 8)
- Copy
  - Adds selected shapes to the “clipboard”. Nothing visible occurs on
the screen.
  - Copying should not deselect shapes
- Paste
  - If there is anything on the clipboard, paste it on the screen
somewhere. You can paste it at origin (0, 0), some offset of the
original shapes, or somewhere else that makes sense. Do not
paste to the same location as the original shapes; you will not be
able to see the pasted shapes and it will get marked as “not
working”.
- Delete
  - Deletes the selected shape(s)
- Undo/Redo Paste and Delete
- Outline Selected Shapes
  - Shapes that are selected will display with a dashed outline around
them. These will need to be offset slightly so they don’t overlap
the shape. Move the start point up and to the left 5px and add
10px to the height and width. You can adjust these values
depending on personal preference.
  - The outline must be the same shape as the shape that’s selected

- Have at least four design patterns implemented
- Whiteboard exercise/Discussion post


# Currently Unimplmented Features


## Sprint 4 (Ends on Week 10)
- Group
  - Clicking this button will cause all Selected shapes to operate as a
group.
  - Shapes grouped together should be operated on as if they were
one shape.
  - To select a grouped shape, any part of the invisible bounding box
around the shapes in the group can be selected.
  - The selection box should display along the boundaries of the
group of shapes, NOT the individual shapes
  - Groups can be part of other groups
- Ungroup
  - Any selected shapes that are grouped shapes will no longer be
grouped.
  - If a selected group is comprised of one or more groups, only the
outer-most group is ungrouped
- Undo/Redo Group and Ungroup

## Additional Requirements
- Have at least 5 design patterns implemented
- Whiteboard exercise/Discussion post (DUE WEEK 9)
Non-functional requirements
- Must use at least 5 unique design patterns. Please note you won’t get
credit for using MVC as a design pattern. Further, you won’t get credit
for using the Java SDK implementations of patterns (e.g.
Observer/Iterator).
- Must use Source Control (see details below).
- The application must be written in Java using the Java SDK 1.14 or
higher.
- Only features and capabilities that are part of the Java2 SDK may be
used in the application. No third-party software such as BlueJay or
JBuilder class libraries or COM/CORBA components.