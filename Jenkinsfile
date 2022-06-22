#!groovy
pipeline {
    agent any

    stages {
        stage('maven 构建') {
            steps {
                echo "maven 构建"
                sh "pwd"
                sh "mvn -s '/opt/maven35/conf/settings_xy.xml' clean install -T8 -Dmaven.compile.fork=true -Dmaven.test.skip=true"
            }
        }
    }

}
