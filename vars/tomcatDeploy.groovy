#!/usr/bin/env groovy

def call(String credentials = 'war-deployer', String context = 'calculadora') {
   deploy adapters: [tomcat9(credentialsId: "${credentials}", path: '', url: 'http://192.168.56.10:8080')], contextPath: "${context}", war: '**/*.war'
}