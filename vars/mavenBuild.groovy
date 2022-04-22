#!/usr/bin/env groovy

def call() {

 def mvnHome = tool 'Maven 3.3.9'
 def mvnExec = mvnHome + "/bin/mvn"

 //sh "mvn clean package"
 sh "mvn clean deploy -U -Dmaven.test.skip=true"

 //sh ("${mvnExec} -B deploy:deploy-file")

}
