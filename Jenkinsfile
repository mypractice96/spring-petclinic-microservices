pipeline {
    environment {
    dtr_host="10.3.2.90"    
    registry = "admin/calc-service"
    registryCredential = 'dtr_repo'
    taggedDockerImage = ''
    latestDockerImage = ''  
  }
    
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
            parallel {                
                   
                    stage('Server'){
                          steps{
                              sh 'echo Deploy here'
                              /*
                              sshPublisher(
                                 publishers: [
			                        sshPublisherDesc(configName: 'petclinic-server',
                         			 transfers:						 
        							 [
        							 sshTransfer(
        							 
        							 cleanRemote: false,
        							 excludes: '',
        							 execCommand: 'ansible-playbook /root/web-server-apps/copyAndDeployWar.yml',
        							 execTimeout: 120000,
        							 flatten: false,
        							 makeEmptyDirs: false,
        							 noDefaultExcludes: false,
        							 patternSeparator: '[, ]+',
        							 remoteDirectory: '//root//web-server-apps',
        							 remoteDirectorySDF: false,
        							 removePrefix: 'target',
        							 sourceFiles: 'target/*.war'
        							 )
        							 ],
    							 usePromotionTimestamp: false,
    							 useWorkspaceInPromotion: false,
    							 verbose: false
    							)
                    			]
                    			)
                    			*/
			
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
