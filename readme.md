# Introduction
This is the JPaint application produced for SE450 in Summer 2022 at DePaul Univesity, instructed by Jeff Sharpe.  The substantive additions to the base code in this repository were made by John Smillie and David Zwerdling.

# Grading Notes
## Missing Features

## Bugs

## Extra Credit

## Miscellaneous Notes

## GitHub Repository
https://github.com/zwerdlds/CSC450-JPaint

# List of Design Patterns
## 1. Command
The behavior of the user interface needs to be extensible.  While the project starts with limited functionality, more will be added.  Interacting with the logic layer should be decoupled from the interface, but the interface should have knowledge of the functions it is capable of performing.  For these reasons, the project implements a Command pattern.  The UI layer is one client capable of creating commands, which then are passed to the logic layer to be invoked, and kept in a stack, to be revoked in case of an undo.

### 2. 
### 3. 
### 4. 
### 5. 
### 6. 

## Additional Discussion

- Since the distinct drawing behaviour of each shape is decided by an enumerator, each the shape class will be implemented identically across all shapes.  The shape object need only contain a start, end, primary color, secondary color, and type field.  This is sufficient to deterministically draw each shape.  Since shape types do not contain any fields distinctive to them, there is no reason to differentiate each by type.  Drawing method does differ by type, however.  Drawing is a behavior, and since it differs by shape type, we should implement the strategy pattern to impement drawing.

- Each shape is drawn in a similar way.  This may be implemented as a Chain of Responsibility.  It is perhaps capable of being combined with a decorator pattern to alter the way each is invoked.


## Unresolved Issues
- Should new shape commands be built using a builder?
  
The MouseHandler is probably the only place the mouse down and up location will be dynamic.  Elsewhere these values will be used, but as start and end points on shapes.  It is an unresolved issue:  should we implement a command builder capable of creating the newshapecommands that can set its start and end positions?
  
The start value is required.  This is understood to mean that the best way to implement it is via the type-system.  That would require the mousehandler to keep track of the state, so when the mousedown takes place, the mousehandler will set an internal field storing the location.

However, it is possible that the functionality of creating newshapecommands will be required elsewhere in the future.  That would benefit from a builder pattern, perhaps encapsulated further by a static factory.


- Should ICommand include additional functionality?

Most but not all ICommands are undoable.  For example, moving shapes, creating new shapes, changing colors etc.  However, not all ICommands can be undone - save, for example.  Should ICommand require implementation of undo/redo?  What is the requirement for ICommand-level functionality?  Should other interfaces require this implementation instead?  Once these are implemented should the interfaces be aggregated and how are they to be differentiated at runtime?

- How should undo be implemented with commands?

Commands are, probably, undoable.  However, Undo is also a command.  This means that, in order for the undo command to take its action, it must act on the CommandHistory, or the undo action must take place outside the command.  
  - Perhaps this will not be an issue: we can keep the CommandHistory as a shared object given to the command on creation, and ignore its implications in the LogicFacade.

- Do we need a LogicFacade?
  
This was probably created prematurely as a place to encapsulate all the actions the UI can take, but those will be implemented in each command.  Perhaps this class is unecessary.


# To-Do
- Sprint 1 Demo
- Draw
  - Functional Impl
  - Refactor to patterns

# Currently Unimplmented Features
- Pick a shape
  - Ellipse
  - Triangle
  - Rectangle
- Pick a primary color
- Pick a secondary color
- Select shading type (outline only, filled-in, outline and filled-in)
- Click and drag to draw a shape
- Click and drag to select shapes
- Click and drag to move selected shapes
- Copy selected shapes
- Paste copied shapes
- Delete selected shapes
- Undo last action
- Redo last action
- Group selected shapes
- Ungroup selected shapes
- Selected shapes have dashed outline

## Sprint 1 (Ends on Week 4)
- Draw a filled-in Rectangle
  - Click and drag while in Draw mode – a Rectangle will display on the screen on mouse release. The Rectangle will match the direction and size of the mouse movement. Rectangle does not need to display while clicking and dragging – it will suddenly appear on the screen only once the mouse is released.
- Undo/Redo Draw
- Whiteboard exercise/Discussion post
Grading Notes:
- For grading purposes, the order in which shapes appear on canvas
don’t matter. If you draw one shape on top of another, undo, then redo,
and the order changes, that’s okay!

## Sprint 2 (Ends on Week 6)
- Draw Rectangles, Ellipses, and Triangles
- Draw shapes with various colors
- Draw shapes with various shading types
  - Outline Only – Only shape outline will be drawn. Use Primary
Color to draw this.
  - Filled-In – Only the inside of the shape will be drawn – there will
be no visible outline. Use Primary Color to draw this.
  - Outline and Filled-In – Both the inside and the outline will be
drawn. Use Primary Color for the inside and Secondary Color for
the outline.
- Select a shape.
  - In Select mouse mode, select any shapes that are touched by the
invisible bounding box created by clicking and dragging to select.
You can use (and share on D2L) a Collision detection algorithm
that you find. The selection can be imprecise; when selecting,
assume any shape (e.g. ellipse or triangle) has an invisible
bounding box that surrounds the shape. You can use that
bounding box for your collision detection calculation (this is much
easier for you!).
  - If you click a single point on a shape while in Select mode, that
shape should be selected. If you click a single point on the canvas
or select an empty area, the selected shapes should be
deselected This is the default behavior for collision detection and
shouldn’t require any modification – this is easier for you!
  - You should be able to click and drag into any part of a shape to
select it – it does not need to be completely surrounded
  - At this point, nothing visible has to happen.
- Move a shape
  - In Move Mouse Mode, clicking and dragging will offset any
Selected shapes by the amount your mouse moves.
  - Moving should not deselect any shapes
- Undo/Redo Move
- Have at least two design patterns implemented
- Whiteboard exercise/Discussion post
Grading Notes:
- The ability to move a shape is dependent on the ability to select a
shape.
- Shape selection must include the ability to click and drag to select
multiple shapes at once. You should not be able to click on shapes one
at a time to select
- You can move by clicking and dragging anywhere on the screen, you
don’t need to click and drag on the highlighted shape(s).

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
- Outline Selected Shapes
  - Shapes that are selected will display with a dashed outline around
them. These will need to be offset slightly so they don’t overlap
the shape. Move the start point up and to the left 5px and add
10px to the height and width. You can adjust these values
depending on personal preference.
  - The outline must be the same shape as the shape that’s selected
- Undo/Redo Paste and Delete
- Have at least four design patterns implemented
- Whiteboard exercise/Discussion post

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
- Have at least six design patterns implemented
- Whiteboard exercise/Discussion post (DUE WEEK 9)
Non-functional requirements
- Must use at least 6 unique design patterns. Please note you won’t get
credit for using MVC as a design pattern. Further, you won’t get credit
for using the Java SDK implementations of patterns (e.g.
Observer/Iterator).
- Must use Source Control (see details below).
- The application must be written in Java using the Java SDK 1.14 or
higher.
- Only features and capabilities that are part of the Java2 SDK may be
used in the application. No third-party software such as BlueJay or
JBuilder class libraries or COM/CORBA components.