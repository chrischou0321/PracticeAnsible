pipeline {
  agent {
    node {
      label 'master'
    }
    
  }
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/serenotus/PracticeAnsible.git', branch: 'master')
      }
    }
    stage('Build') {
      steps {
        script {
          def currentBuildResult = 'FAILURE'
          try {
            sh '''for file in $(find . -type f -name"*.yml")
do
ansible-lint $file
done'''
            currentBuildResult = 'SUCCESS'
          } catch(err) {
            currentBuildResult = 'FAILURE'
          }
        }
        
      }
    }
    stage('Delivery') {
      steps {
        sh 'echo \'Publish artifact over SSH.\''
      }
    }
    stage('Notify') {
      steps {
        script {
          NotifyLine()
        }
        
      }
    }
  }
}