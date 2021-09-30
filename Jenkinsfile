pipeline {
    
    agent any

     environment {
            AWS_ACCOUNT_ID = "669321770540"
            AWS_DEFAULT_REGION = "eu-west-1"
            IMAGE_REPO_NAME = "tufi"
            IMAGE_TAG = "latest"
            REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
            dockerImage = ''
        }

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
                        def identity = awsIdentity(); //Log AWS credentials
                      //  s3Upload(bucket:'infi-s3-ping', workingDir:'target', includePathPattern:'**/*.jar');
                      // sh '''aws ec2 describe-instances'''
                    }
                }
            }
        }

        // Building Docker images
        stage('Building docker image') {
          steps{
            script {
              echo 'Building image....'
              dockerImage = docker.build hello-world
            }
          }
        }

        stage('Pushing to ECR') {
          steps{
            script {
                 echo 'Pushing image to ECR....'
          /*    withAWS(credentials:'AWS-S3', region:'eu-west-1') {
                sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${REPOSITORY_URI}:$IMAGE_TAG"
                sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
                } */
             }
            }
          }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }

}
