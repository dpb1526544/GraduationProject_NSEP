FROM centos:centos7
MAINTAINER DPB
RUN mkdir /usr/local/jdk
WORKDIR /usr/local/jdk
ADD jdk1.8.0_361_MDb4dr.tar.gz /usr/local/jdk
#java环境配置
ENV JAVA_HOME /usr/local/jdk/jdk1.8.0_361
ENV JRE_HOME /usr/local/jdk/jdk1.8.0_361/jre
ENV PATH $JAVA_HOME/bin:$PATH