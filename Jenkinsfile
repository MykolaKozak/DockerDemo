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
			sh 'mvn clean'
		}
	}
	stage('Depoly to QA')
	{
		steps
		{
			sh /usr/local/bin/docker-compose -f ./docker-compose/selenoid-docker-compose-local.yml --env-file ./docker-compose/.env.local down
		}
	}
	stage('Run API tests')
	{
		steps
		{
			echo 'should mvn test'
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