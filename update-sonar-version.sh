#!/bin/bash

# LEANR VERSION FILE sonar-project.properties
CURRENT_VERSION=$(awk -F'=' '/sonar.projectVersion/ {print $2}' sonar-project.properties)

# Increment version
NEW_VERSION=$(echo "$CURRENT_VERSION" | awk -F'.' '{$NF+=1; OFS="."; print $0}')

# UPDATE FILE sonar-project.properties
awk -v new_version="$NEW_VERSION" '/sonar.projectVersion/ {$0="sonar.projectVersion="new_version} {print}' sonar-project.properties > temp && mv temp sonar-project.properties

echo "New version: $NEW_VERSION"

# Export new version for GitHub Actions
echo "NEW_VERSION=$NEW_VERSION" >> $GITHUB_ENV
echo "::set-output name=new_version::$NEW_VERSION"