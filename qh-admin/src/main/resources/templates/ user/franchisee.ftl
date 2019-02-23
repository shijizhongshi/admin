<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学员管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/indent/excle.js"></script>

<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-student-user-franchisee">
<div ng-controller="gradeController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>用户管理</li>
				<li>/</li>
				<li>加盟商信息管理</li>
			</ul>
		</div>
<div class="details-frame">
	<div class="details-frame-content">
		<div class="select-3">
							<span>加盟商注册时间</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
					
	<div class="select-3">
		<span>加盟商名称</span>
		
		<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
		<div class="select-3">
		<span>地区</span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?"></option>
			<option ></option>
			<option ></option>
		</select>
	</div>
	
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()" value="检索">
				</div>
	</div>
<div class="manage">
	<ul class="show">

			<li style="background:#9DE879;" onclick="showDiv()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加加盟商</li>
		<li style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改加盟商</li>
		<li style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除加盟商</li>
		<li  onclick="showDiv2()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;加盟商充值</li>
		<li><span class="glyphicon glyphicon-briefcase"></span>&nbsp;停用</li>

 
	</ul>
	<div class="admin-table">

  <table>

	<tbody>
	<tr>
		<th>加盟商名称</th>
	<th>负责人</th>
	<th>联系电话</th>
	<th>LOGO</th>
	<th>地区</th>
	<th>充值总额</th>
	<th>课程余额</th>
	<th>用户状态</th>
	<th>加盟时间</th>
	<th>最后登陆时间</th>
	</tr>
	<tr>
			<th>加盟商名称</th>
	<th>负责人</th>
	<th>联系电话</th>
	<th>LOGO</th>
	<th>地区</th>
	<th>充值总额</th>
	<th>课程余额</th>
	<th>用户状态</th>
	<th>加盟时间</th>
	<th>最后登陆时间</th>
	</tr>



	</tbody></table>
	</div>
	<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination="" boundary-links="true" total-items="total" ng-model="current" items-per-page="pageSize" max-size="5" class="pagination-sm ng-pristine ng-untouched ng-valid ng-not-empty" previous-text="?" next-text="?" first-text="?" last-text="?" ng-change="courseBases()">
                        </ul>
                    </div>
	<!--添加修改加盟商-->
		<div class="poop" id="add" style="width:600px;left:15%;top:0;" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3>{添加、修改}加盟商</h3>
	<div style="float:left;width:49%;">
<div class="select-2">
		<span>加盟商名称<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入名称" >
	</div>
	
		<div class="select-3">
		<span>所在城市<i class="bitian">*</i></span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?">所在省区</option>
			<option ></option>
			<option ></option>
		</select>
	</div>
		<div class="select-3">
		<span>&nbsp;</span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?">所在市区</option>
			<option ></option>
			<option ></option>
		</select>
	</div>

	
	<div class="select-2" style="clear:both;">
		<span>负责人姓名<i >*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请姓名" >
	</div>
	<div class="select-2" style="border-botom:1px #F0F0F0 solid;">
		<span>联系电话<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入用户密码" >
	</div>
	<div class="select-2">
		<span>初始充值余额<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
<div class="select-2">
		<span>拥有多少钱的课程<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
	</div>
	<div style="width:49%;float:right;">
	<div class="costs-uploadfile-div">
	<b>加盟商LOGO</b> <input type="file" id="file" value="上传加盟商LOGO" accept="image/gif, image/jpeg, image/png, image/jpg">
	<div style="margin-top: 3px;">
	<div id="polyved" style="display: none;"></div>
		</div>
	<div class="select-radio" style="margin:24px 0;">
		<ul><li>账户状态</li>  
		<li><input type="radio" ng-value="1" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="1"> 正常</li> 
		<li><input type="radio" ng-value="0" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="0">停用</li></ul>
		</div>	
		<div class="select-2">
		<span>设置账号<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
	<div class="select-2">
		<span>设置密码<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
	<div class="select-2">
		<span>确认密码<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
		
							</div>
							
	
	</div>
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="courseId!=null" ng-click="addCourse()" style="background:#5ED8A9;" class="ng-hide">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv()" class="esc">
		</div>
		</form>
	</div>
<!-- 充值操作-->

<div class="poop" id="revise">
<form id="formReset2">
<h3>充值操作</h3>
<div class="select-2">
		<span>加盟商名称</span>
 <p style="border-radius:5px;border:#F0F1F3 1px solid;background:#F7F8FC;width:100%;height:30px;font-size:1.4rem;text-indent:1rem;line-height:30px;color:#75758B;">加盟商名字</p>
	</div>
	<div class="select-2">
		<span>充值金额<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入金额" >
	</div>
		<div class="select-2">
		<span>兑换的课程金额<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入兑换的课程金额" >
	</div>
<div class="end">
			<input name="git" type="submit" value="提交"  style="background:#5ED8A9;">

			<input name="esc" type="reset" value="取消" onclick="CloseDiv2()" class="esc">
		</div>
	</form>
</div>









</div>

</div>










	</div>
</div>
</div>


</@b.body>

</html>