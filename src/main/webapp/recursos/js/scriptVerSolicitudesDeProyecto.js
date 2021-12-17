window.onload = function (){
    let botonesVerMas = document.querySelectorAll(".contenedorSolicitud .botonVerMas");
    let numBotonesVerMas = botonesVerMas.length;
    for (let i = 0; i < numBotonesVerMas; i++){
        let botonVerMasActual = botonesVerMas[i];
        botonVerMasActual.addEventListener("click", function(){verMasDeSolicitudDeProyecto(botonVerMasActual.id);});
    }
    
    let botonesIniciarProyecto = document.querySelectorAll(".contenedorSolicitud .botonIniciarProyecto");
    let numBotonesIniciarProyecto = botonesIniciarProyecto.length;
    for (let i = 0; i < numBotonesIniciarProyecto; i++){
        let botonIniciarProyectoActual = botonesIniciarProyecto[i];
        botonIniciarProyectoActual.addEventListener("click", function(){aparecerDivOcultoParaForma(botonIniciarProyectoActual.id);});
    }
    
    document.getElementById("divOculto").addEventListener("click", function(){desaparecerDivOculto(event);});
};

function verMasDeSolicitudDeProyecto(idBotonVerMas){
    informarCargaDeSolicitudDeProyectoParaConsulta(idBotonVerMas);
    let idSolicitudDeProyectoAsociada = getIdSolicitudDeProyectoAsociada(idBotonVerMas, "verSolicitud");
    solicitarSolicitudDeProyectoParaConsulta(idSolicitudDeProyectoAsociada);
}

function informarCargaDeSolicitudDeProyectoParaConsulta(idBotonVerMas){
    console.log("Quiero ver los codirectores del botón: " + idBotonVerMas);
    let botonVerMasPresionado = document.getElementById(idBotonVerMas);
    let contenedorSolicitudPorLlenar = botonVerMasPresionado.parentNode;
    
    let atributosExtraSolicitud = contenedorSolicitudPorLlenar.querySelector(".atributosExtraSolicitud");
    atributosExtraSolicitud.innerHTML = "Cargando...";
}

function getIdSolicitudDeProyectoAsociada(idBotonIniciarProyecto, prefijo){
    let indexPrevioANumero = indexDeUltimoCharEnSubstring(idBotonIniciarProyecto, prefijo);
    let idSolicitudDeProyectoAsociada = idBotonIniciarProyecto.substring((indexPrevioANumero + 1), idBotonIniciarProyecto.length);
    return idSolicitudDeProyectoAsociada;
}

function indexDeUltimoCharEnSubstring(string, substring){
    let indexPrimerCharEnSubstring = string.indexOf(substring);
    let desplazamiento = substring.length - 1;
    return indexPrimerCharEnSubstring + desplazamiento;
}

function solicitarSolicitudDeProyectoParaConsulta(idSolicitudDeProyecto){
    const solicitudHTTP = new XMLHttpRequest();
    const rutaAlRecurso = "../AJAXEnviarSolicitudDeProyectoParaConsulta?idSolicitudDeProyecto=" + idSolicitudDeProyecto;
    
    solicitudHTTP.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            let solicitudDeProyectoDevuelta;
            try{
                solicitudDeProyectoDevuelta = JSON.parse(this.responseText);
            } catch(exception){
                solicitudDeProyectoDevuelta = null;
            }
            cargarSolicitudDeProyectoParaConsulta("verSolicitud" + idSolicitudDeProyecto, solicitudDeProyectoDevuelta);
            console.log("El objeto JSON retornado y calculado es:\n" + JSON.stringify(solicitudDeProyectoDevuelta, undefined, 4));
        }
    };
    
    solicitudHTTP.open("GET", rutaAlRecurso);
    solicitudHTTP.send();
}

