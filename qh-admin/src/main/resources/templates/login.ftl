<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="登陆"/>
<script src="/scripts/login.js"></script>
<body ng-app="app">
 <div class="load" ng-controller="loginController">
 	<div class="load-body">
 		<form>
 			<input type="text" ng-model="username" placeholder="请输入用户名"/>
 			<input type="password" ng-model="password" placeholder="请输入密码" />
 			<input type="submit" value="登陆" ng-click="login()"/>
 		</form>
 	</div>
 </div>
</body>
 <style type="text/css">
.load{width: 100%;height: 100%;background: url(../images/load1.png) no-repeat; background-size:100%;display:flex;justify-content:center;align-items:center;}
.load-body{background: url(../images/load2.png) no-repeat;background-size:100%;width:60%;height: 50%;}
.load-body form{width: 50%;height: 70%;float: right;padding:15% 10%;margin-top:0.1rem;}
.load-body  input{width: 100%;height: 5%;border: 1px solid #B1B1B1;padding: 8%;margin-top:0.1rem;font-size:0.2rem;line-height: 5%;}
</style>
 </html>