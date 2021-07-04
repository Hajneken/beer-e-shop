#!/bin/bash
# Startup script

./build-backend.sh
./build-frontend.sh



printf "\n"
echo "Creating environment with docker..."
echo "Using development configuration ./config/env.dev"

docker-compose --env-file ./config/env.dev --compatibility build

cd ..