NETWORK=ylper-test
DB_CONTAINER=ylperj-test-db
APP_CONTAINER=ylperj-test-app
USER='Ylper'
PASS='!Qazxsw2'
DB_PORT=11000

docker network create $NETWORK 2>/dev/null

chmod +x ./ylperj-scripts/*

cd ylperj-scripts
echo 'Starting database'
./deploy-database.sh $DB_CONTAINER $DB_PORT $NETWORK $USER $PASS
echo 'Starting app'
./run-test-tomcat.sh $APP_CONTAINER 11080 $NETWORK
cd ..

mysql -u $USER -h 127.0.0.1 -P $DB_PORT --password="$PASS" < "./Ylperj-database/test-data.sql"

#docker stop $DB_CONTAINER 2>/dev/null || true
#docker stop $APP_CONTAINER 2>/dev/null || true
#docker rm $DB_CONTAINER 2>/dev/null || true
#docker rm $APP_CONTAINER 2>/dev/null || true
#docker network rm $NETWORK

