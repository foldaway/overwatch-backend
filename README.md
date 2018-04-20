# Overwatch Backend
[![CircleCI](https://circleci.com/gh/bottleneckco/overwatch-backend/tree/master.svg?style=shield)](https://circleci.com/gh/bottleneckco/overwatch-backend/tree/master) [![Maintainability](https://api.codeclimate.com/v1/badges/689edae552b3598102ec/maintainability)](https://codeclimate.com/github/bottleneckco/overwatch-backend/maintainability)

Web server that provides competitive statistics for a selection of Overwatch players.

## Dependencies
- `$ bundle install`

## Development
### Database setup
1. Run `$ rake db:setup`
2. Run: `$ bundle exec rails s`
3. Access [http://localhost:3000](http://localhost:3000)

## Architecture
The web application is written in Ruby with the Rails framework (API only).
