function execute(connections, errors, results, properties) { 
    connarray = connections.getArray();
    errorsarray = errors.getArray();
    resarray = results.getArray();
    proparray = properties.getArray();
    sent = 0;
    receiv = 0;
    for (i=0; i < resarray.length; i++)
        if (resarray[i].isProducer)
            sent++;
        else
            receiv++;
    print(" enviados " + sent);
    print(" recebidos " + receiv);
}

function help() {
    print("tps - rotina automatica de apresentacao dos dados");
}
