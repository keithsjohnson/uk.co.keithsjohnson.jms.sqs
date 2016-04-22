@REM https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3.html
eb init sqs-jms --region eu-west-1 --platform "Docker 1.6.2"

eb setenv aws.sqs.destination=PostcodeLocationFinderQueue

eb create sqs-jms-app --cname sqs-jms-app --instance_type t2.micro --region eu-west-1 --tier webserver
