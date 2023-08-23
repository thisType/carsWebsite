 let url  = new Map();
url.set('cart','Cart.html');
url.set('viewProduct','viewProduct.html');
url.set('account','Account.html');
url.set('quickData','quickServlet');
url.set('login','loginSignin.html');
url.set('imageUrl','ImageServlet');
url.set('servlet','postServlet');


let  searchButton  = document.querySelector("#search");
let login  =  document.querySelector("#login");
let account  = document.querySelector("#account");
let cart = document.querySelector("#cart");
let inputSearch = document.querySelector("#searchInput");
let  mainPost  =document.querySelector("main");



async function identifyProduct(productID){
	let url = new URLSearchParams();
	url.append('item',productID);
	 
	 
	 try{
		 
		 
		 let response = await fetch('postServlet',{method:"POST",
			 credentials:"same-origin",body:url});
		 
		 if(response.ok){
			 
			 window.location.href= "ViewProduct.html"; 
			 
		 }
		 
		 
		 
		 
	 }catch(error){
		 }
	 }


// fetching postData

function uiPost(postData){
	try{
       console.log(postData);
       let  posts  = String(postData).split('.');
       posts.pop();
       console.log(posts);
     for(let post of posts){
     	let postUi= document.createElement("section");
     	 postUi.className ='post';
          let img = document.createElement("img");
     	 let carName = document.createElement("p");
     	 let model = document.createElement("p");
     	 let price  = document.createElement("p");
     	 price.className='price';
      let  content = post.split(',');
      console.log(content);

           postUi.dataset['productId'] = content[0];
           img.style.width='25vw';
           img.style.height='15vw';
           img.src = 'ImageServlet?id='+content[0];
           img.dataset['productId'] = content[0];
           img.addEventListener("click",(e)=>{identifyProduct(e.target.dataset["productId"]); console.log(e.target.dataset["productId"]);});

           carName.textContent= content[1];
           model.textContent = content[2];
           price.textContent =  content[3]+"ksh";
           

           postUi.appendChild(img);
           postUi.appendChild(carName);
           postUi.appendChild(model);
           postUi.appendChild(price);

       mainPost.appendChild(postUi);
       }
	}
       catch(error){
    	   
    	   
    	   console.log(error);
       }


} 





 async function getPost(a,b){

let url = new URLSearchParams();
url.append('search',a);
url.append("searchValue",b);

 	try{

 		let response = await fetch('postServlet?'+ url.toString(), {
 			method:"GET",credentials:"same-origin"
 		});


          let result  = await response.text();
          uiPost(result);




 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }
 getPost("all","all");
 
 
 
  
  
 //session
  
  
async function checkLogin(){
	let url = new URLSearchParams();
	url.append("sessionName","account");
	
	try{
		
		let response = await fetch("SessionServlet?"+url.toString(),{
			method:"GET",credentials:"same-origin"
		});
		
		if(response.ok){
			
			console.log('login');
			login.hidden = true;
			account.hidden = false;
			
			
		}
		
		
	} catch(error) {
	  console.log("session error");
		
		}
	
}
checkLogin();

login.addEventListener("click",(e)=>{ window.location.href="loginSignin.html"});
account.addEventListener("click",(e)=>{ window.location.href="Account.html"});
cart.addEventListener("click",(e)=>{ window.location.href="Cart.html"});

function removeCreate(){
	document.body.removeChild(mainPost);
   document.body.appendChild(document.createElement("main"));
   
	
	
}

let btns = document.querySelectorAll("p.listing button");
btns[0].disable = true;
function navListener(event){
	if(event.target.textContent.localeCompare("all")==0){
	removeCreate();
		mainPost = document.querySelector("main");
		getPost("all",event.target.textContent);
	}  else {
		 btns[0].disable = false;
		 removeCreate();
			mainPost = document.querySelector("main");
			getPost("type",event.target.textContent);
		
		
	}
	}


 for(let x of btns){
	 
	 x.addEventListener("click",navListener);
 }

function userSearch(event){
	 if(inputSearch.value.length >1){
		 
		removeCreate();
		mainPost = document.querySelector("main");
		getPost("search",inputSearch.value);
		 
		 }    
	
}
searchButton.addEventListener("click",userSearch);



 

 