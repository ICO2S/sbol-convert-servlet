
FROM ubuntu:16.04
MAINTAINER James Alastair McLaughlin <j.a.mclaughlin@ncl.ac.uk>

RUN apt-get update && apt-get -y dist-upgrade
RUN apt-get install -y \
	openjdk-8-jre-headless \
	curl 
	
RUN useradd ubuntu -p ubuntu -m -s /bin/bash

ADD apache-tomcat-8.0.33.tar.gz /opt/
COPY out/artifacts/sbol_convert_servlet_war_exploded/sbol-convert-servlet.war /opt/apache-tomcat-8.0.33/webapps/
COPY setenv.sh /opt/apache-tomcat-8.0.33/bin/
RUN chown -R ubuntu:ubuntu /opt/apache-tomcat-8.0.33

COPY startup.sh /
RUN chmod +x /startup.sh

EXPOSE 8080

ENTRYPOINT ["/startup.sh"]



