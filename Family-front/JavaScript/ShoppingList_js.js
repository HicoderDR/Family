var bannername=["营养便当","经典风味面","营养汤粥","经典蒸包-馒头","营养三明治","豆浆","美味饭团","和风寿司-手卷","烤制工坊","甜品","关东煮","呀米将","风味小食","鲜爽沙拉","咖啡","冰淇淋"];
var bannerimg=["http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yybd-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/jdfwm-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yytz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/zbmt-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yysmz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/dj-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/mwft-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/hfss-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/kzgf-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/Sweet+-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/gdz-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/yamijiang.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/fwxs-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/xianshishala.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/bkkf-banner.jpg","http://www.familymart.com.cn/static/common/img/ftc/xxtv-banner/bql-banner.jpg"];
var dataMouth = [];//x轴
var data1 = [];
var user;
var nowday=new Date();

window.onload=function () {
  xdata();//加载日期戳
  let that=this;
  let attr=document.getElementById("user_attr");
  let head=document.getElementById("head_portrait");
  let fold=document.getElementById("fold_attr");
  let cart_icon=document.getElementById("shopping_cart");
  let list=$("#purchase_list");
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
  cart_icon.onclick=function () {
    if(list.css("display")=="none") {
      list.css("display","block");
      cart_icon.style.backgroundColor="#ecf0f1";
    }
    else {
      list.css("display","none");
      cart_icon.style.backgroundColor="white";
    }
  }
  document.getElementById("cart_close_btn").onclick=function () {
    list.css("display","none");
    cart_icon.style.backgroundColor="white";
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
      user=resp.data;
      if(user==null){
        document.getElementById("user_name").innerHTML="您尚未登录，请"
        document.getElementById("log_in").style.display="inline-block";
      }
      else if(user.userid){
        document.getElementById("shopping_cart").style.display="flex";
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
        document.getElementById("balance").innerHTML=user.balance.toFixed(1)+"$";
      }
      else if(user.stuffid){
        document.getElementById("log_out").style.display="inline-block";
        document.getElementById("user_attr").style.display="none";
        document.getElementById("user_name").innerHTML=user.name;
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      document.getElementById("user_name").innerHTML="连接服务器失败";
      document.getElementById("user_name").style.color="#e74c3c";
    }
  });
  this.initsidebar();
  //初始化为营养便当
  this.newcategory("营养便当");
  document.getElementsByClassName("selector")[0].classList.add("chosen");
  document.getElementById( "shopping_list" ).addEventListener( 'DOMSubtreeModified', function () {
    let total = 0.0, discount = 0.0, addpoint = 0;
    for ( var i in $( ".shopping_item" ) ) {
      if ($( ".item_price_wrap" )[i].innerHTML && $( ".item_num_wrap" )[i].innerHTML) {
        let num=parseFloat( $( ".item_num_wrap" )[i].innerHTML.split( '×' )[1] );
        let price=parseFloat( $( ".item_price_wrap" )[i].innerHTML.split( '￥' )[1] );
        total += num * price;
        let discount_info = $( ".discount_info_1" )[i].innerHTML;
        if (discount_info != "") {
          var classify = discount_info.split( ',' );
          if (user.viptype == 1) {
            if (classify[0] == "a") {
              discount+=price*parseInt(parseInt(classify[3])*num/(parseInt(classify[2])+parseInt(classify[3]))) ;
            }
            if (classify[0] == "b") {
              addpoint+=parseInt(num*parseInt(classify[2]));
            }
            if (classify[0] == "c") {
              discount+=parseInt(num/parseInt(classify[2]))*parseInt(classify[3]);
            }
          }
          if (user.viptype == 0 && classify[1] == "0") {
            if (classify[0] == "a") {
              discount+=price*parseInt(parseInt(classify[3])*num/(parseInt(classify[2])+parseInt(classify[3]))) ;
            }
            if (classify[0] == "b") {
              addpoint+=parseInt(num*parseInt(classify[2]));
            }
            if (classify[0] == "c") {
              discount+=parseInt(num/parseInt(classify[2]))*parseInt(classify[3]);
            }
          }
        }
      }
    }
    discount=discount.toFixed(1);
    total=total.toFixed(1);
    let result=total-discount;
    result=result.toFixed(1);
    if(user.viptype == 0) addpoint+=parseInt(result);
    else addpoint+=2*parseInt(result);
    $("#total_price").html("￥"+total);
    $("#discount").html("￥-"+discount);
    $("#point_plus").html("+"+addpoint);
    $("#result_price").html("￥"+result);
  }, false);
}
window.onresize = function () {
  this.sizechange(document.getElementsByClassName('card').length-document.getElementsByClassName('empty').length-1);
}

