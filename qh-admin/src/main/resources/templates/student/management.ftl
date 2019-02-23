<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学员管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-student" submenu="sidebarmenu-student-management">
<div ng-controller="gradeController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>学员信息管理</li>
				<li>/</li>
				<li>学员管理</li>
			</ul>
		</div>
<div class="details-frame">
	<div class="details-frame-content">
		<div class="select-3">
							<span>学员注册时间</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
					
	<div class="select-3">
		<span>学员姓名或电话</span>
		
		<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
		<div class="select-3">
		<span>学员状态</span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?"></option>
			<option >全部</option>
			<option ></option>
		</select>
	</div>
	
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()" value="检索">
				</div>
<div>
<input type="button" class="btn-lg im-key" value="导出excle" onclick="method5('tableExcel')">
				</div>
	</div>
<div class="manage">
	<ul class="show">

			<li style="background:#9DE879;" onclick="showDiv()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加学员</li>
		<li style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改学员</li>
		<li style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除学员</li>
		<li  onclick="showDiv2()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;网课报班</li>
		<li><span class="glyphicon glyphicon-briefcase"></span>&nbsp;课程报名</li>
		<li ><span class="glyphicon glyphicon-briefcase"></span>&nbsp;直播报班</li>
		<li onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;免费半价重学</li>
 
	</ul>
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>学员名</th>

	<th>学员电话</th>
	<th>真实姓名</th>
	<th>销售人员</th>
	<th>已报网课班级</th>
	<th>已报课程</th>
	<th>学习总时长</th>
	<th>总登录次数</th>
	<th>最后登陆时间</th>
	<th>学院状态</th>
	<th>操作</th>
	</tr>
	<tr>
		<th>学员名</th>

	<th>学员电话</th>
	<th>真实姓名</th>
	<th>销售人员</th>
	<th>已报网课班级</th>
	<th>已报课程</th>
	<th>学习总时长</th>
	<th>总登录次数</th>
	<th>最后登陆时间</th>
	<th>学院状态</th>
	<th>
	<input type="button"  ng-show="0==0" class="btn-lg im-key" value="禁用" style="padding:3px 10px;margin:0;">
	<input type="button"  ng-show="0==1" class="btn-lg im-key ng-hide" value="启用" style="padding:3px 10px;margin:0;background:#47e84c;">
						</th>
	</tr>



	</tbody></table>
	</div>
		<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="templateBases()">
						</ul>
					</div>
	<!--添加修改学员-->
		<div class="poop" id="add" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3>{{}}学员</h3>
<div class="select-2">
		<span>真实姓名<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入学员姓名" >
	</div>
	<div class="select-2">
		<span>学员电话<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入学员电话" >
	</div>
	<div class="select-2">
		<span>用户密码<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入用户密码" >
	</div>
	<div class="select-2">
		<span>确认密码<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请确认密码" >
	</div>
	<div class="select-3">
		<span>所在地区</span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?">所在省区</option>
			<option ></option>
			<option ></option>
		</select>
	</div>
		<div class="select-3">
		<span>&nbsp;</span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?">所在市区</option>
			<option ></option>
			<option ></option>
		</select>
	</div>
	<div class="select-radio ">
		<ul><li>学员状态</li>  
		<li><input type="radio" ng-value="1" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="1"> 正常</li> 
		<li><input type="radio"  ng-value="0" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="0">停用</li></ul>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="courseId!=null" ng-click="addCourse()" style="background:#5ED8A9;" class="ng-hide">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv()" class="esc">
		</div>
		</form>
	</div>
<!-- 网课/直播报班/课程报名-->

