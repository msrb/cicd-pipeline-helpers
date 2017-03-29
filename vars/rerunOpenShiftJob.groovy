#!/usr/bin/groovy

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    sh "oc --context=${config.cluster} get job ${config.jobName} --export=true --output=yaml > ${config.jobName}.yaml"
    sh "oc --context=${config.cluster} delete job ${config.jobName}"
    sh "oc --context=${config.cluster} create -f ${config.jobName}.yaml"
}

