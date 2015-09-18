Spring Boot AWS SQS JMS Example 
-------------------------------

Introduction
------------
The example Sends and Receives a String message using JMS to and from an AWS SQS Queue.

WARNING: This only works locally - when uploaded to AWS it does not Work, an Access Denied problem occurs.

Configuration
-------------
When running locally add the following JVM arg to pick up Profile Credentials Provider - this will use the credentials in $USER_HOME/.aws/credentials
-Duse.profile.credentials=true

When running in AWS leave off the JVM arg because it defaults to false and will use an Instance Profile Credentials Provider - AWS will then magically assign the appropriate credentials that is configured from the role assigned to the AWS Instance.

The Amazon Region defaults to eu-west-1 - if you require a different region then add the following JVM arg:
-Damazon.region=<region string e.g. eu-west-1>

Using
-----
When running the application locally to send a message use the following URL :
http://localhost:8080/send?message=Hi Everyone

JMS 
---
The project use a local build of skyscreamer nevado-jms that can be found at the following GitHub URL:
https://github.com/skyscreamer/nevado

The skyscreamer nevado-jms has been forked at the following site where it has been enhanced to use additional AWS Credentials options than just the Basic AWS Credentials:
https://github.com/keithsjohnson/nevado 

Currently the version at https://github.com/keithsjohnson/nevado needs to be build locally and then included in this project.
(This changed version still needs to be committed and merged).