#!/usr/bin/env groovy

def call() {
   deploy adapters: [tomcat9(credentialsId: 'war-deployer', path: '', url: 'http://192.168.56.10:8080')], contextPath: 'calculadora', war: '**/*.war'
}