#!/usr/bin/env groovy

def call() {

  
					steps{
                sh "mvn clean package"
                sh "mvn clean deploy -U -Dmaven.test.skip=true"
					}
				
	
}
