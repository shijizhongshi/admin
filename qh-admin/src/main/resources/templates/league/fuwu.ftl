<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="服务店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/league/fuwu.js"></script>
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/league.js"></script>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-fuwu">
<div ng-controller="fuwushopController">
	<div>
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>加盟商管理</li>
					<li>/</li>
					<li>服务店铺</li>
				</ul>
			</div>
			<div class="details-frame">
				<div class="details-frame-content" id="details-frame-content">
					<ul>
						<li onmousedown="go(0)"
							style="border-bottom: 3px solid red; font-weight: 900;">服务店铺</li>
						<li onmousedown="go(1)">核审管理</li>
					</ul>
				</div>
				<div id="guanli">
					<div class="manage">
					<form>
						<ul style="height: 80px;" class="managr-dianpu">
						
							<div class="select-3">
								<span>店铺名称</span> <input type="text" ng-model="shopName"/>
							</div>
							<div class=" select-3">
								<img src="/images/sjk-xl.png" /> <span>所在地区</span> <select ng-model="address">
									<option value="">查看全部</option>
									<option value="山东"></option>
									<option></option>
								</select>
							</div>
							<div class=" select-3">
								<img src="/images/sjk-xl.png" /> <span>推荐级别</span> <select ng-model="isrecommend">
									<option value="0">不推荐</option>
									<option value="1">推荐</option>
								</select>
							</div>
							<div>
								<input type="button" class="btn-lg im-key" ng-click="shopList()"
									value="检索"  />
							</div>
							
						</ul>
						</form>
						<ul style="height: 60px;" class="show">

							<li  style="background: #9DE879;"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
							<li  style="background: #F9CD33;"><span
								class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
							<li  style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
							<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;项目管理</li>
							<li style="float: right; margin-right: 100px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>店铺名称</th>
									<th>地址</th>
									<th>推荐级别</th>
									<th>营业类别</th>
									<th>门头照片</th>
									<th>负责人</th>
									<th>联系电话</th>
									<th>创建时间</th>
									<th>详细信息</th>
								</tr>

								<tr ng-repeat="s in shoplist" ng-click="checkshop(s)"
									ng-class="{'selected':selected==s}">
									<th>{{s.shopName}}</th>
									<th>{{s.address}}</th>
									<th>{{s.recommend}}</th>
									<th>{{s.serveDomain}}</th>
									<th><img ng-src="{{s.doorHeadUrl}}"></th>
									<th>{{s.leaderName}}</th>
									<th>{{s.leaderMobile}}</th>
									<th>{{s.addtime}}</th>
									<th><span class="xiangqing" ng-click="checkedAll(s)">查看详情</span></th>
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

						<!--弹窗-->
						<div class="poop" id="add">
							<form id="myform">
								<h3>添加店铺</h3>
								<div class="template-add">
									<div class="grade-left" style="padding-right: 5%;">
										<div class="select-2">
											<span>请输入店铺名称</span> <input type="text" />
										</div>

										<div class=" select-2">
											<img src="/images/sjk-xl.png" /> <span>店铺类型</span> <select>
												<option disabled selected style='display: none;'>服务店铺</option>
												<option></option>
												<option></option>
											</select>
										</div>

										<div class="select-2">
											<span>负责人</span> <input type="text" placeholder="请输入负责人姓名" />
										</div>
										<div class="select-2">
											<span>联系电话</span> <input type="text" placeholder="请输入联系电话" />
										</div>
										<div class="select-2">
											<span>身份证号</span> <input type="text" placeholder="请输入负责人身份证号" />
										</div>
										<div class="grade-text">
											<span>店铺地址</span>
											<textarea></textarea>
										</div>
										<p>营业类别</p>
										<div class="leibie">
											<ul>
												<li><input type="checkbox" />小儿推拿师</li>
												<li><input type="checkbox" />小儿推拿师</li>
												<li><input type="checkbox" />小儿推拿师</li>
											</ul>
										</div>

									</div>
									<div class="grade-center">
										<div class="costs-uploadfile-div">
											营业执照<input type="file" name="file" accept="image/*"
												value="上传课程图片" onchange="" /> <input type="hidden" />
											<div class="costs-img">
												<img src="" />
											</div>
										</div>
										<div class="costs-uploadfile-div">
											执业资格<input type="file" name="file" accept="image/*"
												value="上传课程图片" onchange="" /> <input type="hidden" />
											<div class="costs-img">
												<img src="" />
											</div>
										</div>
										<div class="costs-uploadfile-div">
											门头照片<input type="file" name="file" accept="image/*"
												value="上传课程图片" onchange="" /> <input type="hidden" />
											<div class="costs-img">
												<img src="" />
											</div>
										</div>
										<div class="costs-uploadfile-div">
											店面照片<input type="file" name="file" accept="image/*"
												value="上传课程图片" onchange="" /> <input type="hidden" />
											<div class="costs-img">
												<img src="" />
											</div>
										</div>
										<div class=" select-2">
											<img src="/images/sjk-xl.png" /> <span>推荐级别</span> <select>
												<option disabled selected style='display: none;'>请选择推荐级别</option>
												<option></option>
												<option></option>
											</select>
										</div>
									</div>
								</div>
							</form>
							<div class="end">
								<input name="git" type="submit" value="提交"
									style="background: #5ED8A9;" /> <input name="esc" type="reset"
									value="取消" onclick="CloseDiv();formReset()" class="esc" />
							</div>
						</div>
					</div>
					<!-- 核审管理内容 -->
					<div class="manage" style="display: none;">
						<h4 style="padding-left: 30px;">核审列表()</h4>
						<form >
						<div class="details-frame-heshen">
							<div class="select-3" style="">
								<span>店铺名称</span> <input type="text" name="search" ng-model="shopName"/>

							</div>
							
							<div>
								<input type="button" class="btn-lg im-key" ng-click="islimitList()"
									value="检索"  />
							</div>
						</div>
						</form>
						<div class="admin-table">

							<table>
								<tr>
									<th>名称</th>
									<th>地址</th>
									<th>负责人</th>
									<th>申请时间</th>
									<th>操作</th>
								</tr>

								<tr ng-repeat="i in islimitlist" ng-click="checkshop(i)"
									ng-class="{'selected':selected==i}">

									<th>{{i.shopName}}</th>
									<th>{{i.address}}</th>
									<th>{{i.leaderName}}</th>
									<th>{{i.addtime}}</th>
									<th><span class="xiangqing" ng-click="checkedAll(i)">查看详情</span></th>
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
					<!-- 查看详情 -->
					<div id="revise" class="resource">
						<form id="myform2">
							<h3>详细信息</h3>
							<div class="template-add">
								<div class="grade-left" style="padding-right: 5%;">
									<h5></h5>
									<ul>
										<li>店铺名称</li>
										<li>{{s.shopName}}</li>
									</ul>
									<ul>
										<li>店铺类型</li>
										<li>服务店铺</li>
									</ul>
									<ul>
										<li>店铺地址</li>
										<li>{{s.address}}</li>
									</ul>
									<ul>
										<li>营业时间</li>
										<li>{{s.businessHours}}</li>
									</ul>
									<ul>
										<li>营业类别</li>
										<li>{{s.serveDomain}}</li>
									</ul>
									<ul>
										<li>负责人</li>
										<li>{{s.leaderName}}</li>
									</ul>
									<ul>
										<li>联系方式</li>
										<li>{{s.leaderMobile}}</li>
									</ul>
									<ul>
										<li>身份证号</li>
										<li>{{s.idcard}}</li>
									</ul>
									<ul>
										<li>备注信息</li>
										<li>{{s.remarked}}</li>
									</ul>
								</div>



								<div class="grade-center">
									<div class="costs-uploadfile-div">
										营业执照：
										<div class="costs-img">
											<img ng-src="{{s.businessLicenseUrl}}" name="营业执照" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										执业资格证：
										<div class="costs-img" style="height: 110px;">
											<img ng-src="{{s.imgList[0].imgUrl}}" name="资格证1"
												style="width: 30%; float: left; height: 50px; margin-right: 3%;" />
											<img ng-src="{{s.imgList[1].imgUrl}}" name="资格证2"
												style="width: 30%; float: left; height: 50px; margin-right: 3%;" "/>
											<img ng-src="{{s.imgList[2].imgUrl}}" name="资格证3"
												style="width: 30%; float: left; height: 50px;" /> <img
												ng-src="{{s.imgList[3].imgUrl}}" name="资格证4"
												style="width: 30%; float: left; height: 50px; margin-top: 10px;" />

										</div>
									</div>
									<div class="costs-uploadfile-div">
										门头照片：
										<div class="costs-img">
											<img ng-src="{{s.doorHeadUrl}}" name="营业执照" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										店面照片：
										<div class="costs-img" style="height: 110px;">
											<img ng-src="{{s.environmentImgList[0].imgUrl}}" name="资格证1"
												style="width: 30%; float: left; height: 50px; margin-right: 3%;" />
											<img ng-src="{{s.environmentImgList[1].imgUrl}}" name="资格证2"
												style="width: 30%; float: left; height: 50px; margin-right: 3%;" "/>
											<img ng-src="{{s.environmentImgList[2].imgUrl}}" name="资格证3"
												style="width: 30%; float: left; height: 50px;" /> <img
												ng-src="{{s.environmentImgList[3].imgUrl}}" name="资格证4"
												style="width: 30%; float: left; height: 50px; margin-top: 10px;" />

										</div>
									</div>

								</div>
								<div class="grade-right" id="islimits">
									<h3>操作</h3>
									<div>
										<ul>
											<li>是否同意</li>
											<li><input ng-model="islimits" type="radio" value="1"
												name="1" /> 同意</li>
											<li><input ng-model="islimits" type="radio" value="2"
												name="1" />不同意</li>
										</ul>
									</div>
									<div class="grade-text">
										<span>拒绝原因</span>
										<textarea style="height: 200px; width: 100% !important"></textarea>
									</div>

								</div>
						
						<div class="end">
							<input ng-click="updateshop(s)" name="git" type="submit" value="提交"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" ng-click="reset()" class="esc" />
						</div>
				</form>



					</div>
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

.poop {
	width: 55%;
	height: 550px;
	position: absolute;
	left: 15%;
	top: 10%;
	display: none;
}

@media screen and (max-width:1401px) {
	.poop {
		width: 560px;
	}
}

.poop span {
	font-size: 1.5rem;
}

.resource .grade-left, .resource .grade-center {
	width: 400px;
	float: left;
	height: 550px;
	overflow: auto;
}

.poop .grade-left, .poop .grade-center {
	width: 49%;
	float: left;
	height: 720px;
	overflow: auto;
}

.resource .grade-right {
	width: 200px;
	border-right: none;
	height: 500px;
	float: left;
}

.details-frame-content {
	height: 51px;
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
	width: 50%;
}

.manage .managr-dianpu .select-3 {
	width: 12%;
	margin-left: 18px;
	margin-right: 0;
}

@media screen and (max-width: 901px) {
	.manage .managr-dianpu .select-3, .details-frame-heshen .select-3,
		.details-frame-heshen .select-2 {
		width: 90% !important;
	}
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
	width: auto;
	height: 600px;
	position: absolute;
	left: 10%;
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

.end {
	float: none;
	margin-left: auto;
}
</style>
</div>

</@b.body>

</html>