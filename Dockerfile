FROM hch/javabase

LABEL author=hch
WORKDIR /home/sbp
ENV SPRING_PROFILE=prod
ADD springboot-practice-1.0-SNAPSHOT.jar ./

CMD java -Xms1024m -Xmx1024m -jar springboot-practice-1.0-SNAPSHOT.jar --spring.profiles.active=dev