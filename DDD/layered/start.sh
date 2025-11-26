#!/bin/bash

# Check if containers are running
if docker ps | grep -q "layered-mysql"; then
    echo "MySQL container is already running"
else
    # Check if containers exist but are stopped
    if docker ps -a | grep -q "layered-mysql"; then
        echo "Starting existing MySQL container"
        docker start layered-mysql
    else
        echo "Creating and starting new MySQL container"
        docker compose up -d
    fi
fi

# Wait for MySQL to be ready
echo "Waiting for MySQL to be ready..."
while ! docker exec layered-mysql mysqladmin ping -h"localhost" -P"3306" -u"moyeohaeng" -p"password" --silent; do
    sleep 1
done
echo "MySQL is ready!"

# Start the Spring Boot application
./gradlew bootRun
