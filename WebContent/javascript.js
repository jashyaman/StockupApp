function validateForm() {
    var fname = document.forms["UserRegistration"]["firstname"].value;
    var lname = document.forms["UserRegistration"]["lastname"].value;
    var uname = document.forms["UserRegistration"]["username"].value; 
    var pass = document.forms["UserRegistration"]["password"].value; 
    var cpass = document.forms["UserRegistration"]["cpassword"].value;
    if(cpass != pass)
    if (fname == null || fname == "" || lname == null || lname == "" || 
    		uname == null || uname == "" || pass == null || pass == "") {
        alert("Name must be filled out");
        return false;
    }else
    	{
    		alert("all done");
    		return true;
    	}
}