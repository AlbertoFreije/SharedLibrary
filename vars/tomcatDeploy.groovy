#!/usr/bin/env groovy

def call(String credentials = 'war-deployer', String context = 'calculadora') {

   def config = [:]
   config.groupId = readMavenPom().getGroupId()
   config.artifactId = readMavenPom().getArtifactId()
   config.version = readMavenPom().getVersion()

   println("El GroupId es " + config.groupId)
   println("El artifactId es " + config.artifactId)
   println("Version " + config.version)

   //sh "mvn dependency:get -DremoteRepositories=http://192.168.56.10:8881/repository/maven-releases/ -DgroupId=org.springframework -DartifactId=calculadora -Dversion=0.1.0 -Dpackaging=war -DType=war"
   
   //sh "mvn dependency:copy -Dartifact=org.springframework:calculadora:0.1.0 -DoutputDirectory=./target" 
   sh "mvn dependency:copy -DremoteRepositories=http://192.168.56.10:8881/repository/maven-releases/ -DgroupId=org.springframework -DartifactId=calculadora -Dversion=0.1.0 -Dpackaging=war -DType=war -DoutputDirectory=./target" 
   //sh "mvn dependency:copy-dependencies"
   
   deploy adapters: [tomcat9(credentialsId: "${credentials}", path: '', url: 'http://192.168.56.10:8080')], contextPath: "${config.artifactId}", war: './target/calculadora-0.1.0.war'
}