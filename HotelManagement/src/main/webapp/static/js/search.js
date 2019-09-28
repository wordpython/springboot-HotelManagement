/**
 * 搜索
 */

function doSearch() {
	var keyword = document.getElementById("inputSearch").value;
	$.ajax({
		url:"search",
		type:"POST",
		data:{keyword:keyword},
		dataType:"JSON",
		success:function (data) {
			layer.close(index);
			layer.open({
				title: "信息",
				content: data
			});
			addContent(result);
		}
	})
}

//将返回的数据添加进去
function addContent(result){
	//先清空，再插入
	clearContent();
	var jsonObj =  JSON.parse(result);//将json字符串转换成json对象
	var size=jsonObj.length;
	for(var i=0;i<size;i++){
		//获取数据
		var href=jsonObj[i].systemid;
		var title=jsonObj[i].headline;
		var content=jsonObj[i].blurb;
		//获取定位div
		var father=document.getElementById("father");
		//创建div
		var one=document.createElement("div");
		var one_title=document.createElement("div");
		var one_content=document.createElement("div");
		var one_time=document.createElement("div");
		one.className="one";
		one_title.className="one_title";
		one_content.className="one_content";
		one_time.className="one_time";
		//将数据插入分别div
		one_title.innerHTML="<p><a href="+"http://"+href+">"+title+"</a></p>";
		one_content.innerHTML=content;
		one_time.innerHTML="Associated Press · Sports · Mar 8, 2019";
		//整合div
		one.appendChild(one_title);
		one.appendChild(one_content);
		one.appendChild(one_time);
		father.appendChild(one);
	}
}

//清空异步加载的数据
function clearContent(){
	var father=document.getElementById("father");
	var size=father.childNodes.length;
	for(var i=size-1;i>=0;i--){
		father.removeChild(father.childNodes[i]);
	}
//	var one=document.getElementsByClassName("one");
}