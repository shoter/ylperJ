MSSQL_PORT = 32000
IMAGE_NAME = ylperj-database
CONTAINER_NAME = ylperj-database
CONTAINER_FLAGS = --rm
VOLUME_NAME = ylper-volume


docker-image:
	echo "Buduje image"

docker-clean:
	echo "Clean"

docker-run: docker-image docker-clean
    echo ''