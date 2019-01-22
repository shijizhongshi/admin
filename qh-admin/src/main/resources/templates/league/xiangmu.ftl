<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="服务店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/xiangmu.js"></script>
<script src="/scripts/league/league.js"></script>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-xiangmu">
<div ng-controller="shopServeController">
	<div>
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>加盟商管理</li>
					<li>/</li>
					<li>店铺服务</li>
					<li>/</li>
					<li>项目管理</li>
				</ul>
			</div>
			<div class="details-frame">
				<div class="details-frame-content" id="details-frame-content">
					<ul>
						<li onmousedown="go(0)"
							style="border-bottom: 3px solid red; font-weight: 900;">商品管理</li>
						<li onmousedown="go(1)">项目管理</li>
					</ul>
				</div>
				<div id="guanli">
					<!-- 商品管理 -->
					<div class="manage">
						<ul style="height: 80px;" class="managr-dianpu">
							<div class="select-3">
								<span>商品名称</span> <input type="text" />
							</div>
							<div class="select-3">
								<span>店铺名称</span> <input type="text" />
							</div>
							<div class=" select-3">
								<img src="/images/sjk-xl.png" /> <span>商品分类</span> <select>
									<option disabled selected style='display: none;'></option>
									<option></option>
									<option></option>
								</select>
							</div>

							<div>
								<input type="button" class="btn-lg im-key" ng-click=""
									value="检索" ng-click="" />
							</div>
						</ul>
						<ul class="show">
							<li ng-click="deletetemplate()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
							<li style="float: right; margin-right: 100px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>商品名称</th>
									<th>商品价格</th>
									<th>优惠价格</th>
									<th>分类</th>
									<th>所属店铺</th>
									<th>商品图片</th>
									<th>推荐级别</th>
									<th>展示时间</th>
									<th>详细信息</th>
									<th>审核状态</th>
									<th>操作</th>
								</tr>
								<tr >
									<th>商品名称</th>
									<th>商品价格</th>
									<th>优惠价格</th>
									<th>分类</th>
									<th>所属店铺</th>
									<th>商品图片</th>
									<th>推荐级别</th>
									<th>展示时间</th>
									<th><span class="xiangqing" onclick="showDiv2()">查看详情</span></th>
									<th>审核状态</th>
									<th><input type="button" class="btn-lg im-key" value="允许"
										style="background: #7bd88b;" /> <input type="button"
										class="btn-lg im-key" value="拒绝" style="background: #8e9a91;" />
									</th>
								</tr>
							</table>
						</div>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="current" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="courseBases()">
							</ul>
						</div>
					</div>

					<!-- 项目管理 -->
					<div class="manage" style="display: none;">
					<form>
						<ul style="height: 80px;" class="managr-dianpu">
							<div class="select-3">
								<span>项目名称</span> <input type="text" ng-model="serveName"/>
							</div>
							<div class="select-3">
								<span>店铺名称</span> <input type="text" ng-model="shopName"/>
							</div>
							<div class=" select-3">
								<img src="/images/sjk-xl.png" /> <span>项目分类</span> 
								<select ng-model="serveType" >
									<option value="">查看全部</option>
									<option ng-repeat="s in servetypelist"  value="{{s.name}}" ng-selected="serveType==s.name">{{s.name}}</option>
									
								</select>
							</div>
							<div class=" select-3">
								<img src="/images/sjk-xl.png" /> <span>审核状态</span> 
								<select ng-model="serveStatus" >
									<option  value="">查看全部</option>
									<option  value="0" ng-selected="serveStatus==0">待审批</option>
									<option  value="1" ng-selected="serveStatus==1">已通过</option>
									<option  value="2" ng-selected="serveStatus==2">已下架</option>
									<option  value="3" ng-selected="serveStatus==3">用户已删除</option>
									<option  value="4" ng-selected="serveStatus==4">未通过</option>
								</select>
							</div>

							<div>
								<input type="button" class="btn-lg im-key" ng-click="serveList()"
									value="检索" />
							</div>
						</ul>
						</form>
						<ul class="show">
							<li ng-click="deletetemplate()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>项目名称</th>
									<th>项目价格</th>
									<th>优惠价格</th>
									<th>分类</th>
									<th>所属店铺</th>
									<th>项目图片</th>
									<th>推荐级别</th>
									<th>审核状态</th>
									<th>操作</th>
								</tr>
								<tr ng-repeat="s in servelist" ng-click="checkserve(s)"
									ng-class="{'selected':selected==s}">
									<th>{{s.serveName}}</th>
									<th>{{s.price}}</th>
									<th>{{s.discountPrice}}</th>
									<th>{{s.serveType}}</th>
									<th>{{s.shopName}}</th>
									<th><img src="{{s.imgUrl}}"></th>
									<th>{{s.hot}}</th>
									<th>{{s.Status}}</th>
									<th>
										<span class="xiangqing" ng-click="checkedAll(s)">查看详情</span>
										<input ng-click="updateserve('',1,s.id)"  ng-show="yunxu==true"  type="button" class="btn-lg im-key" value="允许"
										style="background: #7bd88b;" /> 
										<input ng-click="updateserve('',4,s.id)"  ng-show="jujue==true" id="no"  type="button"
										class="btn-lg im-key" value="拒绝" style="background: #8e9a91;" />
									</th>
								</tr>
							</table>
						</div>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="current" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="serveList()">
							</ul>
						</div>
					</div>


				</div>
			</div>
			<!-- 商品详情 -->
			<div id="revise" class="resource" style="display: none;">
				<form id="myform2">
					<h3>商品详情</h3>
					<div class="template-add">
						<div class="grade-left" style="padding-right: 3%;">

							<div class="costs-uploadfile-div">
								商品照片：
								<div class="costs-img">
									<img src="" name="营业执照" />
								</div>
							</div>
							<ul>
								<li>是否是药品</li>
								<li>是</li>
							</ul>
							<ul>
								<li>商品名称</li>
								<li>11111</li>
							</ul>
							<ul>
								<li>商品价格</li>
								<li>2</li>
							</ul>
							<ul>
								<li>优惠价格</li>
								<li>2</li>
							</ul>
							<ul>
								<li>所属分类</li>
								<li>5466</li>
							</ul>
							<ul>
								<li>所属店铺</li>
								<li>5466</li>
							</ul>
							<ul style="height: 70px;">
								<li>其他信息:</li>
								<li><span>中医推拿&nbsp;</span><span>小儿推拿&nbsp;</span></li>
							</ul>
						</div>



						<div class="grade-center">
							<div class=" select-2">
								<img src="/images/sjk-xl.png" /> <span>推荐级别<i
									class="bitian">*</i></span> <select>
									<option disabled selected style='display: none;'></option>
									<option></option>
									<option></option>
								</select>
							</div>
							<div class="select-2">
								<span>展示时间<i class="bitian">*</i></span> <input type="date"
									name="search" ng-model="fromdate"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>

							<div class="select-2">
								<span></span> <input type="date" name="search" ng-model="todate"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
						</div>
				</form>
				<div class="end">
					<input name="git" type="submit" value="提交"
						style="background: #5ED8A9;" /> <input name="esc" type="reset"
						value="取消" onclick="CloseDiv2();formReset2()" class="esc" />
				</div>
			</div>
		</div>
		<!-- 项目详情 -->
		<div id="add" class="resource" style="display: none;">
			<form id="myform">
				<h3>项目详情</h3>
				<div class="template-add">
					<div class="grade-left" style="padding-right: 3%;">

						<div class="costs-uploadfile-div">
							项目照片：
							<div class="costs-img">
								<img src="{{s.imgUrl}}" name="营业执照" />
							</div>
						</div>
						<ul>
							<li>项目名称</li>
							<li>{{s.serveName}}</li>
						</ul>
						<ul>
							<li>项目类别</li>
							<li>{{s.serveType}}</li>
						</ul>
						<ul>
							<li>商品价格</li>
							<li>{{s.price}}</li>
						</ul>
						<ul>
							<li>优惠价格</li>
							<li>{{s.discountPrice}}</li>
						</ul>
						<ul>
							<li>所属店铺</li>
							<li>{{s.shopName}}</li>
						</ul>
					</div>



					<div class="grade-center">

						<div class="grade-text">
							<span>服务详情:</span>
							<div style="height:100px;">
							<span>{{s.content}}</span>
							</div>
						</div>
						<div class="grade-text">
							<span>购买须知:</span>
							<div style="height:100px;">
							<span>{{s.explains}}</span>
							</div>
						</div>
						<div class=" select-2">
							<img src="/images/sjk-xl.png" /> <span>推荐级别<i
								class="bitian">*</i></span> 
							<select ng-model="s.ishot">
								<option value="1">推荐</option>
								<option value="0">不推荐</option>
							</select>
						</div>
						
					</div>
			
			<div class="end">
				<input ng-click="updateserve(s.ishot,'',s.id)" name="git" type="submit" value="提交"style="background: #5ED8A9;" />
				<input name="esc" type="reset"value="取消" onclick="CloseDiv();formReset()" class="esc" />
			</div>
	</form>



		</div>




	</div>



	<style type="text/css">
