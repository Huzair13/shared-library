def call(Map params = [:]) {
    def ecrRepoUri = params.get('ecrRepoUri')
    def dockerfilePath = params.get('dockerfilePath', '.')
    def tag = params.get('tag', 'latest')

    if (!ecrRepoUri) {
        error "ECR repository URI is required"
    }

    def helper = new org.example.DockerHelper(this)
    helper.buildAndPushDockerImage(ecrRepoUri, dockerfilePath, tag)
}
