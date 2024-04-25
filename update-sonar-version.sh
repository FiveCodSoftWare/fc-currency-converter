#!/bin/bash

# LEANR VERSION FILE sonar-project.properties
CURRENT_VERSION=$(grep 'sonar.projectVersion' sonar-project.properties | awk -F'=' '{print $2}')

# Increment versión
NEW_VERSION=$(echo "$CURRENT_VERSION" | awk -F'.' '{$NF+=1; OFS="."; print $0}')

# UPDATE FILE sonar-project.properties
sed -i "s/sonar.projectVersion=.*/sonar.projectVersion=$NEW_VERSION/" sonar-project.properties

echo "NEW_VERSION=$NEW_VERSION" >> $GITHUB_ENV