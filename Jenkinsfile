pipeline
{
agent { label 'mypc'}

   tools
        {
          maven 'Maven 3.8.6'
          jdk 'JDK 1.8'
        }

stages
      {
         stage('Docker config')
               {
                  parallel
                         {
                            stage('Docker up')
                                  {
                                    steps
                                         {
                                          echo '<<------ DOCKER UP ------>>'
                                          sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml up >>docker_log.txt'
                                         }
                                  }
                            stage('Docker chrome scale')
                                 {
                                    steps
                                         {
                                           sh 'sleep 40'
                                           echo '<<------ DOCKER CHROME SCALE UP ------>>'
                                           sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml scale chrome=5'
                                         }
                                 }

                            stage('Linked stages')
                                                 {
                                                 stages{
                                                        stage('Run tests')
                                                                      {
                                                                         steps
                                                                               {
                                                                                 echo '<<------ RUN TESTS ------>>'
                                                                                 sh "mvn clean"
                                                                                 sh "mvn test"
                                                                               }
                                                                      }
                                                        stage('Docker down')
                                                                      {
                                                                        steps
                                                                               {
                                                                                 echo '<<------ DOCKER DOWN ------>>'
                                                                                  sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml down'
                                                                               }
                                                        }             }
                                                 }
                        }
               }
     }
}