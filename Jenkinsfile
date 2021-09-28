pipeline {
    
    agent any

      tools {
            maven 'Maven 3.8.2'
            jdk 'Java 9'
        }

    stages {

         stage('Check version') {
                steps {
                    echo 'Checking version..'
                    sh 'mvn -v'
                }
            }

        stage('Build') {
            steps {
                echo 'Building..'
                git 'https://github.com/tufisii2006/SoftwareDesignTemplate-A2.git'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
