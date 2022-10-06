#!/usr/bin/bash

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=$1"
