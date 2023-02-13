<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="plug.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">JavaEvaluator</h3>
  <p>Math expressions evaluator written in Java</p>
</div>

### About 
Multiple clients can be connected to a centralized server in order solve math expressions. The main idea with this project is to test the new features coming in **Project Loom**

### Requirements: 
- OpenJDK 20 


### Packaging:
    
    mvn clean package

### Running on a local machine:
#### Server:

In order to run the server you need to execute the flow command:

    java --enable-preview -jar server/target/server-1.0-SNAPSHOT.jar

* can expecify the server port exporting the enviroment variable PORT


#### Client:

In order to run the client you need to execute the flow command:

    java --enable-preview -jar client/target/client-1.0-SNAPSHOT.jar -s localhost -p 5000

* where:
    

    usage: Usage:
    -p,--port <arg>     Set the server port
    -s,--server <arg>   Set the server hostname

### Running tests
    
    mvn test