//载入统计日期
function xdata() {

  for(var i=0;i<14;i++){
    var item=(nowday.getMonth()>9)?(nowday.getMonth()+1)+"-"+nowday.getDate():"0"+(nowday.getMonth()+1)+"-"+nowday.getDate();
    dataMouth.push(item);
    nowday=nowday.setDate(nowday.getDate()-1);
    nowday=new Date(nowday);
  }
  dataMouth.reverse();
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
        if(user){
          (function(data){
            cardcell.onclick=function () {
              if(user.userid){
                $("#item_detail").css("display","flex");
                setTimeout("$('#item_detail').addClass('wrap_appear')",0);
                $("#item_img").attr("src",data.uri);
                document.getElementById("item_name").innerHTML=data.type;
                document.getElementById("item_price").innerHTML="￥"+data.price;
                document.getElementById("item_desc").innerHTML=data.description;
                document.getElementById("surplus").innerHTML=document.getElementById("purchase_num").max=data.total;
                $("#discount_info").empty();
                if(data.buysend!=null){
                  let discount=data.buysend.split(',');
                  if(discount[1]=="1"){
                    if(discount[0]=="a") $("#discount_info").html("尊享会员 每买"+discount[2]+"赠送"+discount[3]+"件");
                    if(discount[0]=="b") $("#discount_info").html("尊享会员 单件加赠"+discount[2]+"积分");
                    if(discount[0]=="c") $("#discount_info").html("尊享会员 每买"+discount[2]+"件减免"+discount[3]+"元");
                  }
                  if(discount[1]=="0"){
                    if(discount[0]=="a") $("#discount_info").html("普通会员 每买"+discount[2]+"赠送"+discount[3]+"件");
                    if(discount[0]=="b") $("#discount_info").html("普通会员 单件加赠"+discount[2]+"积分");
                    if(discount[0]=="c") $("#discount_info").html("普通会员 每买"+discount[2]+"件减免"+discount[3]+"元");
                  }
                }
                var purchase_num=document.getElementById("purchase_num");
                purchase_num.oninput=function () {
                  this.value=this.value.replace(/[^\d]/g,'');
                  if(this.value<0) this.value=0;
                  if(this.value=="") this.value=1;
                  if(this.value>data.total) this.value=data.total;
                }
                document.getElementById("purchase_btn").onclick=function () {
                  if(purchase_num.value!=0){
                    animation();//执行动画
                    addchild(data.uri,data.type,data.price,document.getElementById("purchase_num").value,data.buysend);//加入购物车
                  }
                }
              }
              else if(user.stuffid){
                $("#item_detail").css("display","flex");
                setTimeout("$('#item_detail').addClass('wrap_appear')",0);
                document.getElementById("detail_wrap").style.display="none";
                document.getElementById("statistics_wrap").style.display="block";
                document.getElementById("statistics_name").innerHTML=data.type;
                $.ajax({
                  type: "get",
                  url: "http://47.100.107.158:80/ord/statistic",
                  async:false,
                  data: {
                    "goodname":data.type,
                  },
                  success: function (resp) {
                    var list=resp.data;
                    data1=[0,0,0,0,0,0,0,0,0,0,0,0,0,0];
                    for(var j in list){
                      var date=list[j].saledate.substring(5,10);
                      for(var k in dataMouth){
                        if(date==dataMouth[k]){
                          data1[k]=list[j].num;
                        }
                      }
                    }
                  },
                  error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("获取数据失败");
                  }
                });
                chart();
              }
            }
          })(data[i]);
        }
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
function purchase(op){
  var path="http://47.100.107.158:80/user/pay";
  if(op==0) path+="score"
  var goodlist=[],numlist=[]
  const data=$(".item_name_wrap")
  const data2=$(".item_num_wrap")
  var price=$("#result_price").html().split('￥')[1]
  var score=$("#point_plus").html().split('+')[1]
  for(var i=0;i<data.length;i++){
    goodlist.push(data[i].innerHTML)
    numlist.push(data2[i].innerHTML.split('×')[1])
  }

  $.ajax({
    url:path,
    type:"post",
    dateType:'json',
    data:{
      "userid":user.userid,
      "goodlist":goodlist,
      "numlist":numlist,
      "money":parseFloat(price),
      "stuffid":"wk001",
      "score":parseFloat(score)
    },
    success:function(res){
      location.reload()
    },
    error:function(err){
      alert("网络连接失败,稍后重试",err);
    }
  })

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

function animation(){
  $("#item_detail").removeClass("wrap_appear");
  setTimeout("$('#item_detail').css('display','none')",400);
  $("#detail_wrap").addClass("slide_out");
  setTimeout("$('#detail_wrap').removeClass('slide_out')",400);
  setTimeout("document.getElementById('purchase_num').value=1",400);
}
function addchild(img,name,price,num,discount) {
  let jud=1;
  var curv=document.getElementById("purchase_num");
  for(var i in $(".item_name_wrap")){
    if(name==$(".item_name_wrap")[i].innerHTML){
      if(parseInt(curv.max)<=parseInt($(".item_num_wrap")[i].innerHTML.split('×')[1])+parseInt(num)){
        $(".item_num_wrap")[i].innerHTML="×"+curv.max;
        document.getElementsByClassName("item_num_wrap")[i].style.color="#e74c3c";
      }
      else {
        $(".item_num_wrap")[i].innerHTML="×"+(parseInt($(".item_num_wrap")[i].innerHTML.split('×')[1])+parseInt(num));
      }
      jud=0;
    }
  }
  if(jud){
    var purchase_item=document.createElement("div");
    purchase_item.className="shopping_item";
    var cancel_btn=document.createElement("div");
    cancel_btn.className="cancel_item";
    cancel_btn.onclick=function(){
      $(this).parent("div").remove();
    }
    purchase_item.appendChild(cancel_btn);
    var icon=document.createElement("i");
    icon.className="fa fa-minus";
    cancel_btn.appendChild(icon);
    var img_wrap=document.createElement("div");
    img_wrap.className="item_img_wrap";
    purchase_item.appendChild(img_wrap);
    var img_url=document.createElement("img");
    img_url.src=img;
    img_wrap.appendChild(img_url);
    var name_wrap=document.createElement("div");
    name_wrap.className="item_name_wrap";
    name_wrap.innerHTML=name;
    purchase_item.appendChild(name_wrap);
    var price_wrap=document.createElement("div");
    price_wrap.className="item_price_wrap";
    price_wrap.innerHTML="￥"+price;
    purchase_item.appendChild(price_wrap);
    //记录幽灵的折扣信息
    var discount_info=document.createElement("div");
    discount_info.className="discount_info_1";
    discount_info.innerHTML=discount;
    purchase_item.appendChild(discount_info);
    //
    var num_wrap=document.createElement("div");
    num_wrap.className="item_num_wrap";
    num_wrap.innerHTML="×"+num;
    purchase_item.appendChild(num_wrap);
    document.getElementById("shopping_list").appendChild(purchase_item);
  }
}
//员工关闭盒子
function close_wrap() {
  $("#item_detail").removeClass("wrap_appear");
  $('#item_detail').css('display','none');
}
function href_HomePage() {
  self.location.href="../html5/HomePage.html";
}
function chart(){
  var myChart = echarts.init(document.getElementById('chart'));
// 使用刚指定的配置项和数据显示图表。
  //显示数据，可修改
  var  option = {
    title: {
      text: '近两周的销售量',
      left: 'left',
      top: 'top',
    },
    tooltip: {
      trigger: 'axis'
    },
    grid: {
      left: '1%',
      right: '15%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        axisLine:{
          symbol:['none','none'],
          lineStyle:{
            color:'#9b9b9b',
          }
        },
        name: '日期',
        data: dataMouth,
        splitLine:{
          show:false,
        },
      }
    ],
    yAxis: [
      {
        type: 'value',
        axisLine:{
          symbol:['none','none'],
          lineStyle:{
            color:'#9b9b9b',
          }
        },
        name: '销售量',
        min: 0,
        max: 10,
        interval: 1,
        axisLabel: {
          formatter: '{value} '
        },
        splitLine:{
          show:false,
        },
      }
    ],
    series: [

      {
        name:'用户统计',
        type:'bar',
        /*设置柱状图颜色*/
        itemStyle: {
          normal: {
            color: function(params) {
              // build a color map as your need.
              var colorList = [
                '#d9e8fd'
              ];
              return colorList[params.dataIndex]
            },
            /*信息显示方式*/
            label: {
              show: false,
              position: 'top',
              formatter: '{b}\n{c}'
            }
          }
        },
        data:data1
      },
      {
        name:'折线',
        type:'line',
        itemStyle : {  /*设置折线颜色*/
          normal : {
            color:'#78aef9'
          }
        },
        data:data1
      }
    ]
  };
  myChart.setOption(option);
}
