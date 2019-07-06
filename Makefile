MSSQL_PORT = 32000
IMAGE_NAME = ylperj-database
CONTAINER_NAME = ylperj-database
CONTAINER_FLAGS = --rm
VOLUME_NAME = ylper-volume

build:
	@echo "build"

unit-test:
	@echo "unit-test"

int-test:
	@echo "int-test"

acc-test:
	@echo "acc-test"

test: unit-test int-test acc-test


