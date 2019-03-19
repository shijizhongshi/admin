<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="超级管理员"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/system/super_admin.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-superAdmin">
<div ng-app="app" ng-controller="superAdminController">
<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>题库资源管理</li>
				<li>/</li>
				<li>试题错误信息反馈</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;">
					<span>账号</span>
					<form id="search">
						<input type="text"  ng-model="nickname" placeholder="" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>账号名称</span>
					<form id="search">
						<input type="text"  ng-model="courseTypeSubclassName"  placeholder="" />
					</form>
				</div>
				
				<div class="select-3" style="width: 15%;">
					<img src="/images/sjk-xl.png" /> <span>账号属性</span>
					<form id="search">
					
						<select ng-model="status">
							<option ng-selected="status==0" value=0>教务</option>
							<option ng-selected="status==1" value=1>加盟商</option>
						</select>
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="" />
				</div>			
				
			</div>
			<div class="manage">
				<ul class="show">
				<li style="background:#9DE879;" ng-click="addshow()"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加账号</li>
					<li ng-click="update()" style="background: #F9CD33;">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;修改账号</li>
					
					<li ng-click="deletefeedback()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除信息</li>
					
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>账号</th>
							<th>昵称</th>
							<th>账号密码</th>
							<th>角色类别</th>
							<th>添加时间</th>
							<th>更新时间</th>
							<th>权限操作</th>
						</tr>
						<tr ng-repeat ="u in list">
							<th>{{u.username}}</th>
							<th>{{u.nickname}}</th>
							<th>{{u.password}}</th>
							<th>{{u.category}}</th>
							<th>{{u.addtime | date:'yyyy.MM.dd'}}</th>
							<th>{{u.updatetime | date:'yyyy.MM.dd'}}</th>
							<th><input type="button" class="btn-lg im-key" value="查看详情" style="padding: 3px 10px; margin: 0;background:#F9CD34;"></th>
						</tr>
					</table>
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="current" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="feedbackList()">
						</ul>
					</div>
					
					<!-- 添加修改账号 -->
				<div id="resource" class="resource">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">{{html}}账号</h3>
	<div>
	<div style="float:left;width:49%;">
	<div class="select-2">
		<span>账号昵称<i class="bitian">*</i></span>
<input type="text" placeholder="请输入昵称" ng-model="userRole.nickname">
	</div>
					
				<div class="select-2" >
					<img src="/images/sjk-xl.png" /> <span >账号属性</span>
					<form id="search" >
					
						<select ng-model="userRole.category" ng-options="x for x in category" >
							<option ng-selected="status==0" value=0 ></option>
						</select>
					</form>
				</div>
	<div class="select-2">
		<span>设置账号<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请设置账号名称" ng-model="userRole.username">
	</div>
		<div class="select-2">
		<span>设置密码<i class="bitian">*</i></span>
<input type="password" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请设置密码" ng-model="userRole.password">
	</div>
		<div class="select-2">
		<span>确认密码<i class="bitian">*</i></span>
<input type="password" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请确认密码" ng-model="password">
	</div>
	</div>
	<div style="width:49%;float:right;">
<div class="qx">
<p>权限设置</p>
<ul ng-model="userRole.limits">

<li><input type="checkbox" ng-checked="isSelected('网课资源管理')" ng-click="updateSelection($event,'网课资源管理')"/>网课资源管理</li>
<li><input type="checkbox" ng-checked="isSelected('题库资源管理')" ng-click="updateSelection($event,'题库资源管理')"/>题库资源管理</li>
<li><input type="checkbox" ng-checked="isSelected('公共资源管理')" ng-click="updateSelection($event,'公共资源管理')"/>公共资源管理</li>
<li><input type="checkbox" ng-checked="isSelected('订单管理')" ng-click="updateSelection($event,'订单管理')"/>订单管理</li>
<li><input type="checkbox" ng-checked="isSelected('加盟商管理')" ng-click="updateSelection($event,'加盟商管理')"/>加盟商管理</li>
<li><input type="checkbox" ng-checked="isSelected('用户管理')" ng-click="updateSelection($event,'用户管理')"/>用户管理</li>
<li><input type="checkbox" ng-checked="isSelected('学员信息管理')" ng-click="updateSelection($event,'学员信息管理')"/>学员信息管理</li>
<li><input type="checkbox" ng-checked="isSelected('发布管理')" ng-click="updateSelection($event,'发布管理')"/>发布管理</li>
</ul>
</div>
	</div>
	
</div>


	<div class="end" style="margin-top:10px;">
			<input id="addbutton" name="git" type="submit" value="添加" ng-click="insertquestionbank()"  style="background:#5ED8A9;">
			<input id="updatebutton" name="git" type="submit" value="修改" ng-click="updatequestionbank()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv3()" ng-click="resetbank()" class="esc">
		</div>
</form>

</div>
<div class="poop" style="display:none;" id="add">
<span class="close" onclick="CloseDiv()">X</span>
<p>账号权限 </p>
<ul>
<li>网课资源</li>
</ul>
</div>


</div>

					
			</div>
</div>
</div>
</div>



<style>
.resource{width:50%;left:10%;min-width:600px;}
.resource .select-2 , .resource .select-3{width:95%;}
.qx ul{width:100%;background:#F6F6F6;padding:15px;}
.qx ul li{margin-bottom:5px;font-size:1.5rem;}
.qx ul li input{margin-right:8px;}
.poop p{font-size:1.8rem;font-weight:bold;}
.poop ul{display: flex; justify-content: space-between; flex-wrap: wrap;}
.poop ul li{padding: 3px 12px;background:#F8F8F8;border:1px solid #F3F4F4;border-radius: 20px;display: inline-block;margin-right:8px;margin-bottom:8px;}
</style>

</@b.body>  
</html>