#!/bin/bash
# Script collection for running project tasks


# 1. Start docker databases and Consul services
# echo "Starting services..."
docker-compose -f docker-compose-consul.yaml -f docker-compose-db.yaml up -d
# 2. Start service spring boot applications
mvn spring-boot:run