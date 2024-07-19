# flink-kafka-hello world

This Java Flink project reads messages from a Kafka topic and prints them out.

## Environment Setup

1. Install Java 11 Open JDK. As of this writing, Flink only works with Java 11 and not later versions.

2. Install [Docker](https://www.docker.com/)

3. Download the [Kafka Docker image](https://kafka.apache.org/downloads#)

4. (Windows) Set up Windows Subsystem for Linux (WSL)
   1. Open a Windows PowerShell prompt.
   2. Enter `wsl --install` to install Ubuntu Linux.
   3. Start a WSL terminal window by entering `wsl` at a PowerShell prompt.  

5. Install the Kafka command-line tools
   1. Go to the Kafka download page: https://kafka.apache.org/downloads#
   2. Find the source download link.
   3. In a terminal window, download the Kafka source with wget: `wget https://downloads.apache.org/kafka/3.7.1/kafka-3.7.1-src.tgz` 
   4. Unzip the file using this command: `tar -xvzf kafka-XXX-src.tgz`
   5. The command-line tools are in the kafka_XXX/bin folder.

You don't need to install the Flink server. This hello world app runs Flink interactively in the debugger. We are not submitting a job to a Flink server in this project.

## Building

The package dependencies are described in the Maven pom.xml file.

In JetBrains IntelliJ, right-click on the pom.xml file and select Maven | Reload Project. Maven will fetch all the Flink and Kafka packages that our code needs to run.  

## Running

1. Start Kafka
   1. In Docker Desktop, start the Kafka image as a container.
   2. The default Kafka port should be 9002 

2. Open a terminal and verify Kafka is working by listing topics:

   `./kafka-topics.sh --bootstrap-server "localhost:9092" --list`

3. In the terminal, create a new topic: 

   `./kafka-topics.sh --bootstrap-server "localhost:9092" --create --topic "hello-world-input"`

4. Run ReadKafkaFromFlink.main() in IntelliJ and make sure the Console window is visible.

5. In the terminal, add messages to your Kafka topic:

   `./kafka-console-producer.sh --bootstrap-server "localhost:9092" --topic "hell
   o-world-input"`
   
6. You will see a > symbol. Type a message then press Enter.

7. You should see the message print out in the IntelliJ console.