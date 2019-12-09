window.onload=function () {
  let attr=document.getElementById("user_attr");
  let head=document.getElementById("head_portrait");
  let fold=document.getElementById("fold_attr");
  attr.onmouseenter=function () {
    fold.style.display="block";
    head.className="head_portrait enlarge";
    document.getElementById("user_name").style.display="none";
  }
  attr.onmouseleave=function () {
    fold.style.display="none";
    head.className="head_portrait";
    document.getElementById("user_name").style.display="inline";
  }
  var userID=getURLParameter("userID");
  $.ajax({
    type: "get",
    url: "http://47.100.107.158:80/user/getone",
    async:false,
    data: {
      "userID":userID,
    },
    success: function (resp) {
      var user=resp.data;
      if(user==null){
        document.getElementById("user_name").innerHTML="您尚未登录，请"
        document.getElementById("log_in").style.display="block";
      }
      else {
        document.getElementById("user_attr").style.display="block";
        document.getElementById("user_name_1").innerHTML=user.username;
        document.getElementById("user_name").innerHTML=user.username;
        document.getElementById("user_id").innerHTML=user.userid;
        if(user.viptype==0) {
          document.getElementById("root").innerHTML="普通";
          document.getElementById("due_time").innerHTML="永久";
        }
        else {
          document.getElementById( "root" ).innerHTML = "尊享";
          document.getElementById("due_time").innerHTML=user.vipdate;
        }
        document.getElementById("points").innerHTML=user.vipscore;
        document.getElementById("balance").innerHTML=user.balance+"$";
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      document.getElementById("user_name").innerHTML="连接服务器失败";
      document.getElementById("user_name").style.color="#e74c3c";
    }
  });
  document.getElementById("log_out").onclick=function () {
    self.location.href="../html5/HomePage.html";
  }
  document.getElementById("log_in").onclick=function () {
    self.location.href="../html5/HomePage.html";
  }
  

  this.initsidebar();
  //初始化为营养便当
  this.newcategory("营养便当");
}
function getURLParameter(name) {
  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}
function initsidebar(){
  $.ajax({
    type: "get",
    url: "http://47.100.107.158:80/goodtype/categorylist",
    async:false,
    success: function (resp) {
      var data=resp.data;
      $("class_contain").html("");  //清空
      for(var i in data){
        var cell=document.createElement("li");
        cell.innerHTML=data[i];
        cell.onclick=function(){
          var cate=this.innerHTML;
          parent.newcategory(cate);
        };
        document.getElementById("class_contain").appendChild(cell);
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      //获取失败
    }
  });
}
function newcategory(category){
  $.ajax({
    type: "get",
    url: "http://47.100.107.158:80/goodtype/category?category="+category,
    async:false,
    success: function (resp) {
      var data=resp.data;
      $("#grid_box").html("");  //清空
      for(var i in data){
        var cardcell=document.createElement("div");
        cardcell.className="card";
        var imgcell=document.createElement("img");
        imgcell.src=data[i].uri;
        var namecell=document.createElement("span");
        namecell.innerHTML=data[i].type;
        var pricecell=document.createElement("span");
        pricecell.innerHTML=data[i].price;

        cardcell.appendChild(imgcell);
        cardcell.appendChild(namecell);
        cardcell.appendChild(pricecell);

        document.getElementById("grid_box").appendChild(cardcell);
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      //获取失败
    }
  });
}