<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
	<@h.header title="用户订单管理" />
	<link rel="stylesheet" href="/styles/admin.css" />
	<script src="/scripts/admin.js"></script>
	<script src="/scripts/orders/orders.js"></script>
	<script src="/scripts/league/league.js"></script>
	<style>
	.tab0{
     border-bottom:3px solid red;
     font-weight:900;
	}
	</style>
	<@b.body menu="sidebarmenu-orders" submenu="sidebarmenu-orders-list">
		<div ng-controller="ordersController">
			<div class="details" style="width: 100%">
				<div class="details-nav">
					<ul>
						<li><img src="/images/sjk-home.png"  />我的主页</li>
						<li>/</li>
						<li>订单管理</li>
						<li>/</li>
						<li>订单管理</li>
					</ul>
				</div>
				<div class="details-frame">
					<div class="details-frame-content" id="details-frame-content">
						<ul>
							<li ng-click="onmousedowngo(0)" ng-class="{'tab0':tab0==0}">商品订单</li>
							<li ng-click="onmousedowngo(2)" ng-class="{'tab0':tab0==2}">服务订单</li>
						</ul>
					</div>
					<div id="guanli">
						<div class="manage">
							<ul style="height: 80px;" class="managr-dianpu">
								<div class="select-3">
									<span>手机号</span>
									<input type="text" ng-model="mobile"/>
								</div>
						<div class="select-3">
							<span>申请时间</span>
								<input type="date" name="search" ng-model="fromdate"/>
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" ng-model="todate"/>
						</div>
			
			<div>
					<input type="button" class="btn-lg im-key"
						value="检索" ng-click="loaddata()"/>
				</div>
							</ul>
							
							<div class="admin-table">

								<table>
									<tr>
										<th>手机号</th>
										<th>姓名</th>
										<th>订单号</th>
										<th>支付金额</th>
										<th>订单状态</th>
										<th>售后服务</th>
										<th>时间</th>
										<th>详细信息</th>
									</tr>

									<tr ng-repeat="o in list">
										<th>{{o.userMobile}}</th>
										<th>{{o.receiver}}</th>
										<th>{{o.orderno}}</th>
										<th>{{o.payaccount}}</th>
										<th>{{o.statusName}}</th>
										<th>{{o.refund}}</th>
										<th>{{o.showtime}}</th>
										<th><span class="xiangqing" onclick="showDiv2()">查看详情</span></th>
									</tr>
									
								</table>

							</div>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total" ng-model="current" items-per-page="pageSize" max-size="5" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;" ng-change="loaddata()">
								</ul>
							</div>

						
						</div>
                           <!-- 服务订单 -->
						<div class="manage" style="display: none;">
						     	<ul style="height: 80px;" class="managr-dianpu">
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
										
										<th>支付金额</th>
									
										<th>售后服务</th>
										<th>时间</th>
										<th>操作</th>
										<th>详细信息</th>
									</tr>

									<tr>
										<th>手机号</th>
										<th>姓名</th>
									
										<th>支付金额</th>
									
										<th>售后服务</th>
										<th>时间</th>
										<th><form ><span>允许</span><input type="radio" value="允许" style="background:#7bc472;" name="caozuo" /><span>&nbsp;拒绝</span><input type="radio" value="拒绝"  name="caozuo"/></form></th>
										<th><span class="xiangqing" onclick="showDiv2()">查看详情</span></th>
									</tr>
									
								</table>

							</div>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total" ng-model="current" items-per-page="pageSize" max-size="5" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
								</ul>
							</div>

						
						
						
					
						</div>
				<!-- 查看详情 -->
					<div id="revise" class="resource">
								<form id="myform2">
									<h4>详细信息</h4>
						<div class="template-add">
										<div class="grade-left" style="border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;padding: 10px 0;">
                                       
                                        <ul>
                                        	<li>订单编号：</li>
                                        	<li>e464s454s65844545</li>
                                        </ul>
                                         <ul>
                                        	<li>姓名:</li>
                                        	<li>猪蹄子</li>
                                        </ul>
                                         <ul style="height: 70px;">
                                        	<li>收货地址：</li>
                                        	<li style="margin-top: 5px;">地址地址地址地址地址地址地址地址地址地址地址</li>
                                        </ul>
                                         <ul>
                                        	<li>购买店铺：</li>
                                        	<li>111</li>
                                        </ul>
                                         <ul>
                                        	<li>支付金额：</li>
                                        	<li>121</li>
                                        </ul>
                                         <ul>
                                        	<li>订单状态：</li>
                                        	<li>完成</li>
                                        </ul>
                                        <ul>
                                        	<li>售后服务：</li>
                                        	<li>无</li>
                                        </ul>
                                        <ul>
                                        	<li>申请售后时间：</li>
                                        	<li>110</li>
                                        </ul>
                                        <ul>
                                        	<li>快递编号：</li>
                                        	<li>2222</li>
                                        </ul>
                                        <ul>
                                        	<li>时间：</li>
                                        	<li>10.25</li>
                                        </ul>
	                                       </div>
								</div>
								<div>
								<p  style="padding-top: 15px;">购买清单</p>
								<ul style="width: 100%;height: 80px;margin: 15px 0;">
								<div style="width:20%;height:100%;float: left;"><img src="/images/sjk-home.png" style="height:100%;width:100%;" /></div>
								<div style="width:75%;height:100%;float: right;"><p>名称：</p>
								<p>规格：</p>
								<p><span>价格：</span> <span style="font-size:1.3rem ;color: #B1B1B1;">&nbsp;原价：</span></p>
								</div>
								</ul>
		
								</div>
								</form>
								<div class="end" style="width: 100px;">
									<input name="esc" type="reset" value="取消" onclick="CloseDiv2();formReset2()" class="esc" />
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
.manage .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}
.details-frame-heshen{width:100%;padding:0 30px;background: white;height:auto;}
.details-frame-heshen .select-3{width:15%; }
.details-frame-heshen .select-2{width: 10%;float: left;}
.details-frame-heshen .select-2 input {border-radius:0;}
.resource{width: 30%;
				height: 660px;
				position: absolute;
				left: 25%;
				top: 20%;
				display: none;padding:20px;}

.resource .grade-left ul{width:100%;margin:3px 0;height:30px;line-height: 30px;}
.resource .grade-left ul li:nth-child(1){float:left;font-size: 1.5rem;}
.resource .grade-left ul li:nth-child(2){float:right;color:#999;font-size: 1.5rem;}
 .admin-table table tr form span{font-size:1.4rem;}
		</style>
	
	</@b.body>

</html>