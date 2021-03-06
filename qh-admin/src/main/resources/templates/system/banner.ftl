<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="banner广告管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/system/banner.js"></script>
<style type="text/css">
.selected {
	background-color: #c1ddec
}
</style>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-banner">
<div class="details" ng-controller="bannerController"
	style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>公共资源管理</li>
			<li>/</li>
			<li>banner管理</li>


		</ul>
	</div>
	<div class="details-frame">
		<div class="details-frame-content">
			<div class="select-3" style="width: 15%;margin-right:0%" >
				<span>广告横幅的位置</span> <img src="/images/sjk-xl.png" /> <select
					ng-model="types">
					<option ng-selected="types==0" value="0">查看全部</option>
					<option ng-selected="types==1" value="1">学院的banner</option>
					<option ng-selected="types==2" value="2">学院水印logo</option>
				</select>

			</div>


			<div style="float: left;">
				<input type="button" class="btn-lg im-key" ng-click="loaddata()"
					value="立即检索"  style="background: #E9484D" />
			</div>

		</div>
		<div class="manage">
			<ul class="show">

				<li ng-click="add()" style="background: #9DE879;"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;添加图片</li>
				<li ng-click="updatebanner()" style="background: #F9CD33;"><span
					class="glyphicon glyphicon-pencil"></span>&nbsp;修改图片</li>
				<li ng-click="deletebanner()" style="background: #F86846;"><span
					class="glyphicon glyphicon-trash"></span>&nbsp;删除图片</li>
				
				<li style="float: right; margin-right: 20px; background: none;"><img
					src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
			</ul>
			<div class="admin-table">

				<div class="panel-body" style="padding: 0;">
					<div class="table-responsive">

						<table cellspacing="0"
							class="table table-small-font table-bordered table-striped">

							<tr>
								<th data-priority="1">图片</th>
								<th data-priority="2">显示级别</th>
								<th data-priority="3">链接</th>
								<th data-priority="4">是否展示</th>
							</tr>


							<tr ng-repeat="b in bannerlist" ng-click="checkedbanner(b)"
								ng-class="{'selected':selected==b}">
								<td><img ng-src="{{b.imageurl}}"
									style="width: 50px; height: 30px;" /></td>
								<td ng-show="b.type==1">学院的banner</td>
								<td ng-show="b.type==2">学院水印logo</td>

								<td>{{b.outLinks}}</td>
								<td ng-show="b.isshow==1">是</td>
								<td ng-show="b.isshow!=1">否</td>
							</tr>

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


				<!--弹窗-->
				<div class="poop" id="add" style="height: 520px;">
					
						<h3>添加/修改广告</h3>
						<div class="select-2 ">
							<span>链接:</span> <input type="text" ng-model="banner.outLinks"
								placeholder="请输入跳转链接" />
						</div>

						<div class="select-2 ">
							<img src="/images/sjk-xl.png" /> <span>级别:</span> <select
								ng-model="banner.type" name="type">
								<option ng-show="bannerId==null"  selected value="">请选择</option>
								<option ng-selected="banner.type==1" value="1">学院的banner</option>
								<option ng-selected="banner.type==2" value="2">学院水印logo</option>
								
							</select>
						</div>
						<div class=" select-2">
							<img src="/images/sjk-xl.png" /> <span>显示顺序：</span> <select>
								<option disabled selected style='display: none;'>顺序</option>
								<option></option>
								<option></option>
							</select>
						</div>
						<div>
							<div style="margin-bottom: 10px">图片:</div>
							<input type="file"
								onchange="angular.element(this).scope().uploadmainimage(this)" />
							<img src="{{imageurl}}" style="width: 50px;" />
						</div>

						<div class="select-radio ">
							<ul>
								<li>是否显示</li>
								<li><input type="radio" ng-model="banner.isshow"
									ng-value="1" name="isshow" /> 是</li>
								<li><input type="radio" ng-model="banner.isshow"
									ng-value="0" name="isshow" />否</li>
							</ul>
						</div>

						<div class="end">
							<input name="git" ng-show="bannerId==null" type="submit"
								value="提交" ng-click="banneradd()" style="background: #5ED8A9;" />
							<input name="git" ng-show="bannerId!=null" type="submit"
								value="修改" ng-click="bannerupdate()"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" ng-click="reset()" class="esc" />
						</div>
				</div>

				
			</div>


		</div>

	</div>
</div>
</@b.body>    
</html>
