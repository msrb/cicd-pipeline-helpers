#!/usr/bin/groovy

def call() {
    sh "docker ps -aq | xargs -r docker rm -vf"
}

