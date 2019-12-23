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
  //JavaScript原生写法
  //var user = document.getElementById('user').value;
  //var password = document.getElementById('password').value;
  if(user==""||password==""){
    inner_tip.innerHTML="用户名或密码不能为空";
    retry();
  }
  else {
  $.ajax({
    type: "post",  //post put get 等等
    url: "http://47.100.107.158:80/user/login",
    //编写登录功能时，要将异步设置为false（缺省为true）
    //如果async是ture,对于FireFox浏览器，会刷新掉alert()弹出框的内容
    //对于Chrome浏览器，第一次注册时会执行error的回调函数，输出“请求在连接过程中出现错误..”
    async:false,
    data: { 
      "username": user,
      "password": password
    },
    success: function (resp) {
      //连接至ashx文件成功时，执行函数
      //data是从ashx文件返回来的信息，可以是字符串也可以是一个对象
      //如果data是字符串，则可以输出data的值
      //如果data是对象，则可以将这个对象的各属性值赋给其他变量
      //textStatus是表示状态的字符串，这里textStatus的值是"success"
      if (resp.message=="SUCCESS") {
        if(resp.data.userid) {
          var url = "shop?" + "userID=" + resp.data.userid;
          self.location.href = url;
        }else{
          var url = "shop?" + "userID=" + resp.data.stuffid;
          self.location.href = url;
        }
      }
      else {
        inner_tip.innerHTML=resp.message;
        retry();
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {  //连接至ashx文件失败时，执行函数
      //XMLHttpRequest在这个例子里没有用到
      //textStatus是表示状态的字符串，这里textStatus的值是"error"
      //errorThrown包含连接失败的信息，可以输出查看
      inner_tip.innerHTML="请求在连接过程中出现错误";
      retry();
    }
  });
  }
}
