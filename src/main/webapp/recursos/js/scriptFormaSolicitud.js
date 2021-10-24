let numAutores, numCodirectores, numPalabrasClave;
numAutores = numCodirectores = numPalabrasClave = 1;

window.onload = function(){
    document.getElementById("botonMasAutores").addEventListener("click", function(){agregarAutor();});
    document.getElementById("botonMasCodirectores").addEventListener("click", function(){agregarCodirector();});
    document.getElementById("botonMasPalabrasClave").addEventListener("click", function(){agregarPalabraClave();});
};

function agregarAutor(){
    numAutores++;
    
    let nuevoCampo = document.createElement("input");
    nuevoCampo.type = "text";
    nuevoCampo.name = "autor" + numAutores;
    nuevoCampo.required = "required";
    
    let nuevoBoton = document.createElement("button");
    nuevoBoton.type = "button";
    let textoEquis = document.createTextNode("✕");
    nuevoBoton.appendChild(textoEquis);
    nuevoBoton.addEventListener("click", function(){quitarAutor(event);});
    
    let nuevoCampoConBoton = document.createElement("div");
    nuevoCampoConBoton.classList.add("campoConBoton");
    nuevoCampoConBoton.appendChild(nuevoCampo);
    nuevoCampoConBoton.appendChild(nuevoBoton);
    
    let camposAutores = document.getElementById("camposAutores");
    camposAutores.appendChild(nuevoCampoConBoton);
}

function quitarAutor(event){
    numAutores--;
    let botonClicado = event.target;
    let campoConBotonRelacionado = botonClicado.parentElement;
    campoConBotonRelacionado.remove();
    
    if(numAutores !== 1){
        let camposAutores = document.getElementById("camposAutores");
        let camposConBoton = camposAutores.children;
        let numCamposConBoton = camposConBoton.length;
        for (let i = 1; i < numCamposConBoton; i++)
            camposConBoton[i].querySelector("input").name = "autor" + (i+1);
    }
}

function agregarCodirector(){
    numCodirectores++;
    
    let nuevoCampo = document.createElement("input");
    nuevoCampo.type = "text";
    nuevoCampo.name = "codirector" + numCodirectores;
    nuevoCampo.required = "required";
    
    let nuevoBoton = document.createElement("button");
    nuevoBoton.type = "button";
    let textoEquis = document.createTextNode("✕");
    nuevoBoton.appendChild(textoEquis);
    nuevoBoton.addEventListener("click", function(){quitarCodirector(event);});
    
    let nuevoCampoConBoton = document.createElement("div");
    nuevoCampoConBoton.classList.add("campoConBoton");
    nuevoCampoConBoton.appendChild(nuevoCampo);
    nuevoCampoConBoton.appendChild(nuevoBoton);
    
    let camposCodirectores = document.getElementById("camposCodirectores");
    camposCodirectores.appendChild(nuevoCampoConBoton);
}

function quitarCodirector(event){
    numCodirectores--;
    let botonClicado = event.target;
    let campoConBotonRelacionado = botonClicado.parentElement;
    campoConBotonRelacionado.remove();
    
    if(numCodirectores !== 1){
        let camposCodirectores = document.getElementById("camposCodirectores");
        let camposConBoton = camposCodirectores.children;
        let numCamposConBoton = camposConBoton.length;
        for (let i = 1; i < numCamposConBoton; i++)
            camposConBoton[i].querySelector("input").name = "codirector" + (i+1);
    }
}

function agregarPalabraClave(){
    numPalabrasClave++;
    
    let nuevoCampo = document.createElement("input");
    nuevoCampo.type = "text";
    nuevoCampo.name = "palabraClave" + numPalabrasClave;
    nuevoCampo.required = "required";
    
    let nuevoBoton = document.createElement("button");
    nuevoBoton.type = "button";
    let textoEquis = document.createTextNode("✕");
    nuevoBoton.appendChild(textoEquis);
    nuevoBoton.addEventListener("click", function(){quitarPalabraClave(event);});
    
    let nuevoCampoConBoton = document.createElement("div");
    nuevoCampoConBoton.classList.add("campoConBoton");
    nuevoCampoConBoton.appendChild(nuevoCampo);
    nuevoCampoConBoton.appendChild(nuevoBoton);
    
    let camposPalabrasClave = document.getElementById("camposPalabrasClave");
    camposPalabrasClave.appendChild(nuevoCampoConBoton);
}

function quitarPalabraClave(event){
    numPalabrasClave--;
    let botonClicado = event.target;
    let campoConBotonRelacionado = botonClicado.parentElement;
    campoConBotonRelacionado.remove();
    
    if(numPalabrasClave !== 1){
        let camposPalabrasClave = document.getElementById("camposPalabrasClave");
        let camposConBoton = camposPalabrasClave.children;
        let numCamposConBoton = camposConBoton.length;
        for (let i = 1; i < numCamposConBoton; i++)
            camposConBoton[i].querySelector("input").name = "palabraClave" + (i+1);
    }
}