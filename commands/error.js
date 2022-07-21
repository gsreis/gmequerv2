function execute(connections, errors, results, properties) { 
    errorsarray = errors.getArray();
    for(i=0; errorsarray.length; i++) {
        print(errorsarray[i]);
    }
    errors.clear();    
}

function help() {
    print("error - mostra todos os erros acumulados até o momento");
    print("após apresentar os erros eles serão zerados");
}

