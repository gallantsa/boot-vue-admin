document.write(`

      <div style="height: 60px; line-height: 60px; border: 1px solid #ccc; display: flex">
        <div style="width: 200px; text-align: center;">
            <img src="imgs/logo.png" style="width: 30px; height: 30px; position: relative; top: -2px" alt="">
            <span style="font-size: 20px; color: #0d6efd; margin-left: 5px">后台管理系统</span>
        </div>

        <div style="flex: 1"></div>

        <div style="width: 150px; text-align: center;">
            <div class="dropdown">
                <span class="dropdown-toggle" data-bs-toggle="dropdown" style="cursor: pointer">
                    个人信息
                </span>
                <ul class="dropdown-menu" style="line-height: 20px; width: 120px; text-align: center">
                    <li><a class="dropdown-item" href="/person.html">个人信息</a></li>
                    <li><a class="dropdown-item" href="/password.html">修改密码</a></li>
                    <li><a class="dropdown-item" href="/user/logout">退出登录</a></li>
                </ul>
            </div>
        </div>
      </div>


`)