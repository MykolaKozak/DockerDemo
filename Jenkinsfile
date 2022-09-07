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