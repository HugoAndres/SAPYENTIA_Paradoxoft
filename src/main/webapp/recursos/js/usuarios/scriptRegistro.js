window.onload = function(){
    cargarDivOculto();
    document.querySelector("select").addEventListener("change", cambiarCampos);
};

function cambiarCampos(){
    let valorActual = document.querySelector("select").value;
    if(valorActual === "alumno"){
        ocultarEtiqueta("labelAcademia");
        ocultarCampo("comboAcademia");
        ocultarEtiqueta("labelGrado");
        ocultarCampo("campoGrado");
        
        mostrarEtiqueta("labelCarrera");
        mostrarCampo("campoCarrera");
    }
    else
        if(valorActual === "docente"){
            ocultarEtiqueta("labelCarrera");
            ocultarCampo("campoCarrera");
            
            mostrarEtiqueta("labelAcademia");
            mostrarCampo("comboAcademia");
            mostrarEtiqueta("labelGrado");
            mostrarCampo("campoGrado");
        }
        else{
            ocultarEtiqueta("labelCarrera");
            ocultarCampo("campoCarrera");
            ocultarEtiqueta("labelAcademia");
            ocultarCampo("comboAcademia");
            ocultarEtiqueta("labelGrado");
            ocultarCampo("campoGrado");
        }
}

function ocultarEtiqueta(idElemento){
    let etiquetaPorOcultar = document.getElementById(idElemento);
    if(etiquetaPorOcultar.style.display !== "none")
        etiquetaPorOcultar.style.display = "none";
}

function ocultarCampo(idCampo){
    let campoPorOcultar = document.getElementById(idCampo);
    
    if(campoPorOcultar.style.display !== "none")
        campoPorOcultar.style.display = "none";
        
    if(campoPorOcultar.hasAttribute("required"))
        campoPorOcultar.removeAttribute("required");
}

function mostrarEtiqueta(idEtiqueta){
    let elementoPorMostrar = document.getElementById(idEtiqueta);
    if(elementoPorMostrar.style.display !== "block")
        elementoPorMostrar.style.display = "block";
}

function mostrarCampo(idCampo){
    let campoPorMostrar = document.getElementById(idCampo);
    
    if(campoPorMostrar.style.display !== "block")
        campoPorMostrar.style.display = "block";
        
    if(!campoPorMostrar.hasAttribute("required"))
        campoPorMostrar.required = "required";
}