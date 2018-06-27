FROM frolvlad/alpine-oraclejdk8:slim

# Build system will provide arguments to identify source location/version
ARG GIT_REPO_NAME='NOT SPECIFIED'
ARG GIT_BRANCH_NAME='NOT SPECIFIED'
ARG GIT_COMMIT_HASH='NOT SPECIFIED'
ARG BUILD_TIMESTAMP='NOT SPECIFIED'

# Label image with source location/version
LABEL com.makara.build.repo=${GIT_REPO_NAME} \
      com.makara.build.branch=${GIT_BRANCH_NAME} \
      com.makara.build.timestamp=${BUILD_TIMESTAMP} \
      com.makara.build.commit-hash=${GIT_COMMIT_HASH}

RUN apk --no-cache add --update bash

ARG JAVA_OPTS=''

COPY app.jar /app.jar


EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

