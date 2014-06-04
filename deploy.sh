#!/usr/bin/env bash
set -e

TOMCAT_HOME=${TOMCAT_HOME:-/usr/local/tomcat}
echo "INFO: Using $TOMCAT_HOME as root of destination."
if [ ! -d ${TOMCAT_HOME} ]; then
  echo "ERROR: The root destination does not exists. Install tomcat and point TOMCAT_HOME to tomcat's root." && exit 1
fi

WEBAPPS_ROOT="${TOMCAT_HOME}/webapps"
echo "INFO: Using $WEBAPPS_ROOT as destination of the sotaro.war."
if [ ! -d ${WEBAPPS_ROOT} ]; then
  echo "ERROR: Could not find ${WEBAPPS_ROOT}. Perhaps TOMCAT_HOME points to an invalid tomcat installation." && exit 1
fi

SOTARO_WEBAPP_ALL="${WEBAPPS_ROOT}/sotaro*"
echo "INFO: Removing past sotaro webapp archives (${SOTARO_WEBAPP_ALL})..."
rm -rf ${SOTARO_WEBAPP_ALL}

SOTARO_WEBAPP_WAR="./target/sotaro.war"
echo "INFO: Copying ${SOTARO_WEBAPP_WAR} under ${WEBAPPS_ROOT}..."
cp ${SOTARO_WEBAPP_WAR} ${WEBAPPS_ROOT}

echo "INFO: Finished without encountering errors."

