pipeline {
  agent any
  stages {
    stage('compile') {
      steps {
        sh 'mvn package'
      }
    }

    stage('test') {
      steps {
        sh 'mvn package'
      }
    }

    stage('package') {
      steps {
        sh 'mvn package'
      }
    }

    stage('archive ') {
      steps {
        archiveArtifacts 'target/*.jar'
      }
    }

    stage('publish result') {
      steps {
        junit '**/*.xml'
      }
    }

  }
}