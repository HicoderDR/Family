import uuid
import threading,time
import random
import requests
import json

def run(a,b):
    path="http://localhost:80/goodtype/setbuysend?"+"goodname="+a+"&buysend="+b
    requests.post(path)
    time.sleep(2)

def generate(price):
    op=random.randint(0,2)
    if op==0:
        return 'a,'+str(random.randint(0,1))+',2,1'
    elif op==1:
        return 'b,'+str(random.randint(0,1))+','+str(random.randint(1,price//2)*100)
    else:
        return 'c,'+str(random.randint(0,1))+','+str(random.randint(1,2))+','+str(random.randint(1,price//2))

def buysend():
    resp=requests.get("http://47.100.107.158/goodtype/all")
    data=json.loads(resp.text).get("data")
    randlist=random.sample(range(0,len(data)),80)
    pool=[]
    for i in randlist:
        if data[i].get("price")>=5:
            goodname=data[i].get("type")
            buysend=generate(data[i].get("price"))
            print(goodname+' '+buysend)
            pool.append(threading.Thread(target=run,args=(goodname,buysend)))
    for i in pool:
        i.start()
    for i in pool:
        i.join()

buysend()