cd "${0%/*}" # sets directory of script as current directory. (this script can be invoked from other folders than this one)

CONTAINER_NAME=$1
TOMCAT_PORT=$2
NETWORK=$3

IMAGE_NAME='ylperj-app'
echo "CONTAINER_NAME = $CONTAINER_NAME"
echo "TOMCAT_PORT = $TOMCAT_PORT"
echo "IMAGE_NAME = $IMAGE_NAME"




cd ../Ylperj-docker/app
make build

docker stop $CONTAINER_NAME || true
docker rm $CONTAINER_NAME || true
docker run --rm --network $NETWORK -dt -p $TOMCAT_PORT:8080 --name $CONTAINER_NAME $IMAGE_NAME

until [ "`curl --silent --show-error --connect-timeout 1 -I http://localhost:$TOMCAT_PORT | grep 'HTTP/1.1 200'`" != "" ];
do
  echo 'Waiting for tomcat'
  sleep 5
done

echo 'Tomcat is ready'

cd ../../YlperJ-api

mvn tomcat7:deploy -Denvironment=test -f pom.xml



