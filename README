The application has three APIs implemented:

- HTTP GET /hello-envoy
This API returns a simple string as response

- HTTP GET /hello-envoy-delay
This API has a thread sleep for 10s and then returns a simple string as response. This is to introduce a latency of ~15ms

- HTTP GET /cpu-intensive
This API performs a CPU intensive operation to drive up the CPU utilization of the node. This API is invoked a few times to make the node's CPU utilization breach >70%


Task 2:
The jar file of the app is uploaded to S3 bucket. 
The AWS launch template deployes the app with the following script:

#!/bin/bash
cd /home/ec2-user
mkdir exotel_app
cd exotel_app
yum -y install java-11-amazon-corretto-headless
wget https://exotel-app.s3.ap-south-1.amazonaws.com/aws_envoy-0.0.3-SNAPSHOT.jar
java -jar aws_envoy-0.0.3-SNAPSHOT.jar


Task 3: (Envoy test)
For testing envoy proxy, this app is converted into a docker image with app & envoy running as images on the same node.
Thus envoy is deployed as a side car on the node. 
Steps for deploying the docker images:

- Generate Jar file:
mvn clean package

- Build app docker image
docker build -t aws_envoy .

- Run docker compose to create envoy docker image and start app & envoy
docker-compose up  

