pipeline {
    
    agent any

     environment {
            AWS_ACCOUNT_ID = "669321770540"
            AWS_DEFAULT_REGION = "eu-west-1"
            IMAGE_REPO_NAME = "tufi"
            RELEASE_NOTES_IMG_TAG = sh (script: """git log --format="medium" -1 ${GIT_COMMIT}""", returnStdout:true)
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

        // This was just before: I wanted to send the JAR file to S3 bucket.
        // The next step will create a docker image and send it to AWS ECR.
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
              echo 'Image tag: $RELEASE_NOTES_IMG_TAG'
              dockerImage = docker.build "${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG}"
            }
          }
        }

         stage('Logging into AWS ECR') {
            steps {
                script {
                sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
                }
             }
         }

        stage('Pushing to ECR') {
          steps{
            script {
                echo 'Pushing image to ECR....'
                sh "docker tag ${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG} ${REPOSITORY_URI}:$RELEASE_NOTES_IMG_TAG"
            //    sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${RELEASE_NOTES_IMG_TAG}"
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
