function execute(connections, errors, results, properties) { 
    connarray = connections.getArray();
    errorsarray = errors.getArray();
    resarray = results.getArray();
    proparray = properties.getArray();
    for(i = 0; i < connarray.length; i++) {  
           print(connarray[i].jmsFactory.toString());
    } 
}

function help() {
    print("config - mostra as configurações das conexões");
}


