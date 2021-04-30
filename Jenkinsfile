pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
  }
  stages {
      stage('run test') {
        steps {
            bat 'mvn test'
        }
      }
      stage('SonarQube analysis') {
        steps {
            bat 'mvn clean package sonar:sonar -Dsonar.login=55320663377d02ee326abd806b1898a0554d5a93'
        }
      }
      stage('Deployment') {
        when {
            branch 'master'
        } 
        steps {
            echo 'This is a deployment'
        }
      }
  }
}

