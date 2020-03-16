pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Deploy') {
      steps {
        sh 'cp target/user-api.war ~/apache-tomcat-9.0.31/webapps'
      }
    }
  }
}