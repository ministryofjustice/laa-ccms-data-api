# laa-ccms-data-api

This API is made up of multiple projects:
* data-api
* data-service

## data-api

The data-api project is a lightweight api interface that is generated using the open-api generator.
The [open-api-specification.yml](./data-api/open-api-specification.yml) need to be kept up to date. 

In order to generate the interface and models a build can be run on the overall project due to the gradle task dependency setup.

## data-service

the data-service implements the api interface generated in the data-api subproject.