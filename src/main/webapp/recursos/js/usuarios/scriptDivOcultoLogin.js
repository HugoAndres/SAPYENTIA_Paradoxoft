function cargarDivOculto(){
    document.querySelector("header nav button").addEventListener("click", function(){aparecerDivOculto();});
    document.getElementById("divOculto").addEventListener("click", function(){desaparecerDivOculto(event);});
}

function aparecerDivOculto(){
    document.getElementById("divOculto").style.display = "flex";
}

function desaparecerDivOculto(event){
    var fondoDiv = document.getElementById("divOculto");
    if(event.target === fondoDiv)
        fondoDiv.style.display = "none";
}