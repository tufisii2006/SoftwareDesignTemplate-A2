pipeline {
    
    agent any

    tools {
         maven 'Maven 3.8.2'
         jdk 'Java 9'
        }

    stages {

        stage('Check maven version') {
                steps {
                    echo 'Checking Maven....'
                    sh 'mvn -v'
                }
            }

        stage('Clean') {
              steps {
                      echo 'Cleaning....'
                      git (
                          url:'https://github.com/tufisii2006/SoftwareDesignTemplate-A2.git',
                          branch: "dev"
                      )
                      sh 'mvn clean compile'
                    }
                }

        stage('Build') {
            steps {
                echo 'Building....'
                sh 'mvn install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing....'
                sh 'mvn test'
            }
        }

        stage('Publish to S3 Bucket') {
            steps {
                echo 'Publishing to AWS S3....'
                script {
                    withAWS(credentials:'AWS-S3', region:'eu-west-1') {
                        def identity=awsIdentity(); //Log AWS credentials
                      //  s3Upload(bucket:'infi-s3-ping', workingDir:'target', includePathPattern:'**/*.jar');
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script{
                echo 'Deploying....'
                   withAWS(credentials:'AWS-S3', region:'eu-west-1') {
                    sh '''
                    aws ec2 run-instances --image-id ami-0ff338189efb7ed37 --instance-type t3.micro
                    '''
                  }
               }
            }
        }
    }

}
