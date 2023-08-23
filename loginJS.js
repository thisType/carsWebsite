


let formSignup = document.querySelector("#signup");
let formLogin = document.querySelector('#login');
let messages = document.querySelector("p.messages");
let  account = document.querySelector("#account");




async function signup(){
   
	let url = new URLSearchParams();
	url.append("firstName",formSignup.elements.firstName.value);
	url.append("secondName",formSignup.elements.secondName.value);
	url.append("email",formSignup.elements.email.value);
	url.append("password",formSignup.elements.password.value);
	
	
	
	
 try{
      
      let response  = await fetch('FormServlet',{method:'POST',credentials:"same-origin",body:url});


      if(response.ok){

messages.textContent =" Account Created Successfully.Your are not yet logged in.Login to access  capabilities such as purchase";


      } else {


      	message.textContent = "Account creation error";
      }


 } catch(error){
 	messages.textContent = "submission failed";


 }


}
async function  login(){
	let url = new URLSearchParams();
	url.append("email",formLogin.elements.email.value);
	url.append("password",formLogin.elements.password.value);

 try{
      
      let response  = await fetch('FormServlet?'+url.toString(),{method:'GET',credentials:"same-origin"});


      if(response.ok){

messages.textContent =" Login successful. In all your session.";
account.className ='Enabled';
  account.disabled = false;


      } else {
 messages.textContent ='Login failed. Create an account';


      }


 } catch(error){

  messages.textContent = "submission failed";
  console.log(error);
 	
 }


}


formSignup.addEventListener("submit", (event)=> {  event.preventDefault(); messages.textContent= "Submiitting.......";  signup(); });
formLogin.addEventListener("submit", (event)=>{  event.preventDefault(); messages.textContent = "Submitting.......";   login();});
account.addEventListener("click",(event)=>{
          window.location.href ='Account.html';
});

async function checkLogin(){
	let url = new URLSearchParams();
	url.append("sessionName","account");
	
	try{
		
		let response = await fetch("SessionServlet?"+url.toString(),{
			method:"GET",credentials:"same-origin"
		});
		
		if(response.ok){
			
			
			messages.textContent ="Your are loggen in";
			
			
		}
		
		
	} catch(error) {
	  console.log("session error");
		
		}
	
}
checkLogin();



