<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/line-white.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-line-white">

<div class="details" id="details" ng-controller="LineWhiteController"
	style="width: 100%;">
	<input type="hidden" value="${liveId}" id="liveId" /> <input
		type="hidden" value="${liveName}" id="liveName" />
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>直播资源管理</li>
			<li>/</li>
			<li>直播间名称：${liveName}</li>


		</ul>
	</div>
	<div class="details-frame">
		<div class="details-frame-content">

			<div class="select-3" style="width: 15%; margin-right: 0%">
				<span>手机号查询</span> <input ng-model="mobile" placeholder="请输入手机号码"
					type="text" style="text-indent: 2em;" />

			</div>

			<div>
				<input type="button" class="btn-lg im-key"
					ng-click="lineWhiteBases()" value="立即检索" />
			</div>
		</div>




		<div class="manage">

			<ul style="float: left; padding-top: 20px;" class="show">
				<li ng-click="uploadL()"><span
					class="glyphicon glyphicon-briefcase"></span>&nbsp;导入白名单</li>
				<li ng-click="add()" style="background: #5ED8A9; width: 110px;"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;添加白名单</li>
				<li ng-click="update()" style="background: #F9CD33; width: 110px;"><span
					class="glyphicon glyphicon-pencil"></span>&nbsp;修改白名单</li>
				<li ng-click="deletelineWhite()"
					style="background: #F86846; width: 110px;"><span
					class="glyphicon glyphicon-trash"></span>&nbsp;删除白名单</li>


				<li ng-click="deleteAll()"
					style="background: #F86846; width: 110px;"><span
					class="glyphicon glyphicon-trash"></span>&nbsp;清空白名单</li>

			</ul>
			<div class="admin-table">

				<table>
					<tr>
						<th>手机号</th>
					</tr>
					<tr ng-repeat="l in lineWhitelist" ng-click="checkedlineWhite(l)"
						ng-class="{'selected':selected==l}">
						<th>{{l.mobile}}</th>
					</tr>
				</table>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="lineWhiteBases()">
					</ul>
				</div>
			</div>


			<!--弹窗-->
			<div class="poop" id="add" style="width: auto; height: auto;">
				<form id="myform">
					<div>
						<h3>添加白名单</h3>
						<p>
							<span style="float: left;">直播间名称：</span><span
								style="float: right;">${liveName}</span>
						</p>


						<div class=" select-2" style="clear: both">
							<span>手机号码:</span> <input type="text" ng-model="lineWhite.mobile"
								placeholder="手机号码" style="width: 230px; text-indent: 2em;" />
						</div>

					</div>
					<div class="end">
						<input name="git" type="submit" ng-show="id==null" value="提交"
							ng-click="addlineWhite()" style="background: #5ED8A9;" /> <input
							name="git" type="submit" ng-show="id!=null" value="修改"
							ng-click="updatelineWhite()" style="background: #5ED8A9;" /> <input
							name="esc" type="reset" value="取消" ng-click="reset1()"
							class="esc" />
					</div>
				</form>
			</div>

			<div class="poop" id="revise" style="width: auto; height: auto;">
				<form id="myform">
					<div>
						<h3>批量添加白名单</h3>

						<div>
							<input type="file" id="file" accept=".xlsx, .xls" />
						</div>

					</div>
					<div class="end">
						<input name="git" type="submit" value="提交"
							ng-click="uploadlineWhite()" style="background: #5ED8A9;" /> <input
							name="esc" type="reset" value="取消" ng-click="reset()" class="esc" />
					</div>
				</form>
			</div>

		</div>

	</div>
</div>

</@b.body>    
</html>
