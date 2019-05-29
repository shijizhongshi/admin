<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>
<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<script src="/scripts/admin.js"></script>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/playback.js"></script>
<link rel="stylesheet" href="/styles/management.css" />
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-playback">
<div ng-controller="PlayBackController">

	<input type="hidden" value="${liveId}" id="liveId" /> 
	<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>网课资源管理</li>
				<li>/</li>
				<li>回放管理</li>


			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%; margin-right: 0%">
					<span>搜索回放</span> <input type="text" placeholder="请输入回放名称"
						ng-model="playbackName" />

				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="showPlayBack()"
						value="立即检索" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改回放</li>
					<li ng-click="deletePlayBack()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除回放</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
				</ul>
				<div class="admin-table">

					<table>

						<tr>
							<th>回放名称</th>
							<th>回放链接</th>
							<th>创建时间</th>
						</tr>

						<tr ng-repeat="pb in playBacklist" ng-click="checkedPlayBack(pb)"
							ng-class="{'selected':selected==pb}">
							<th>{{pb.playbackName}}</th>
							<th>{{pb.videoId}}</th>
							<th>{{pb.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							
						</tr>
					</table>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-change="showPlayBack()">
						</ul>
					</div>
				</div>

				<!--弹窗-->
				<div class="poop" id="add">
					
						<h3>添加课程</h3>
						<div class="flex">
							<div style="width: 80%;">
								<div class=" select-2">
									<span>回放名称</span> <input type="text"
										ng-model="playBack.playbackName" placeholder="请输入回放名称" />
								</div>
								<div style="width: 100%; clear: both;">
									<div class=" select-2">

										<span>视频的videoId:</span> 
										<input type="text"
								ng-model="playBack.videoId" placeholder="视频的videoId"
								style="width: 230px; text-indent: 2em;" ng-keyup="ccnew(playBack.videoId)"/>
									</div>
								</div>
							<div class="costs-uploadfile-div" ng-show="ccvideo">
								
								<div style="margin-top: 3px;">
									<iframe id="{{scriptss2}}" src="{{trustSrc()}}" frameborder="0" height="490" width="600"></iframe>
								</div>
							</div>
							</div>
						</div>
						<div class="end">
							<input name="git" type="submit"
								value="修改"  ng-click="updatePlayBack()"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" ng-click="reset()" class="esc" />
						</div>
					
				</div>

			</div>

		</div>
	</div>
</div>
<style type="text/css">
.poop {
	width: 1100px;
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

.flex {
	display: flex;
	justify-content: space-between;
}

.flex .select-2 {
	width: 100%;
}

.cltab {
	weight: 100;
	height: auto;
	border: 1px solid #EEEEEE;
	border-radius: 10px;
	overflow: hidden;
}

.poop table {
	width: 100%;
}

.poop table tr:nth-child(2n) {
	background: #F3F4F6;
}

.poop table tr:nth-child(2n-1) {
	background: #FFFFFF;
}

.poop table tr:nth-child(1) {
	background: #A3AAB0;
	color: white;
	border-radius: 5px;
}

.poop table th {
	text-align: center;
}

.poop table tr th:nth-child(1) {
	border-right: 1px solid #EEEEEE;
}
</style>

</@b.body>    
</html>
