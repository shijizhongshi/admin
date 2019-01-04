<#import "_settingspane.ftl" as sp/>
<#import "_sidebarmenu.ftl" as sm/>
<#macro body menu submenu>
    <body class="page-body" ng-app="app">
    <@sp.settingspane />
    <div class="page-container">
        <@sm.sidebarmenu activemenuid="${menu}" activesubmenuid="${submenu}"/>
        <div class="main-content">
            <!-- User Info, Notifications and Menu Bar -->
            <nav class="navbar user-info-navbar" role="navigation" style="background-color:#e9484d">
                <!-- Left links for user info navbar -->
                <ul class="user-info-menu left-links list-inline list-unstyled">
                    <li class="hidden-sm hidden-xs">
                        <a href="#" data-toggle="sidebar">
                            <i class="fa-bars"></i>
                        </a>
                    </li>
                </ul>
                <!-- Right links for user info navbar -->
                <ul class="user-info-menu right-links list-inline list-unstyled">
                    <li class="dropdown user-profile">
                        <a href="#" data-toggle="dropdown">
                            <img src="/assets/images/user-4.png" alt="user-image"
                                 class="img-circle img-inline userpic-32" width="28"/>
                            <span>
                                       ${username!''}
                                       <i class="fa-angle-down"></i>
                                       </span>
                        </a>
                        <ul class="dropdown-menu user-profile-menu list-unstyled">
                            <li class="last">
                                <a href="/web/user/logout">
                                    <i class="fa-lock"></i>
                                    登出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <#nested/>
            <!-- Main Footer -->
            <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
            <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
            <!-- Or class "fixed" to  always fix the footer to the end of page -->

        </div>
    </div>
    </body>
</#macro>