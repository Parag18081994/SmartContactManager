console.log("this is script file")

const toggleSidebar=()=>{
  if($('.sidebar').is(":visible"))
  {
  //true
  //close it
 $(".sidebar").css("display","none");
 $(".content").css("margin-left","0%");
  }
  else{
  //show karna hai

 $(".sidebar").css("display","block");
 $(".content").css("margin-left","20%");
  }


};