@media screen and (max-width:901px) {
	.managr-dianpu, .details-frame-heshen {
		overflow: auto;
	}
}

.resource .grade-left, .resource .grade-center, .resource .grade-right {
	width: 380px;
	float: left;
	height: auto;
}

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

.grade-text textarea {
	height: 70px;
	width: 70%;
}

.manage .managr-dianpu .select-3 {
	width: 10%;
	margin-left: 18px;
	margin-right: 0;
}

.details-frame-heshen {
	width: 100%;
	padding: 0 30px;
	background: white;
	height: auto;
	border-bottom: solid 1px #EEEFF1;
}

@media screen and (max-width: 901px) {
	.manage .managr-dianpu .select-3, .details-frame-heshen .select-3,
		.details-frame-heshen .select-2 {
		width: 90% !important;
	}
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
	height: auto;
	position: absolute;
	left: 15%;
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

.resource .grade-left  ul p li {
	color: #999;
}

.resource .grade-center .select-2 {
	width: 70%;
}

.biaoqian {
	width: 80%;
	height: 80px;
}

.biaoqian ul li {
	float: left;
	background: #EDEEF0;
	margin-right: 5px;
}

.biaoqian i {
	color: #B1B1B1;
	font-weight: 100;
}

.end {
	float: none;
	margin-left: auto;
}
</style>

</div>
</@b.body>
</html>