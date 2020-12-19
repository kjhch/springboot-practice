mvn clean package -DskipTests
cp Dockerfile target/
cd target

id=`docker ps -a | awk '$2 ~ /sbp/ {print $1}'`
docker stop $id
docker rm $id
docker rmi hch/sbp
docker build -t hch/sbp .
docker run -d -p8080:8080 -m2g --cpus=2 hch/sbp