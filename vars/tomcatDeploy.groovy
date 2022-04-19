#!/usr/bin/env groovy

def call(String credentialsId = 'war-deployer') {
   deploy adapters: [tomcat9(credentialsId: ${credentialsId}, path: '', url: 'http://192.168.56.10:8080')], contextPath: 'calculadora', war: '**/*.war'
}