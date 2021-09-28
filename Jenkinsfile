pipeline {
    
    agent any

    stages {


        stage("Fix the permission issue") {

            agent any

            steps {
                sh "sudo chown root:jenkins /run/docker.sock"
            }

        }

        stage('Build') {
            steps {
                echo 'Building..'
                git 'https://github.com/tufisii2006/SoftwareDesignTemplate-A2.git'
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
                sh './mvnw test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
