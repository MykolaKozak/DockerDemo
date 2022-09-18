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
                                          sh '/usr/local/bin/docker-compose-v1 -f docker-compose.yaml up >>docker_log.txt --scale ${BROWSER_NAME}=5 -d'
                                         }
                                  }
                            stage('Linked stages')
                                                 {
                                                 stages{
                                                        stage('Run tests')
                                                                      {
                                                                         steps
                                                                               {
                                                                                 sh 'sleep 15'
                                                                                 echo '<<------ Replace config ------>>'
                                                                                     script
                                                                                      {
                                                                                      config = readFile "src/main/java/config/properties.properties"
                                                                                      newconfig = config.replaceAll("env=.*","env=${ENV}")
                                                                                      writeFile file: "src/main/java/config/properties.properties", text: "${newconfig}"
                                                                                      config = readFile "src/main/java/config/properties.properties"
                                                                                      newconfig = config.replaceAll("browserName=.*","browserName=${BROWSER_NAME}")
                                                                                      writeFile file: "src/main/java/config/properties.properties", text: "${newconfig}"
                                                                                      }
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