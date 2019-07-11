build-core:
	mvn -f YlperJ-core/pom.xml -Dmaven.test.skip=true install

test: build-core
	./test.sh
	mvn -f YlperJ-core/pom.xml test


