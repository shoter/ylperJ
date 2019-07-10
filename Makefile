build-core:
	mvn -f YlperJ-core/pom.xml install

test: build-core
	./test.sh

