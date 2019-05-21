<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/salesman/salesmanSecond.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-salesman" submenu="sidebarmenu-salesman-salesmanSecond">
<div>
	<div class="details" style="width: 100%" ng-controller="salesmanSecondController">
	<input type="hidden" value="${salesmanId}" id="salesmanId" />
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>销售管理</li>
				<li>/</li>
				<li>客户管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
			
				
				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>手机号码</span>
					<input type="text" ng-model="mobile" placeholder="请输入手机号码" />
				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="SalesmanSecondList()"
						value="立即检索" />
				</div>


			</div>
			<div class="manage">
				<ul class="show">

					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>手机号码</th>
						</tr>

						<tr ng-repeat="scl in salesmanSecondlist" ng-click="checkedsalesmanSecond(scl)"
							ng-class="{'selected':selected==scl}">
							<th>{{scl.mobile}}</th>
						</tr>
					</table>

					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="SalesmanSecondList()">
						</ul>
					</div>

				</div>
				
		</div>
	</div>
	 
</div>
 
<style type="text/css">
.poop {
	overflow-y: scroll;
}

.poop span {
	font-size: 1.5rem;
}

.template-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.template-left, .template-right {
	width: 50%;
	float: left;
	height: auto;
}

.template-left ul {
	height: 50px;
	line-height: 50px;
	font-size: 2rem;
}

.template-left ul li {
	float: left;
	margin-right: 5px;
}

.template-left ul li:nth-child(1) {
	margin-right: 10px;
}

.template-right .grade-text {
	width: 80%;
}

.template-right .grade-text textarea {
	width: 100%;
	height: 110px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background: #EDEEF0;
	border-radius: 20px;
	text-indent: 2em;
}

.details-frame-content .select-2 {
	float: left;
	margin-right: 15px;
	width: 18%;
}

@media screen and (max-width: 901px) {
	.details-frame-content .select-2 {
		width: 90%;
	}
}
</style>
</@b.body>

</html>
