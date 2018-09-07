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
		script {
			def result = 'SUCCESS'
			def isFailure = result == 'FAILURE'
			def token = 'PnbiZptLccIfx4DXQLOW3SP7IvgMF91sNaXioIgHcIk'
			def url = 'https://notify-api.line.me/api/notify'
			def message = "Build ${env.BRANCH_NAME}, result is ${result}. \n${env.BUILD_URL}"
			def imageThumbnail = isFailure ? 'FAILED_IMAGE_THUMBNAIL' : ''
			def imageFullsize = isFailure ? 'FAILED_IMAGE_FULLSIZE' : ''
				  
			sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}' \
			-F 'imageThumbnail=${imageThumbnail}' -F 'imageFullsize=${imageFullsize}'"
        }
	}
  }
}
