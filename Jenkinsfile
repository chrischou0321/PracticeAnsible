void NotifyLine(token, result) {
	sh 'echo \'Notify to Line Start.\''
	// def result = 'SUCCESS'
    // def token = 'PnbiZptLccIfx4DXQLOW3SP7IvgMF91sNaXioIgHcIk'
    def url = 'https://notify-api.line.me/api/notify'
    def message = "Build ${env.BRANCH_NAME}, result is ${result}. \n${env.BUILD_URL}"
          
    sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}'"
}
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
        script {
          NotifyLine('PnbiZptLccIfx4DXQLOW3SP7IvgMF91sNaXioIgHcIk', currentBuildResult)
        }
        
      }
    }
  }
}
