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

        stage('Archive') {
            steps {
                echo 'Archiving the build artifacts...'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
