<#import "/layout/header.ftl" as h/>
 <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="服务店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/yishi.js"></script>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-xiangmu">
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
						style="border-bottom: 3px solid red; font-weight: 900;">项目名称</li>
				</ul>
			</div>
			<div id="guanli">
				<div class="manage">
					<ul style="height: 80px;" class="managr-dianpu">
						<div class="select-3">
							<span>项目名称</span> <input type="text" />
						</div>
						<div class=" select-3">
							<img src="/images/sjk-xl.png" /> <span>购买方式</span> <select>
								<option disabled selected style='display: none;'></option>
								<option></option>
								<option></option>
							</select>
						</div>

						<div>
							<input type="button" class="btn-lg im-key" ng-click="" value="检索"
								ng-click="" />
						</div>
					</ul>
					<ul style="height: 80px;" class="show">

						<li onclick="showDiv()" style="background: #9DE879;"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;添加项目</li>
						<li ng-click="update()" style="background: #F9CD33;"><span
							class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
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
								<th>项目名称</th>
								<th>服务时长</th>
								<th>项目类别</th>
								<th>原价</th>
								<th>优惠价格</th>
								<th>服务图片</th>
								<th>推荐级别</th>
								<th>详细信息</th>
							</tr>

							<tr>
								<th>医师名称</th>
								<th>所在医院</th>
								<th>科室</th>
								<th>职位职称</th>
								<th>是否推荐</th>
								<th>注册时间</th>
								<th>推荐级别</th>
								<th><span class="xiangqing" onclick="showDiv2()">查看详情</span></th>
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
							<h3>项目</h3>
							<div class="template-add">
								<div class="grade-left">
										<div class="select-2">
										<span>项目名称<i class="bitian">*</i></span> <input type="text" />
									</div>

									<div class=" select-2">
										<img src="/images/sjk-xl.png" /> <span>项目类别<i class="bitian">*</i></span> <select>
											<option disabled selected style='display: none;'></option>
											<option></option>
											<option></option>
										</select>
									</div>
								
										<div class="select-2">
										<span>服务时间<i class="bitian">*</i></span> <input type="text" />
									</div>
										<div class="select-2">
										<span>原价<i class="bitian">*</i></span> <input type="text" />
									</div>

									
										<div class="select-2">
										<span>优惠价格<i class="bitian">*</i></span> <input type="text" /></div>
							
                                  
										<div class="select-2">
										<span>推荐级别</span> <input type="text" />
									</div>

								</div>
								<div class="grade-center">
								<div class="grade-text">
										<span>服务详情<i class="bitian">*</i></span>
										<textarea></textarea>
									</div>
									<div class="grade-text">
										<span>购买须知<i class="bitian">*</i></span>
										<textarea></textarea>
									</div>
									<div class="costs-uploadfile-div">
										服务图片<input type="file" name="file" value="上传课程图片"/>
										<input type="hidden" />
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
				<!-- 查看详情 -->
				<div id="revise" class="resource" style="display: none;">
					<form id="myform2">
						<h3>详细信息</h3>
						<div class="template-add">
							<div class="grade-left" style="padding-right: 5%;">
											<ul>
									<li>店铺名称</li>
									<li>王尼玛</li>
								</ul>
								<ul>
									<li>店铺类型</li>
									<li>11111</li>
								</ul>
								<ul>
									<li>负责人</li>
									<li>王小明</li>
								</ul>
								<ul>
									<li>联系电话</li>
									<li>112</li>
								</ul>
								<ul>
									<li>身份证号</li>
									<li>5466</li>
								</ul>
								<ul style="height: 70px;">
                                        	<li>店铺地址</li>
                                        	<li style="margin-top: 5px;">地址地址地址地址地址地址地址地址地址地址地址</li>
                                        </ul>
                                        <ul style="height: 70px;">
                                        	<li>营业类别:</li>
                                            <li> <span>中医推拿&nbsp;</span><span>小儿推拿&nbsp;</span></li>
                                        </ul>
							</div>



							<div class="grade-center">
								<div class="costs-uploadfile-div">
									营业执照：
									<div class="costs-img">
										<img src="" name="营业执照" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									职业资格：
									<div class="costs-img" style="height: 70px;">
										<img src=""
											style="float: left; height: 70px; margin-right: 3%;" />
										<img src="" style="float: left; height: 70px;" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									门头照片：
									<div class="costs-img">
										<img src="" name="门头照片" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									店面照片：
									<div class="costs-img" style="height: 110px;">
										<img src=""
											style="float: left; height: 50px; margin-right: 3%;" />
										
									</div>
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
		</div>
	</div>

		<style type="text/css">
@media screen and (max-width:901px){.managr-dianpu , .details-frame-heshen  {overflow: auto;}}
			.poop {
				
				width: 55%;
				height: 550px;
				position: absolute;
				left: 15%;
				top: 10%;
				display: none;
			}
				@media screen and (max-width:1401px){.poop {width: 560px;}}		
			.poop span {
				font-size: 1.5rem;
			}
.resource .grade-left , .resource .grade-center , .resource .grade-right{width:380px;float: left;height:470px;overflow: auto;}
.resource .grade-right{width:200px;border-right: none;height: 500px;}	
.poop .grade-left , .poop .grade-center {width:49%;float: left;height:450px;overflow: auto;}
.details-frame-content {height: 50px;padding:0 29px;}
			
.details-frame-content ul li {	float: left;height: 50px;text-align: center;line-height: 50px;font-size: 1.5rem;cursor: pointer;margin-right:15px;}
.leibie{background:#F5F6F8;width: 50%;text-align: center; }
.grade-text textarea {height:70px;width: 70%;}
.manage .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}
.details-frame-heshen{width:100%;padding:0 30px;background: white;height:auto;border-bottom: solid 1px #EEEFF1;}
@media screen and (max-width: 901px){.manage .managr-dianpu .select-3 , .details-frame-heshen .select-3 , .details-frame-heshen .select-2 {width:90% !important;}}
.details-frame-heshen .select-3{width:15%; }
.details-frame-heshen .select-2{width: 10%;float: left;}
.details-frame-heshen .select-2 input {border-radius:0;}
.resource{width:auto;
				height: 600px;
				position: absolute;
				left: 10%;
				top: 20%;
				display: none;}
@media screen and (max-width:1401px){.resource{width:840px;}}
.resource .grade-left ul{width:100%;margin:3px 0;height:50px;}
.resource .grade-left ul li:nth-child(1){float:left;}
.resource .grade-left ul li:nth-child(2){float:right;color:#999;}
.resource .grade-left  ul p li{color: #999;}
.biaoqian{width: 80%;height:80px;}
.biaoqian ul li{float: left;background:#EDEEF0;margin-right: 5px;}
.biaoqian i{color: #B1B1B1;font-weight: 100;}
.end {float: none; margin-left: auto;}
		</style>
	
	
	</@b.body>
</html>