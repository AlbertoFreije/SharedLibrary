#!/usr/bin/env groovy

def call() {

 sh "mvn clean package"
 sh "mvn clean deploy -U -Dmaven.test.skip=true"

}
