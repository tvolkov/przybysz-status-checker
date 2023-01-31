FROM eclipse-temurin:19-jre

WORKDIR /opt/app

ARG DUW_PL_CERT=duw-pl-chain.pem
COPY ${DUW_PL_CERT} duw-pl-chain.pem
RUN $JAVA_HOME/bin/keytool -import -file duw-pl-chain.pem -alias duwplchain -keystore $JAVA_HOME/jre/lib/security/cacerts -trustcacerts -storepass changeit -noprompt

ARG JAR_FILE=target/psc-app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]