#!/usr/bin/groovy

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    sh "oc --context=${config.context} process ${config.processParams} -f ${config.template} | oc --context=${config.context} apply -f -"
}

