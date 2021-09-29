pipeline {
    
    agent any

    tools {
         maven 'Maven 3.8.2'
         jdk 'Java 9'
         docker 'Docker'
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

        // This was just before: I wanted to send the JAR file to S3 bucket.
        // The next step will create a docker image and send it to AWS ECR.
        stage('Publish JAR to S3 Bucket') {
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

        // Building Docker images
        stage('Building image') {
          steps{
            script {
              dockerImage = docker.build "just-name:latest"
            }
          }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
                  sh '''
                          aws --version
                    '''
             //   withAWS(credentials:'AWS-S3', region:'eu-west-1') {
             //  sh '''
             //  aws ec2 run-instances --image-id ami-0ff338189efb7ed37 --instance-type t3.micro
             //  '''
            //   }
            }
        }
    }

}
