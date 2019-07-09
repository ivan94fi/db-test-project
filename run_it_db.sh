# TODO: understand if it is already running: docker ps | grep -oP pg-docker
docker run --rm --name pg-docker -e POSTGRES_DB=it_db -e POSTGRES_USER=it_user -e POSTGRES_PASSWORD=it_pwd --detach -p 5436:5432 postgres:11.4-alpine



# Manual command:
# mvn -f db-test-aggregator/pom.xml clean verify -P enable-jacoco -P enable-coveralls -DrepoToken=$COVERALLS_TOKEN
