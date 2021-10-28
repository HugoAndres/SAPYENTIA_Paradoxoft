const dropArea = document.querySelector(".drag-area"),
dragText = dropArea.querySelector("header"),
button = dropArea.querySelector("button"),
input = dropArea.querySelector("input");
let file; //this is a global variable and we'll use it inside multiple functions
button.onclick = ()=>{
  input.click(); //if user click on the button then the input also clicked
}
input.addEventListener("change", function(){
  file = this.files[0];

  dropArea.classList.add("active");
  showFile(); 
});
dropArea.addEventListener("dragover", (event)=>{
  event.preventDefault(); 
  dropArea.classList.add("active");
  dragText.textContent = "Suelte para subir archivo";
});
dropArea.addEventListener("dragleave", ()=>{
  dropArea.classList.remove("active");
  dragText.textContent = "Arrastre y suelte";
});

dropArea.addEventListener("drop", (event)=>{
  event.preventDefault(); 
  file = event.dataTransfer.files[0];
  showFile(); 
});
function showFile(){
  let fileType = file.type; 
  let validExtensions = ["application/pdf"]; 
  if(validExtensions.includes(fileType)){ 
    let fileReader = new FileReader();
    fileReader.onload = ()=>{
      dropArea.innerHTML ="Se ha cargado: "+ file.name; //adding that created img tag inside dropArea container
    };
    //fileReader.readAsDataURL(file);
  }else{
    alert("No es pdf");
    dropArea.classList.remove("active");
    dragText.textContent = "Drag & Drop to Upload File";
  }
}