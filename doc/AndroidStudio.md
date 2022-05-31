# Android Studio

For these lessons, we'll be using Android Studio. While much of what is discussed will also apply to
OnBot Java, Android Studio is what is used widely throughout the industry, and generally provides a
better experience than OnBot Java. If it's too big of a leap for your programming team, starting
with OnBot Java is a great way to get started, but there are _different_ challenges with using it (
In our team's experience, OnBot has a sad tendency to occasionally swallow code, and causes real
difficulties when working with multiple programmers concurrently)

## Installing

Download Android Studio from
[the Android developer website](https://developer.android.com/studio/) and install it using "
default" settings.

### Alternatives/Advanced installation

If you're using a 'package managers':

- Windows:
    - `choco install androidstudio`
- macOS:
    - `brew install androidstudio`
- Debian/Ubuntu
    - > TODO something like apt install androidstudio

At this point, there's a very detailed walk through of the 'high level' stuff from
First [right here](https://www.firstinspires.org/resource-library/ftc/technology-information-and-resources)
. Feel free to read as much of that as you'd like. It's more detailed than what's here, but at the
same time, it's also missing some helpful details that we try to cover.

## Configuring Android Studio

In order to properly work with the REV control hub, you need to make sure you have the correct
version of the Android SDK installed. As of this writing, you need to install the Android 10 SDK.
Open up the "SDK Mananger" from the Tools folder (picture) and ensure that the Android 10 (Q) is
installed ('API Level 29').

> Please note, installing the Android SDK can download 1GB+ of data. You should
> be connected to the fastest network you can to get the SDK installed.

## Opening the FTC Project

At this point, you should launch Android Studio, and open the folder containing your FTC robotics
project.

## Getting around in Android Studio

Android Studio can feel a little overwhelming to new programmers. It's exposing a tremendous amount
of power. As basic robotics programmers, most of what's there isn't actually used. That's okay.
We'll help you focus on the parts of the environment you'll be using.

### The basics

There are logically 4 places you need to "look" in Android studio.

- To the left (after a few seconds of processing) you'll typically find a
  "Project" tab. This is where you can explore the very deep nest of folders and files that are used
  to program the robot. For the contents of this tutorial, we'll be working from the folder
  `ForTutorials > org.firstinspires.ftc.teachcode`
  > TODO: Screen shot of the project docked window

* Across the top, there's a toolbar, which has buttons to let you select your
  "configuration" (for our purposes, this is always "LearningBot"), build, and deploy. These
  functions are also available in the menu.

* Along the bottom are a set of tabs that will bring up a docked window across the bottom of the
  screen. This will show status of compiling your code into a single glob under the 'Build' tab, as
  well as the progress of deploying your code over to your robot under the 'Deploy' tab.

* In the center, is the code editor. This is where your program text lives. Typing code into the
  text editor is one of the core tasks of "programming".

There are also a few other tabs across the right side of the screen which can show useful
information when problems occur. The "Gradle" tab will show you if there are dependency problems.
The "Device Manager" can show you which devices are currently connected to your computer.

> Sometimes you can accidentally close a docked window that you didn't mean to!
> You can find all the windows you can open in the "View" menu under "Tool
> Windows".

## Building your code

## Deploying your code to your robot

### Connecting to the robot

The first question you need to consider is if you want to connect to the robot wirelessly, or using
a cable. The cable method is generally more reliable, but has the downside of being a cable between
your laptop and a potentially highly mobile robot cable of ripping the cable right out of your
laptop's USB port.
