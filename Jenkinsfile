pipeline {
    agent any

    environment {
        BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        MAVEN_HOME = tool name: 'Maven 3', type: 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/khanhsienghoc/Personal-Project-HRM-Maven.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat """
                    "%MAVEN_HOME%\\bin\\mvn" clean compile
                """
            }
        }
            stage('Debug Credentials') {
                    steps {
                        withCredentials([usernamePassword(
                            credentialsId: 'test-account',
                            usernameVariable: 'CI_USER',
                            passwordVariable: 'CI_PASS'
                        )]) {
                            bat """
                                echo DEBUG USERNAME = %CI_USER%
                                echo DEBUG PASSWORD length = %CI_PASS%
                            """
                        }
                    }
                }
       stage('Run Tests') {
                  steps {
                      withCredentials([usernamePassword(
                          credentialsId: 'test-account',
                          usernameVariable: 'CI_USER',
                          passwordVariable: 'CI_PASS'
                      )]) {
                          bat """
                              echo DEBUG: USERNAME= %CI_USER%
                              echo DEBUG: PASSWORD length= %CI_PASS%
                              "%MAVEN_HOME%\\bin\\mvn" test -DbaseUrl=%BASE_URL% -Dusername=%CI_USER% -Dpassword=%CI_PASS%
                          """
                      }
                  }
              }
          }


        stage('Allure Report') {
            steps {
                bat """
                    allure generate target/allure-results --clean -o target/allure-report
                """
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always { cleanWs() }
        success { echo '✅ Tests passed!' }
        failure { echo '❌ Tests failed!' }
    }
}
