


let name = document.querySelector("#name");
let id = document.querySelector("#accountId");
let email  = document.querySelector("#email");
let reveal = document.querySelector("button.reveal");

let history = document.querySelector("section.history");
   



 function populate(text){


 	let list = text.split(",");
 	id.dataset["id"] = list[0];
   name.textContent = list[1];
   email.textContent = list[2];


 }






async function fetchDetails(){
	let url = new URLSearchParams();
	url.append("action","user");


try {
     

     let response = await fetch("UserServlet?"+url.toString(),{method:"GET", credentials:"same-origin"});

     if(response.ok){

     	  let text =  await response.text();

           populate(text);



     } else {

       name.textContent = "Not account or not yet logged in";


     }

   

} catch (error){






}
    

}

fetchDetails();

function historyList(text){
	//check for pop

let  list  = text.split(',');
  list.pop();
  for(let x of list){
  	let p = document.createElement("p");
  	p.className ='list';
  	p.textContent =x;
  	history.appendChild(p);


  }





}


async function fetchHistory(){
	let url = new URLSearchParams();
	url.append("action","history");


try {
     

     let response = await fetch("UserServlet?"+url.toString(),{method:"GET", credentials:"same-origin"});

     if(response.ok){

     	  let text =  await response.text();
         historyList(text);
           



     } else {

       history.textContent = "Not purchased product";


     }

   

} catch (error){






}
    

}
fetchHistory();


let x = true;

reveal.addEventListener("click",(e)=>{ 
	if(x==true){

		id.textContent = id.dataset["id"];
		x = false;
		e.target.textContent = 'hide'; 
	} else {
		id.textContent = '***********';
		x= true;
		e.target.textContent = "reveal";

	}


           });
















