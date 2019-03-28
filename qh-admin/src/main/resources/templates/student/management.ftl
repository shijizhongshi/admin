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
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>学员注册时间</span> <input type="date" name="search"
						ng-model="fromdate"
						class="ng-pristine ng-untouched ng-valid ng-empty">
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
						placeholder="请输入姓名或电话"
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
				<!--添加修改学员-->
				<div class="poop" id="add">
					<form id="myform" class="ng-pristine ng-valid">
						<h3 ng-show="userId==null">添加学员</h3>
						<h3 ng-show="userId!=null">修改学员</h3>
						<div class="select-2">
							<span>真实姓名<i class="bitian">*</i></span> <input type="text"
								ng-model="user.realname"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入学员姓名">
						</div>
						<div class="select-2">
							<span>学员电话<i class="bitian">*</i></span> <input type="text"
								ng-model="user.mobile"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入学员电话">
						</div>
						<div class="select-2">
							<span>用户密码<i class="bitian">*</i></span> <input type="text"
								ng-model="user.password"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入用户密码">
						</div>
						<div class="select-2">
							<span>确认密码<i class="bitian">*</i></span> <input type="text"
								ng-model="confirmPassword"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请确认密码">
						</div>
						<div class="select-3">
							<span>所在地区</span> <img src="/images/sjk-xl.png"> <select
								ng-model="p" ng-options="p.provinceName for p in provincelist"
								ng-change="getCity(p)">

							</select>
						</div>
						<div class="select-3">
							<span>&nbsp;</span> <img src="/images/sjk-xl.png"> <select
								ng-options="city.cityName for city in citylist"
								ng-model="cityName" ng-change="getCityName(cityName)">

							</select>
						</div>
						<div class="select-radio ">
							<ul>
								<li>学员状态</li>
								<li><input type="radio" ng-value="0"
									ng-model="user.isdisabled"> 正常</li>
								<li><input type="radio" ng-value="1"
									ng-model="user.isdisabled">禁用</li>
							</ul>
						</div>
						<div class="end">
							<input name="git" type="submit" value="提交" ng-show="userId==null"
								ng-click="saveORupdateUser()" style="background: #5ED8A9;">
							<input name="git" type="submit" value="修改" ng-show="userId!=null"
								ng-click="saveORupdateUser()" style="background: #5ED8A9;">
							<input name="esc" type="reset" value="取消" onclick="CloseDiv()"
								class="esc">
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