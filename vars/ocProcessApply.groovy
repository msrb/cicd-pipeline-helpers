#!/usr/bin/groovy

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    // we don't want to deploy to rh-idev now
    if ("test-context".equals(config.context)) {
        echo "Skipping deploy to rh-idev..."
        return
    }

    sh "oc --context=${config.context} process ${config.processParams} -f ${config.templateFile} | oc --context=${config.context} apply -f -"
}

