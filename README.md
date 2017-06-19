# Overwatch Dashboard
[![Build Status](https://travis-ci.com/duncanleo/overwatch-dashboard.svg?token=K55cj8GL5QGanosi8wGd&branch=master)](https://travis-ci.com/duncanleo/overwatch-dashboard)

Web app that displays competitive statistics for a selection of Overwatch players.

## Running
1. Define your battle tags in `tag.json`
2. Build the JAR file for the application. Open the project in IntelliJ and run the "Produce JAR file" Run Configuration.
3. We recommend running this in a Docker container. Do the following in a terminal:  
    `$ docker build -t owdashboard .`  
    `$ docker run -p 8080:8080 -d owdashboard`

## Architecture
The web application is written in Kotlin with the Spark Java web framework. Freemarker is used to produce the HTML template.
