function execute(connections, errors, results, properties) {
       var values = properties.getArray();
       for (i=0; i < values.length; i++) { 
            print(values[i].name + "......" + values[i].value);
       } 
       print("Threads iniciadas"); 
}

