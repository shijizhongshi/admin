<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
	<@h.header title="用户提现" />
	<link rel="stylesheet" href="/styles/admin.css" />

	<script src="/scripts/admin.js"></script>
	<script src="/scripts/league/league.js"></script>
	<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-tixian">
		<div>
			<div class="details" style="width: 100%">
				<div class="details-nav">
					<ul>
						<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
						<li>/</li>
						<li>用户管理</li>
						<li>/</li>
						<li>用户提现
						
						</li>
					</ul>
				</div>
				<div class="details-frame">
					<div class="details-frame-content" id="details-frame-content">
						<ul>
							<li  style="border-bottom:3px solid red;font-weight: 900;">核审列表</li>
						</ul>
					</div>
					<div id="guanli">
					<!-- 核审列表 -->
						<div class="manage">
							<ul style="height: 80px;" class="managr-dianpu">
							<div class=" select-3">
									<img src="/images/sjk-xl.png"> <span>核审状态</span> <select>
										<option disabled="" selected="" style="display: none;" value=""></option>
										<option value="">通过</option>
										<option value="">未通过</option>
									</select>
								</div>
								<div class="select-3">
									<span>手机号</span>
									<input type="text"/>
								</div>
						<div class="select-3">
							<span>申请时间</span>
								<input type="date" name="search"/>
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search"/>
						</div>
			
			<div>
					<input type="button" class="btn-lg im-key"
						value="检索" />
				</div>
							</ul>
				
							<div class="admin-table">

								<table>
									<tr>
										<th>手机号</th>
										<th>姓名</th>
										<th>申请提现金额</th>
										<th>账户类型</th>
										<th>申请时间</th>
										<th>操作</th>
									</tr>

									<tr>
										<th>手机号</th>
										<th>姓名</th>
										<th>申请提现金额</th>
										<th>账户类型</th>
										<th>申请时间</th>
										<th><form ><span>允许</span><input type="radio" value="允许" style="background:#7bc472;" name="caozuo" /><span>&nbsp;拒绝</span><input type="radio" value="拒绝"  name="caozuo"/></form></th>
									</tr>
									
								</table>

							</div>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total" ng-model="current" items-per-page="pageSize" max-size="5" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
								</ul>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>

		<style type="text/css">

.details-frame-content {
				height: 50px;
				padding:0 29px;}
			
			.details-frame-content ul li {
				float: left;
				height: 50px;
				text-align: center;
				line-height: 50px;
				font-size: 1.5rem;
				cursor: pointer;
				
				margin-right:15px;
			}
.leibie{background:#F5F6F8;width: 50%;text-align: center; }
.grade-text textarea {height:70px;width: 50%;}
.manage .managr-dianpu{padding-left:20px;}
.manage .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}
@media screen and (min-width: 1601px) {.manage .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}}
@media screen and (max-width: 1600px)  {.manage .managr-dianpu .select-3{width: 14%;margin-left: 18px;margin-right: 0;}}
@media screen and (max-width: 1400px)  {.manage .managr-dianpu .select-3{width: 20%;margin-left: 18px;margin-right: 0;}}
.details-frame-heshen{width:100%;padding:0 30px;background: white;height:auto;border-bottom: solid 1px #EEEFF1;}

.details-frame-heshen .select-2{width: 10%;float: left;}
.details-frame-heshen .select-2 input {border-radius:0;}
 .admin-table table tr form span{font-size:1.4rem;}

		</style>
	
	</@b.body>

</html>