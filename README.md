# pe-scripting-playground
A playground to see how scripting in Pricing Engine works

## Download the Code

If you are familiar with version control (more specifically, Git) you may choose to clone this repository.

Alternatively, you can [download a ZIP archive](https://github.com/smec/pe-scripting-playground/archive/refs/heads/main.zip) containing the source code of this project. Unzip the archive to an appropriate working folder on your hard drive.

## Before You Begin Coding

We recommend to use [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) with this project.

> Community Edition is free to use for personal and commercial development. The IDE and most of it bundled plugins are open-source, licensed under Apache 2.0.

Please download the correct version for your operating system, install it, and start it.

## Opening the Project

1. In the "Welcome to IntelliJ IDEA" dialog click the "Open" button, navigate to the folder where you unzipped the ZIP archive with the code and select the "pom.xml" file.
2. Next, choose to "Open as project".
3. Navigate to the "File" menu, choose "Project Structure". In the dialog go to the "SDKs" tab and click the "+" at the top. From here you can "Download JDK...". Choose Version "16" and Vendor "Azul Zulu Community".

## From Here On

You are now set to give the scripting capabilities a try. You can use the "Sandbox.groovy" class to get some feedback on the results of the scripts.

Pricing Engine uses Groovy for its scripts. The official Groovy documentation can be found [here](https://docs.groovy-lang.org/next/html/documentation/).

You can find a collection of [script examples here](./script-examples.md), and [best practices here](./best-practices.md).
