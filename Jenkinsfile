pipeline {
  agent {
    node {
      label 'master'
    }
    
  }
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/serenotus/PracticeAnsible.git', branch: 'master', changelog: true)
      }
    }
    stage('Build') {
      steps {
        sh '''sh \'\'\'for file in $(find . -type f -name"*.yml")
do
  ansible-lint $file
done\'\'\''''
      }
    }
    stage('Delivery') {
      steps {
        sh 'sh \'echo \\\'Publish artifact over SSH.\\\'\''
      }
    }
  }
}