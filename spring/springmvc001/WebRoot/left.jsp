<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>竖向的折叠菜单</title>
<script language = JavaScript>
function showmenu(id) {
	var list = document.getElementById("list"+id);
	var menu = document.getElementById("menu"+id)
	if (list.style.display=="none") {
		document.getElementById("list"+id).style.display="block";
		menu.className = "title1";
	}else {
		document.getElementById("list"+id).style.display="none";
		menu.className = "title";
	}
}
</script>
<style type="text/css">
<!--
*{margin:0;padding:0;list-style:none;font-size:14px}
#nav{margin:10px;text-align:center;line-height:25px;width:200px;}
.title{background:#336699;color:#fff;border-bottom:1px solid #fff;cursor:pointer;}
.title1{background:#888;color:#000;border-bottom:1px solid #666;cursor:pointer;}
.content li{color:#336699;background:#ddd;border-bottom:1px solid #fff;}
-->
</style>
</head>
<body>
<div id="nav">
		<div class="title" id="menu1" onclick="showmenu('1') ">Product and Control</div>
		<div id="list1" class="content" style="display:none">
		    <ul>
			<li>HLS</li>
			<li>HCB</li>
			<li>RPS</li>
			<li>OBS</li>
			<li>HUB</li>
			</ul>
		</div>

		<div class="title" id="menu2" onclick="showmenu('2')">Login</div>
		<div id="list2" class="content" style="display:none">
			<ul>
			<li>菜单导航</li>
			<li>层和布局</li>
			<li>图片切换</li>
			</ul>
		</div>
			<div class="title" id="menu3" onclick="showmenu('3')">Logout</div>

</div>
</body>
</html>