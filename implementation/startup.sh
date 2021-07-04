#!/bin/bash
# Startup script

./build.sh

docker-compose --env-file ./config/env.dev --compatibility up --build --force-recreate

./shutdown.sh
