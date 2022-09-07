pipeline
{
agent {
    docker {image'node:16-alpine'}
}

stages {
    stage('Build')
	{
		steps
		{
			sh 'node -v'
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