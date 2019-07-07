USER='Ylper'
PASS='!Qazxsw2'

docker stop test || true
docker rm test || true
docker run -d --rm -it -p 11000:3306 --name test -p 10000:1433 -v -d ylperj-database --default-authentication-plugin=mysql_native_password

while ! mysqladmin ping -h 127.0.0.1 -P 11000 --silent; do
    echo 'Waiting for server connection'
    sleep 3
done

cat Ylperj-database/create_order | while read file; do
    echo "./Ylperj-database/$file"
    mysql -u $USER -h 127.0.0.1 -P 11000 --password="$PASS" < "./Ylperj-database/$file"
done



