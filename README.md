
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

__Remember to run on the system you targeted__

````shell
./target/spring-native-demo
````
