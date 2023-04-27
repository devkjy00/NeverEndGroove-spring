pipeline {
    agent any

	environment {
		GPG_KEY = credentials('gpg-key')
		GPG_PASS = credentials('gpg-password')
		SPRING_IMAGE = 'neverendgroove-spring'
	}

    stages {
        stage('git secret') {
            steps {
                script {
					sh 'gpg --batch --import ${GPG_KEY}'
					sh 'git secret reveal -p ${GPG_PASS}'
                }
            }
        }

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
					sh 'docker compose stop spring'
          sh 'docker compose rm -f spring'
					sh 'docker images --format="{{.Repository}}" | grep -w "${SPRING_IMAGE}" && docker rmi ${SPRING_IMAGE} || true'
					sh 'docker compose up -d'
// 					sh 'docker ps -a --filter="name=${SPRING_CONTAINER}" --format="{{.Names}}" | grep -w "${SPRING_CONTAINER}" && (docker stop ${SPRING_CONTAINER} && docker rm ${SPRING_CONTAINER}) || true'
// 					sh 'docker build -t ${SPRING_IMAGE}:latest .'

          // 네트워크와 레디스 컨테이너가 있는지 확인하고 없으면 생성합니다.
//           def networkExists = sh(script: 'docker network ls | grep ${BRIDGE_NETWORK}|| true', returnStdout: true).trim()
//           if (!networkExists) {
//               sh 'docker network create ${BRIDGE_NETWORK}'
//           }
//           def redisContainerExists = sh(script: 'docker ps | grep ${REDIS_CONTAINER}|| true', returnStdout: true).trim()
//           if (!redisContainerExists) {
//               sh 'docker run -d --name ${REDIS_CONTAINER} --network ${BRIDGE_NETWORK} redis'
//           }

//           sh 'docker run -p 8080:8080 -d --name ${SPRING_CONTAINER} --network ${BRIDGE_NETWORK} ${SPRING_IMAGE}:latest'

					}
				}
			}
        }
    }
