pipeline {
    agent any

    environment {
        BASE_URL = "https://staging.example.com"
        MAVEN_HOME = tool name: 'Maven 3', type: 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }

        stage('Run Tests') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'test-account',
                                                 usernameVariable: 'USERNAME',
                                                 passwordVariable: 'PASSWORD')]) {
                    sh "${MAVEN_HOME}/bin/mvn test -DbaseUrl=${BASE_URL} -Dusername=$USERNAME -Dpassword=$PASSWORD"
                }
            }
        }

        stage('Allure Report') {
            steps {
                sh "allure generate target/allure-results --clean -o target/allure-report"
                // Nếu muốn mở report trực tiếp trong server Jenkins
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo '✅ Tests passed!'
        }
        failure {
            echo '❌ Tests failed!'
        }
    }
}
