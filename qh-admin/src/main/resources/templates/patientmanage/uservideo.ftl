<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="短视频管理" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src='https://player.polyv.net/script/polyvplayer.min.js'></script>
<script src="/scripts/patientmanage/uservideo.js"></script>
<@b.body menu="sidebarmenu-patientmanage" submenu="sidebarmenu-patientmanage-uservideo">
<div ng-controller="uservideoController">
	
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>发布管理</li>
					<li>/</li>
					<li>短视频管理</li>
				</ul>
			</div>
			<div class="details-frame">
				
				<div id="guanli">
					<div class="manage">
						<form>
							<ul style="height: 80px;" class="managr-dianpu">

								<div class="select-3" style="width: 10%;margin-right:5px">
									<span>视频名称</span> <input type="text" ng-model="videoName" />
								</div>
								<div>
									<input type="button" class="btn-lg im-key"
										ng-click="uservideoList()" value="立即检索" />
								</div>

							</ul>
						</form>
						<ul class="show">


							<li ng-click="uservideodelete()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							<li ng-click="viewvideo()" style="background: #F9CD33;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;查看视频</li>
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>用户昵称</th>
									<th>用户类型</th>
									<th>视频的标题</th>
									<th>视频名称</th>
									<th>封面图</th>
									<th>点赞数</th>
									<th>评论个数</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>

								<tr ng-repeat="uv in uservideolist" ng-click="checkvideo(uv)"
									ng-class="{'selected':selected==uv}">
									<th>{{uv.nickname}}</th>
									<th>{{uv.type}}</th>
									<th>{{uv.title}}</th>
									<th>{{uv.videoName}}</th>
									<th><img ng-src="{{uv.firstImage}}"></th>
									<th>{{uv.likeNumber}}</th>
									<th>{{uv.commentNumber}}</th>
									<th>{{uv.showtime}}</th>
									<th><span class="xiangqing" ng-click="details(uv.id)">查看评论</span></th>
								</tr>

							</table>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total"
									ng-model="current" items-per-page="pageSize" max-size="5"
									class="pagination-sm" previous-text="&lsaquo;"
									next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
									ng-click="uservideoList()">
								</ul>
							</div>
						</div>
						
					<!--弹窗-->
			<div class="poop" id="add" style="width: auto; height: auto;left:30%;">
			<p class="close" ng-click="cancel()" style="font-size:2.0rem;">X</p>
				<form id="myform">
					<div>
						
						<iframe id="{{scriptss2}}" src="{{trustSrc()}}" frameborder="0" height="490" width="600"></iframe>
						
				</div>

				</form>
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