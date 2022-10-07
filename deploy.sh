#!/usr/bin/bash

mvn spring-boot:build-image
docker tag hazelcast-embedded-springboot:latest localhost:5000/hazelcast-embedded-springboot:latest
docker push localhost:5000/hazelcast-embedded-springboot:latest
kubectl delete deployments hazelcast-embedded-springboot-deployment
kubectl apply -f kube/deployment.yaml
