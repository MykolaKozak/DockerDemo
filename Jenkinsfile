pipeline
{
agent { label 'mypc'}

   tools {
      maven 'Maven 3.8.6'
      jdk 'JDK 1.8'
    }

stages {
 stage('Parallel Stage') {
         parallel
         {
            stage('Docker up')
                {
                steps
                    {
                    echo '<<------ DOCKER UP ------>>'
                    sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml up >>docker_log.txt'
                    sh 'sleep 40'
                    }
                }
            stage('Docker chrome scale')
                 {
                 steps
                     {
                     echo '<<------ DOCKER CHROME SCALE UP ------>>'
                      sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml scale chrome=5'
                      sh 'sleep 30'
                      }
                  }
            stage('Run tests')
                {
                 steps
                     {
                      echo '<<------ RUN TESTS ------>>'
                     sh "mvn test"
                     }
                }
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