 <#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/user/userinfo.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-userinfo">
<div ng-controller="userinfoController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>用户管理</li>
				<li>/</li>
				<li>用户信息</li>
			</ul>
		</div>
		<div class="details-frame">
			<form id="myform">
				<div class="details-frame-content">
					<ul class="managr-dianpu">


						<div class="select-3" style="width: 10%;margin-right:10px">
							<span>昵称</span> <input type="text" ng-model="nickname" />
						</div>
						<div class="select-3" style="width: 10%;margin-right:10px">
							<span>手机号</span> <input type="text" ng-model="mobile" />
						</div>
						<div class="select-3" style="width: 10%;margin-right:10px">
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

				<table>
					<tr>
						<th>手机号</th>
						<th>昵称</th>
						<th>用户类型</th>
						<th>用户状态</th>
						<th>修改用户状态</th>
					</tr>

					<tr ng-repeat="u in userlist" ng-click="checkuser(u)"
						ng-class="{'selected':selected==u}">
						<th>{{u.mobile}}</th>
						<th>{{u.nickname}}</th>
						<th>{{u.userroles}}</th>
						<th>{{u.disabled}}</th>
						<th><input type="button" ng-click="changedisabled(1,u.id)"
							ng-show="{{u.isdisabled}}==0" class="btn-lg im-key" value="禁用"
							style="padding: 3px 10px; margin: 0;" /> <input type="button"
							ng-click="changedisabled(0,u.id)" ng-show="{{u.isdisabled}}==1"
							class="btn-lg im-key" value="启用"
							style="padding: 3px 10px; margin: 0; background: #47e84c;" /></th>

					</tr>
				</table>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="selectUser()">
					</ul>
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
