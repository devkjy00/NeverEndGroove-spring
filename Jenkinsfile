pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
                script {
                    // Use the Gradle Wrapper if your project uses Gradle
                    sh './gradlew build -x test'

                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                script {
                    // Use the Gradle Wrapper if your project uses Gradle
                    sh './gradlew test'

                }
            }
        }
		stage('deploy') {
			steps {
				script {
					sh 'docker ps -a --filter="name=neverendgroove1" --format="{{.Names}}" | grep -w "neverendgroove1" && (docker stop neverendgroove1 && docker rm neverendgroove1) || true'
					sh 'docker images --format="{{.Repository}}" | grep -w "neverendgroove" && docker rmi neverendgroove || true'
					sh 'docker build -t neverendgroove:latest .'
					sh 'docker run -p 8080:8080 -d --name neverendgroove1 neverendgroove'
					}
				}
			}
        }
    }
