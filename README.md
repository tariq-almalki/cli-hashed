# Hashing Functions through CLI

> built using picocli

<p align="center">
  <img src="./misc/README-images/600px-Hashing.png" />
</p>

## Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation) 
- [Motivation](#motivation) 
- [Conclusion](#conclusion)


## Introduction

I wanted in this project to build a utility that is handy, if you wanted a hash for a text or file or even multiple files, all you going to do is open
the terminal or command prompt and type few commands and provide necessary arguments and that is it, you are one press away from your message digest. 

## Features

with this handy tool you can hash text, a file or multiple files with various hashing functions
with ease.

supported hashing functions are the following:
- **SHA-1**
- **SHA-256**
- **SHA-384**
- **SHA-512**
- **SHA-512/224**
- **SHA-512/256**
- **SHA3-224**
- **SHA3-256**
- **SHA3-384**
- **SHA3-512**


## Installation


### windows:

*prerequisites*
    
the only prerequisite is having installation of JDK 17 or higher.

steps:
1. go to `Releases` and scroll down to the `Assets` section
2. download `cli-hashed.zip`
3. unzip `cli-hashed.zip` folder, cd to cli-hashed folder, you will find 2 folders `bin` and `repo` and 1 text file called `hashing_functions.txt` 
4. create a system environment variable(not user environment variable) called `HASHING_FUNCTIONS` containing the absolute path to `hashing_functions.txt`
5. after setting the system environment variable you can execute the `hash.bat` from the same directory of the launcher scripts i.e. `hash.bat`

**NOTES**
- to be able to call `hash.bat` from any directory, you need to add it into your `PATH` system or user environment variables.
- it is not mandatory to call `hash.bat` with extension you can call it like this `hash`
- type `hash` and press Enter for help 
- in `repo` there you will find the dependencies for the program
- in `bin` you will find the launcher scripts `hash.bat` and `hash-linux` for windows and linux respectively
- the directory of launcher scripts is `bin`, launcher scripts are `hash.bat` and `hash-linux`

### linux:



## Motivation

## Conclusion






















