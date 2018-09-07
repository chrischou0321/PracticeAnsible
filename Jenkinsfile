pipeline {
  agent {
    node {
      label 'master'
    }
    
  }
  stages {
	try {
		stage('Checkout') {
		  steps {
			git(url: 'https://github.com/serenotus/PracticeAnsible.git', branch: 'master')
		  }
		}
		stage('Build') {
		  steps {
			sh '''for file in $(find . -type f -name"*.yml")
					do
					  ansible-lint $file
					done'''
			currentBuild.result = 'SUCCESS'
		  }
		}
	} catch (err) {
        currentBuild.result = 'FAILURE'
    }
    
    stage('Delivery') {
      steps {
        sh 'echo \'Publish artifact over SSH.\''
		notifyLINE('PnbiZptLccIfx4DXQLOW3SP7IvgMF91sNaXioIgHcIk', currentBuild.result)
      }
    }
  }
}

def notifyLINE(token, result) {
    def isFailure = result == 'FAILURE'
      
    def url = 'https://notify-api.line.me/api/notify'
    def message = "Build ${env.BRANCH_NAME}, result is ${result}. \n${env.BUILD_URL}"
    def imageThumbnail = isFailure ? 'FAILED_IMAGE_THUMBNAIL' : ''
    def imageFullsize = isFailure ? 'FAILED_IMAGE_FULLSIZE' : ''
      
    sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}'   
    -F 'imageThumbnail=${imageThumbnail}' -F 'imageFullsize=${imageFullsize}'"
}