FROM docker.gojek.com/assessment/alpine-jre

ADD build/distributions/parking-lot.tar /

ENTRYPOINT ["/parking-lot/bin/parking-lot"]
