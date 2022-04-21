#!/usr/bin/env groovy

def call(String credentials = 'war-deployer', String context = 'calculadora') {

   def config = [:]
   config.groupId = readMavenPom().getGroupId()
   config.artifactId = readMavenPom().getArtifactId()
   config.version = readMavenPom().getVersion()

   println("El GroupId es " + config.groupId)
   println("El artifactId es " + config.artifactId)
   println("Version " + config.version)


   deploy adapters: [tomcat9(credentialsId: "${credentials}", path: '', url: 'http://192.168.56.10:8080')], contextPath: "${context}", war: '**/*.war'
}