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

        stage('Deploy') {
        //pune sus JAR-ul in S3
        //ia JAR-ul cu versiunea X din S3
            steps {
                echo 'Deploying....'
            }
        }
    }
}
