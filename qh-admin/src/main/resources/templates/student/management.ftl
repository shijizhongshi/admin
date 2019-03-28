<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学员管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-student"
submenu="sidebarmenu-student-management">
<div ng-controller="studentController">
	<input type="hidden" value="${username}" id="username" /> <input
		type="hidden" value="${surplusaccount}" id="surplusaccount">
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
<<<<<<< HEAD
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>学员注册时间</span> <input type="date" name="search"
						ng-model="fromdate"
						class="ng-pristine ng-untouched ng-valid ng-empty">
=======
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
		
		<input type="text" ng-model="realnameORmobile" placeholder="请输入姓名或电话"  class="ng-pristine ng-untouched ng-valid ng-empty">
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
>>>>>>> 182a68c22d99a774269383bb175994db729f2f10
				</div>
				<div class="select-3"
					style="font-size: 1.6rem; width: 1%; text-align: center;">

					&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
				</div>
				<div class="select-3">
					<span>&nbsp;</span> <input type="date" name="search"
						ng-model="todate"
						class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>

				<div class="select-3">
					<span>学员姓名或电话</span> <input type="text" ng-model="realnameORmobile"
						class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3">
					<span>学员状态</span> <img src="/images/sjk-xl.png"> <select
						ng-model="status">
						<option value="">全部</option>
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select>
				</div>

				<div style="float: left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索">
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="导出excle"
						onclick="method5('tableExcel')">
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					

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
							<tr ng-repeat="u in userlist" ng-click="checkUser(u)"
								ng-class="{'selected':selected==u}">
								<th>{{u.nickname}}</th>
								<th>{{u.mobile}}</th>
								<th>{{u.realname}}</th>
								<th>{{u.address}}</th>
								<th ng-show="{{u.isdisabled=='0'}}">正常</th>
								<th ng-show="{{u.isdisabled=='1'}}">禁用</th>
								<th>{{u.logintime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
								<th>{{u.addtime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
							</tr>



						</tbody>
					</table>
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
			</div>

		</div>
	</div>
</div>
</div>


</@b.body>

</html>