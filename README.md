# 2017SteamWorks

This is the project repository of our code for FRC's 2017 Steamworks game.

## Prerequisites

 * An internet connection
    * This is to get all dependencies. Once you have downloaded all dependencies you no longer need an internet connection.

```sh
#run this to get this repository
git clone git@github.com:technomancers/2017SteamWorks.git
cd 2017SteamWorks
#to make eclipse friendly
.\gradlew.bat eclipse
#to deploy
.\gradlew.bat deploy
#to just build
.\gradlew.bat build
```

> **NOTE**: on Unix systems you may need to run `chmod +x gradlew` before you are able to use the command.  

## VS-Code plugin

While developing, I am using Red Hat's plugin "Language Support for Java(TM) by Red Hat" for VS-Code.

## Contributing

Please add any files that are IDE/TextEditor specific settings and configuration files to the `.gitignore` file. One should be able to open up this code and deploy to the Robot without any other special software.
