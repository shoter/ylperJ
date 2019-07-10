cd "${0%/*}" # sets directory of script as current directory. (this script can be invoked from other folders than this one)

CONTAINER_NAME=$1
DB_PORT=$2
NETWORK=$3
USER=$4
PASS=$5


docker stop $CONTAINER_NAME 2>/dev/null || true
docker rm $CONTAINER_NAME 2>/dev/null || true
docker run -d --network $NETWORK --rm -dt -p $DB_PORT:3306 --name $CONTAINER_NAME -p 10000:1433 ylperj-database --default-authentication-plugin=mysql_native_password

while ! mysqladmin ping -h 127.0.0.1 -P $DB_PORT --silent; do
    echo 'Waiting for server connection'
    sleep 3
done

cat ../Ylperj-database/create_order | while read file; do
    echo "Invoking ../Ylperj-database/$file"
    mysql -u $USER -h 127.0.0.1 -P $DB_PORT --password="$PASS" < "../Ylperj-database/$file"
done


