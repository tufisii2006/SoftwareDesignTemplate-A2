pipeline {
    
     agent any

    parameters {
         booleanParam(name: "PUSH_TO_ECR", defaultValue: false, description: "Whether you want to push to AWS ECR or not.")
       //  string(name: "TEST_STRING", defaultValue: "ssbostan", trim: true, description: "Sample string parameter")
       //  text(name: "TEST_TEXT", defaultValue: "Jenkins Pipeline Tutorial", description: "Sample multi-line text parameter")
       //  password(name: "TEST_PASSWORD", defaultValue: "SECRET", description: "Sample password parameter")
       //  choice(name: "TEST_CHOICE", choices: ["production", "staging", "development"], description: "Sample multi-choice parameter")
     }

     environment {
            AWS_ACCOUNT_ID = "669321770540"
            AWS_DEFAULT_REGION = "eu-west-1"
            IMAGE_REPO_NAME = "tufi"
            RELEASE_NOTES_IMG_TAG = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
            REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
            dockerImage = ''
        }

/*     tools {
         maven 'Maven 3.8.2'
         jdk 'Java 9'
        }
*/

    stages {

        stage('Check maven version') {
                steps {
                    echo 'Checking Maven....'
                    sh 'mvn -v'
             }
        }

        stage('Clean and test') {
              steps {
                      echo 'Cleaning and test....'
                      git (
                          url:'https://github.com/tufisii2006/SoftwareDesignTemplate-A2.git',
                          branch: "dev"
                      )
                      sh 'mvn clean compile test'
                    }
                }

        // I wanted to send the JAR file to S3 bucket.
        stage('Publish JAR to S3 Bucket') {
            steps {
                echo 'Publishing to AWS S3....'
                script {
                    withAWS(credentials:'AWS-S3', region:'eu-west-1') {
                     //  Log AWS credentials
                     // def identity = awsIdentity();
                     //  s3Upload(bucket:'infi-s3-ping', workingDir:'target', includePathPattern:'**/*.jar');
                     // sh '''aws ec2 describe-instances'''
                    }
                }
            }
        }

        stage('Building docker image') {
          steps{
            script {
              echo 'Building image....'
              dockerImage = docker.build "${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG}"
            }
          }
        }

         stage('Logging into AWS ECR') {
            steps {
                script {
                echo 'Logging into AWS ECR....'
                sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
                }
             }
         }

        stage('Pushing to ECR') {
          steps{
            script {
                sh "docker tag ${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG} ${REPOSITORY_URI}:$RELEASE_NOTES_IMG_TAG"
                echo 'Pushing image to ECR....'
                when { expression { params.PUSH_TO_ECR == true } }
                  steps {
                      sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG}"
                    }
                 }
              }
          }

        stage('Deploy') {
            steps {
                echo 'Deploying.... TODO'
            }
        }
    }

}
