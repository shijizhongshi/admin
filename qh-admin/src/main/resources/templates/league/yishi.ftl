<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="商品店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/yishi.js"></script>
<script src="/scripts/league/league.js"></script>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-yishi">
<div ng-controller="doctorsController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>加盟商管理</li>
				<li>/</li>
				<li>医师管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content" id="details-frame-content">
				<ul>
					<li onmousedown="go(0)" ng-click="doctorsList()"
						style="border-bottom: 3px solid red; font-weight: 900;">医师管理</li>
					<li onmousedown="go(1)" ng-click="islimitList()">医师入驻</li>
				</ul>
			</div>
			<div id="guanli">
				<div class="manage">
					<form action="">
						<ul style="height: 80px;" class="managr-dianpu">
							<div class="select-3" style="width: 10%;margin-right:5px">
								<span>医师名称</span> <input type="text" placeholder="请输入医师名称" ng-model="name" />
							</div>
							<div class="select-3" style="width: 10%;margin-right:5px">
								<img src="/images/sjk-xl.png" /> <span>医院科室</span> <select
									ng-model="offices">
									<option value="">查看全部</option>
									<option>内分泌</option>
									<option>内科</option>
								</select>
							</div>
							<div class="select-3" style="width: 10%;margin-right:5px">
								<img src="/images/sjk-xl.png" /> <span>是否真实</span> <select
									ng-model="isvirtual">

									<option ng-selected="isvirtual==0" value="0">是</option>
									<option value="1">否</option>
								</select>
							</div>

							<div>
								<input type="button" class="btn-lg im-key" value="立即检索"
									ng-click="doctorsList()" />
							</div>
						</ul>
					</form>
					<ul class="show">


						<li style="background: #F86846;"><span
							class="glyphicon glyphicon-trash"></span>&nbsp;删除医师</li>
						
						<li style="float: right; margin-right: 20px; background: none;"><img
							src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
					</ul>
					<div class="admin-table">

						<table>
							<tr>
								<th>医师名称</th>
								<th>所在医院</th>
								<th>科室</th>
								<th>职位职称</th>
								<th>是否推荐</th>
								<th>注册时间</th>
								<th>详细信息</th>
							</tr>

							<tr ng-repeat="d in doctorslist" ng-click="checkdoctor(d)"
								ng-class="{'selected':selected==d}">
								<th>{{d.name}}</th>
								<th>{{d.hospital}}</th>
								<th>{{d.offices}}</th>
								<th>{{d.professional}}</th>
								<th>{{d.recommend}}</th>
								<th>{{d.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
								<th><span class="xiangqing" ng-click="checkedAll(d)">查看详情</span></th>
							</tr>

						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="current" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-click="doctorsList()">
							</ul>
						</div>
					</div>

					<!--弹窗-->
					<div class="poop" id="add">
						<form id="myform">
							<h3>医师</h3>
							<div class="template-add">
								<div class="grade-left" style="padding-right: 5%;">
									<div class="select-2">
										<span>医师名称</span> <input type="text" ng-model="doctors.name" />
									</div>

									<div class=" select-3">
										<img src="/images/sjk-xl.png" /> <span>性别</span> <select
											ng-model="doctors.sexs">
											<option disabled selected style='display: none;'></option>
											<option>女</option>
											<option>男</option>
										</select>
									</div>
									<div class=" select-3">
										<img src="/images/sjk-xl.png" /> <span>学历</span> <select
											ng-model="doctors.edu">
											<option disabled selected style='display: none;'></option>
											<option>本科</option>
											<option>硕士</option>
											<option>研究生</option>
											<option>博士</option>
										</select>
									</div>
									<div class=" select-2" style="clear: both;">
										<img src="/images/sjk-xl.png" /> <span>地区</span> <select
											ng-model="doctors.address">
											<option disabled selected style='display: none;'></option>
											<option>山东</option>
											<option></option>
											<option></option>
											<option></option>
										</select>
									</div>

									<div class="select-2">
										<span>毕业院校</span> <input ng-model="doctors.school" type="text"
											placeholder="请输入毕业院校" />
									</div>

									<div class="select-2">
										<span>所在医院</span> <input ng-model="doctors.hospital"
											type="text" placeholder="" />
									</div>
									<div class="select-2">
										<span>所在科室</span> <input ng-model="doctors.offices"
											type="text" placeholder="" />
									</div>
									<div class="select-2">
										<span>职位职称</span> <input ng-model="doctors.professional"
											type="text" placeholder="" />
									</div>
									<div class="grade-text">
										<span>擅长治疗</span>
										<textarea ng-model="doctors.skilled"></textarea>
									</div>
									<div class="select-2">
										<span>添加标签</span> <input ng-model="doctors.signs" type="text"
											placeholder="" style="border-radius: 0;" />

									</div>




								</div>
								<div class="grade-center">
									<div class="select-2">
										<span>身份证号</span> <input ng-model="doctors.idcard" type="text"
											placeholder="请输入身份证号" />
									</div>
									<p>身份证照片</p>
									<div class="costs-uploadfile-div">
										<span style="float: left;">正面:&nbsp;</span><input
											onchange="angular.element(this).scope().uploadmainimage(this,1)"
											type="file" name="file" accept="image/*" value="身份证正面" /> <input
											type="hidden" ng-model="frontIdcardImg" />
									</div>
									&nbsp;
									<div class="costs-uploadfile-div">
										<span style="float: left;">反面:&nbsp;</span><input
											onchange="angular.element(this).scope().uploadmainimage(this,2)"
											type="file" name="file" accept="image/*" value="身份证反面" /> <input
											type="hidden" ng-model="reverseIdcardImg" />
									</div>
									&nbsp;
									<div class="costs-uploadfile-div">
										医师照片<input
											onchange="angular.element(this).scope().uploadmainimage(this,3)"
											type="file" name="file" accept="image/*" value="上传医师照片"
											onchange="" /> <input type="hidden" ng-model="headImg" />
										<div class="costs-img">
											<img src="{{headImg}}" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										职称证明<input
											onchange="angular.element(this).scope().uploadmainimage(this,4)"
											type="file" name="file" value="上传职称证明" /> <input
											type="hidden" ng-model="professionalImg" />
										<div class="costs-img">
											<img src="{{professionalImg}}" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										资质证书<input
											onchange="angular.element(this).scope().uploadmainimage(this,5)"
											type="file" name="file" value="上传资质证书" /> <input
											type="hidden" ng-model="practiceImg" />
										<div class="costs-img">
											<img src="{{practiceImg}}" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										其他证书<input
											onchange="angular.element(this).scope().uploadmainimage(this,6)"
											type="file" name="file" value="上传其他证书" /> <input
											type="hidden" ng-model="elseImg" />

									</div>
									<div class="select-radio ">
										<ul>
											<li>是否推荐</li>
											<li><input ng-model="doctors.isrecommend" type="radio"
												ng-value="1"
												class="ng-pristine ng-untouched ng-valid ng-empty" name="4"
												value="1"> 是</li>
											<li><input ng-model="doctors.isrecommend" type="radio"
												ng-value="0"
												class="ng-pristine ng-untouched ng-valid ng-empty" name="4"
												value="0">否</li>
										</ul>
									</div>
								</div>
							</div>
						</form>
						<div class="end">
							<input ng-click="insertdoctor()" name="git" type="submit"
								value="提交" style="background: #5ED8A9;" /> <input name="esc"
								type="reset" value="取消" ng-click="reset()" class="esc" />
						</div>
					</div>
				</div>


				<!-- 核审管理内容 -->
				<div class="manage" style="display: none;">
					<h4 style="padding-left: 30px;">
						<span>{{doctorcount}}</span>
					</h4>
					<div class="details-frame-heshen">
						<div class="select-3" style="width: 15%;margin-right:0%">
							<span>医师姓名</span> <input type="text" placeholder="请输入医师名称" ng-model="name" />

						</div>
						<div>
							<input type="button" class="btn-lg im-key"
								ng-click="islimitList()" value="立即检索">
						</div>
					</div>
					<div class="admin-table">

						<table>
							<tr>
								<th>姓名</th>
								<th>科室</th>
								<th>所在医院</th>
								<th>职称</th>
								<th>申请时间</th>
								<th>详细信息</th>
							</tr>

							<tr ng-repeat="i in islimitlist" ng-click="checkdoctor(i)"
								ng-class="{'selected':selected==i}">

								<th>{{i.name}}</th>
								<th>{{i.offices}}</th>
								<th>{{i.hospital}}</th>
								<th>{{i.professional}}</th>
								<th>{{i.addtime}}</th>
								<th><span class="xiangqing" ng-click="checkedAll(i)">查看详情</span></th>
							</tr>

						</table>
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="istotal"
								ng-model="iscurrent" items-per-page="ispageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-click="islimitList()">
							</ul>
						</div>
					</div>


				</div>
				<!-- 查看详情 -->
				<div id="revise" class="resource">
					<form id="myform2">
						<h3>详细信息</h3>
						<div class="template-add">
							<div class="grade-left" style="padding-right: 5%;">
								<div
									style="width: 50%; margin: 0 auto; text-align: center; height: 130px">
									<img ng-src="{{d.headImg}}" style="height: 100px;" />
									<p>{{d.name}}</p>
								</div>

								<ul>
									<li>医师姓名</li>
									<li>{{d.name}}</li>
								</ul>
								<ul>
									<li>身份证号</li>
									<li>{{d.idcard}}</li>
								</ul>
								<ul>
									<li>性别</li>
									<li>{{d.sexs}}</li>
								</ul>
								<ul>
									<li>地区</li>
									<li>{{d.address}}</li>
								</ul>
								<ul>
									<li>学历</li>
									<li>{{d.edu}}</li>
								</ul>
								<ul>
									<li>毕业院校</li>
									<li>{{d.school}}</li>
								</ul>
								<ul>
									<li>所在医院</li>
									<li>{{d.hospital}}</li>
								</ul>
								<ul>
									<li>所在科室</li>
									<li>{{d.offices}}</li>
								</ul>
								<ul>
									<li>职位职称</li>
									<li>{{d.professional}}</li>
								</ul>
								<ul>
									<li>标签</li>
									<li>{{d.signs}}</li>
								</ul>
								<ul>
									<li>擅长治疗</li>
									<li>{{d.skilled}}</li>
								</ul>

							</div>



							<div class="grade-center">
								<div class="costs-uploadfile-div">
									职称证明：
									<div class="costs-img">
										<img ng-src="{{d.professionalImg}}" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									身份证：
									<div class="costs-img" style="height: 70px;">
										<img ng-src="{{d.frontIdcardImg}}"
											style="float: left; height: 70px; margin-right: 3%;" /> <img
											ng-src="{{d.reverseIdcardImg}}"
											style="float: left; height: 70px;" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									资质证明：
									<div class="costs-img">
										<img ng-src="{{d.practiceImg}}" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									其他证书：
									<div class="costs-img" style="height: 110px;">
										<img ng-src="{{d.elseImg}}"
											style="float: left; height: 80px; margin-right: 3%;" />

									</div>
								</div>

							</div>
							<div class="grade-right" id="islimit">

								<h3>操作</h3>
								<div>
									<ul>
										<li>是否同意</li>
										<li><input ng-model="islimit" type="radio" value="1"
											name="1" /> 同意</li>
										<li><input ng-model="islimit" type="radio" value="2"
											name="1" />不同意</li>
									</ul>
								</div>
								<div class="grade-text">
									<span>拒绝原因</span>
									<textarea style="height: 200px; width: 100% !important"></textarea>
								</div>
							</div>
					</form>
					<div class="end">
						<input ng-click="updatedoctor(d.id)" name="git" type="submit"
							value="提交" style="background: #5ED8A9;" /> <input name="esc"
							type="reset" value="取消" ng-click="reset()" class="esc" />
					</div>




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

.resource .grade-left, .resource .grade-center, .resource .grade-right {
	width: 400px;
	float: left;
	height: auto;
	overflow: auto;
}

.resource .grade-right {
	width: 200px;
	border-right: none;
	height: 500px;
}

.poop .grade-left, .poop .grade-center {
	width: 49%;
	float: left;
	height: 760px;
	overflow: auto;
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
	left: 10%;
	top: 20%;
}

.end {
	float: none;
	margin-left: auto;
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