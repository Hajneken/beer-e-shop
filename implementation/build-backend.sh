#!/bin/bash
# Build backend


printf "\n"
echo "Install electro auth services..."
cd ./at.electro.shop.auth.service
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install electro shop services..."
cd ..
cd ./at.electro.shop.transaction.service
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install electro shop notification service..."
cd ..
cd ./at.electro.shop.notification.service
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install electro shipment services..."
cd ..
cd ./at.electro.shop.shipment.service
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install electro user services..."
cd ..
cd ./at.electro.shop.user.service
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Instsall electro shop bff..."
cd ..
cd ./at.electro.shop.bff
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install Inventory service..."
cd ..
cd ./at.electro.shop.inventory.service
./mvnw -f pom.xml clean install -U

cd ..