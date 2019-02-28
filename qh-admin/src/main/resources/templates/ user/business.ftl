<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学员管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/user/business.js"></script>
<script src="/scripts/indent/excle.js"></script>

<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-business">
<div ng-controller="businessController">
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
								<input type="date" ng-model="fromdate" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" ng-model="todate" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
					
	<div class="select-3">
		<span>加盟商名称</span>
		
		<input type="text" ng-model="name" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
		<div class="select-3">
		<span>省</span>
		<img src="/images/sjk-xl.png">
		<select  ng-model="p" ng-options="p.provinceName for p in provincelist" ng-change="getCity(p)">
		</select>
	</div>
	<div class="select-3">
		<span>市</span>
		<img src="/images/sjk-xl.png">
		<select  ng-options="city.cityName for city in citylist" ng-model="city" ng-change="getCityName(city)">
		</select>
	</div>
	<div class="select-3">
		<span>到期时间排序</span>
		<img src="/images/sjk-xl.png">
		<select ng-model="expireOrders">
		<option value="1">升序</option>
		<option value="2">降序</option>
		</select>
	</div>
	<div class="select-3">
		<span>开课余额</span>
		<img src="/images/sjk-xl.png">
		<select ng-model="superOrders">
		<option value="1">升序</option>
		<option value="2">降序</option>
		</select>
	</div>
	
	
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()" value="检索">
				</div>
	</div>
<div class="manage">
	<ul class="show">

			<li style="background:#9DE879;" ng-click="add()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加加盟商</li>
		<li style="background:#F9CD33;" ng-click="update()"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改加盟商</li>
		<li style="background:#F86846;" ng-click="deleteBusiness()"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除加盟商</li>
		<li  ng-click="tocharge()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;加盟商充值</li>
		<li ng-click="closeBusiness()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;停用</li>
 
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
	<th>兑换总额</th>
	<th>课程余额</th>
	<th>用户状态</th>
	<th>加盟时间</th>
	<th>到期时间</th>
	</tr>
	<tr ng-repeat="business in businesslist" ng-click="checkBusiness(business)" ng-class="{'selected':selected==business}">
	<th>{{business.name}}</th>
	<th>{{business.principal}}</th>
	<th>{{business.mobile}}</th>
	<th><img ng-src="{{business.logo}}"></th>
	<th>{{business.address}}</th>
	<th>{{business.payaccount}}</th>
	<th>{{business.account}}</th>
	<th>{{business.surplusaccount}}</th>
	<th ng-show="{{business.status=='1'}}">钱不够了</th>
	<th ng-show="{{business.status=='2'}}">到期了</th>
	<th ng-show="{{business.status=='0'}}">正常</th>
	<th>{{business.addtime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
	<th>{{business.expireTime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
	</tr>



	</tbody></table>
	</div>
	<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination boundary-links="true" 
                        total-items="total" ng-model="current" items-per-page="pageSize" 
                        max-size="5" class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;" 
								ng-change="loaddata()">
                        </ul>
                    </div>
	<!--添加修改加盟商-->
		<div class="poop" id="add" style="width:600px;left:15%;top:0;" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3 ng-show="businessId==null">添加加盟商</h3>
	<h3 ng-show="businessId!=null">修改加盟商</h3>
		<div style="float:left;width:49%;">
<div class="select-2">
		<span>加盟商名称<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" ng-model="business.name" placeholder="请输入名称" >
	</div>
	
		<div class="select-3">
		<span>所在城市<i class="bitian">*</i></span>
		<img src="/images/sjk-xl.png">
		<select ng-model="p" ng-options="p.provinceName for p in provincelist" ng-change="getCity(p)">
		
		</select>
	</div>
		<div class="select-3">
		<span>&nbsp;</span>
		<img src="/images/sjk-xl.png">
		<select ng-options="city.cityName for city in citylist" ng-model="cityName" ng-change="getCityName(cityName)">
		</select>
	</div>

	
	<div class="select-2" style="clear:both;">
		<span>负责人姓名<i >*</i></span>
<input type="text" ng-model="business.principal" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入负责人姓名" >
	</div>
	<div class="select-2" style="border-botom:1px #F0F0F0 solid;">
		<span>联系电话<i class="bitian">*</i></span>
<input type="text" ng-model="business.mobile" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入联系电话" >
	</div>
	<div class="select-2">
		<span>初始充值余额<i class="bitian">*</i></span>
<input type="number" ng-model="business.payaccount" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入充值金额" >
	</div>
<div class="select-2">
		<span>兑换多少钱的课程<i class="bitian">*</i></span>
<input type="number" ng-model="business.account" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入兑换多少钱的课程" >
	</div>
	</div>
	<div style="width:49%;float:right;">
	<div class="costs-uploadfile-div">
	<b>加盟商LOGO</b> <input type="file" value="上传加盟商LOGO" 
	accept="image/gif, image/jpeg, image/png, image/jpg"
	onchange="angular.element(this).scope().uploadmainimage(this)">
	<div style="height: 130px; margin-top: 3px;">
	<img src="{{business.logo}}" style="height: 130px;"/>
	</div>
	<b>加盟商banner</b> <input type="file" value="上传加盟商banner" 
	accept="image/gif, image/jpeg, image/png, image/jpg"
	onchange="angular.element(this).scope().uploadmainimage1(this)">
	<div style="height: 130px; margin-top: 3px;">
	<img src="{{business.banner}}" style="height: 130px;"/>
	</div>
	<div class="select-radio" style="margin:24px 0;">
		<ul><li>账户状态</li>  
		<li><input type="radio" ng-model="business.status" ng-value="0"> 正常</li> 
		<li><input type="radio" ng-model="business.status" ng-value="2">到期</li>
		<li><input type="radio" ng-model="business.status" ng-value="1">没钱</li>
		</ul>
		</div>	
		<div class="select-2">
		<span>设置账号<i class="bitian">*</i></span>
<input type="text" ng-model="business.username" placeholder="请设置登录的账号" >
	</div>
	<div class="select-2">
		<span>设置密码<i class="bitian">*</i></span>
<input type="password" ng-model="business.password" placeholder="请设置登录的密码" >
	</div>
	<div class="select-2">
		<span>确认密码<i class="bitian">*</i></span>
<input type="password" ng-model="business.confirmPassword" placeholder="请输入确认密码">
	</div>
		
							</div>
							
	
	</div>
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="businessId==null" ng-click="saveUpdateBusiness()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="businessId!=null" ng-click="saveUpdateBusiness()" style="background:#5ED8A9;">
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
 <p style="border-radius:5px;border:#F0F1F3 1px solid;background:#F7F8FC;width:100%;height:30px;font-size:1.4rem;text-indent:1rem;line-height:30px;color:#75758B;">{{businessName}}</p>
	</div>
	<div class="select-2">
		<span>充值金额<i class="bitian">*</i></span>
<input type="number" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入金额" ng-model="newBusiness.payaccount" >
	</div>
		<div class="select-2">
		<span>兑换的课程金额<i class="bitian">*</i></span>
<input type="number" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入兑换的课程金额" ng-model="newBusiness.account" >
	</div>
<div class="end">
			<input name="git" type="submit" value="提交" ng-click="submitcharge()"  style="background:#5ED8A9;">

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