<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="商品店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>
<script src="/scripts/league/yishi.js"></script>
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
					<li onmousedown="go(0)"
						style="border-bottom: 3px solid red; font-weight: 900;">医师管理</li>
					<li onmousedown="go(1)">医师入驻</li>
				</ul>
			</div>
			<div id="guanli">
				<div class="manage">
					<ul style="height: 80px;" class="managr-dianpu">
						<div class="select-3">
							<span>医师名称</span> <input type="text" />
						</div>
						<div class=" select-3">
							<img src="/images/sjk-xl.png" /> <span>医院科室</span> <select>
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
							class="glyphicon glyphicon-plus"></span>&nbsp;添加医师</li>
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
								<th>医师名称</th>
								<th>所在医院</th>
								<th>科室</th>
								<th>职位职称</th>
								<th>是否推荐</th>
								<th>注册时间</th>
								<th>详细信息</th>
							</tr>

							<tr>
								<th>医师名称</th>
								<th>所在医院</th>
								<th>科室</th>
								<th>职位职称</th>
								<th>是否推荐</th>
								<th>注册时间</th>
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
							<h3>医师</h3>
							<div class="template-add">
								<div class="grade-left" style="padding-right: 5%;">
									<div class="select-2">
										<span>医师名称</span> <input type="text" />
									</div>

									<div class=" select-3">
										<img src="/images/sjk-xl.png" /> <span>性别</span> <select>
											<option disabled selected style='display: none;'></option>
											<option>女</option>
											<option>男</option>
										</select>
									</div>
									<div class=" select-3">
										<img src="/images/sjk-xl.png" /> <span>学历</span> <select>
											<option disabled selected style='display: none;'></option>
											<option>本科</option>
											<option>硕士</option>
											<option>研究生</option>
											<option>博士</option>
										</select>
									</div>
									<div class=" select-2" style="clear: both;">
										<img src="/images/sjk-xl.png" /> <span>地区</span> <select>
											<option disabled selected style='display: none;'></option>
											<option>山东</option>
											<option></option>
											<option></option>
											<option></option>
										</select>
									</div>

									<div class="select-2">
										<span>毕业院校</span> <input type="text" placeholder="请输入毕业院校" />
									</div>

									<div class="select-2">
										<span>所在医院</span> <input type="text" placeholder="" />
									</div>
									<div class="select-2">
										<span>所在科室</span> <input type="text" placeholder="" />
									</div>
									<div class="select-2">
										<span>职位职称</span> <input type="text" placeholder="" />
									</div>
									<div class="grade-text">
										<span>擅长治疗</span>
										<textarea></textarea>
									</div>
									<div class="select-2">
										<span>添加标签</span> <input type="text" placeholder=""
											style="border-radius: 0;" />
										<div
											style="border: 1px solid #B1B1B1; position: absolute; right: 0px; top: 31px; line-height: 28px; display: inherit; cursor: pointer; height: 28px;">
											<i class="glyphicon glyphicon-plus"
												style="font-size: 1.0rem;"></i>&nbsp;添加
										</div>
									</div>
									<div class="biaoqian">
										<ul>
											<li><span>这里是标签 </span>&nbsp;<i
												class="glyphicon glyphicon-remove"></i></li>
											<li><span>这里是标签 </span>&nbsp;<i
												class="glyphicon glyphicon-remove"></i></li>
										</ul>
									</div>



								</div>
								<div class="grade-center">
									<div class="select-2">
										<span>身份证号</span> <input type="text" placeholder="请输入身份证号" />
									</div>
									<p>身份证照片</p>
									<div class="costs-uploadfile-div">
										<span style="float: left;">正面:&nbsp;</span><input type="file"
											name="file" accept="image/*" value="身份证正面" onchange="" /> <input
											type="hidden" ng-model="" />
									</div>
									&nbsp;
									<div class="costs-uploadfile-div">
										<span style="float: left;">反面:&nbsp;</span><input type="file"
											name="file" accept="image/*" value="身份证反面" onchange="" /> <input
											type="hidden" ng-model="" />
									</div>
									&nbsp;
									<div class="costs-uploadfile-div">
										医师照片<input type="file" name="file" accept="image/*"
											value="上传课程图片" onchange="" /> <input type="hidden"
											ng-model="" />
										<div class="costs-img">
											<img src="" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										职称证明<input type="file" name="file" value="上传课程图片" onchange="" />
										<input type="hidden" ng-model="" />
										<div class="costs-img">
											<img src="" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										资质证书<input type="file" name="file" value="上传课程图片" onchange="" />
										<input type="hidden" ng-model="" />
										<div class="costs-img">
											<img src="" />
										</div>
									</div>
									<div class="costs-uploadfile-div">
										其他证书<input type="file" name="file" value="上传课程图片" onchange="" />
										<input type="hidden" ng-model="" />
									</div>
									<div class="select-radio ">
										<ul>
											<li>是否推荐</li>
											<li><input type="radio" ng-model="" ng-value="1"
												class="ng-pristine ng-untouched ng-valid ng-empty" name="3"
												value="1"> 是</li>
											<li><input type="radio" ng-model="" ng-value="0"
												class="ng-pristine ng-untouched ng-valid ng-empty" name="4"
												value="0">否</li>
										</ul>
									</div>
								</div>
							</div>
						</form>
						<div class="end">
							<input name="git" type="submit" value="提交"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" onclick="CloseDiv();formReset()" class="esc" />
						</div>
