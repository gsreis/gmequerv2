
# Nível 1 - somente executar 

Baixar todos os arquivos em um diretório 

## Criar uma instância de MQ na máquina
Pode ser feito com o comando </br>

<li>docker volume qm1data</li>
<li>docker run --name mqdemo --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --env MQ_ENABLE_METRICS=true --env MQ_ENABLE_EMBEDDED_WEB_SERVER=true --env MQ_ADMIN_PASSWORD=passw0rd --env MQ_APP_PASSWORD=passw0rd --volume qm1data:/mnt/mqm --publish 1414:1414 --publish 9009:9443 --publish 9157:9157 --detach ibmcom/mq</li>

## este utiliza a imagem aberta de demo do MQ no Docker.hub, e aponta para uma persistência externa (--volume)
## ou

<li>docker volume qm1data</li>
<li>docker run --name mqdemo --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --env MQ_ENABLE_METRICS=true --env MQ_ENABLE_EMBEDDED_WEB_SERVER=true --publish 1414:1414 --publish 9009:9443 --publish 9157:9157 --detach icr.io/ibm-messaging/mq</li>

Depois é só abrir um command prompt e executar ./gmequer.sh ou gmequer.bat (depende do sistema operacional)
Ele já está configurado para apontar para o IP loopback e as configurações default do comando docker acima

# Nivel 2 - Mudar configurações 

