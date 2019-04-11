<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="banner广告管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/system/operating.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-operating">
<div class="details" style="width: 100%" ng-controller="operatingController">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>公共资源管理</li>
			<li>/</li>
			<li>操作记录</li>
		</ul>
	</div>
<div class="details-frame" >
<div class="details-frame-content">

				<div class="select-3">
					<span>选择时间</span> <input type="date" name="search" ng-model="fromdate" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3"
					style="font-size: 1.6rem; width: 5px; text-align: center;">

					&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
				</div>
				<div class="select-3" style="width: 11%;margin-right:15px">
					<span>&nbsp;</span> <input type="date" name="search"
						ng-model="todate" />
				</div>

				<div class="select-3">
					<span>操作区域</span> <input  type="text" placeholder="请输入操作区域" ng-model="operatingScope" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3">
					<span>负责人</span> <input type="text" placeholder="请输入操作人" ng-model="userRoleUsername" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				
				
				
				<div style="float: left;">
					<input ng-click="operating()" type="button" class="btn-lg im-key"  value="立即检索">
				</div>
			

<div class="admin-table">

						<table>
							<tbody>
							<tr>
								<th>操作人</th>
								<th>操作区域</th>
                                  <th>操作内容</th>
                                  <th>被操做者</th>
                                  <th>操作时间</th>
							</tr>
							
							<tr ng-repeat="ol in operatinglist" ng-class="{'selected':selected==ol}" ng-click="checkOperating(ol)">
								<th>{{ol.userRoleUsername}}</th>
								<th>{{ol.operatingScope}}</th>
                                  <th>{{ol.operatingStatus}}</th>
                                  <th>{{ol.operatingUser}}</th>
                                  <th>{{ol.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>

						</tbody></table>

						<div class="col-sm-6"></div>
		<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="operating()">
						</ul>
					</div>
					</div>
</div>
</div>	
	
</div>
</@b.body> 
<style>
.details-frame-content .select-3{float:left;margin-right: 20px;width: 15%;}

</style>
</html>