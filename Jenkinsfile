pipeline {    
    agent any
    tools {
        maven 'maven3.6.0'
        jdk 'JDK1.8'
    }
    
    stages {
        
        stage ('Code') {
            parallel{
                stage('Git Hub'){
                    steps {   
                        git 'https://github.com/mypractice96/spring-petclinic-microservices.git'                        
                    }
                }
            }
        }
        
        stage('Test') {
            parallel {
                stage('Unit Test') {
                    steps {
                       sh 'mvn test'                      
                    }
                }
            }
        }

        stage ('Build') {
            parallel{
                stage('Maven'){
                    steps {
                        sh 'mvn package'
                    }
                }
            }            
        }
        
        stage ('Deploy') {
            parallel{
                stage('Server'){
                    steps {
                     
                       sshPublisher(publishers: [sshPublisherDesc(configName: 'petclinic-server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: './deploy-petclinic.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: true, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'petclinic-jars', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '**/target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])          
                    }
                }
            }            
        }
        
 
 
        stage ('Clean Up') {
            parallel {
                    stage('Local Artifacts'){
                        steps{
                            sh "echo remove local artifacts here"
                        }
                    }
            }
        }
        
        
    }
}
