pipeline {
    agent any

    tools {
        maven 'maven-3.6.1'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn test-compile'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn surefire:test'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }


        stage('Deploy') {
            steps {
                sh 'echo In progress'
            }
        }
    }
}