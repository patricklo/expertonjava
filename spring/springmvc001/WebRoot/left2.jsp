<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>JS+CSS打造三级折叠菜单，自动收缩其它级</title>
<style>
*,body,ul,h1,h2{ margin:0; padding:0; list-style:none;}
body{font:12px "宋体"; padding-top:20px;}
a{ color:#777;border:none;}
#menu { width:200px; margin:auto;}
 #menu h1 { font-size:12px; border:#C60 1px solid; margin-top:1px;  background-color:#F93;}
 #menu h2 { font-size:12px; border:#E7E7E7 1px solid; border-top-color:#FFF; background-color:#F4F4F4;}
 #menu ul { padding-left:15px; height:100px;border:#E7E7E7 1px solid; border-top:none;overflow:auto;}
 #menu a { display:block; padding:5px 0 3px 10px; text-decoration:none; overflow:hidden;}
 #menu a:hover{ color:#6F0; background:#000;}
 #menu .no {display:none;}
 #menu .h1 a{color:#6F0;}
 #menu .h2 a{color:#06F;}
 #menu  h1 a{color:#FFF;}
</style>
<script language="JavaScript">
<!--//
function ShowMenu(obj,n){
 var Nav = obj.parentNode;
 if(!Nav.id){
  var BName = Nav.getElementsByTagName("ul");
  var HName = Nav.getElementsByTagName("h2");
  var t = 2;
 }else{
  var BName = document.getElementById(Nav.id).getElementsByTagName("span");
  var HName = document.getElementById(Nav.id).getElementsByTagName("h1");
  var t = 1;
 }
 for(var i=0; i<HName.length;i++){
  HName[i].innerHTML = HName[i].innerHTML.replace("-","+");
  HName[i].className = "";
 }
 obj.className = "h" + t;
 for(var i=0; i<BName.length; i++){if(i!=n){BName[i].className = "no";}}
 if(BName[n].className == "no"){
  BName[n].className = "";
  obj.innerHTML = obj.innerHTML.replace("+","-");
 }else{
  BName[n].className = "no";
  obj.className = "";
  obj.innerHTML = obj.innerHTML.replace("-","+");
 }
}
//-->
</script>
</head>
<body>
<div id="menu">
 <h1 onClick="javascript:ShowMenu(this,0)"><a href="javascript:void(0)">+ Product and Control</a></a></h1>
 <span class="no">
  <h2 onClick="javascript:ShowMenu(this,0)"><a href="javascript:void(0)">+ LS Product Control</a></a></h2>
  <ul class="no">
   <a href="javascript:void(0)">Maintenance</a>
   <a href="javascript:void(0)">Approval</a>
   <a href="javascript:void(0)">Enquiry</a>
  </ul>
  <h2 onClick="javascript:ShowMenu(this,1)"><a href="javascript:void(0)">+ LS Sub Product Control</a></h2>
  <ul class="no">
   <a href="javascript:void(0)">Maintenance</a>
   <a href="javascript:void(0)">Approval</a>
   <a href="javascript:void(0)">Enquiry</a>
  </ul>
  <h2 onClick="javascript:ShowMenu(this,2)"><a href="javascript:void(0)">+ LS Repayment Scheme Control</a></h2>
  <ul class="no">
   <a href="javascript:void(0)">Maintenance</a>
   <a href="javascript:void(0)">Approval</a>
  </ul>
 </span>
        
 <h1 onClick="javascript:ShowMenu(this,1)"><a href="javascript:void(0)">+ Account & Schedule</a></h1>
 <span class="no">
  <h2 onClick="javascript:ShowMenu(this,0)"><a href="javascript:void(0)">+ LS Account Master</a></h2>
  <ul class="no">
    <a href="javascript:void(0)">Maintenance</a>
    <a href="javascript:void(0)">Approval</a>
  </ul>
  <h2 onClick="javascript:ShowMenu(this,1)"><a href="javascript:void(0)">+ LS Loan Scheduler</a></h2>
  <ul class="no">
   <a href="javascript:void(0)">Maintenance</a>
   <a href="javascript:void(0)">Approval</a>
  </ul>
  <h2 onClick="javascript:ShowMenu(this,2)"><a href="javascript:void(0)">+ LS Irregular Loan Repayment Schedule</a></h2>
    <ul class="no">
     <a href="javascript:void(0)">Maintenance</a>
     <a href="javascript:void(0)">Approval</a>
    </ul>
 </span>
    
 <h1 onClick="javascript:ShowMenu(this,2)"><a href="javascript:void(0)">+ Enquiry</a></h1>
 <span class="no">
  <a href="javascript:void(0)">LS all Deal Journal and Deal History Enquiry</a>
 </span>
    
 <h1 onClick="javascript:ShowMenu(this,3)"><a href="javascript:void(0)">+ Report</a></h1>
  <span class="no">
   <a href="javascript:void(0)">A/C Level Yield Analysis Report</a>
   <a href="javascript:void(0)">A/C Level Yield Analysis Report</a>
  </span>


  <h1 onClick="javascript:ShowMenu(this,4)"><a href="javascript:void(0)">+ System</a></h1>
   <span class="no">
    <a href="javascript:void(0)">HLS</a>
    <a href="javascript:void(0)">HUB</a>
    <a href="javascript:void(0)">RPS</a>
    <a href="javascript:void(0)">OBS</a>
    <a href="javascript:void(0)">HUB</a>
   </span>
</div>
</body>
</html>
