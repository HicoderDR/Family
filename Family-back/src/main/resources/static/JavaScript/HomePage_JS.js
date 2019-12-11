function login() {
  //登录逻辑
  //jQuery写法
  let inner_tip=document.getElementById("tip");
  function retry() {
    inner_tip.style.color="#e74c3c";
    setTimeout(function () {
      inner_tip.innerHTML="请重试";
      inner_tip.style.color="#000";
    }, 2000);
  }
  var user = $('#user').val();
  var password = $('#password').val();

  if(user==""||password==""){
    inner_tip.innerHTML="用户名或密码不能为空";
    retry();
  }
  else {
  $.ajax({
    type: "post",  //post put get 等等
    url: "http://47.100.107.158:80/user/login",
    async:false,
    data: {  //要传入ashx文件的数据
      "username": user,
      "password": password
    },
    success: function (resp) {
      if (resp.message=="SUCCESS") {
        var url="ShoppingList.html?"+"userID="+resp.data.userid;
        self.location.href=url;
      }
      else {
        inner_tip.innerHTML=resp.message;
        retry();
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {  //连接至ashx文件失败时，执行函数
      inner_tip.innerHTML="请求在连接过程中出现错误";
      retry();
    }
  });
  }
}
