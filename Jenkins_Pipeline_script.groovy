node('master') {
    git 'https://github.com/XXX.git'
    sh '''for file in $(find . -type f -name "*.yml")
    do
	    ansible-lint $file
    done'''
}