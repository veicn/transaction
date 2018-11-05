FROM 10.144.136.10:5000/tomcat-vim

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" >> /etc/timezone
RUN rm -rf /usr/local/tomcat/webapps/*
RUN sed -i '2i JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8  -Duser.timezone=GMT+08"' /usr/local/tomcat/bin/catalina.sh
RUN sed -i '3i JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"' /usr/local/tomcat/bin/catalina.sh


ADD ./target/*.war /usr/local/tomcat/webapps/ROOT.war