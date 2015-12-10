# Scala Microservices Template

If you are using Scala and are looking for the right set of packaging that allows you to build microservices, this template will help you get started. 

It uses Scala 2.11.7 and SBT 0.13.8

It pulls together the following libraries and frameworks
- [x] REST API - akka-http
- [x] Json Support - spray
- [x] Database - Postgres SQL
- [x] ORM - Slick
- [ ] Migrations - Flyway
- [x] Config - Typesafe Config
- [ ] Metrics - Codahale Metrics
- [ ] Documentation - Swagger UI and Spec
- [x] Logging - Slf4j API with Logback as backend
- [ ] Deployment - Docker on Heroku
- [x] Unit Testing - ScalaTest, ScalaMock
- [ ] Integration Testing - Akka-Http-TestKit
- [ ] API End 2 End Testing -  
- [ ] Dependency Injection - Guice vs Macwire (TBD)
- [ ] Gitter Template 


[![Build Status](https://snap-ci.com/codingnirvana/scala-microservices-template/branch/master/build_image)](https://snap-ci.com/codingnirvana/scala-microservices-template/branch/master)
[![codecov.io](https://codecov.io/github/codingnirvana/scala-microservices-template/coverage.svg?branch=master)](https://codecov.io/github/codingnirvana/scala-microservices-template?branch=master)

## Why PetStore


## Getting Started



## Running the tests

To run the unit tests, run `sbt test`
To run the integration tests, run `sbt it:test`

## Metrics


## Database

### ORM

## Rest API 

### Server

### Documentation


## Configuration


## Logging

We are using slf4j API and Logback backend with default settings with 30 day log rotation.

## Dependency Injection




### License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


