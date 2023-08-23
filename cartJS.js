


 let  mainPost  =document.querySelector("main");
 let  userLogin = false;
 let    purchase  = document.querySelector("#purchase");
 let account = document.querySelector("#account");
 let  login  = document.querySelector("#login");


 function removePost(element){
     let parent = element.parentElement;
mainPost.removeChild(parent);


 }

 
 async function removeCart(item, source){
	 let url = new URLSearchParams();
	 url.append('item',item);


     try{

		let response = await fetch("CartServlet?"+url.toString(), {

			method:"DELETE",
			credentials:"same-origin",
			
		});


     if(response.ok){
      
        removePost(source);

     }




	} catch(error){

    console.log('problem');

	}

}
 
 
 
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
	     	 let remove = document.createElement("p");
	     	 remove.className='remove';
	     	 remove.textContent ="remove";
	      let  content = post.split(',');
	      console.log(content);

	           postUi.dataset['productId'] = content[0];
	           img.style.width='25vw';
	           img.style.height='15vw';
	           img.src = 'ImageServlet?id='+content[0];
	           
	           remove.dataset['productId'] = content[0];
	           carName.textContent= content[1];
	           model.textContent = content[2];
	           price.textContent =  content[3]+"ksh";
	          remove.addEventListener("click",(e)=>{
	        	  removeCart(e.target.dataset['productId'], e.target);
	        	  
	        	  
	          });

	           postUi.appendChild(img);
	           postUi.appendChild(carName);
	           postUi.appendChild(model);
	           postUi.appendChild(price);
                postUi.appendChild(remove);
	       mainPost.appendChild(postUi);
	       }
		}
	       catch(error){
	    	   
	    	   
	    	   console.log(error);
	       }




} 

async function getPost(){


 	try{

 		let response = await fetch("CartServlet", {

 			method:"GET",credentials:"same-origin"
 		});


     if(response.ok){


     


          let result  = await response.text();

          uiPost(result);

}   else {
  mainPost.textContent ="no cart";


}


 	} catch(error){

        console.log('problem');

 	}
 }



 

getPost();






async function checkLogin(){
	let url = new URLSearchParams();
	url.append("sessionName","account");
	
	try{
		
		let response = await fetch("SessionServlet?"+url.toString(),{
			method:"GET",credentials:"same-origin"
		});
		
		if(response.ok){
			
			
			login.hidden = true;
			account.hidden = false;
			userLogin = true;
			
		}
		
		
	} catch(error) {
	  console.log("session error");
		
		}
	
}
checkLogin();


login.addEventListener("click",(e)=>{ window.location.href="loginSignin.html"});
account.addEventListener("click",(e)=>{ window.location.href="Account.html"});






async function makePurchase(){
	let url = new URLSearchParams();
	url.append("purchase","account");
       
       try{


        let response  = fetch("CartServlet", {method:"POST",credentials:"same-origin", body:url});

        purchase.textContent ="Purchase Successful";
        setTimeout(()=>{ purchase.textContent ="Purchase"},3000);


       }catch (e){
    	   console.log(e);

}

}

function handlePurchase(event){

        if(userLogin){



         makePurchase();



        } else {

        purchase.textContent = "Login First to purchase";
        setTimeout(()=>{  purchase.textContent = "purchase"},5000);

        }


}
purchase.addEventListener('click',handlePurchase);

 