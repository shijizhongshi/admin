<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
	<@h.header title="服务店铺" />
	<link rel="stylesheet" href="/styles/admin.css" />
	<script src="/scripts/course/grade-template.js"></script>
	<script src="/scripts/admin.js"></script>
	<script src="/scripts/league/league.js"></script>
	<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-fuwu">
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
							<li onmousedown="go(0)" style="border-bottom:3px solid red;font-weight: 900;">服务店铺</li>
							<li onmousedown="go(1)">核审管理</li>
						</ul>
					</div>
					<div id="guanli">
						<div class="manage">
							<ul style="height: 80px;" class="managr-dianpu">
								<div class="select-3">
									<span>店铺名称</span>
									<input type="text"/>
								</div>
								<div class=" select-3">
									<img src="/images/sjk-xl.png" /> <span>所在地区</span> <select>
										<option disabled selected style='display: none;'></option>
										<option></option>
										<option></option>
									</select>
								</div>
								<div class=" select-3">
									<img src="/images/sjk-xl.png" /> <span>推荐级别</span> <select>
										<option disabled selected style='display: none;'></option>
										<option></option>
										<option></option>
									</select>
								</div>
								<div>
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索" ng-click="search()" />
				</div>
							</ul>
							<ul style="height: 80px;" class="show">
							
								<li onclick="showDiv()" style=" background: #9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
								<li ng-click="update()" style="background: #F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
								<li ng-click="deletetemplate()" style="background: #F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
								<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
								<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
								<li style="float: right; margin-right: 100px; background: none;"><img src="/images/sjk-f5.png" name="changyi" /></li>
							</ul>
							<div class="admin-table">

								<table>
									<tr>
										<th>店铺名称</th>
										<th>地址</th>
										<th>店铺类型</th>
										<th>推荐级别</th>
										<th>营业类别</th>
										<th>门头照片</th>
										<th>负责人</th>
										<th>联系电话</th>
										<th>创建时间</th>
										<th>详细信息</th>
									</tr>

									<tr>
										<th>店铺名称</th>
										<th>地址</th>
										<th>店铺类型</th>
										<th>推荐级别</th>
										<th>营业类别</th>
										<th>门头照片</th>
										<th>负责人</th>
										<th>联系电话</th>
										<th>创建时间</th>
										<th><span class="xiangqing">查看详情</span></th>
									</tr>
									
								</table>

							</div>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total" ng-model="current" items-per-page="pageSize" max-size="5" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
								</ul>
							</div>

							<!--弹窗-->
							<div class="poop" id="add">
								<form id="myform">
									<h3>添加店铺</h3>
									<div class="template-add">
										<div class="grade-left" style="padding-right: 5%;">
										<div class="select-2">
											<span>请输入店铺名称</span>
											<input type="text" />
										</div>
										
									<div class=" select-2">
									<img src="/images/sjk-xl.png" /> <span>店铺类型</span> <select>
										<option disabled selected style='display: none;'>服务店铺</option>
										<option></option>
										<option></option>
									</select>
								</div>
										
									<div class="select-2">
											<span>负责人</span>
											<input type="text" placeholder="请输入负责人姓名"/>
										</div>
										<div class="select-2">
											<span>联系电话</span>
											<input type="text" placeholder="请输入联系电话"/>
										</div>
										<div class="select-2">
											<span>身份证号</span>
											<input type="text" placeholder="请输入负责人身份证号"/>
										</div>
										<div class="grade-text">
											<span>店铺地址</span>
											<textarea></textarea>
										</div>
										<p>营业类别</p>
										<div class="leibie">
											<ul>
												<li><input type="checkbox"/>小儿推拿师</li>
												<li><input type="checkbox"/>小儿推拿师</li>
												<li><input type="checkbox"/>小儿推拿师</li>
											</ul>
										</div>
										
									</div>
									<div class="grade-center">
				<div class="costs-uploadfile-div">   
				营业执照<input type="file" name="file"   accept="image/*"  value="上传课程图片" onchange="" /> 
   <input type="hidden" ng-model="" />
  <div class="costs-img"> <img  src="" /></div></div>
  				<div class="costs-uploadfile-div">   
				执业资格<input type="file" name="file"   accept="image/*"  value="上传课程图片" onchange="" /> 
   <input type="hidden" ng-model="" />
  <div class="costs-img"> <img  src="" /></div></div>
  				<div class="costs-uploadfile-div">   
				 门头照片<input type="file" name="file"   accept="image/*"  value="上传课程图片" onchange="" /> 
   <input type="hidden" ng-model="" />
  <div class="costs-img"> <img  src="" /></div></div>
  				<div class="costs-uploadfile-div">   
				店面照片<input type="file" name="file"   accept="image/*"  value="上传课程图片" onchange="" /> 
   <input type="hidden" ng-model="" />
  <div class="costs-img"> <img  src="" /></div></div>
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
									<input name="git" type="submit" value="提交" style="background: #5ED8A9;" /> <input name="esc" type="reset" value="取消" onclick="CloseDiv();formReset()" class="esc" />
								</div>
							</div>
						</div>

						<div class="manage" style="display: none;">
						<h4>核审列表()</h4>
							<ul style="height: 80px;" class="managr-dianpu">
                 <div class="select-3">
                 	<span>店铺名称</span>
                 	<input type="text" />
                 </div>

						
						</ul>
						</div>
					
					</div>
				</div>
			</div>
		</div>

		<style type="text/css">
			.poop {
				
				width: 55%;
				height: 500px;
				position: absolute;
				left: 25%;
				top: 10%;
				display: none;
			}
			
			.poop span {
				font-size: 1.5rem;
			}
			.grade-left, .grade-center {
	width: 50%;
	float: left;
	height: 670px;
	overflow: auto;
}
			
			.details-frame-content {
				height: 50px;
				padding:0 29px;

			
			}
			
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
.manage .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}
		</style>
	
	</@b.body>

</html>