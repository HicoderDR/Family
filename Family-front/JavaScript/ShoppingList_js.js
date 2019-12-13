var bannername=["营养便当","经典风味面","营养汤粥","经典蒸包-馒头","营养三明治","豆浆","美味饭团","和风寿司-手卷","烤制工坊","甜品","关东煮","呀米将","风味小食","鲜爽沙拉","咖啡","冰淇淋"];
var bannerimg=["http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yybd-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/jdfwm-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yytz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/zbmt-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yysmz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/dj-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/mwft-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/hfss-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/kzgf-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/Sweet+-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/gdz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yamijiang.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/fwxs-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/xianshishala.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/bkkf-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/bql-banner.jpg"];



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
  document.getElementById("close_btn").onclick=function () {
    $("#item_detail").removeClass("wrap_appear");
    $('#item_detail').css('display','none');
    document.getElementById("purchase_num").value=1;
  }
  document.getElementById("purchase_btn").onclick=function () {
    $("#item_detail").removeClass("wrap_appear");
    setTimeout("$('#item_detail').css('display','none')",500);
    $("#detail_wrap").addClass("slide_out");
    setTimeout("$('#detail_wrap').removeClass('slide_out')",500);
    setTimeout("document.getElementById('purchase_num').value=1",500);
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
  document.getElementsByClassName("selector")[0].classList.add("chosen");
}
window.onresize = function () {
  this.sizechange(document.getElementsByClassName('card').length-document.getElementsByClassName('empty').length-1);
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
      for(var i in data){
        var cell=document.createElement("li");
        cell.className="selector"
        cell.innerHTML=data[i];
        cell.onclick=function(){
          var cate=this.innerHTML;
          parent.newcategory(cate);
          $("#class_contain > li").removeClass("chosen");
          this.className="selector chosen";
        };
        document.getElementById("class_contain").appendChild(cell);
      }
      document.getElementById('class').style.overflowY='auto';

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      //获取失败
    }
  });
}
function newcategory(category){
  this.updatebanner(category);
  $.ajax({
    type: "get",
    url: "http://47.100.107.158:80/goodtype/category?category="+category,
    async:false,
    success: function (resp) {
      var data=resp.data;
      $("#grid_box").empty();  //清空
      let i;
      for(i in data){
        var cardcell=document.createElement("div");
        cardcell.className="card";
        (function(data){
          cardcell.onclick=function () {
            $("#item_detail").css("display","flex");
            // $('#item_detail').addClass('wrap_appear');
            setTimeout("$('#item_detail').addClass('wrap_appear')",0);
            $("#item_img").attr("src",data.uri);
            document.getElementById("item_name").innerHTML=data.type;
            document.getElementById("item_price").innerHTML="￥"+data.price;
            document.getElementById("item_desc").innerHTML=data.description;
            document.getElementById("surplus").innerHTML=document.getElementById("purchase_num").max=data.total;
            document.getElementById("purchase_num").oninput=function () {
              this.value=this.value.replace(/[^\d]/g,'');
              if(this.value<0) this.value=0;
              if(this.value=="") this.value=1;
              if(this.value>data.total) this.value=data.total;
            }
          }
        })(data[i]);
        var imgwrap=document.createElement("div");
        var imgcell=document.createElement("img");
        imgwrap.appendChild(imgcell);
        imgwrap.className="img_wrap flex_layout_column";
        imgcell.src=data[i].uri;
        var namecell=document.createElement("span");
        namecell.className="item_name";
        namecell.innerHTML=data[i].type;
        var pricecell=document.createElement("span");
        pricecell.className="item_price";
        pricecell.innerHTML="￥"+data[i].price;
        cardcell.appendChild(imgwrap);
        cardcell.appendChild(namecell);
        cardcell.appendChild(pricecell);
        document.getElementById("grid_box").appendChild(cardcell);
      }
      let count=0;
      do{
        var addcard=document.createElement("div");
        addcard.className="card empty";
        document.getElementById("grid_box").appendChild(addcard);
      }while($(".empty")[count++].offsetTop==$(".card")[i].offsetTop);
      $(".empty")[--count].remove();
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      //获取失败
    }
  });
}

function sizechange(i) {
  $(".empty").remove();
  let count=0;
  do{
    var addcard=document.createElement("div");
    addcard.className="card empty";
    document.getElementById("grid_box").appendChild(addcard);
  }while($(".empty")[count++].offsetTop==$(".card")[i].offsetTop);
  $(".empty")[--count].remove();
}

function updatebanner(category){
  $("#img_desc").html(category);
  document.getElementById("top_img").src=bannerimg[bannername.indexOf(category)];
}

function numchange(attr) {
  var curv=document.getElementById("purchase_num");
  if(attr&&curv.value<parseInt(curv.max)) curv.value++; //max和value均为字符类型，在弱类型语言中进行比较易出现错误。将其中一个变量转换为int型即可。curv.value++等价于curv.value=curv.value+1，依然自动转换为int型，自减类似
  if(!attr&&curv.value>0)                 curv.value--; //与int进行比较会自动将value转换为int型
}
