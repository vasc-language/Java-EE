/**
 * 这个方法用于在每个Ajax请求发送前自动执行特定操作。
 *
 * 实际应用：从 localStorage 获取 userToken 并添加到每个请求的头部，实现了自动身份验证机制，无需在每个 Ajax 请求重复添加 token
 * 功能：注册一个全局 Ajax 事件处理器，在任何 Ajax 请求发送之前自动触发
 * 实现原理：它将回调函数绑定到 document 对象上，每当有 Ajax 请求准备发送时，jQuery 会触发这个回调
 *
 * 参数：
 *   - event：触发的 jQuery 事件对象
 *   - xhr：原生的 XMLHttpRequest 对象，可以修改其属性
 *   - options：Ajax 请求的配置选项
 */
$(document).ajaxSend(function (event, xhr, options) {
    // 从 localStorage 中拿到 userToken
    xhr.setRequestHeader("user_token", localStorage.getItem("userToken"));
});

/**
 * 这个方法用于捕获所有的 Ajax 请求发生的错误并统一处理
 *
 * 实际应用：当服务端返回 401 状态码（未授权）时，会自动重定向到登录页面，实现了统一权限验证和未登录处理
 * 功能：注册一个全局 Ajax 错误处理器，当任何 Ajax 请求失败时自动触发
 * 实现原理：将错误处理回调绑定到document上，任何 Ajax 请求出错时都会调用
 */
$(document).ajaxError(function (event, xhr, options, exc) {
    if (xhr.status == 401) {
        location.href = "blog_login.html";
    }
})


function getUserInfo(url) {
    $.ajax({
        type: "get",
        url: url,
        success: function (result) {
            if (result != null && result.code == "SUCCESS" && result.data != null) {
                var userInfo = result.data;
                // 将用户名（userName）显示在页面左侧卡片的 h3 标签中
                $(".left .card h3").text(userInfo.userName);

                // 将 Github 链接（githubUrl）设置为卡片中的 a 标签的 href 属性
                $(".left .card a").attr("href", userInfo.githubUrl);
            }
        }
    });
}


/**
 * 这个函数会清除本地存储的用户ID和令牌，然后将用户重定向到登陆页面
 * 点击“注销”按钮
 */
function logout() {
    // 清除本地存储的用户信息
    localStorage.removeItem("loginUserId");
    localStorage.removeItem("userToken");
    // 跳转到登录页
    location.href = "blog_login.html";
}