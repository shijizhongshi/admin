 <#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="用户开课"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/studentinfo.js"></script>
<style type="text/css">
.selected {
	background-color: #c1ddec
}
</style>
<@b.body menu="sidebarmenu-student"
submenu="sidebarmenu-student-studentinfo">
<div ng-app="app" ng-controller="studentinfoController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>学员信息管理</li>
				<li>/</li>
				<li>用户开课</li>
			</ul>
		</div>
		<div class="details-frame">
			<form id="myform">
				<div class="details-frame-content">
					<ul class="managr-dianpu">

						<div class="select-3" style="width: 10%; margin-right: 10px">
							<span>手机号</span> <input type="text" placeholder="请输入手机号码"  ng-model="mobile" />
						</div>
						
						<div>
							<input type="button" class="btn-lg im-key"
							 ng-click="selectUser()" value="立即检索" />
						</div>
					</ul>
			</form>
		</div>
		<div class="manage">

			<div class="admin-table">
				<ul class="show">
					<li style="background: #9DE879;" ng-click="add()"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加用户</li>
					<li style="background: #F9CD33;" ng-click="update()"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改用户</li>
					<li style="background: #F86846;" ng-click="deleteUser()"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除用户</li>
					<li ng-click="applys(1)" style="background: #9DE879;"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;网课报班</li>
					<li ng-click="applys(2)" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;课程报名</li>
					<li ng-click="applys(3)"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;直播报班</li>
					<li ng-click="applys(3)"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;免费半价重学</li>
				</ul>
				<div class="admin-table">
					<table id="tableExcel">
						<tr>
							<th>手机号</th>
							<th>真实姓名</th>
							<th>昵称</th>
							<th>用户类型</th>
							<th>用户生日</th>
							<th>注册时间</th>
							<th>所在地址</th>
							<th>用户状态</th>
						</tr>

						<tr ng-repeat="u in userList" ng-click="checkUser(u)"
							ng-class="{'selected':selected==u}">
							<th>{{u.mobile}}</th>
							<th>{{u.realname}}</th>
							<th>{{u.nickname}}</th>
							<!--用户类型 展示 -->
							<th ng-show="{{u.usertype=='0'}}">普通用户</th>
							<th ng-show="{{u.usertype=='1'}}">服务店铺用户</th>
							<th ng-show="{{u.usertype=='2'}}">商城店铺用户</th>
							<th ng-show="{{u.usertype=='3'}}">服务店铺用户/商城店铺用户</th>
							<th ng-show="{{u.usertype=='10'}}">医生用户</th>
							<th ng-show="{{u.usertype=='11'}}">医生/服务店铺用户</th>
							<th ng-show="{{u.usertype=='12'}}">医生/商城店铺用户</th>
							<th ng-show="{{u.usertype=='13'}}">医生/服务店铺用户/商城店铺用户</th>
							<th>{{u.birthday | date:'yyyy-MM-dd'}}</th>
							<th>{{u.addtime | date:'yyyy-MM-dd'}}</th>
							<th>{{u.address}}</th>
							<!--用户状态  -->
							<th ng-show="{{u.isdisabled=='0'}}">正常</th>
							<th ng-show="{{u.isdisabled=='1'}}">禁用</th>
						</tr>
					</table>
				</div>

				<!-- 网课/直播报班/课程报名-->

				<div class="resource" id="revise">
					<form id="formReset2">
						<h3>{{types}}</h3>
						<div style="display: flex; justify-content: space-between;">
							<div class="classify" style="width: 28%;">
								<p class="xiaobiaoti">选择专业</p>
								<ul class="menu" style="box-shadow: none;">

									<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.id)">{{ctl.courseTypeName}}
										<ul class="items" ng-class="{'active':active==ctl.id}">
									<li ng-repeat="sub in courseTypeSubclass"
										ng-click="typeSub(ctl.courseTypeName,sub)"
											ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>

									</ul>
									</li>


								</ul>
							</div>
							<div style="width: 40%;">
								<p class="xiaobiaoti">选择{{typesName}}</p>
								<div
									style="weight: 100; height: auto; border: 1px solid #EEEEEE; border-radius: 10px; overflow: hidden;">
									<table>

										<tbody>
											<tr>
												<th>选择</th>
												<th>{{typesName}}名称</th>
												<th>{{typesName}}价格</th>
												<th>{{typesName}}折扣价</th>
												<th>{{typesName}}年限</th>

											</tr>
											<tr ng-show="typesName=='班级'"
												ng-repeat="class in classlist">
												<th ng-show="class.isbuy=='0'"><input type="checkbox"
													ng-checked="isSelected(class.id)"
													ng-click="updateSelection($event,class.id,class,class.classDiscountPrice)"/></th>
												<th ng-show="class.isbuy=='1'">已购买</th>
												<th>{{class.className}}</th>
												<th>{{class.classPrice}}</th>
												<th>{{class.classDiscountPrice}}</th>
												<th>{{class.classYear}}</th>
											</tr>
											<tr ng-show="typesName=='课程'"
												ng-repeat="course in courselist">
												<th ng-show="course.isbuy=='0'"><input type="checkbox"
													ng-checked="isSelected(course.id)"
													ng-click="updateSelection($event,course.id,course,course.courseDiscountPrice)" /></th>
												<th ng-show="course.isbuy=='1'">已购买</th>
												<th>{{course.courseName}}</th>
												<th>{{course.coursePrice}}</th>
												<th>{{course.courseDiscountPrice}}</th>
												<th>{{course.courseYear}}</th>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div style="width: 28%;">
								<p>已选{{typesName}}</p>
								<div
									style="weight: 100; height:auto; border: 1px solid #EEEEEE; border-radius: 10px; overflow: hidden; padding: 20px 15px;">



									<ul class="yixuanbanji" ng-show="typesName=='课程'"
										ng-repeat="productcourse in productlisted">
										<li><span style="color: red">{{productcourse.courseTypeSubclassName}}-</span>{{productcourse.courseName}}</li>
										<li>价格：{{productcourse.coursePrice}} <span
											style="color: red; margin-left: 20px;">折扣价：{{productcourse.courseDiscountPrice}}</span></li>
									</ul>

									<ul class="yixuanbanji" ng-show="typesName=='班级'"
										ng-repeat="productclass in productlisted">
										<li><span style="color: red">{{productclass.courseTypeSubclassName}}-</span>{{productclass.className}}</li>
										<li>价格：{{productclass.classPrice}} <span
											style="color: red; margin-left: 20px;">折扣价：{{productclass.classDiscountPrice}}</span></li>
									</ul>


								</div>
							</div>
						</div>

						<div style="float: left; font-size: 1.6rem; line-height: 50px;"
							ng-show="{{jiamengshang}}">
							您当前余额:{{surplusaccount}}&nbsp;所需班级需扣除：{{prices}}元；</div>

						<div
							style="float: left; font-size: 1.6rem; line-height: 50px; margin-left: 4%;"
							ng-show="!{{jiamengshang}}">
							销售人员<i class="bitian">*</i><input type="text"
								ng-model="openCourse.salesName"
								style="border: #F0F1F3 1px solid; border-radius: 5px; width: 150px; height: 30px; margin-left: 20px; background: #F7F8FC;">
						</div>
						<div
							style="float: left; font-size: 1.6rem; line-height: 50px; margin-left: 4%;"
							ng-show="!{{jiamengshang}}">
							销售人员电话<i class="bitian">*</i><input type="text"
								ng-model="openCourse.salesMobile"
								style="border: #F0F1F3 1px solid; border-radius: 5px; width: 150px; height: 30px; margin-left: 20px; background: #F7F8FC;">
						</div>

						<div class="end">
							<input name="git" type="submit" value="提交"
								ng-click="openCourses()" style="background: #5ED8A9;"> <input
								name="esc" type="reset" value="取消" ng-click="reset()"
								class="esc">
						</div>
					</form>
				</div>


				<!-- 免费半价重学 -->
				<div class="resource" id="resource">
					<form id="formReset3">
						<h3>{学员名字}</h3>
						<div style="display: flex; justify-content: space-between;">
							<div class="classify" style="width: 28%;">
								<p class="xiaobiaoti">选择专业</p>
								<ul class="menu" style="box-shadow: none;">

									<li class="list" ng-click="typeList(1)"><b>医师资格 </b>
										<ul class="items" ng-class="{'active':active==1}">
											<li ng-repeat="sub in courseTypeSubclass"
												ng-click="typeSub('医师资格',sub)"
												ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>

										</ul></li>
									<li class="list" ng-click="typeList(2)"><b>药师资格</b>
										<ul class="items" ng-class="{'active':active==2}">
											<li ng-repeat="sub in courseTypeSubclass"
												ng-click="typeSub('药师资格',sub)"
												ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>

										</ul></li>
									<li class="list" ng-click="typeList(3)"><b>中医基础理论</b>
										<ul class="items" ng-class="{'active':active==3}">
											<li ng-repeat="sub in courseTypeSubclass"
												ng-click="typeSub('中医基础理论',sub)"
												ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
										</ul></li>
									<li class="list" ng-click="typeList(4)"><b>卫生资格</b>
										<ul class="items" ng-class="{'active':active==4}">
											<li ng-repeat="sub in courseTypeSubclass"
												ng-click="typeSub('卫生资格 ',sub)"
												ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>

										</ul></li>
									<li class="list"><b>健康管理师</b>
										<ul class="items" ng-class="{'active':active==5}">
											<li ng-repeat="sub in courseTypeSubclass"
												ng-click="typeSub('健康管理师',sub)"
												ng-class="{'selected':selected==sub}">{{sub.courseTypeSubclassName}}</li>
										</ul></li>



								</ul>
							</div>
							<div style="width: 70%;">
								<ul class="xinxixuanze">
									<li style="width: 27%;">专业名称：左边的专业</li>
									<li style="width: 30%;"><img src="/images/sjk-xl.png" />
										<span>选择年份： </span> <select>
											<option>全部</option>
											<option></option>
											<option></option>
									</select></li>
									<li style="width: 30%;"><span>重学类型:</span> <select>
											<option>全部</option>
											<option></option>
											<option></option>
									</select></li>
									<input type="button" class="btn-lg im-key" value="确定">
								</ul>



								<div
									style="weight: 100; height:auto; border: 1px solid #EEEEEE; border-radius: 10px; overflow: hidden;">
									<table>

										<tbody>
											<tr>
												<th>选择</th>
												<th>名称</th>
												<th>班级价格</th>
												<th>班级折扣价</th>
												<th>班级年限</th>

											</tr>
											<tr>
												<th><input type="checkbox" /></th>
												<th>名称</th>
												<th>班级价格</th>
												<th>班级折扣价</th>
												<th>班级年限</th>
											</tr>



										</tbody>
									</table>
								</div>
							</div>

						</div>

						<div class="end">
							<input name="git" type="submit" value="提交"
								style="background: #5ED8A9;"> <input name="esc"
								type="reset" value="取消" onclick="CloseDiv3()" class="esc">
						</div>
					</form>
				</div>
				<!--添加/修改弹窗  -->
				<!--添加修改学员-->
				<div class="poop" id="add">
					<form id="myform" class="ng-pristine ng-valid">
						<h3 ng-show="userId==null">添加学员</h3>
						<h3 ng-show="userId!=null">修改学员</h3>
						<div class="select-2">
							<span>真实姓名<i class="bitian">*</i></span> <input type="text"
								ng-model="user.realname"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入学员姓名">
						</div>
						<div class="select-2">
							<span>学员电话<i class="bitian">*</i></span> <input type="text"
								ng-model="user.mobile"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入学员电话">
						</div>
						<div class="select-2">
							<span>用户密码<i class="bitian">*</i></span> <input type="text"
								ng-model="user.password"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入用户密码">
						</div>
						<div class="select-2" id="confirmPassword" style="display: none;">
							<span>确认密码<i class="bitian">*</i></span> <input type="text"
								ng-model="confirmPassword"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请确认密码">
						</div>
						<div class="select-3">
							<span>所在地区</span> <img src="/images/sjk-xl.png"> <select
								ng-model="p" ng-options="p.provinceName for p in provincelist"
								ng-change="getCity(p)">

							</select>
						</div>
						<div class="select-3">
							<span>&nbsp;</span> <img src="/images/sjk-xl.png"> <select
								ng-options="city.cityName for city in citylist"
								ng-model="cityName" ng-change="getCityName(cityName)">

							</select>
						</div>
						<div class="select-radio ">
							<ul>
								<li>学员状态</li>
								<li><input type="radio" ng-value="0"
									ng-model="user.isdisabled"> 正常</li>
								<li><input type="radio" ng-value="1"
									ng-model="user.isdisabled">禁用</li>
							</ul>
						</div>
						<div class="end">
							<input name="git" type="submit" value="提交" ng-show="userId==null"
								ng-click="saveORupdateUser()" style="background: #5ED8A9;">
							<input name="git" type="submit" value="修改" ng-show="userId!=null"
								ng-click="saveORupdateUser()" style="background: #5ED8A9;">
							<input name="esc" type="reset" value="取消" onclick="CloseDiv()"
								class="esc">
						</div>
					</form>
				</div>
			</div>



		</div>


	</div>
</div>

 
<style type="text/css">
.managr-dianpu .select-3 {
	width: 10%;
	margin-left: 18px;
	margin-right: 0;
}

.admin-table table tr form span {
	font-size: 1.4rem;
}
</style>
</div>
  </@b.body>

</div>
</html>
