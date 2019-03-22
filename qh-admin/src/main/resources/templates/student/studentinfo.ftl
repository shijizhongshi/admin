 <#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="用户开课"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/studentinfo.js"></script>
<style type="text/css">
.selected {
	background-color: #c1ddec
}
</style>
<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-userinfo">
<div ng-app="app" ng-controller="studentinfoController">
	<div class="details" style="width: 100%">
		<input type="hidden" value="${nickname}" id="nickname" />
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>学员信息管理</li>
				<li>/</li>
				<li>用户开课</li>
			</ul>
		</div>
		<div class="details-frame">
			<form id="myform">
				<div class="details-frame-content">
					<ul class="managr-dianpu">


						<div class="select-3" style="width: 10%; margin-right: 10px">
							<span>昵称</span> <input type="text" ng-model="nickname" />
						</div>
						<div class="select-3" style="width: 10%; margin-right: 10px">
							<span>手机号</span> <input type="text" ng-model="mobile" />
						</div>
						<div class="select-3" style="width: 10%; margin-right: 10px">
							<img src="/images/sjk-xl.png" /> <span>用户类型</span> <select
								ng-model="userrole">
								<option value="">全部用户</option>
								<option value="0">普通用户</option>
								<option value="1">商家用户</option>
							</select>
						</div>

						<div>
							<input type="button" class="btn-lg im-key"
								ng-click="selectUser()" value="立即检索" />
						</div>
					</ul>
			</form>
		</div>
		<div class="manage">

			<div class="admin-table">
				<ul class="show">
					<li ng-click="applys(1)" style="background:#9DE879;"><span
						class="glyphicon glyphicon-briefcase" ></span>&nbsp;网课报班</li>
					<li ng-click="applys(2)" style="background:#F9CD33;"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;课程报名</li>
					<li ng-click="applys(3)"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;直播报班</li>
					<li ng-click="applys(3)"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;免费半价重学</li>
				</ul>

				<div class="admin-table">
					<table id="tableExcel">
						<tr>
							<th>手机号</th>
							<th>昵称</th>
							<th>用户类型</th>
							<th>用户资质</th>
							<th>用户状态</th>
							<th>注册时间</th>
							<th>修改用户状态</th>
						</tr>
						
						<tr ng-repeat="u in userList" ng-click="checkUser(u)"
							ng-class="{'selected':selected==u}">
							<th>{{u.mobile}}</th>
							<th>{{u.nickname}}</th>
							<th>{{u.userroles}}</th>
							<th>{{u.isdoctors}}</th>
							<th>{{u.disabled}}</th>
							<th>{{u.addtime | date:'yyyy-MM-dd'}}</th>
							<th><input type="button" ng-click="changedisabled(1,u.id)"
								ng-show="{{u.isdisabled}}==0" class="btn-lg im-key" value="禁用"
								style="padding: 3px 10px; margin: 0;" /> <input type="button"
								ng-click="changedisabled(0,u.id)" ng-show="{{u.isdisabled}}==1"
								class="btn-lg im-key" value="启用"
								style="padding: 3px 10px; margin: 0; background: #47e84c;" /></th>

						</tr>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="selectUser()">
					</ul>
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
				      <li class="list" ng-click="typeList(5)">
				      <b>健康管理师</b> 
				      <ul class="items" ng-class="{'active':active==5}">
				         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
				      </ul>
				      </li>
				      <li class="list" ng-click="typeList(7)">
				      <b>建筑资格</b> 
				      <ul class="items" ng-class="{'active':active==7}">
				         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('建筑资格',sub)" ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
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
					ng-click="updateSelection($event,class.id,class,class.classDiscountPrice)"/></th>
					<th>{{class.className}}</th>
					<th>{{class.classPrice}}</th>
					<th>{{class.classDiscountPrice}}</th>
					<th>{{class.classYear}}</th>
					</tr>
					<tr ng-show="{{typesName=='课程'}}" ng-repeat="course in courselist">
					<th><input type="checkbox" ng-checked="isSelected(course.id)"
					ng-click="updateSelection($event,course.id,course,course.courseDiscountPrice)"/></th>
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
				您当前余额:{{surplusaccount}}&nbsp;所需班级需扣除：{{prices}}元；
				</div>
				
				<div style="float:left;font-size: 1.6rem;line-height: 50px;margin-left:4%;" ng-show="!{{jiamengshang}}">
				销售人员<i class="bitian">*</i><input type="text" ng-model="openCourse.salesName" style="border:#F0F1F3 1px solid;border-radius:5px;width:150px;height:30px;margin-left:20px;background:#F7F8FC;">
				</div>
				<div style="float:left;font-size: 1.6rem;line-height: 50px;margin-left:4%;" ng-show="!{{jiamengshang}}">
				销售人员电话<i class="bitian">*</i><input type="text" ng-model="openCourse.salesMobile" style="border:#F0F1F3 1px solid;border-radius:5px;width:150px;height:30px;margin-left:20px;background:#F7F8FC;">
				</div>
				
				<div class="end">
							<input name="git" type="submit" value="提交" ng-click="openCourses()"  style="background:#5ED8A9;">
				
							<input name="esc" type="reset" value="取消" onclick="CloseDiv2()" class="esc">
						</div>
					</form>
				</div>
			</div>



		</div>


	</div>
</div>

 
<style type="text/css">
.managr-dianpu .select-3 {
	width: 10%;
	margin-left: 18px;
	margin-right: 0;
}

.admin-table table tr form span {
	font-size: 1.4rem;
}
</style>
</div>
  </@b.body>

</div>
</html>
