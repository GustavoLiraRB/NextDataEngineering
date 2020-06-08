# Covid-19 Tracking and Monitor Database

## Introduction

This repository is a theoritical project that aims to build a database according to a person situation:
1- Healthy.
2- Suspect.
3- Confirmed.
4- Cured.

Track down their movements through geolocatization data, and with that simulate how will COVID-19 spread. 

The project was built on the assumption that all data needs to be anonymn, thus everything was built using UUIDs.

Also the Window Sliding Technique was used to achieve two key points:
1. Reduce the time complexity and amount of data.
2. Simulate an amount of time the virus remains airborn.

All the data used was dummy data to assure that no privacy laws were broken, and finally the implementation was built using Object-oriented programming with Java.

## Getting Started
### Dependencies
* Java SDK14
* SQL

### Executing Program

* Set the location and password of the database on the persistence.xml file
* Run the app main in the folder:
```
../src/school.cesar.next.covid/ui/
```
* Go to http://localhost:9000/
* For a test version of the database and more info on the database itself you can visit this [repository](https://github.com/Next-DataEngineering/Covid-19)

## License
[MIT](https://opensource.org/licenses/MIT)

## Issue/Bug

Please open issues on github to report bugs or make feature requests.
