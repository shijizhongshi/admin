<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="用户提现" />
<link rel="stylesheet" href="/styles/admin.css" />

<script src="/scripts/admin.js"></script>
<script src="/scripts/league/league.js"></script>
<script src="/scripts/user/withdrawlist.js"></script>
<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-withdraw">
<div ng-controller="withdrawController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>用户管理</li>
				<li>/</li>
				<li>用户提现</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content" id="details-frame-content">
				<ul>
					<li style="border-bottom: 3px solid red; font-weight: 900;">核审列表</li>
				</ul>
			</div>
			<div id="guanli">
				<!-- 核审列表 -->
				<div class="manage">
					<ul style="height: 80px;" class="managr-dianpu">
						<div class="select-3" style="width: 10%;margin-right:10px">
							<img src="/images/sjk-xl.png"> <span>核审状态</span> <select
								ng-model="payStatus">
								<option value="">核审状态</option>
								<option value="1">通过</option>
								<option value="2">未通过</option>
								<option value="0">未审核</option>
							</select>
						</div>
						<div class="select-3" style="width: 10%;margin-right:10px">
							<span>手机号</span> <input type="text" ng-model="mobile" />
						</div>
						<div class="select-3" style="width: 10%;margin-right:10px">
							<span>申请时间</span> <input type="date" ng-model="fromdate"
								name="search" />
						</div>
						<div class="select-3"
							style="font-size: 1.6rem; width: 1%; text-align: center;">

							&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3" style="width: 10%;margin-right:10px">
							<span>&nbsp;</span> <input type="date" ng-model="todate"
								name="search" />
						</div>

						<div>
							<input type="button" class="btn-lg im-key" value="立即检索"
								ng-click="loaddata()" />
						</div>
					</ul>

					<div class="admin-table">

						<table>
							<tr>
								<th>手机号</th>

								<th>申请提现金额</th>
								<th>账户类型</th>
								<th>申请时间</th>
								<th>操作</th>
							</tr>

							<tr ng-repeat="w in withdrwlist">
								<th>{{w.mobile}}</th>
								<th>{{w.money}}</th>
								<th ng-show="{{w.types==1}}">支付宝</th>
								<th ng-show="{{w.types==2}}">微信</th>
								<th>{{w.showtime}}</th>
								<th></th>
							</tr>

						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="current" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="loaddata()">
							</ul>
						</div>
					</div>


				</div>

			</div>
		</div>
	</div>
</div>

<style type="text/css">
.details-frame-content {
	height: 50px;
	padding: 0 29px;
	overflow-y:hidden;
}

.details-frame-content ul li {
	float: left;
	height: 50px;
	text-align: center;
	line-height: 50px;
	font-size: 1.5rem;
	cursor: pointer;
	margin-right: 15px;
	
}

.leibie {
	background: #F5F6F8;
	width: 50%;
	text-align: center;
}

.grade-text textarea {
	height: 70px;
	width: 50%;
}

.manage .managr-dianpu {
	padding-left: 20px;
}

.manage .managr-dianpu .select-3 {
	width: 10%;
	margin-left: 18px;
	margin-right: 0;
}

.details-frame-heshen {
	width: 100%;
	padding: 0 30px;
	background: white;
	height: auto;
	border-bottom: solid 1px #EEEFF1;
}

.details-frame-heshen .select-3 {
	width: 15%;
}

.details-frame-heshen .select-2 {
	width: 10%;
	float: left;
}

.details-frame-heshen .select-2 input {
	border-radius: 0;
}

.admin-table table tr form span {
	font-size: 1.4rem;
}
</style>

</@b.body>

</html>