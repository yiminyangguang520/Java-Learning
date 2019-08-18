# Live Streaming Docker
Docker Compose for Live Streaming API &amp; Webapp. Follow the below instructions to run using `docker-compose`.

**1. Clone the repository**
```bash
$ sudo git clone https://github.com/attacomsian/live-streaming-docker.git --recurse-submodules
```

**2. Open directory**
```bash
$ cd live-streaming-docker
```

**3. Build REST API**
You must build Spring Boot REST API for container usage.
```bash
$ cd live-streaming && sudo ./gradlew build && cd ..
```

**4. Build Docker-Compose**
```bash
$ sudo docker-compose build
```

**5. Run Docker-Compose**
```bash
$ sudo dockeer-compose up
```

That's all. After `docker-compose` is started, please go to [http://localhost:4200](http://localhost:4200) and start using the service.