function cargarSolicitudDeProyectoParaConsulta(idBotonVerMas, solicitudDeProyecto){
    let botonVerMasPresionado = document.getElementById(idBotonVerMas);
    let contenedorSolicitudPorLlenar = botonVerMasPresionado.parentNode;
    let atributosExtraSolicitud = contenedorSolicitudPorLlenar.querySelector(".atributosExtraSolicitud");
    atributosExtraSolicitud.innerHTML = "";
    
    if(solicitudDeProyecto === null || (Object.keys(solicitudDeProyecto).length === 0 && solicitudDeProyecto.constructor === Object)){
        let mensajeDeError = document.createTextNode("Ha ocurrido un error al desplegar la información solicitada. Inténtelo de nuevo o recargue la página.");
        atributosExtraSolicitud.appendChild(mensajeDeError);
    }
    else{
        let fechaDeRealizacionSolicitud = document.createElement("span");
        let negritaFechaDeRealizacionSolicitud = document.createElement("span");
        negritaFechaDeRealizacionSolicitud.className = "atributoSolicitudNegrita";
        let textoNegritaFechaDeRealizacionSolicitud = document.createTextNode("Fecha de realización:");
        negritaFechaDeRealizacionSolicitud.appendChild(textoNegritaFechaDeRealizacionSolicitud);
        fechaDeRealizacionSolicitud.appendChild(negritaFechaDeRealizacionSolicitud);
        let textoFechaDeRealizacionSolicitud = document.createTextNode(" " + solicitudDeProyecto.fechaRealizacion);
        fechaDeRealizacionSolicitud.appendChild(textoFechaDeRealizacionSolicitud);
        atributosExtraSolicitud.appendChild(fechaDeRealizacionSolicitud);

        let institucionSolicitud = document.createElement("span");
        let negritaInstitucionSolicitud = document.createElement("span");
        negritaInstitucionSolicitud.className = "atributoSolicitudNegrita";
        let textoNegritaInstitucionSolicitud = document.createTextNode("Institución:");
        negritaInstitucionSolicitud.appendChild(textoNegritaInstitucionSolicitud);
        institucionSolicitud.appendChild(negritaInstitucionSolicitud);
        let textoInstitucionSolicitud = document.createTextNode(" " + solicitudDeProyecto.institucion);
        institucionSolicitud.appendChild(textoInstitucionSolicitud);
        atributosExtraSolicitud.appendChild(institucionSolicitud);

        let unidadAcademicaSolicitud = document.createElement("span");
        let negritaUnidadAcademicaSolicitud = document.createElement("span");
        negritaUnidadAcademicaSolicitud.className = "atributoSolicitudNegrita";
        let textoNegritaUnidadAcademicaSolicitud = document.createTextNode("Unidad académica:");
        negritaUnidadAcademicaSolicitud.appendChild(textoNegritaUnidadAcademicaSolicitud);
        unidadAcademicaSolicitud.appendChild(negritaUnidadAcademicaSolicitud);
        let textoUnidadAcademicaSolicitud = document.createTextNode(" " + solicitudDeProyecto.unidadAcademica);
        unidadAcademicaSolicitud.appendChild(textoUnidadAcademicaSolicitud);
        atributosExtraSolicitud.appendChild(unidadAcademicaSolicitud);

        let palabrasClaveSolicitud = document.createElement("span");
        let negritaPalabrasClaveSolicitud = document.createElement("span");
        negritaPalabrasClaveSolicitud.className = "atributoSolicitudNegrita";
        let textoNegritaPalabrasClaveSolicitud = document.createTextNode("Palabras clave:");
        negritaPalabrasClaveSolicitud.appendChild(textoNegritaPalabrasClaveSolicitud);
        palabrasClaveSolicitud.appendChild(negritaPalabrasClaveSolicitud);
        let textoPalabrasClaveSolicitud = document.createTextNode(" " + solicitudDeProyecto.palabrasClave);
        palabrasClaveSolicitud.appendChild(textoPalabrasClaveSolicitud);
        atributosExtraSolicitud.appendChild(palabrasClaveSolicitud);
        
        let nuevoBoton = document.createElement("button");
        nuevoBoton.className = "botonVerMenos";
        nuevoBoton.innerHTML = "Ver menos";
        let idSolicitudDeProyectoAsociada = getIdSolicitudDeProyectoAsociada(idBotonVerMas, "verSolicitud");
        nuevoBoton.id = "verMenos" + idSolicitudDeProyectoAsociada;
        nuevoBoton.addEventListener("click", function(){verMenosDeSolicitudDeProyecto(nuevoBoton.id);});

        contenedorSolicitudPorLlenar.replaceChild(nuevoBoton, botonVerMasPresionado);
    }
}

