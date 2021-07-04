#!/bin/bash
# Startup script

docker save user-service:latest transaction-service:latest auth-service:latest electro-bff:latest electro-shop-ui:latest shipment-service:latest notification-service:latest inventory-service:latest postgres obsidiandynamics/kafdrop confluentinc/cp-kafka zookeeper | gzip > ./deployment/deployment.tar.gz