#!/bin/bash

ECR_REPOSITORY=$AWS_ACCOUNT.dkr.ecr.$REGION.amazonaws.com/travel-shooting
IMAGE_TAG=latest

# Pull new image
docker pull $ECR_REPOSITORY:$IMAGE_TAG

# Stop existing container
docker stop spring-app || true
docker rm spring-app || true

# Run new container
docker run -d \\
  --name spring-app \\
  -p 8080:8080 \\
  $ECR_REPOSITORY:$IMAGE_TAG
