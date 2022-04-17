# pokedex-tl

docker build -t pokedex .
docker run -d -p 5000:5000 --name pokedex-container pokedex

curl 'http://localhost:5000/pokemon/translated/pikachu'

curl 'http://localhost:5000/pokemon/pikachu'