FROM jdk1.8u361:latest
#开放8081端口
EXPOSE 8081

#添加后端项目jar包，重命名为nsep.jar
ADD NsepBack-0.0.1-SNAPSHOT.jar nsep.jar

RUN bash -c 'touch /nsep.jar'

ENTRYPOINT ["java","-jar","nsep.jar"]