function verMenosDeSolicitudDeProyecto(idBotonVerMenos){
    let botonVerMenosPresionado = document.getElementById(idBotonVerMenos);
    let contenedorSolicitud = botonVerMenosPresionado.parentNode;
    let atributosExtraSolicitud = contenedorSolicitud.querySelector(".atributosExtraSolicitud");
    atributosExtraSolicitud.style.display = "none";
    
    let nuevoBotonVerMas = document.createElement("button");
    nuevoBotonVerMas.className = "botonVerMas";
    nuevoBotonVerMas.innerHTML = "Ver más...";
    let idSolicitudDeProyectoAsociada = getIdSolicitudDeProyectoAsociada(idBotonVerMenos, "verMenos");
    nuevoBotonVerMas.id = "verSolicitudOffline" + idSolicitudDeProyectoAsociada;
    nuevoBotonVerMas.addEventListener("click", function(){verMasDeSolicitudDeProyectoOffline(nuevoBotonVerMas.id);});
    
    contenedorSolicitud.replaceChild(nuevoBotonVerMas, botonVerMenosPresionado);
}

function verMasDeSolicitudDeProyectoOffline(idBotonVerMas){
    let botonVerMasPresionado = document.getElementById(idBotonVerMas);
    let contenedorSolicitud = botonVerMasPresionado.parentNode;
    let atributosExtraSolicitud = contenedorSolicitud.querySelector(".atributosExtraSolicitud");
    atributosExtraSolicitud.style.display = "block";
    
    let nuevoBoton = document.createElement("button");
    nuevoBoton.className = "botonVerMenos";
    nuevoBoton.innerHTML = "Ver menos.";
    let idSolicitudDeProyectoAsociada = getIdSolicitudDeProyectoAsociada(idBotonVerMas, "verSolicitudOffline");
    nuevoBoton.id = "verMenos" + idSolicitudDeProyectoAsociada;
    nuevoBoton.addEventListener("click", function(){verMenosDeSolicitudDeProyecto(nuevoBoton.id);});
    
    contenedorSolicitud.replaceChild(nuevoBoton, botonVerMasPresionado);
}

function aparecerDivOcultoParaForma(idBotonIniciarProyecto){
    informarCargaDeFormaDeAltaDeProyectoMedianteSolicitud();
    document.getElementById("divOculto").style.display = "flex";
    let idSolicitudDeProyectoAsociada = getIdSolicitudDeProyectoAsociada(idBotonIniciarProyecto, "iniciarProyectoConSolicitud");
    solicitarSolicitudDeProyectoParaForma(idSolicitudDeProyectoAsociada);
}

function informarCargaDeFormaDeAltaDeProyectoMedianteSolicitud(){
    let divFormaDeAltaDeProyectoMedianteSolicitud = document.getElementById("divFormaDeAltaDeProyectoMedianteSolicitud");
    divFormaDeAltaDeProyectoMedianteSolicitud.innerHTML = "Cargando. Espere...";
}

function solicitarSolicitudDeProyectoParaForma(idSolicitudDeProyecto){
    const solicitudHTTP = new XMLHttpRequest();
    const rutaAlRecurso = "../AJAXEnviarSolicitudDeProyectoParaFormaDeAltaDeProyecto?idSolicitudDeProyecto=" + idSolicitudDeProyecto;
    
    solicitudHTTP.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            let solicitudDeProyectoDevuelta;
            try{
                solicitudDeProyectoDevuelta = JSON.parse(this.responseText);
            } catch(exception){
                solicitudDeProyectoDevuelta = null;
            }
            cargarFormaDeAltaDeProyectoMedianteSolicitud(solicitudDeProyectoDevuelta);
            console.log("El objeto JSON retornado y calculado es:\n" + JSON.stringify(solicitudDeProyectoDevuelta, undefined, 4));
        }
    };
    
    solicitudHTTP.open("GET", rutaAlRecurso);
    solicitudHTTP.send();
}

