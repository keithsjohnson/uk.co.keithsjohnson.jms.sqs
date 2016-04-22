Spring Boot AWS SQS JMS Example 
-------------------------------

Introduction
------------
The example Sends and Receives a String message using JMS to and from an AWS SQS Queue.

This OK works both locally and when uploaded to AWS.

Configuration
-------------
When running locally add the following JVM arg to pick up Profile Credentials Provider - this will use the credentials in $USER_HOME/.aws/credentials
-Duse.profile.credentials=true

When running in AWS leave off the JVM arg because it defaults to false and will use an Instance Profile Credentials Provider - AWS will then magically assign the appropriate credentials that is configured from the role assigned to the AWS Instance.

The Amazon Region defaults to eu-west-1 - if you require a different region then add the following JVM arg:
-Damazon.region=<region string e.g. eu-west-1>

The AWS Destination is configured using the following JVM Arg:
-Daws.sqs.destination=PostcodeLocationFinderQueue

Using
-----
When running the application locally to send a message use the following URL :
http://localhost:8080/send?message=Hi Everyone

http://sqs-jms-app.eu-west-1.elasticbeanstalk.com/send?message=Hi Everyone

JMS 
---
Updated to use the process described in the following blog:
https://java.awsblog.com/post/Tx282Y6YU7OP4AB/Using-Amazon-SQS-with-Spring-Boot-and-Spring-JMS

Build
-----
gradle clean build buildDockerZip