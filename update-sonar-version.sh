#!/bin/bash

# LEER ARCHIVO DE VERSIÓN sonar-project.properties
CURRENT_VERSION=$(awk -F'=' '/sonar.projectVersion/ {print $2}' sonar-project.properties)

# Incrementar versión
IFS='.' read -ra VERSION_PARTS <<< "$CURRENT_VERSION"
MAJOR="${VERSION_PARTS[0]}"
MINOR="${VERSION_PARTS[1]}"
PATCH="${VERSION_PARTS[2]}"
PATCH=$((PATCH+1))
NEW_VERSION="$MAJOR.$MINOR.$PATCH"

# ACTUALIZAR ARCHIVO sonar-project.properties
awk -v new_version="$NEW_VERSION" '/sonar.projectVersion/ {$0="sonar.projectVersion="new_version} {print}' sonar-project.properties > temp && mv temp sonar-project.properties

echo "Nueva versión: $NEW_VERSION"

# Exportar nueva versión para GitHub Actions
echo "NUEVA_VERSION=$NEW_VERSION" >> $GITHUB_ENV
