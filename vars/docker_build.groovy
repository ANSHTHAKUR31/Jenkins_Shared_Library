def call(String imageName, String imageTag = 'latest', String hubUser = 'anshthakur31') {
  
    def fullImageName = "${hubUser}/${imageName}"
    def dockerfile = 'Dockerfile'
    def context = '.'
    
    echo "Building Docker image: ${fullImageName}:${imageTag}"
    
    sh """
        docker build -t ${fullImageName}:${imageTag} -t ${fullImageName}:latest -f ${dockerfile} ${context}
    """
}
