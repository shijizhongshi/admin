<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="服务店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/xiangmu.js"></script>
<script src="/scripts/league/shangpinguanli.js"></script>
<script src="/scripts/league/common.js"></script>
<style>
.tab0 {
	border-bottom: 3px solid red;
	font-weight: 900;
}
</style>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-xiangmu">


<div ng-controller="commonController">
	<input type="hidden" value="${shopId}" id="shopId">

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

		<div ng-controller="shopdrugControllered">
			<div class="details-frame" id="drugshow" style="display: none;">
				<div class="details-frame-content" id="details-frame-content">
					<ul>
						<li ng-click="go(0)" class="tab0">商品管理</li>
						<li ng-click="go(1)">项目管理</li>
					</ul>
				</div>
				<div id="guanli">

					<!-- 商品管理 -->
					<div class="manage">

						<form>
							<ul style="height: 80px;" class="managr-dianpu">
								<div class="select-3" style="width: 10%;margin-right:5px">
									<span>商品名称</span> <input type="text" placeholder="请输入商品名称"  ng-model="drugName" />
								</div>
								<div class="select-3" style="width: 10%;margin-right:5px">
									<span>店铺名称</span> <input type="text" placeholder="请输入店铺名称"  ng-model="shopName" />
								</div>
								<div class="select-3" style="width: 10%;margin-right:5px" ng-mouseleave="subcategoryList()">
									<img src="/images/sjk-xl.png" /> <span>商品大类别</span> <select
										ng-model="categoryName">
										<option value="">查看全部</option>
										<option ng-repeat="cat in categorylist"
											value="{{cat.categoryName}}"
											ng-selected="categoryName==cat.categoryName">{{cat.categoryName}}</option>
									</select>
								</div>
								<div class="select-3" style="width: 10%;margin-right:5px" ng-mouseenter="subcategoryList()">
									<img src="/images/sjk-xl.png" /> <span>商品小类别</span> <select
										ng-model="categorySubname">
										<option value="">查看全部</option>
										<option ng-repeat="sub in subcategorylist"
											value="{{sub.subName}}"
											ng-selected="categorySubname==sub.subName">{{sub.subName}}</option>
									</select>
								</div>
								<div class="select-3" style="width: 10%;margin-right:5px">
									<img src="/images/sjk-xl.png" /> <span>审核状态</span> <select
										ng-model="drlimits">
										<option ng-selected="drlimits==''" value="">查看全部</option>
										<option ng-selected="drlimits=='0'" value="0">未审核</option>
										<option ng-selected="drlimits=='1'" value="1">已通过</option>
										<option ng-selected="drlimits=='2'" value="2">未通过</option>

									</select>
								</div>



								<div>
									<input type="button" class="btn-lg im-key"
										ng-click="drugList()" value="立即检索" />
								</div>
							</ul>
						</form>

						<ul class="show">
							<li ng-click="deletetemplate()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除商品</li>
							<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>商品名称</th>
									<th>分类</th>
									<th>所属店铺</th>
									<th>商品图片</th>
									<th>推荐级别</th>
									<th>审核状态</th>
									<th>操作</th>

								</tr>
								<form>
									<tr ng-repeat="d in druglist" ng-click="checkdrug(d)"
										ng-class="{'selected':selected==d}">
										<th>{{d.drugName}}</th>
										<th>{{d.categorySubname}}</th>
										<th>{{d.shopName}}</th>
										<th><img ng-src="{{d.imgUrl}}"></th>
										<th>{{d.recommend}}</th>
										<th>{{d.limits}}</th>
										<th><span class="xiangqing" ng-click="checkedAlldrug(d)"><input
												type="hidden" ng-model="d" />查看详情</span> <input
											ng-click="updatedrug('',1,'','','',d.id)"
											ng-show="d.yunxu1==true" type="button" class="btn-lg im-key"
											value="允许" style="background: #7bd88b;" /> <input
											ng-click="updatedrug('',2,'','','',d.id)"
											ng-show="d.jujue1==true" id="no" type="button"
											class="btn-lg im-key" value="拒绝" style="background: #8e9a91;" />
										</th>
									</tr>
								</form>
							</table>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total"
									ng-model="page" items-per-page="pageSize" max-size="5"
									class="pagination-sm" previous-text="&lsaquo;"
									next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
									ng-click="drugList()">
								</ul>
							</div>
						</div>

					</div>

				</div>
			</div>
			<!-- 商品详情 -->
			<div id="revise" class="resource" style="display: none;">

				<form id="myform2">
					<h3>商品详情</h3>
					<div class="template-add">
						<div class="grade-left" style="width: auto;">

							<div class="costs-uploadfile-div">
								商品照片：
								<div class="costs-img" style="display: -webkit-inline-box;">
									<img ng-src="{{d.imgUrl}}" name="商品照片" />
								</div>
							</div>
							<div style="float: left; width: 300px; padding-right: 30px;">
								<ul>
									<li>药品名称</li>
									<li>{{d.productName}}</li>
								</ul>
								<ul>
									<li>商家名称</li>
									<li>{{d.shopName}}</li>
								</ul>
								<ul>
									<li>商品的大类别</li>
									<li>{{d.categoryName}}</li>
								</ul>
								<ul>
									<li>商品的小类别</li>
									<li>{{d.categorySubname}}</li>
								</ul>
								<ul style="color: red;">
									<li>治疗功能</li>
									<li style="color: red;">{{d.healingPowers}}</li>
								</ul>
								<ul>
									<li>药品规格</li>
									<li>{{d.specification}}</li>
								</ul>
								<ul style="color: red;">
									<li>原价</li>
									<li style="color: red;">{{d.originalPrice}}</li>
								</ul>
								<ul>
									<li style="color: red;">折扣价</li>
									<li style="color: red;">{{d.discountPrice}}</li>
								</ul>
								<ul style="color: red;">
									<li>销量</li>
									<li style="color: red;">{{d.salesNumber}}</li>
								</ul>
								<ul style="color: red;">
									<li>运费</li>
									<li style="color: red;">{{d.freight}}</li>
								</ul>
								<ul>
									<li>库存</li>
									<li>{{d.stocks}}</li>
								</ul>

								<ul>
									<li>规格参数</li>
									<li>{{d.specificationParams}}</li>
								</ul>

								<ul>
									<li>生产厂家</li>
									<li>{{d.manufacturer}}</li>
								</ul>
								<ul>
									<li>药品标识</li>
									<li>{{d.drugSign}}</li>
								</ul>
								<ul>
									<li>批准文号</li>
									<li>{{d.approvalNumber}}</li>
								</ul>
								<ul style="color: red;">
									<li>有效期</li>
									<li style="color: red;">{{d.periodValidity}}</li>
								</ul>
								<ul>
									<li>存储</li>
									<li>{{d.storages}}</li>
								</ul>
								<ul>
									<li>单位</li>
									<li>{{d.units}}</li>
								</ul>
								<ul>
									<li>适宜人群</li>
									<li>{{d.properPeople}}</li>
								</ul>


							</div>
							<div
								style="float: left; width: 300px; margin-top: -150px; padding-right: 30px;">
								<ul>
									<li>成分</li>
									<li>{{d.elements}}</li>
								</ul>
								<ul>
									<li>性状</li>
									<li>{{d.characters}}</li>
								</ul>
								<ul>
									<li>包装</li>
									<li>{{d.packagings}}</li>
								</ul>
								<ul>
									<li>适应症</li>
									<li>{{d.indication}}</li>
								</ul>
								<ul>
									<li>用量</li>
									<li>{{d.dosage}}</li>
								</ul>
								<ul>
									<li>不良症状</li>
									<li>{{d.badSymptom}}</li>
								</ul>
								<ul>
									<li>禁忌</li>
									<li>{{d.taboo}}</li>
								</ul>
								<ul>
									<li>注意事项</li>
									<li>{{d.attentionMatter}}</li>
								</ul>
								<ul>
									<li>药物相互作用</li>
									<li>{{d.drugInteractions}}</li>
								</ul>
								<ul>
									<li>药理作用</li>
									<li>{{d.drugAction}}</li>
								</ul>
								<ul>
									<li>条形码</li>
									<li>{{d.shapCode}}</li>
								</ul>
								<ul>
									<li>友情提示</li>
									<li>{{d.friendlyHint}}</li>
								</ul>
								<ul>
									<li>状态</li>
									<li>{{d.statu}}</li>
								</ul>
								<ul>
									<li>限时规定的天数</li>
									<li>{{d.deadlines}}</li>
								</ul>
								<ul>
									<li>商品详情</li>
									<li>{{d.drugDetail}}</li>
								</ul>
								<ul>
									<li>审批的时间</li>
									<li>{{d.approvalTime | date:'yyyy.MM.dd HH:mm:ss'}}</li>
								</ul>
							</div>
						</div>


						<div class="grade-center">
							<div class=" select-2">
								<img src="/images/sjk-xl.png" /> <span>推荐级别<i
									class="bitian">*</i></span> <select ng-model="drisrecommend">
									<option value="">查看全部</option>
									<option ng-selected="d.isrecommend==0" value="0">不推荐</option>
									<option ng-selected="d.isrecommend==1" value="1">推荐</option>
								</select>
							</div>
							<div class=" select-2">
								<img src="/images/sjk-xl.png" /> <span>是否限时抢购<i
									class="bitian">*</i></span> <select ng-model="dristimes">
									<option value="">查看全部</option>
									<option ng-selected="d.istimes==0" value="0">不限时抢购</option>
									<option ng-selected="d.istimes==1" value="1">限时抢购</option>
								</select>
							</div>
							<div class=" select-2">
								<img src="/images/sjk-xl.png" /> <span>是否促销<i
									class="bitian">*</i></span> <select ng-model="drissales">
									<option value="">查看全部</option>
									<option ng-selected="d.issales==0" value="0">不促销</option>
									<option ng-selected="d.issales==1" value="1">促销</option>
								</select>
							</div>
							<div class=" select-2">
								<img src="/images/sjk-xl.png" /> <span>是否热卖<i
									class="bitian">*</i></span> <select ng-model="drishot">
									<option value="">查看全部</option>
									<option ng-selected="d.ishot==0" value="0">不热卖</option>
									<option ng-selected="d.ishot==1" value="1">热卖</option>
								</select>
							</div>

						</div>
				</form>
				<div class="end">
					<input name="git" type="submit" value="提交"
						ng-click="updatedrug(drishot,'',dristimes,drisrecommend,drissales,d.id)"
						style="background: #5ED8A9;" /> <input name="esc" type="reset"
						value="取消" onclick="CloseDiv2();formReset2()" class="esc" />
				</div>
			</div>

		</div>
	</div>


	<div ng-controller="shopServeControllered">
		<div class="details-frame" id="serveshow" style="display: none;">
			<div class="details-frame-content" id="details-frame-content">
				<ul>
					<li ng-click="go(0)">商品管理</li>
					<li ng-click="go(1)" class="tab0">项目管理</li>
				</ul>
			</div>
			<div id="guanli">



				<!-- 项目管理 -->
				<div class="manage">
					<form id="myform1">
						<ul style="height: 80px;" class="managr-dianpu">
							<div class="select-3" style="width: 10%;margin-right:5px">
								<span>项目名称</span> <input type="text" placeholder="请输入项目名称"  ng-model="serveName" />
							</div>
							<div class="select-3" style="width: 10%;margin-right:5px">
								<span>店铺名称</span> <input type="text" placeholder="请输入店铺名称"  ng-model="shopName" />
							</div>
							<div class="select-3" style="width: 10%;margin-right:5px">
								<img src="/images/sjk-xl.png" /> <span>项目分类</span> <select
									ng-model="serveType">
									<option value="">查看全部</option>
									<option ng-repeat="s in servetypelist" value="{{s.name}}"
										ng-selected="serveType==s.name">{{s.name}}</option>

								</select>
							</div>
							<div class="select-3" style="width: 10%;margin-right:5px">
								<img src="/images/sjk-xl.png" /> <span>审核状态</span> <select
									ng-model="serveStatus">
									<option ng-selected="serveStatus==''" value="">查看全部</option>
									<option ng-selected="serveStatus=='0'" value="0">待审批</option>
									<option ng-selected="serveStatus=='1'" value="1">已通过</option>
									<option ng-selected="serveStatus=='2'" value="2">已下架</option>
									<option ng-selected="serveStatus=='3'" value="3">用户已删除</option>
									<option ng-selected="serveStatus=='4'" value="4">未通过</option>
								</select>
							</div>

							<div>
								<input type="button" class="btn-lg im-key"
									ng-click="serveList()" value="立即检索" />
							</div>
						</ul>
					</form>
					<ul class="show">
						<li ng-click="deletetemplate()" style="background: #F86846;"><span
							class="glyphicon glyphicon-trash"></span>&nbsp;删除项目</li>
						<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
						<li><span class="glyphicon glyphicon-sort-by-attributes"
							class="move-down"></span>&nbsp;下移</li>
						<li style="float: right; margin-right: 20px; background: none;"><img
							src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
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
								<th><img ng-src="{{s.imgUrl}}"></th>
								<th>{{s.hot}}</th>
								<th>{{s.Status}}</th>
								<th><span class="xiangqing" ng-click="checkedAll(s)">查看详情</span>
									<input ng-click="updateserve('',1,s.id)" ng-show="s.yunxu"
									type="button" class="btn-lg im-key" value="允许"
									style="background: #7bd88b;" /> <input
									ng-click="updateserve('',4,s.id)" ng-show="s.jujue" id="no"
									type="button" class="btn-lg im-key" value="拒绝"
									style="background: #8e9a91;" /></th>
							</tr>
						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="servetotal"
								ng-model="servecurrent" items-per-page="servepageSize"
								max-size="5" class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="serveList()">
							</ul>
						</div>
					</div>

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
								<img ng-src="{{s.imgUrl}}" name="营业执照" />
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
							<div style="height: 100px;">
								<span>{{s.content}}</span>
							</div>
						</div>
						<div class="grade-text">
							<span>购买须知:</span>
							<div style="height: 100px;">
								<span>{{s.explains}}</span>
							</div>
						</div>
						<div class=" select-2">
							<img src="/images/sjk-xl.png" /> <span>推荐级别<i
								class="bitian">*</i></span> <select ng-model="s.ishot">
								<option value="1">推荐</option>
								<option value="0">不推荐</option>
							</select>
						</div>

					</div>

					<div class="end">
						<input ng-click="updateserve(s.ishot,'',s.id)" name="git"
							type="submit" value="提交" style="background: #5ED8A9;" /> <input
							name="esc" type="reset" value="取消"
							onclick="CloseDiv();formReset()" class="esc" />
					</div>
			</form>




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

.resource .grade-left, .resource .grade-center, .resource .grade-right {
	width: 250px;
	float: left;
	height: auto;
}

.details-frame-content {
	height: 50px;
	padding: 0 29px;
	overflow-y:hidden;
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
	height: 600px;
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

.resource  ul {
	width: 100%;
	margin: 3px 0;
	height: auto;
	padding: 5px 0;
	clear: both;
}

.resource  ul li:nth-child(1) {
	float: left;
	font-size: 12px;
}

.resource  ul li:nth-child(2) {
	float: right;
	color: #999;
	font-size: 12px;
}

.resource   ul p li {
	color: #999;
}

.resource .grade-center .select-2 {
	width: 95%;
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

</@b.body>
</html>