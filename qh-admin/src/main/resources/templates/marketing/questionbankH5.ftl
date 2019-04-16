<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="H5题库管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/indent/excle.js"></script>
<script src="/scripts/marketing/questionbankH5.js"></script>
<@b.body menu="sidebarmenu-marketing"
submenu="sidebarmenu-marketing-questionbankH5">
<div ng-controller="questionbankH5Controller">
	<input type="hidden" value="${username}" id="username" /> <input
		type="hidden" value="${surplusaccount}" id="surplusaccount">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>营销管理</li>
				<li>/</li>
				<li>H5题库管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>姓名</span> <input type="text" ng-model="realname">
				</div>
				<div class="select-3">
					<span>属性</span> <img src="/images/sjk-xl.png"> <select
						ng-model="status">
						<option value="">暂无属性</option>
						<option value="0">app注册用户</option>
						<option value="1">游客用户</option>
					</select>
				</div>
				<div class="select-3">
				<!-- ng-options="a for a in nameList"  -->
					<span>专业</span> <img src="/images/sjk-xl.png"> <select
						ng-model="courseTypeSubclassName" >
						<option value="">无专业</option>
						<option ng-repeat="name in nameList">{{name}}</option>
					</select>
				</div>
				<div style="float: left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索">
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="导出excle"
						onclick="method5('tableExcel')">
				</div>
			</div>
			<div class="manage">
				<div class="admin-table">

					<table id="tableExcel">

						<tbody>
							<tr>
								<th>姓名</th>
								<th>手机号</th>
								<th>用户属性</th>
								<th>专业分类</th>
								<th>答题数</th>
								<th>准确率</th>
								<th>视频数</th>
								<th>最后一次操作时间</th>
							</tr>
							<tr ng-repeat="questionbank in questionbankList">
								<th>{{questionbank.realname}}</th>
								<th>{{questionbank.mobile}}</th>
								<th ng-show="{{questionbank.status=='0'}}">app注册用户</th>
								<th ng-show="{{questionbank.status=='1'}}">游客用户</th>
								<th>{{questionbank.courseTypeSubclassName}}</th>
								<th>{{questionbank.banktotal - questionbank.nobank}}</th>
								<th ng-show="{{questionbank.banktotal - questionbank.nobank != 0}}">{{questionbank.banktrue / (questionbank.banktotal - questionbank.nobank)}}%</th>
								<th ng-show="{{questionbank.banktotal - questionbank.nobank == 0}}">用户未开始做题</th>
								<th>4</th>
								<th ng-show="{{questionbank.updatetime != null}}">{{questionbank.updatetime | date:'yyyy.MM.dd  HH:mm:ss'}}</th>
								<th ng-show="{{questionbank.updatetime == null}}">{{questionbank.addtime | date:'yyyy.MM.dd  HH:mm:ss'}}</th>
							</tr>



						</tbody>
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
		</div>
	</div>
</div>
</div>


</@b.body>

</html>