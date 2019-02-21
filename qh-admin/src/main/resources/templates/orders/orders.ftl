<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="用户订单管理" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/orders/orders.js"></script>
<script src="/scripts/league/league.js"></script>
<script src="/scripts/indent/excle.js"></script>
<style>
.tab0 {
	border-bottom: 3px solid red;
	font-weight: 900;
}
</style>
<@b.body menu="sidebarmenu-orders" submenu="sidebarmenu-orders-list">
<div ng-controller="ordersController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
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
							<span>用户手机号</span> <input type="text" ng-model="mobile" />
						</div>
						<div class="select-3">
							<span>订单状态</span> <select
								ng-options="s.status as s.name for s in statusNames"
								ng-model="ordersStatus">

							</select>
						</div>
						<div class="select-3">
							<span>姓名</span> <input type="text" ng-model="receiver" />
						</div>
						<div class="select-3">
							<span>订单号</span> <input type="text" ng-model="orderno" />
						</div>
						<div class="select-3">
							<span>申请时间</span> <input type="date" name="search"
								ng-model="fromdate" />
						</div>
						<div class="select-3"
							style="font-size: 1.6rem; width: 1%; text-align: center;">

							&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span> <input type="date" name="search"
								ng-model="todate" />
						</div>

						<div style="float: left;">
							<input type="button" class="btn-lg im-key" value="检索"
								ng-click="loaddata()" />
						</div>
						<div>
							<input type="button" class="btn-lg im-key" value="导出excle"
								onclick="method5('tableExcel')" />
						</div>
					</ul>

					<div class="admin-table">

						<table id="tableExcel">
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
								<th><span class="xiangqing" ng-click="detail(o)">查看详情</span></th>
							</tr>

						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="current" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-click="loaddata()">
							</ul>
						</div>
					</div>



				</div>


			</div>
			<!-- 商品订单详情 -->
			<div class="resource" id="revise1">
				<form id="myform2">
					<h4>详细信息</h4>
					<div class="template-add">
						<div class="grade-left"
							style="border-top: 1px solid #dddddd; border-bottom: 1px solid #dddddd; padding: 10px 0;">

							<ul>
								<li>订单编号：</li>
								<li>{{order.orderno}}</li>
							</ul>
							<ul>
								<li>姓名:</li>
								<li>{{order.receiver}}</li>
							</ul>
							<ul style="height: 70px;">
								<li>收货地址：</li>
								<li style="margin-top: 5px;">{{order.address}}</li>
							</ul>
							<ul>
								<li>购买店铺：</li>
								<li>{{order.shopName}}</li>
							</ul>
							<ul>
								<li>支付金额：</li>
								<li>{{order.payaccount}}</li>
							</ul>
							<ul>
								<li>订单状态：</li>
								<li>{{order.statusName}}</li>
							</ul>
							<ul>
								<li>售后服务：</li>
								<li>{{order.refund}}</li>
							</ul>

							<ul>
								<li>快递编号：</li>
								<li>{{order.expressNo}}</li>
							</ul>
							<ul>
								<li>时间：</li>
								<li>{{order.showtime}}</li>
							</ul>
						</div>
					</div>
					<div>
						<p style="padding-top: 15px;">购买清单</p>
						<div ng-repeat="p in productList">
							<ul style="width: 100%; height: 80px; margin: 15px 0;">
								<div style="width: 20%; height: 100%; float: left;">
									<img ng-src="{{p.productImg}}"
										style="height: 100%; width: 100%;" />
								</div>
								<div class="qingdan">
									<p>名称：{{p.productName}}</p>
									<p style="float: left;">
										个数:<span>{{p.count}}</span>
									</p>
									<p style="float: left;">
										价格:<span>{{p.productPrice}}</span>
									</p>
									<p style="float: left;">
										运费:<span>{{p.freight}}</span>
									</p>
									<p style="clear: both;">
										实际支付:<span>{{p.payout}}</span>
									</p>
									<p>
										状态：<span>{{p.statusName}}</span>
									</p>
								</div>
							</ul>
							<ul class="tuihuo" ng-show="{{order.refund=='有'}}">
								<li>商品售后申请:<span>退款</span></li>
								<li>申请售后时间<span>{{p.opr.showtime}}</span></li>
								<li>申请理由:<span>{{p.opr.refundReason}}</span></li>
								<li>退款金额:<span>{{p.opr.refundMoney}}</span></li>

								<div>
									<input type="button" class="btn-lg im-key" value="同意"
										style="background: #6eecb2;"
										ng-click="updateProduct(p.id,'AGREEREFUNED')" /> <input
										type="button" class="btn-lg im-key" value="拒绝"
										style="background: #adafaad1;"
										ng-click="updateProduct(p.id,'REJECTREFUNED')" />
								</div>
							</ul>
						</div>



					</div>
				</form>
				<div class="end" style="width: 100px;">
					<input name="esc" type="reset" value="取消" ng-click="CloseDiv2()"
						class="esc" />
				</div>




			</div>
			<!-- 服务订单详情 -->
			<div id="revise" class="resource" style="height: 600px;">
				<form id="myform2">
					<h4>详细信息</h4>
					<div class="template-add">
						<div class="grade-left"
							style="border-top: 1px solid #dddddd; border-bottom: 1px solid #dddddd; padding: 10px 0;">

							<ul>
								<li>订单编号：</li>
								<li>{{order.orderno}}</li>
							</ul>
							<ul>
								<li>姓名:</li>
								<li>{{order.receiver}}</li>
							</ul>
							<ul>
								<li>手机号：</li>
								<li>{{order.mobile}}</li>
							</ul>
							<ul>
								<li>购买店铺：</li>
								<li>{{order.shopName}}</li>
							</ul>
							<ul>
								<li>服务类别：</li>
								<li>{{order.shopServeDomain}}</li>
							</ul>
							<ul>
								<li>预约时间：</li>
								<li>{{order.timeString}}</li>
							</ul>
							<ul>
								<li>支付金额：</li>
								<li>{{order.payaccount}}</li>
							</ul>
							<ul>
								<li>人数</li>
								<li>{{order.count}}</li>
							</ul>
							<ul>
								<li>售后服务：</li>
								<li>{{order.refund}}</li>
							</ul>
						</div>
					</div>
					<div>
						<p style="padding-top: 15px;">购买清单</p>
						<div ng-repeat="p in productList">
							<ul style="width: 100%; height: 80px; margin: 15px 0;">
								<div style="width: 20%; height: 100%; float: left;">
									<img ng-src="{{p.productImg}}"
										style="height: 100%; width: 100%;" />
								</div>
								<div class="qingdan">
									<p>名称:{{p.productName}}</p>
									<p>
										人数：{{p.count}}</span>
									</p>
									<p style="float: left;">
										价格:<span>{{p.productPrice}}</span>
									</p>
									<p style="float: left;">
										实际支付:<span>{{p.payout}}</span>
									</p>

									<p style="clear: both;">状态：{{p.statusName}}</p>
								</div>
							</ul>
							<ul class="tuihuo" ng-show="{{order.refund=='有'}}">
								<li>售后申请:<span>取消服务</span></li>
								<li>申请理由:<span></span></li>
								<li>退款金额:<span>{{p.payout}}</span></li>
								<div>
									<input type="button" class="btn-lg im-key" value="同意"
										style="background: #6eecb2;"
										ng-click="updateServe(p.id,'AGREEREFUNED')" /> <input
										type="button" class="btn-lg im-key" value="拒绝"
										style="background: #adafaad1;"
										ng-click="updateServe(p.id,'REJECTREFUNED')" />
								</div>
							</ul>
						</div>
					</div>
				</form>
				<div class="end" style="width: 100px;">
					<input name="esc" type="reset" value="取消" ng-click="CloseDiv2();"
						class="esc" />
				</div>




			</div>
		</div>
	</div>
