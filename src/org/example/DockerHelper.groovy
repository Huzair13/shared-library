package org.example

class DockerHelper implements Serializable {
    def script

    DockerHelper(script) {
        this.script = script
    }

    def buildAndPushDockerImage(String ecrRepoUri, String dockerfilePath = ".", String tag = "latest") {
        script.sh """
            aws ecr get-login-password --region ${script.env.AWS_REGION} | docker login --username AWS --password-stdin ${ecrRepoUri}
            docker build -t ${ecrRepoUri}:${tag} ${dockerfilePath}
            docker push ${ecrRepoUri}:${tag}
        """
    }
}
