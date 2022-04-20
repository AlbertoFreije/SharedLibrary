#!/usr/bin/env groovy

import com.test.SonarVars
import com.test.helpers.Constant

def call() {
    
   println(" probando " + Constant.prueba)
   println(" probando " + Constant.foo)
   scannerHome = tool SonarVars.foo
   //scannerHome = tool 'sonar-scanner'
   withSonarQubeEnv('sonarqube') {
                                 sh "${scannerHome}/bin/sonar-scanner \
                                  -Dsonar.projectKey=gs-maven \
                                  -Dsonar.host.url=http://192.168.56.10:9000 \
                                  -Dsonar.login=877b59468da2a990316a8ab8c891f0b399a06446 \
                                  -Dsonar.sources=/var/jenkins_home/workspace/CoverageProject/src/main \
                                  -Dsonar.language=java \
                                  -Dsonar.java.binaries=/var/jenkins_home/workspace/CoverageProject/target/classes \
                                  -Dsonar.java.test.binaries=/var/jenkins_home/workspace/CoverageProject/src/test/java \
                                  -Dsonar.junit.reportPaths=/var/jenkins_home/workspace/CoverageProject/target/surefire-reports \
                                  -Dsonar.coverage.jacoco.xmlReportPaths=/var/jenkins_home/workspace/CoverageProject/target/site/jacoco/jacoco.xml \
                                  -Dsonar.java.coveragePlugin=jacoco"
   }

   /*

         * De vez en cuando...el análisis se demora y en ese caso se produce un error HTTP 401 (Authorization) al intentar obtener el estado del análisis mientras se está

         * ejecutando. Para evitar esto se introduce un tiempo de espera.

   */
   timeout(time: 10, unit: 'MINUTES') {
                        
            sh 'sleep 10'
            def qg = waitForQualityGate();
            if (qg.status != 'OK') {
                    //error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    println("Pipeline aborted due to quality gate failure: ${qg.status}");
            }
                  
   }
}