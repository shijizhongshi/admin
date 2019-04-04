<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="短视频管理" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/patientmanage/videopatient.js"></script>
<@b.body menu="sidebarmenu-patientmanage" submenu="sidebarmenu-patientmanage-uservideo">
<div ng-controller="videopatientController">
	<div class="details" style="width: 100%">
		<input type="hidden" value="${vid}" id="vid" />
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>发布管理</li>
					<li>/</li>
					<li>短视频评论管理</li>
				</ul>
			</div>
			<div class="details-frame">
				
				<div id="guanli">
					<div class="manage">
					
						<ul class="show">

							<li ng-click="returnuservideo()" style="background: #F9CD33;"><span
								class="glyphicon glyphicon-pencil"></span>&nbsp;返回</li>
							<li ng-click="videopatientdelete()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>用户昵称</th>
									
									<th>评论内容</th>
									<th>点赞数</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>

								<tr ng-repeat="vp in videopatient" ng-click="checkvideopatient(vp)"
									ng-class="{'selected':selected==vp}">
									<th>{{vp.nickname}}</th>
									
									<th>{{vp.comments}}</th>
									<th>{{vp.likesNumber}}</th>
									<th>{{vp.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
									<th><span class="xiangqing" ng-click="seeconnmet(vp.id)">查看回复</span></th>
									
								</tr>

							</table>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total"
									ng-model="current" items-per-page="pageSize" max-size="5"
									class="pagination-sm" previous-text="&lsaquo;"
									next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
									ng-click="videopatientlist()">
								</ul>
							</div>
						</div>
						
				<div id="revise" class="resource" style="width: 720px;">
					<div class="admin-table">
							<table>
								<tr>
									<th>用户昵称</th>
									<th>评论内容</th>
									<th>点赞数</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
								<tr ng-repeat="vpl in videopatientlist" >
									<th>{{vpl.nickname}}</th>
									
									<th>{{vpl.comments}}</th>
									<th>{{vpl.likesNumber}}</th>
									<th>{{vpl.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
									<th><span class="xiangqing" ng-click="videopatientsdelete(vpl.id)">删除</span></th>
									
								</tr>
							</table>
						</div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total1"
								ng-model="current1" items-per-page="pageSize1" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="videopatientslist()">
							</ul>
						</div>

					</form>
					<div class="end" style="clear: both;">
						<input name="esc" type="reset"
							value="取消" ng-click="reset()" class="esc" />
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