pipeline
{
agent any

stages {
    stage('Build')
	{
		steps
		{
			sh "clean"
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
			sh "mvn test"
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