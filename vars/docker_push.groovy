def call(String imageName, String imageTag = 'latest', String hubUser = 'anshthakur31') {
    // Ham image name ko hubUser ke saath combine kar rahe hain
    def fullImageName = "${hubUser}/${imageName}"
    // Aapne Jenkins mein jo credentials ID banayi hai, wo yahan likhein
    def credentials = 'docker-hub-credentials' 
    
    echo "Pushing Docker image: ${fullImageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials,
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
