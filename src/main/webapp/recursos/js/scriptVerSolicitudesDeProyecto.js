window.onload = function (){
    let botonesForma = document.querySelectorAll(".contenedorSolicitud button");
    let numBotonesForma = botonesForma.length;
    for (let i = 0; i < numBotonesForma; i++)
        botonesForma[i].addEventListener("click", function(){aparecerDivOculto();});
    document.getElementById("divOculto").addEventListener("click", function(){desaparecerDivOculto(event);});
};

function aparecerDivOculto(){
    document.getElementById("divOculto").style.display = "flex";
}

function desaparecerDivOculto(event){
    var fondoDiv = document.getElementById("divOculto");
    if(event.target === fondoDiv)
        fondoDiv.style.display = "none";
}