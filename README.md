
# spring-native-demo

## Run & Build

### Build for current system

````shell
mvn -DskipTests -Pnative clean package
````

### Build for linux using docker

````shell
./build-linux-docker.sh
````

### Run

**Remember to run on the system you targeted**

````shell
./target/spring-native-demo
````
