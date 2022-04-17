FROM adoptopenjdk:11-jre-hotspot

ADD build/libs/pokedex-0.0.1-SNAPSHOT.jar .

CMD java -jar pokedex-0.0.1-SNAPSHOT.jar
