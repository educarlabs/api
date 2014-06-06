$( document ).ready(function() {

    /**
     * @param {string} key Es la clave para acceder a la api
     * 
     * */
    $.ajax({
        url: "https://api.educ.ar/0.9/recursos/catalogacion",
        type: "GET",
        data: {
            key: "0cd962859f73a0bfd5e432f0dca94f0a5d422791"
        },
        dataType: "json",
        success: mostrarResultados,
        error : mostrarError,
        contentType: "application/json; charset=\"utf-8\""
    });
    /**
     * Clase con algunas funciones que permiten obtener datos de la catalogacion
     * 
     * */
    var temas = null;
    
    var Catalogacion = {
        
        
        /**
         * @param {int} padre El id del tema sobre el cual se buscaran los temas hijos.
         * 
         * @return {array} Un array con los temas hijos. Si no hay hijos devuelve un array vacio.
         * 
         * */
        obtenerHijos : function(padre){
            var hijos = new Array();
            
            $.each(temas, function(index, element){
                if(element.id_padre === padre){
                    hijos.push(element);
                }
            })
            
            return hijos;
        },
        
        /**
         * @param {int} id_tema id del tema a buscar
         * 
         * @return {var} Devuelve un tema o null en caso de no encontrarlo
         * 
         * */
        obtenerTema : function(id_tema){
            
            var temaResponse = null;
            
            $.each(temas, function(index, element){
                
                if(element.id === id_tema){
                    temaResponse =  element;
                }
            })
            
            return temaResponse;
        }
        
    }
    
    
    function mostrarResultados(response, status){
        /**
         * La entity es el tipo de objeto de la api. Sirve para saber con que objeto se esta trabajando y conocer sus atributos y funciones.
         * 
         * Ver http://datosabiertos.educ.ar/documentacion
         * 
         * */
        $('#entidad').append(response.entity);
        
        temas = response.result;
        
        renderizarTemas(null);
    }
    
    
    /**
     * 
     * @param {var} tema El tema que se va a renderizar
     * @param {var} primerNodo Es el nodo raiz. Se utiliza en la recursividad de la funcion para renderizar a los padres. No hace falta pasarlo definirlo en el llamado inicial.
     * 
     * @description Renderiza la jerarquia de catalogacion
     * 
     * */
    function renderizarTemas(tema, primerNodo){
        
        if(primerNodo === undefined){
            primerNodo = tema;
        }
        
        if(tema !== null){

            var objTema = Catalogacion.obtenerTema(tema);
            
            if(objTema.id_padre === primerNodo){
                $('#temas').append("<li id='tema_" + objTema.id + "'>" + objTema.descripcion + "</li>");
            }
            else{
                
                $('#tema_' + objTema.id_padre).append("<ul><li id='tema_" + objTema.id + "'>" + objTema.descripcion + "</li></ul>");
            }
            
        }
        
        var hijos = Catalogacion.obtenerHijos(tema);
        
        if(hijos.length !== 0){
        
            $.each(hijos, function(index, element){
                renderizarTemas(element.id, primerNodo);
            });
        }
                
    }
    
    /**
     * 
     * @description Muestra el error de la peticion ajax
     * 
     * */
    function mostrarError(xmlHttpRequest, status){
        console.log("Error WS: " + status);
    }

});