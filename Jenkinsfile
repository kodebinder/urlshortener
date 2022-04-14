pipeline {
   agent any
   tools{
      maven 'Maven'
   }
   stages {
      stage('Checkout code') {
        steps {
            checkout scm
        }
      }
      stage('Build') {
        steps {
            echo 'Building URL Shortener Application...'
            sh 'mvn clean install'
         }
         post {
             always {
               junit 'urlservice/target/surefire-reports/*.xml'
               junit 'userservice/target/surefire-reports/*.xml'
             }
         }
      }
      stage('Test') {
         steps {
            echo 'Testing URL Shortener Application...'
            sh 'mvn test'
         }
      }
      stage('Deploy') {
         steps {
            echo 'Deploying URL Shortener Application...'
         }
      }
   }
}