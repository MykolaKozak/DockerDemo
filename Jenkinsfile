pipeline
{
agent { label 'mypc'}

   tools {
      maven 'Maven 3.8.6'
      jdk 'JDK 1.8'
    }

stages {
    stage('Docker up')
	{
		steps
		{
			sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml up >>docker_log.txt'
		}
	}
	stage('Depoly to QA')
	{
		steps
		{
			echo 'depoly code to QA env'
			sh 'mvn clean'
		}
	}
	stage('Run UI tests')
	{
		steps
		{
			sh "mvn test"
		}
	}
	stage('Docker down')
	{
		steps
		{
			sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml down'
		}
	}
    }
}