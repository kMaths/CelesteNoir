pipeline {
    agent any

    stages {
        stage('stop old app') {
            steps {
                echo 'stop server'
            }
        }
        stage('building app'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('copy app'){
            steps{
                sh 'cp target/*.war ~/apache-tomcat-9.0.39/webapps'
            }
        }
        stage('start new app'){
            steps{
                echo 'start server'
            }
        }
        
    }
}
