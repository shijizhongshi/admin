<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/course.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-teacher">

<div>
<div class="details" style="width: 100%;">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>教师管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select-2" style="float:left;margin-right:15px;width: 15%;">
	<span>教师名称</span>
	<form id="search">
	<input type="text" placeholder="请输入教师名称"/></form>
	</div>
<div><input type="button" class="btn-lg im-key" ng-click="loaddata()" value="立即检索" ng-click="search()" /></div>	
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加教师</li>
		<li  style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改教师</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
         <li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="/images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

<table>
	<tr>
	<th>教师图片</th>
	<th >教师名称</th>
	<th >讲授课程</th >
	<th >是否显示</th >
	</tr>
 <for:each>
 <tr >
	<th>教师图片</th>
	<th >教师名称</th>
	<th >讲授课程</th >
	<th >是</th >
	</tr>
	</table>

	</div>
<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination boundary-links="true"
                            total-items="total" ng-model="current"
                            items-per-page="pageSize"
                            max-size="5"
                            class="pagination-sm" previous-text="&lsaquo;"
                            next-text="&rsaquo;"
                            first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
                        </ul>
                    </div>

	<!--弹窗-->
			<div class="poop" id="add" style="width:60%;height: 600px;position: absolute;left: 15%;top: 5%;" >
				<form id="myform">
	<h3>添加教师</h3>
		<div class="template-add">
			<div class="template-left" style="padding-right: 5%;">
						<div class=" select-2">
						<span>教师姓名：</span>
			<input type="text" class=""placeholder="请输入教师名称" style="width: 230px;" />
		</div>
<div  class="select-radio ">
		<ul><li>是否显示</li>  <li><input type="radio"/> 显示</li> 
		<li><input type="radio" />不显示</li></ul>
		</div>
		<div  class="select-radio ">
		<ul><li>是否推荐</li>  <li><input type="radio"/> 是</li> 
		<li><input type="radio" />否</li></ul>
		</div>
			<div class="costs-uploadfile-div">   
				 <input type="file" name="file" id="fileField"  onchange="document.getElementById('textfield').value=this.value"  accept="image/*" /> 
    <input type='text' id="textfield" style="border: solid 1px #B1B1B1;" /> 
    <button class="allBtn costs-marl15">老师图片</button>
    <div style="height:130px;width:35%;border: solid 1px #B1B1B1;margin-top:3px;">
    <img  /></div>
        </div>
<b style="margin: 5px 0;font-size: 1.5rem;">讲授课程</b>
<!--医师药师复选框-->
<div class="xuanzekuang"> 
		<dl> 
			<dt  onclick="showHide(this,'items0');"><span></span>医师资格</dt> 
			<dd id="items0"> 
				<ul>
					
					<li><span><input type="checkbox" name="yishi"/></span>临床（执业）助理医师</li>
					<li><span><input type="checkbox" name="yishi"/></span>中医（执业）助理医师</li>
					<li><span><input type="checkbox" name="yishi"/></span>中西医（执业）助理医师</li>
					<li><span><input type="checkbox" name="yishi"/></span>乡村全科助理医师</li>
					<li><span><input type="checkbox" name="yishi"/></span>中医专长医师</li>
				</ul>
			</dd> 
		</dl> 
	
		<dl> 
			<dt  onclick="showHide(this,'items1');">药师资格</dt> 
			<dd id="items1" style="display:none;"> 
				<ul>
					
					<li><span><input type="checkbox" name="yaoshi"/></span>执业中药师</li>
				</ul> 
			</dd> 
		</dl>
	</div>




	</div>
		
		
		<div class="template-right">
			<div  class="grade-text">
				<span>获奖状况</span>
			    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>讲课特点</span>
		    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>老师描述</span>
		    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>详细介绍</span>
				    <textarea></textarea>
			</div>
		</div>


		</form>
	
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
		





		
	
	 
</div>
	
</div>

</div>
</div>
</div>
</div>
<script type="text/javascript">

function getObject(objectId){
	if(document.getElementById && document.getElementById(objectId)){
		return document.getElementById(objectId);
	}else if(document.all && document.all(objectId)){
		return document.all(objectId);
	}else if(document.layers && document.layers[objectId]){
		return document.layers[objectId];
	}else{
		return false;
	}
}

function showHide(e,objname){
	var obj = getObject(objname);
	if(obj.style.display == "none"){
		obj.style.display = "block";
		e.className="minus";
	}else{
		obj.style.display = "none";
		e.className="plus";
	}
}

</script>
 <style type="text/css">
	
		.poop{overflow-y: scroll;}
	.poop span{font-size: 1.5rem;}
	.template-add{width: 100%;border-top:1px solid #F5F6F8;height: 80%;padding-top: 10px;margin-top:10px ;}
	.template-left,.template-right{width: 50%;float: left;height: 650px;overflow:auto;}
	.template-right .grade-text{width: 80%;}
	.template-right .grade-text textarea{width: 100%;height: 110px;font-size:1.5rem}
    position:relative;}
div.costs-uploadfile-div #textfield{width:40%; height:30px;}
div.costs-uploadfile-div #fileField{width:100%;height:30px;     position: absolute; top: 0; left:0;  filter: alpha(opacity:0);opacity: 0;}
div.costs-uploadfile-div .allBtn{ padding:0; margin:0; height: 30px;  line-height: 30px;   width: 35%;  background-color: #18b3cf; border: none;  color: #fff;}
.xuanzekuang{width:60%;height: auto;text-align: center;background: #EDEEF0;}
.xuanzekuang dt , .xuanzekuang ul li{font-size: 1.0em;height: 30px;text-align: 30px;width: 100%; cursor: pointer;;}
.xuanzekuang ul li{font-size:1.2rem ;}
.xuanzekuang input {
	text-align：left;
	width: 15px;height: 15px;
}
.xuanzekuang span{height: 30px;float: left;}
</style>
</@b.body>
 </html>