</div>
</div>

<style type="text/css">
.details-frame-content {
	height: 50px;
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

.manage .managr-dianpu .select-3 {
	width: 12%;
	margin-left: 18px;
	margin-right: 0;
}

.manage .managr-dianpu .select-3 input {
	border-radius: 0;
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
	width: 30%;
	height: 660px;
	position: absolute;
	left: 25%;
	top: 20%;
	display: none;
	padding: 20px;
}

.resource .grade-left ul {
	width: 100%;
	margin: 3px 0;
	height: 30px;
	line-height: 30px;
}

.resource .grade-left ul li:nth-child(1) {
	float: left;
	font-size: 1.5rem;
}

.resource .grade-left ul li:nth-child(2) {
	float: right;
	color: #999;
	font-size: 1.5rem;
}

.admin-table table tr form span {
	font-size: 1.4rem;
}

.tuihuo {
	border-bottom: 1px solid #e8e6e6;
}

.tuihuo li {
	font-size: 16px;
	line-height: 30px;
}

.tuihuo input {
	font-size: 16px;
	margin: 10px;
	padding: 6px;
}

.qingdan {
	width: 80%;
	height: 100%;
	float: right;
}

.qingdan p {
	font-size: 16px;
	text-indent: 1rem;
	margin: 0;
}

.qingdan p span {
	color: red;
}
</style>

</@b.body>

</html>