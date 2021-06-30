FROM hocheol.azurecr.io/nexcore-fwk-jar:v1

ENV TZ=Asia/Seoul

COPY target/nexbank.xyz.corebanking.* /nexcore/runtime/applications/online/

WORKDIR /nexcore/wlp/bin

ENTRYPOINT ["sh", "-c", "./server run -- -DNEXCORE_ID=$(hostname) -DNEXCORE_LOG_HOME=/nexcore/logs/runtime/$(hostname) -DNEXCORE_RUNTIME_MODE=D -DMAIN_DATABASE=j2ee81_demo_fwk"]

