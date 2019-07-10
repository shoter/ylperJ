CONTAINER_NAME=$1
TOMCAT_PORT=$2
EXPOSE_PORT=$3
IMAGE_NAME='ylperj-app'
echo "CONTAINER_NAME = $CONTAINER_NAME"
echo "TOMCAT_PORT = $TOMCAT_PORT"
echo "IMAGE_NAME = $IMAGE_NAME"

cd "${0%/*}" # sets directory of script as current directory. (this script can be invoked from other folders than this one)


cd ../Ylperj-docker/app
make build

docker stop $CONTAINER_NAME || true
docker rm $CONTAINER_NAME || true
docker run --rm -t -p $TOMCAT_PORT:8080 -p $EXPOSE_PORT:$EXPOSE_PORT --name $CONTAINER_NAME -t $IMAGE_NAME



