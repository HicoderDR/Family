
import requests
from bs4 import BeautifulSoup
import sys
import uuid
import threading,time

def run(a,b,c,d,e):
    path="http://localhost:80/goodtype/add?"+"type="+a+"&price="+b+"&url="+c+"&category="+d+"&desc="+e
    requests.post(path)
    time.sleep(2)

#防反爬虫所以设置header
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
                  '(KHTML, like Gecko) Chrome/79.0.3945.45 Safari/537.36 Edg/79.0.309.30',
    'Referer':'http://www.familymart.com.cn/commodity/express',
    'Accept': 'image/webp,image/apng,image/*,*/*;q=0.8',
    'Cookie': 'FMSID=l7qoqvsn683somt794pl1b2cd5; Hm_lvt_1166d2ba6c66a5addbb755e9d3aa650f=1574648828,1574663188,1575252593,1575273581; Hm_lpvt_1166d2ba6c66a5addbb755e9d3aa650f=1575274805',
    'Connection': 'keep-alive',
    'Host': 'www.familymart.com.cn',
}
#若下载图片可以设置代理
proxies = {
    "http": "49.51.195.24:1080",
}

print(sys.getdefaultencoding())#utf-8

start="http://www.familymart.com.cn/commodity/"
path=["yingyangbiandang","jingdianfengweimian","yingyangtangzhou","jingdianzhengbao_mantou","yingyangsanmingzhi","riguangdoujiang","meiweifantuan","hefengshousi_shoujuan","kaojiugongfang","sweets","guandongzhubenpu","yamijiang","fengweixiaoshi","xianshuangshala","baikecoffee","icecream"]
cate=["营养便当","经典风味面","营养汤粥","经典蒸包-馒头","营养三明治","豆浆","美味饭团","和风寿司-手卷","烤制工坊","甜品","关东煮","呀米将","风味小食","鲜爽沙拉","咖啡","冰淇淋"]
leng=len(path)

pool=[]         

for j in range(0,leng):
    response = requests.get(start+path[j])
    page = response.text
    print(response.encoding)  #ISO-8859-1
    page = page.encode("utf-8").decode("utf-8")
    
    soup = BeautifulSoup(page, features='html.parser') #beautifulsoup 解析网页文件
    target = soup.find(id = 'content')
    alist = target.find_all(name = 'img')
    blist = target.find_all(name = 'span')
    clist = target.find_all(name = 'em')
    dlist = target.find_all (class_="product-item")

    url=[]
    price=[]
    name=[]
    descp=[]

    for i in alist:
        aimage = "http://www.familymart.com.cn"+ str(i.attrs.get('data-src'))
        a=str(aimage)
        url.append(a)
    for i in blist:
        a=str(i)[6:-7]
        name.append(a)
    for i in clist:
        a=str(i)[6:-5]
        price.append(a)
    for i in dlist:
        s=str(i.attrs.get('onclick'))
        m=s.split(',')
        descp.append(str(m[3])[2:-2].replace('',''))

    for i in range(0,len(name)):
        print(str(i)+" "+name[i]+" "+price[i]+" "+url[i+1]+" "+descp[i])
        pool.append(threading.Thread(target=run,args=(name[i],price[i],url[i+1],cate[j],descp[i])))
        #path="http://localhost:80/goodtype/add?"+"type="++"&price="+price[i]+"&url="+url[i+1]+"&category="+cate[j]+"&desc="+descp[i]
        #r11 = requests.post(path)

for i in pool:
    i.start()

for i in pool:
    i.join()