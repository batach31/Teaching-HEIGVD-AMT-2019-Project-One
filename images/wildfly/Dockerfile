FROM jboss/wildfly:17.0.1.Final

MAINTAINER Baptiste Hardrick <baptiste.hardrick@heig-vd.ch>

COPY drivers/mysql-connector-java-5.1.48-bin.jar /opt/jboss/wildfly/standalone/deployments
COPY ProjectOneApp.war /opt/jboss/wildfly/standalone/deployments

ADD standalone.xml /opt/jboss/wildfly/standalone/configuration/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]