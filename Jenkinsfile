pipeline {
    agent any

    tools {
        // Make sure 'maven' is configured in your Jenkins Global Tool Configuration
        maven 'maven' 
        jdk 'jdk-21'
    }

    stages {
        stage('Checkout') {
            steps {
                // Jenkins automatically checks out the code from the SCM configured in the job
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Run the tests using Maven
                // We use 'bat' for Windows, use 'sh' for Mac/Linux
                bat 'mvn clean test -Dbrowser=chrome'
            }
        }
    }

    post {
        always {
            // Archive the Extent Report so we can see it in Jenkins
            archiveArtifacts artifacts: 'extent-report.html', allowEmptyArchive: true
        }
    }
}