# wildfly-singleton-timer

Just to demostrate a Wildfly EJB transaction issue when running periodic timers.

Here is the actual Bug report: https://issues.jboss.org/browse/WFLY-4844

### Usage: 

mvn clean install

jboss-cli.sh -c command="deploy target/wildfly.singleton.timer.war"

jboss-cli.sh -c command="undeploy wildfly.singleton.timer.war"