<<<<<<< HEAD
                           <!-- 核审管理内容 -->
						<div class="manage" style="display: none;">
						        <h4 style="padding-left:30px; ">核审列表()</h4>
						<div class="details-frame-heshen">
						<div class="select-3"
							style="">
							<span>医师姓名</span>
							
								<input type="text" name="search" />
							
						</div>
						<div class="select-2">
							<span>选择时间</span>
								<input type="date" name="search"/>
						</div>
						<div class="select-2" style="font-size: 1.6rem;width: 3%;text-align: center;">
							<span>&nbsp;</span>
								<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-2">
							<span>&nbsp;</span>
								<input type="date" name="search"/>
						</div>
					
					<div><input type="button" class="btn-lg im-key" ng-click="" value="检索"></div>
=======
>>>>>>> branch 'master' of https://github.com/shijizhongshi/admin.git
					</div>
				</div>
				<!-- 核审管理内容 -->
				<div class="manage" style="display: none;">
					<h4 style="padding-left: 30px;">核审列表()</h4>
					<div class="details-frame-heshen">
						<div class="select-3" style="">
							<span>医师姓名</span> <input type="text" name="search" />

						</div>
						<div class="select-2">
							<span>选择时间</span> <input type="date" name="search" />
						</div>
						<div class="select-2"
							style="font-size: 1.6rem; width: 3%; text-align: center;">
							<span class="glyphicon glyphicon-hand-right"></span> &nbsp;<span
								class="glyphicon glyphicon-hand-left"></span>
						</div>
						<div class="select-2">
							<span>&nbsp;</span> <input type="date" name="search" />
						</div>

						<div>
							<input type="button" class="btn-lg im-key" ng-click="" value="检索">
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
								<th>操作</th>
							</tr>

							<tr>

								<th>姓名</th>
								<th>科室</th>
								<th>所在医院</th>
								<th>职称</th>
								<th>申请时间</th>
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





				</div>
				<!-- 查看详情 -->
				<div id="revise" class="resource">
					<form id="myform2">
						<h3>详细信息</h3>
						<div class="template-add">
							<div class="grade-left" style="padding-right: 5%;">
								<div
									style="width: 50%; margin: 0 auto; text-align: center; height: 130px">
									<img src="/images/sjk-home.png" style="height: 100px;" />
									<p>王尼玛</p>
								</div>
								<ul>
									<li>医师姓名</li>
									<li>王尼玛</li>
								</ul>
								<ul>
									<li>身份证号</li>
									<li>11111</li>
								</ul>
								<ul>
									<li>性别</li>
									<li>女</li>
								</ul>
								<ul>
									<li>地区</li>
									<li>日本</li>
								</ul>
								<ul>
									<li>学历</li>
									<li>学士</li>
								</ul>
								<ul>
									<li>毕业院校</li>
									<li>家里蹲</li>
								</ul>
								<ul>
									<li>所在医院</li>
									<li>2</li>
								</ul>
								<ul>
									<li>所在科室</li>
									<li>妇产科</li>
								</ul>
								<ul>
									<li>职位职称</li>
									<li>2</li>
								</ul>
								<ul>
									<li>标签</li>
									<li>女</li>
								</ul>
								<ul>
									<li>擅长治疗</li>
									<li>老年痴呆</li>
								</ul>
							</div>



							<div class="grade-center">
								<div class="costs-uploadfile-div">
									职称证明：
									<div class="costs-img">
										<img src="" name="营业执照" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									身份证：
									<div class="costs-img" style="height: 70px;">
										<img src=""
											style="width: 30%; float: left; height: 70px; margin-right: 3%;" />
										<img src="" style="width: 30%; float: left; height: 70px;" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									资质证明：
									<div class="costs-img">
										<img src="" name="营业执照" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									其他证书：
									<div class="costs-img" style="height: 110px;">
										<img src=""
											style="width: 30%; float: left; height: 50px; margin-right: 3%;" />
										<img src=""
											style="width: 30%; float: left; height: 50px; margin-right: 3%;" "/>
										<img src="" style="width: 30%; float: left; height: 50px;" />
										<img src=""
											style="width: 30%; float: left; height: 50px; margin-top: 10px;" />
									</div>
								</div>

							</div>
							<div class="grade-right">
								<h3>操作</h3>
								<div>
									<ul>
										<li>是否同意</li>
										<li><input type="radio" ng-model="" ng-value="1" /> 同意</li>
										<li><input type="radio" ng-model="" ng-value="0" />不同意</li>
									</ul>
								</div>
								<div class="grade-text">
									<span>拒绝原因</span>
									<textarea style="height: 200px; width: 100% !important"></textarea>
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

<<<<<<< HEAD
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
.resource .grade-left , .resource .grade-center , .resource .grade-right{width:39%;float: left;height:720px;overflow: auto;}
.resource .grade-right{width:20%;border-right: none;height: 500px;}	
.poop .grade-left , .poop .grade-center {width:49%;float: left;height:760px;overflow: auto;}
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
.resource{width: 70%;
				height: 600px;
				position: absolute;
				left: 10%;
				top: 20%;
				display: none;}
@media screen and (max-width:1401px){.resource{width:840px;}}
.resource .grade-left ul{width:100%;margin:3px 0;height:50px;}
.resource .grade-left ul li:nth-child(1){float:left;}
.resource .grade-left ul li:nth-child(2){float:right;color:#999;}
.biaoqian{width: 80%;height:80px;}
.biaoqian ul li{float: left;background:#EDEEF0;margin-right: 5px;}
.biaoqian i{color: #B1B1B1;font-weight: 100;}
		</style>
	
	</@b.body>


</html>