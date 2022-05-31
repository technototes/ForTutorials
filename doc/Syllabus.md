# Training overview

This is a draft of what we think we want new programmers to know to get started with FTC.

## Unit 0: Android Studio

> Kevin?

- How to install it
- How to configure it with the right SDK
- How to connect to the bot
- How to deploy/diagnose stuff

## Unit 1: The Basics (TeleOp)

### A: Hardware Overview

> Malcolm, Adhitya, or RyanTi

Raw FTC functions:

- Motors (Encoders and no encoders)
- Servos
- Sensors
    - Bump
    - Distance
    - Color
    - IMU
- Controllers

### B: Drive Base

> RyanTa

Controls & motors:

- Tank drive (2 motors)
    - Show a few different ways to control the tank:
    - Two Sticks (Easiest: wire the Y value to the motor power from each stick)
    - Triggers with a reverse button
    - Stick + trigger (stick is 'steer' trigger is 'gas')
- Add 4 motor Tank drive

> > (Bonus piece is Mecanum/XDrive: for later - lots of math!)

### C: Using Sensors/IMU

> RyanTi

Drive to wall (bump) Drive to wall (distance) Drive to stripe Drive to corner
(IMU!)

### D: Using Servos

> Roy

Continuous Rotation Servos Positional Servos How to program servos Knock over an object beside the
bot as an example?

## Unit 2: Autonomous

### A: Making an auto opmode

Stringing your tasks together:

- Detect something
- Drive somewhere
- Do something

> > Bonus: PID Drive! (Kevin & RyanTi both want to work on this)

### B: Red/Blue and Left/Right position

(Just a thing about naming, yeah?)

### C: Tasks/Capabilities

I don't know if we want to go into this too much here. It's definitely helpful, but I'd rather we
spent time on TechnoLib/FTCLib instead...

### D: Vision

It's just a fancy sensor!

OpenCV

- Camera preview
- Bitmaps
- Matrices
- Color space conversion
- "Watching" Regions

#### Gotcha:

> Don't try to use OpenCV types in static fields/methods! (Java has Static
> Initialization Order Fiasco, too!!!) It can cause your robot to crash during
> startup, before anything even _seems_ to have run!

## Unit 3: Libraries

### A: RoadRunner

- Making your auto easier

### B: TechnoLib (Maybe migrate to FTC Lib?)

- Subsystems
- Commands
- Decisions
- Logging
- other stuff...

### C: FTCDashboard

Any other libraries worth calling out here?

## Unit 4: Source Control/GitHub

- Commit is kinda like "Save"
- Push is "Share"
- Pull is "accept shared stuff"
- Merge vs. Rebase -> I'm sorry, ask your mentor to help, cuz this is messy and overly complicated.
  Git truly is the VHS of source control systems...
