def call(String imageName, String imageTag = 'latest', String hubUser = 'anshthakur31') {
    def fullImageName = "${hubUser}/${imageName}"
    // Yahi ID Jenkins Credentials mein honi chahiye
    def credentialsId = 'docker-hub-credentials' 
    
    echo "Pushing Docker image: ${fullImageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentialsId, // Variable use karein
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD'
    )]) {
        sh """
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker push ${fullImageName}:${imageTag}
            docker push ${fullImageName}:latest
        """
    }
}
