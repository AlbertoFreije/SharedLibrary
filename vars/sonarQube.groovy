#!/usr/bin/env groovy

def call() {
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
   timeout(time: 10, unit: 'MINUTES') {
                        script{
                                sh 'sleep 10'
                                def qg = waitForQualityGate();
                                if (qg.status != 'OK') {
                                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
                                }
                                
                        }
                        
   }
}