function cargarFormaDeAltaDeProyectoMedianteSolicitud(solicitudDeProyecto){
    let divFormaDeAltaDeProyectoMedianteSolicitud = document.getElementById("divFormaDeAltaDeProyectoMedianteSolicitud");
    divFormaDeAltaDeProyectoMedianteSolicitud.innerHTML = "";
    
    if(solicitudDeProyecto === null || (Object.keys(solicitudDeProyecto).length === 0 && solicitudDeProyecto.constructor === Object)){
        let mensajeDeError = document.createTextNode("Ha ocurrido un error al desplegar la información solicitada. Inténtelo de nuevo o recargue la página.");
        divFormaDeAltaDeProyectoMedianteSolicitud.appendChild(mensajeDeError);
    }
    else{
        let tituloForma = document.createElement("p");
        tituloForma.className = "tituloForma";
        let textoTituloForma = document.createTextNode(solicitudDeProyecto.titulo);
        tituloForma.append(textoTituloForma);
        divFormaDeAltaDeProyectoMedianteSolicitud.appendChild(tituloForma);

        let indicacion = document.createElement("p");
        indicacion.className = "descForma";
        let textoIndicacion = document.createTextNode("Asigne un identificador a la solicitud. Después, podrá iniciarla como proyecto.");
        indicacion.append(textoIndicacion);
        divFormaDeAltaDeProyectoMedianteSolicitud.appendChild(indicacion);

        let formaDeAltaDeProyectoMedianteSolicitud = document.createElement("form");
        formaDeAltaDeProyectoMedianteSolicitud.action = "indefinido";
        formaDeAltaDeProyectoMedianteSolicitud.method = "post";
        formaDeAltaDeProyectoMedianteSolicitud.target = "_self";

        let etiquetaIdentificador = document.createElement("label");
        etiquetaIdentificador.htmlFor = "campoIdentificador";
        let textoEtiquetaIdentificador = document.createTextNode("Identificador:");
        etiquetaIdentificador.appendChild(textoEtiquetaIdentificador);
        formaDeAltaDeProyectoMedianteSolicitud.appendChild(etiquetaIdentificador);

        let campoIdentificador = document.createElement("input");
        campoIdentificador.type = "text";
        campoIdentificador.name = "identificador";
        campoIdentificador.id = "campoIdentificador";
        campoIdentificador.required = "required";
        formaDeAltaDeProyectoMedianteSolicitud.appendChild(campoIdentificador);

        let campoIdSolicitudDeProyectoDevuelta = document.createElement("input");
        campoIdSolicitudDeProyectoDevuelta.type = "hidden";
        campoIdSolicitudDeProyectoDevuelta.name = "idSolicitudDeProyecto";
        campoIdSolicitudDeProyectoDevuelta.value = solicitudDeProyecto.idSolicitudProyecto;
        formaDeAltaDeProyectoMedianteSolicitud.appendChild(campoIdSolicitudDeProyectoDevuelta);

        let botonSubmit = document.createElement("input");
        botonSubmit.type = "submit";
        botonSubmit.value = "Iniciar proyecto";
        formaDeAltaDeProyectoMedianteSolicitud.appendChild(botonSubmit);
        
        divFormaDeAltaDeProyectoMedianteSolicitud.appendChild(formaDeAltaDeProyectoMedianteSolicitud);
    }
}

function desaparecerDivOculto(event){
    var fondoDiv = document.getElementById("divOculto");
    if(event.target === fondoDiv)
        fondoDiv.style.display = "none";
}