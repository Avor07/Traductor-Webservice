 function Hablar(message){
  var msg = new SpeechSynthesisUtterance(message);
  var voices = window.speechSynthesis.getVoices();
  msg.voice = voices[0];
  window.speechSynthesis.speak(msg);
}
function Sumar(){
    var n1=document.getElementById("nro1").value;
    var n2=document.getElementById("nro2").value;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/client/calculadora";
    var dataObject = JSON.stringify({
        'numero1': n1,
        'numero2': n2,
    });
    var params=dataObject;
    xhr.open("POST", url,true);
    xhr.setRequestHeader("Content-Type", "application /x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            var respuesta =JSON.parse(xhr.responseText);
            document.getElementById("pro").value=respuesta.Suma;
            document.getElementById("respuesta").innerHTML+=respuesta.Traduccion;
            Hablar("The answer is:"+respuesta.Traduccion);
        }
    };
    xhr.send(params);  
}
function TraducirNro1(){
    var n1=document.getElementById("nro1").value;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/client/traduccion";
    var dataObject = JSON.stringify({
        'numero1': n1,
    });
    var params=dataObject;
    xhr.open("POST", url,true);
    xhr.setRequestHeader("Content-Type", "application /x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            var respuesta =xhr.responseText;
            document.getElementById("n1").innerHTML=respuesta;
            Hablar(respuesta);
        }
    };
    xhr.send(params);  
}
function TraducirNro2(){
    var n2=document.getElementById("nro2").value;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/client/traduccionNro2";
    var dataObject = JSON.stringify({
        'numero2': n2,
    });
    var params=dataObject;
    xhr.open("POST", url,true);
    xhr.setRequestHeader("Content-Type", "application /x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            var respuesta =xhr.responseText;
            document.getElementById("n2").innerHTML=respuesta;
            Hablar(respuesta);
        }
    };
    xhr.send(params);  
}