<div class="resource" id="revise" >
<form id="formReset2">
<h3>{message}报班</h3>
<div style="display: flex; justify-content: space-between;">
<div class="classify" style="width:28%;">
<p class="xiaobiaoti">选择专业</p>
	<ul class="menu" style="box-shadow:none;">
	
   <li class="list" ng-click="typeList(1)" >
   <b>医师资格 </b>
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('医师资格',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">
   <b>药师资格</b>
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('药师资格',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)">
       <b>中医基础理论</b>
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('中医基础理论',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">
       <b>卫生资格</b> 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('卫生资格 ',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list">
      <b>健康管理师</b> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
<div style="width:40%;">
<p class="xiaobiaoti">选择课程</p>
<div style="weight:100;height:455px;border:1px solid #EEEEEE;border-radius:10px;overflow:hidden;">
<table>

	<tbody>
	<tr>
	<th>选择</th>
  <th>名称</th>
	<th>班级价格</th>
	<th>班级折扣价</th>
	<th>班级年限</th>
	
	</tr>
	<tr>
		<th><input type="checkbox" /></th>
	<th>名称</th>
	<th>班级价格</th>
	<th>班级折扣价</th>
	<th>班级年限</th>
	</tr>



	</tbody></table>
</div>
</div>
<div style="width:28%;">
<p>已选班级</p>
<div style="weight:100;height:455px;border:1px solid #EEEEEE;border-radius:10px;overflow:hidden;padding:20px 15px;">



<ul class="yixuanbanji"> 
<li><span style="color:red">健康管理师-</span>健康管理师</li>
<li>价格：1000 <span style="color:red;margin-left:20px;">折扣价：10000</span></li></ul>


</div>
</div>
</div>
<div style="float:left;font-size: 1.6rem;line-height: 50px;">
您当前余额：15454545&nbsp;所需班级需扣除：0元；
</div>
<div style="float:left;font-size: 1.6rem;line-height: 50px;margin-left:4%;">
销售人员<i class="bitian">*</i><input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" style="border:#F0F1F3 1px solid;border-radius:5px;width:150px;height:30px;margin-left:20px;background:#F7F8FC;">
</div>
<div class="end">
			<input name="git" type="submit" value="提交"  style="background:#5ED8A9;">

			<input name="esc" type="reset" value="取消" onclick="CloseDiv2()" class="esc">
		</div>
	</form>
</div>

<!-- 免费半价重学 -->

<div class="resource" id="resource"  >
<form id="formReset3">
<h3>{学员名字}</h3>
<div style="display: flex; justify-content: space-between;">
<div class="classify" style="width:28%;">
<p class="xiaobiaoti">选择专业</p>
	<ul class="menu" style="box-shadow:none;">
	
   <li class="list" ng-click="typeList(1)" >
   <b>医师资格 </b>
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('医师资格',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">
   <b>药师资格</b>
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('药师资格',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)">
       <b>中医基础理论</b>
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('中医基础理论',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">
       <b>卫生资格</b> 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('卫生资格 ',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list">
      <b>健康管理师</b> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
<div style="width:70%;">
<ul class="xinxixuanze">
<li style="width:27%;">专业名称：左边的专业</li>
<li  style="width:30%;"><img src="/images/sjk-xl.png" />
<span>选择年份： </span>
<select>
<option>全部</option>
<option></option>
<option></option></select></li>
<li  style="width:30%;"><span>重学类型:</span> <select>
<option>全部</option>
<option></option>
<option></option></select></li>
<input type="button" class="btn-lg im-key" value="确定">
</ul>



<div style="weight:100;height:455px;border:1px solid #EEEEEE;border-radius:10px;overflow:hidden;">
<table>

	<tbody>
	<tr>
	<th>选择</th>
  <th>名称</th>
	<th>班级价格</th>
	<th>班级折扣价</th>
	<th>班级年限</th>
	
	</tr>
	<tr>
		<th><input type="checkbox" /></th>
	<th>名称</th>
	<th>班级价格</th>
	<th>班级折扣价</th>
	<th>班级年限</th>
	</tr>



	</tbody></table>
</div>
</div>

</div>

<div class="end">
			<input name="git" type="submit" value="提交"  style="background:#5ED8A9;">

			<input name="esc" type="reset" value="取消" onclick="CloseDiv3()" class="esc">
		</div>
	</form>
</div>











</div>

</div>










	</div>
</div>
</div>


</@b.body>

</html>