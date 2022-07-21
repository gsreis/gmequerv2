function execute(connections, errors, results, properties) { 
    connarray = connections.getArray();
    errorsarray = errors.getArray();
    resarray = results.getArray();
    proparray = properties.getArray();
    sent = 0;
    receiv = 0;
    tpssend =0;
    tpsreceiv = 0;
    pctSend = 0;
    pctReceiv = 0;
    time = 1;
    
    mediaTPSSend = 0;
    mediaTPSReceiv = 0;
    mediaBytesSend = 0;
    mediaBytesReceiv = 0;
        
    for (i=0; i < resarray.length; i++) {
        
        if (resarray[i].isProducer) {
            pctSend++;
            sent += resarray[i].messageSize;
        }
        else {
            pctReceiv++;
            receiv += resarray[i].messageSize;
        }
        
        if ((resarray[i].timestamp - time)  > 1000) {
            time = resarray[i].timestamp;
            mediaTPSSend = pctSend;
            mediaTPSReceiv = pctReceiv;
            mediaBytesSend = sent;
            mediaBytesReceiv = receiv;

            pctSend = 0;
            pctReceiv = 0;
            sent = 0;
            receiv = 0;
        }         
    }
    
    print(" bytes enviados " + mediaBytesSend);
    print(" bytes recebidos " + mediaBytesReceiv);
    
    print(" TPS envio " + mediaTPSSend);
    print(" TPS recebimento " + mediaTPSReceiv); 
    
    print("");
}

function help() {
    print("tps - rotina automatica de apresentacao dos dados");
}
