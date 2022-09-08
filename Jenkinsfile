pipeline
{
agent { label 'mypc'}

   tools {
      maven 'Maven 3.8.6'
      jdk 'JDK 1.8'
    }

stages {
    stage('Build')
	{
		steps
		{
		    checkout scm
            @docker.image('selenium/hub:latest').inside("-e COMPOSER_HOME=/tmp/jenkins-workspace")
            {
		      stage("Prepare folders") { sh "mkdir /tmp/jenkins-workspace"}
             }
		}
		}
		stage('Docker Up')
    	{
    		steps
    		{
    			sh 'docker-compose -f docker-compose.yaml up >>docker_log.txt'
    		}
    	}
	stage('Depoly to QA')
	{
		steps
		{
			echo 'depoly code to QA env'
		}
	}
	stage('Run API tests')
	{
		steps
		{
			echo 'need to use: mvn test'
		}
	}
	stage('Run Smoke UI tests')
	{
		steps
		{
			sh "mvn compile"
		}
	}
	stage('Release to PROD')
	{
		steps
		{
			echo 'release code'
		}
	}
    }
}