
let login  =  document.querySelector("#login");
let account  = document.querySelector("#account");
let cart  = document.querySelector("#cart");


function openCart(e){
  window.location.href= "Cart.html";


}
function openLogin(event){

        window.location.href= "loginSignin.html";


}
function openAccount(e){


    window.location.href = "Account.html";
}



cart.addEventListener('click',openCart);
login.addEventListener('click',openLogin);
account.addEventListener('click', openAccount);

  
  let carName = document.querySelector('#carName');
  let carBrand =  document.querySelector('#carBrand');
  let carModel = document.querySelector('#carModel');
let   carCategory = document.querySelector('#carCategory');
let carDescription = document.querySelector('#carDescription');
let  carPrice = document.querySelector('#carPrice');

let mainImage = document.querySelector('img.main');
let imageHolder = document.querySelector('p.imageHolder');

let images = document.querySelectorAll('img.select');



let idMap = new Map();
 
 function uiPropagate(data){
     
let dataSplit = data.split(',');

idMap.set('id',dataSplit[0]);

carName.textContent = dataSplit[1];
carBrand.textContent = dataSplit[2];
carModel.textContent = dataSplit[3];
carCategory.textContent = dataSplit[4];
carDescription.textContent = dataSplit[5];
carPrice.textContent = dataSplit[6]+"ksh";
let one = new URLSearchParams();
one.append("id",dataSplit[0]);
 mainImage.src= 'ImageServlet?'+one.toString();
 let two =new URLSearchParams();
 two.append("id",dataSplit[7]);
images[0].src ='ImageServlet?'+  two.toString();
let three =new URLSearchParams();
three.append("id",dataSplit[8]);
images[1].src = 'ImageServlet?'+  three.toString();
let four =new URLSearchParams();
four.append("id",dataSplit[9]);
images[2].src = 'ImageServlet?'+  four.toString();

}




async function fetchCarDetails(){



        try{
            let response  = await fetch('ProductServlet',{method:"GET",credentials:"same-origin"});

            let text = await response.text();
            console.log(text);
            uiPropagate(text);






        }catch(error){
 }
}

fetchCarDetails();
//called when select images are clicked
function selectImages(event){

      
	mainImage = document.querySelector('img.main');
    let newE = event.target.cloneNode(false);
    newE.className = 'main';
    imageHolder.removeChild(mainImage);
    imageHolder.appendChild(newE);


}


images[0].addEventListener('click',selectImages);
images[1].addEventListener('click',selectImages);
images[2].addEventListener('click', selectImages);




//remaining adding to cart 
 
let addToCart =  document.querySelector("h4.add");



async function addCart(){
	let url = new URLSearchParams();
	url.append('cart',idMap.get("id"));

    try{


    	let response  = await  fetch('ProductServlet',{ method:"POST",credentials:"same-origin",body:url});

    	if(response.ok){

         addToCart.textContent="added";
         
         setTimeout(()=>{ addToCart.textContent="Add to Cart"; },3000);

    		

    	}



    }catch(error){




    }



}

addToCart.addEventListener('click',(e)=>{ 
  addCart();
});


 function  handlelogin(){
           account.hidden = false;
           login.hidden = true;
            

       }

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
				
				
			}
			
			
		} catch(error) {
		  console.log("session error");
			
			}
		
	}
	checkLogin();

 