# Pokedex Application

### Project Structure
###### unzip the main pokedex-tl.zip folder
1. pokedex-tl [SpringBoot2.4.2|Java11|Gradle]

### Steps to compile and test
```sh
$ cd pokedex-tl
$ ./gradlew clean build
```
- This will compile, build and run the unit test, api integration tests and controller tests.
- Gradle wrapper is used hence system without gradle will be able to run this apploication
- Code coverage of the project is 100%(Other than lombok annotations) . Checked it with IntelliJ code coverage functionality

### Steps to run the application using docker
```sh
$ cd pokedex-tl
$ docker build -t pokedex .
$ docker run -d -p 5000:5000 --name pokedex-container pokedex
```

### Steps to run the application without docker
```sh
$ cd pokedex-tl
$ ./gradlew bootRun
```
- This will run the spring boot server on ***port 5000***

### Assumptions while building application
- As translate API has rate limit, if this api fails we will pass original pokemon description

### Approaches/Patterns/OtherDetails used
1. Test Driven Development
2. Domain Driven Design
3. Hexagonal Architecture Project Structure [Ports and Adapters Pattern]
5. API integration tests
6. Spring boot controller tests
7. Mockito for unit tests

### Get Pokemon API
```
curl --location --request GET 'localhost:5000/pokemon/pikachu'
```

### Get Translated Pokemon API
```
curl --location --request GET 'localhost:5000/pokemon/translated/pikachu'
```

Please do reach out to me in case something does not work. Thanks
#### Author
##### [Ajay Singh]
[Ajay Singh]: <https://www.linkedin.com/in/ajaygahlot/>
