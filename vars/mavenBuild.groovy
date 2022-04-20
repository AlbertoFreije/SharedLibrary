#!/usr/bin/env groovy

def call() {

  stage('Maven Build'){
					steps{
                sh "mvn clean package"
                sh "mvn clean deploy -U -Dmaven.test.skip=true"
					}
				
				}
}
