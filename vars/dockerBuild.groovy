def login() {
    withCredentials([usernamePassword(credentialsId: '80bc73ba-89dc-4de0-8cde-ddcf63e85680', usernameVariable: 'username', passwordVariable: 'password')]) {
        sh """
            docker login --username="${username}" --password="${password}"
        """
    }
}

def build(String tag,String file_name) {
    def scriptcontents = libraryResource "Dockerfile"
    writeFile file:"Dockerfile", text: scriptcontents  
    sh """
        docker build --build-arg file_name="${file_name}" -t "${tag}"  .
    """
}

def push(String tag) {
    sh """
        docker push "${tag}"
    """
}
