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
<div ng-controller="studentController">
<input type="hidden" value="${username}" id="username"/>
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
								<input type="date" name="search" ng-model="fromdate" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" ng-model="todate" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
					
	<div class="select-3">
		<span>学员姓名或电话</span>
		
		<input type="text" ng-model="realnameORmobile" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
		<div class="select-3">
		<span>学员状态</span>
		<img src="/images/sjk-xl.png">
		<select ng-model="status">
			<option value="">全部</option>
			<option value="0">正常</option>
			<option value="1">停用</option>
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

			<li style="background:#9DE879;" ng-click="add()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加学员</li>
		<li style="background:#F9CD33;" ng-click="update()"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改学员</li>
		<li style="background:#F86846;" ng-click="deleteUser()"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除学员</li>
		<li ng-click="applys(1)"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;网课报班</li>
		<li ng-click="applys(2)"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;课程报名</li>
		<li ng-click="applys(3)"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;直播报班</li>
		<li onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;免费半价重学</li>
 
	</ul>
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
	<th>学员名</th>
	<th>学员电话</th>
	<th>真实姓名</th>
	<th>所属地</th>
	<th>学员状态</th>
	<th>最后登录的时间</th>
	<th>注册时间</th>
	</tr>
	<tr ng-repeat="u in userlist" ng-click="checkUser(u)" ng-class="{'selected':selected==u}">
	<th>{{u.nickname}}</th>
	<th>{{u.mobile}}</th>
	<th>{{u.realname}}</th>
	<th>{{u.address}}</th>
	<th ng-show="{{u.isdisabled=='0'}}">正常</th>
	<th ng-show="{{u.isdisabled=='1'}}">禁用</th>
	<th>{{u.logintime}}</th>
	<th>{{u.addtime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
	</tr>



	</tbody></table>
	</div>
		<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="loaddata()">
						</ul>
					</div>
	<!--添加修改学员-->
		<div class="poop" id="add" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3 ng-show="userId==null">添加学员</h3>
	<h3 ng-show="userId!=null">修改学员</h3>
<div class="select-2">
		<span>真实姓名<i class="bitian">*</i></span>
<input type="text" ng-model="user.realname" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入学员姓名" >
	</div>
	<div class="select-2">
		<span>学员电话<i class="bitian">*</i></span>
<input type="text" ng-model="user.mobile" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入学员电话" >
	</div>
	<div class="select-2">
		<span>用户密码<i class="bitian">*</i></span>
<input type="text" ng-model="user.password" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入用户密码" >
	</div>
	<div class="select-2">
		<span>确认密码<i class="bitian">*</i></span>
<input type="text" ng-model="confirmPassword" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请确认密码" >
	</div>
	<div class="select-3">
		<span>所在地区</span>
		<img src="/images/sjk-xl.png">
		<select ng-model="p" ng-options="p.provinceName for p in provincelist" ng-change="getCity(p)">
		
		</select>
	</div>
		<div class="select-3">
		<span>&nbsp;</span>
		<img src="/images/sjk-xl.png">
		<select ng-options="city.cityName for city in citylist" ng-model="cityName" ng-change="getCityName(cityName)">
		
		</select>
	</div>
	<div class="select-radio ">
		<ul><li>学员状态</li>  
		<li><input type="radio" ng-value="0" ng-model="user.isdisabled"> 正常</li> 
		<li><input type="radio"  ng-value="1"  ng-model="user.isdisabled">禁用</li></ul>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="userId==null" ng-click="saveORupdateUser()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="userId!=null" ng-click="saveORupdateUser()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv()" class="esc">
		</div>
		</form>
	</div>
<!-- 网课/直播报班/课程报名-->

<div class="resource" id="revise" >
<form id="formReset2">
<h3>{{types}}</h3>
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
<p class="xiaobiaoti">选择{{typesName}}</p>
<div style="weight:100;height:455px;border:1px solid #EEEEEE;border-radius:10px;overflow:hidden;">
<table>

	<tbody>
	<tr>
	<th>选择</th>
  <th>{{typesName}}名称</th>
	<th>{{typesName}}价格</th>
	<th>{{typesName}}折扣价</th>
	<th>{{typesName}}年限</th>
	
	</tr>
	<tr ng-show="{{typesName=='班级'}}" ng-repeat="class in classlist">
	<th><input type="checkbox" ng-checked="isSelected(class.id)"
	ng-click="updateSelection($event,class.id,class)"/></th>
	<th>{{class.className}}</th>
	<th>{{class.classPrice}}</th>
	<th>{{class.classDiscountPrice}}</th>
	<th>{{class.classYear}}</th>
	</tr>
	<tr ng-show="{{typesName=='课程'}}" ng-repeat="course in courselist">
	<th><input type="checkbox" ng-checked="isSelected(course.id)"
	ng-click="updateSelection($event,course.id,course)"/></th>
	<th>{{course.courseName}}</th>
	<th>{{course.coursePrice}}</th>
	<th>{{course.courseDiscountPrice}}</th>
	<th>{{course.courseYear}}</th>
	</tr>
	</tbody></table>
</div>
</div>
<div style="width:28%;">
<p>已选{{typesName}}</p>
<div style="weight:100;height:455px;border:1px solid #EEEEEE;border-radius:10px;overflow:hidden;padding:20px 15px;">



<ul class="yixuanbanji" ng-show="{{typesName=='课程'}}" ng-repeat="productcourse in productlisted"> 
<li><span style="color:red">{{productcourse.courseTypeSubclassName}}-</span>{{productcourse.courseName}}</li>
<li>价格：{{productcourse.coursePrice}} <span style="color:red;margin-left:20px;">折扣价：{{productcourse.courseDiscountPrice}}</span></li></ul>

<ul class="yixuanbanji" ng-show="{{typesName=='班级'}}" ng-repeat="productclass in productlisted"> 
<li><span style="color:red">{{productclass.courseTypeSubclassName}}-</span>{{productclass.className}}</li>
<li>价格：{{productclass.classPrice}} <span style="color:red;margin-left:20px;">折扣价：{{productclass.classDiscountPrice}}</span></li></ul>


</div>
</div>
</div>

<div style="float:left;font-size: 1.6rem;line-height: 50px;" ng-show="{{jiamengshang}}">
您当前余额：15454545&nbsp;所需班级需扣除：0元；
</div>

<div style="float:left;font-size: 1.6rem;line-height: 50px;margin-left:4%;" ng-show="!{{jiamengshang}}">
销售人员<i class="bitian">*</i><input type="text" style="border:#F0F1F3 1px solid;border-radius:5px;width:150px;height:30px;margin-left:20px;background:#F7F8FC;">
</div>
<div style="float:left;font-size: 1.6rem;line-height: 50px;margin-left:4%;" ng-show="!{{jiamengshang}}">
销售人员电话<i class="bitian">*</i><input type="text" style="border:#F0F1F3 1px solid;border-radius:5px;width:150px;height:30px;margin-left:20px;background:#F7F8FC;">
</div>

<div class="end">
			<input name="git" type="submit" value="提交" ng-click="openCourse()"  style="background:#5ED8A9;">

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