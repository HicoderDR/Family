import uuid
import threading,time
import random
import requests
import json

data=[]

def run1(a,b,c):
    path="http://47.100.107.158:80/good/add?"+"goodid="+a+"&goodname="+b+"&makedate="+c
    requests.post(path)
    time.sleep(1)

def run2(a,b):
    path="http://47.100.107.158:80/goodtype/setguarantee?"+"goodname="+a+"&guarantee="+b
    requests.post(path)
    time.sleep(2)

def run3(a,b):
    pool=[]
    for j in range(0,random.randint(10,99)):
        date=mkdate()
        goodid='F'+str(a+1000)[1:]+str(random.randint(0,99)+100)[1:]+date[0:4]+date[5:7]+date[8:10]
        goodname=b
        print(goodid,goodname,date)
        pool.append(threading.Thread(target=run1,args=(goodid,goodname,date)))
    for j in pool:
        j.start()
    for j in pool:
        j.join()
    time.sleep(10)

def mkguarantee():
    return random.randint(3,30)

def mkdate():
    a=(2019,12,1,0,0,0,0,0,0)              
    b=(2019,12,20,23,59,59,0,0,0)    
    start=time.mktime(a)    #生成开始时间戳
    end=time.mktime(b)      #生成结束时间戳
    t=random.randint(start,end)
    date_touple=time.localtime(t)
    date=time.strftime("%Y-%m-%d",date_touple) 
    return date
def good():
    resp=requests.get("http://47.100.107.158/goodtype/all")
    data=json.loads(resp.text).get("data")
    for i in range(0,len(data)):
        pool=[]
        for j in range(0,random.randint(10,99)):
            date=mkdate()
            goodid='F'+str(i+1000)[1:]+str(random.randint(0,99)+100)[1:]+date[0:4]+date[5:7]+date[8:10]
            goodname=data[i].get("type")
            print(goodid,goodname,date)
            pool.append(threading.Thread(target=run1,args=(goodid,goodname,date)))
        for j in pool:
            j.start()
        for j in pool:
            j.join()   
    return


def guarantee():
    resp=requests.get("http://47.100.107.158/goodtype/all")
    data=json.loads(resp.text).get("data")
    for i in data:
        goodname=i.get("type")
        print(goodname,mkguarantee())
        pool.append(threading.Thread(target=run2,args=(goodname,str(mkguarantee()))))
    for i in pool:
        i.start()
    for i in pool:
        i.join()
    return 


'''
    
'''
    
#initdata()
good()

