<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="评论信息管理" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/patientmanage/patient.js"></script>
<@b.body menu="sidebarmenu-patientmanage" submenu="sidebarmenu-patientmanage-patient">
<div ng-controller="patientController">
	
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>评论信息管理</li>
					<li>/</li>
					<li>信息简述</li>
				</ul>
			</div>
			<div class="details-frame">
				
				<div id="guanli">
					<div class="manage">
						<form>
							<ul style="height: 80px;" class="managr-dianpu">

								<div class="select-3" style="width: 10%;margin-right:5px">
									<span>信息标题</span> <input type="text" ng-model="title" />
								</div>
								<div>
									<input type="button" class="btn-lg im-key"
										ng-click="patientList()" value="立即检索" />
								</div>

							</ul>
						</form>
						<ul class="show">


							<li ng-click="patientdelete()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
								<li ng-click="details(id)" style="background: #F9CD33;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;查看详情</li>
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>问题类别</th>
									<th>问题标题</th>
									<th>点赞数</th>
									<th>问题创建时间</th>
								</tr>

								<tr ng-repeat="p in patientlist" ng-click="checkpatient(p)"
									ng-class="{'selected':selected==p}">
									<th>{{p.category}}</th>
									<th>{{p.title}}</th>
									<th>{{p.likes}}</th>
									<th>{{p.showtime}}</th>
								</tr>

							</table>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total"
									ng-model="current" items-per-page="pageSize" max-size="5"
									class="pagination-sm" previous-text="&lsaquo;"
									next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
									ng-click="patientList()">
								</ul>
							</div>
						</div>


					</div>
					
			</div>
		</div>
	</div>

	<style type="text/css">
@media screen and (max-width:901px) {
	.managr-dianpu, .details-frame-heshen {
		overflow: auto;
	}
}

.poop {
	width: 55%;
	height: 550px;
	position: absolute;
	left: 15%;
	top: 10%;
	display: none;
}

@media screen and (max-width:1401px) {
	.poop {
		width: 560px;
	}
}

.poop span {
	font-size: 1.5rem;
}

.resource .grade-left, .resource .grade-center {
	width: 400px;
	float: left;
	height: auto;
	overflow: auto;
}

.poop .grade-left, .poop .grade-center {
	width: 49%;
	float: left;
	height: 720px;
	overflow: auto;
}

.resource .grade-right {
	width: 200px;
	border-right: none;
	height: 500px;
	float: left;
}

.details-frame-content {
	height: 51px;
	padding: 0 29px;
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

.manage .managr-dianpu .select-3 {
	width: 12%;
	margin-left: 18px;
	margin-right: 0;
}

@media screen and (max-width: 901px) {
	.manage .managr-dianpu .select-3, .details-frame-heshen .select-3,
		.details-frame-heshen .select-2 {
		width: 90% !important;
	}
}

.details-frame-heshen {
	width: 100%;
	padding: 0 30px;
	background: white;
	height: auto;
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

.resource {
	width: auto;
	height: 600px;
	position: absolute;
	left: 10%;
	top: 20%;
	display: none;
}

@media screen and (max-width:1401px) {
	.resource {
		width: 840px;
	}
}

.resource .grade-left ul {
	width: 100%;
	margin: 3px 0;
	height: 50px;
}

.resource .grade-left ul li:nth-child(1) {
	float: left;
}

.resource .grade-left ul li:nth-child(2) {
	float: right;
	color: #999;
}

.end {
	float: none;
	margin-left: auto;
}
</style>


</div>
</@b.body>

</html>