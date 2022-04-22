#!/usr/bin/env groovy

def call() {

 def mvnHome = tool 'Maven 3.3.9'
 def mvnExec = mvnHome + "/bin/mvn"

 sh "mvn clean package"
 sh "mvn dependency:get -DremoteRepositories=http://192.168.56.10:8881/repository/maven-releases/ -DgroupId=org.springframework -DartifactId=calculadora -Dversion=0.1.0 -DType=war"
 sh "mvn clean deploy -U -Dmaven.test.skip=true"

 //sh ("${mvnExec} -B deploy:deploy-file")

}
