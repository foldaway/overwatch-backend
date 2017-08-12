# Overwatch Dashboard
[![Build Status](https://travis-ci.com/duncanleo/overwatch-dashboard.svg?token=K55cj8GL5QGanosi8wGd&branch=master)](https://travis-ci.com/duncanleo/overwatch-dashboard)

Web app that displays competitive statistics for a selection of Overwatch players.

## Running
1. Define your battle tags in `db/seeds.rb`
2. Run:  
`$ foreman start -f Procfile.dev`
3. We recommend running this in a Docker container. Do the following in a terminal:  
    `$ docker build -t owdashboard .`  
    `$ docker run -p 8080:8080 -d owdashboard`

## Architecture
The web application is written in Ruby with the Rails framework. React is used for the front-end.
