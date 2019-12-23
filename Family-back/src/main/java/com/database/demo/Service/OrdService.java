package com.database.demo.Service;

import com.alipay.api.domain.McardTemplate;
import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.Ord;
import com.database.demo.Entity.Sale;
import com.database.demo.Repository.GoodtypeRepository;
import com.database.demo.Repository.OrdRepository;
import com.database.demo.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("OrdService")
public class OrdService {
    @Autowired
    OrdRepository ordRepository;
    @Autowired
    SaleRepository saleRepository;

    public List<Sale> statistic(String goodname){
        List<Sale> list=saleRepository.selectbygoodname(goodname);
        List<Sale> ans = new ArrayList<Sale>();
        boolean[] flag=new boolean[list.size()];
        for(int i=0;i<list.size();i++) flag[i]=true;
        for(int i=0;i<list.size();i++){
            if(flag[i]) {
                for(int j=i+1;j<list.size();j++){
                    if(flag[j]){
                        Sale x=list.get(i);
                        Sale y=list.get(j);
                        if(x.getSaledate().equals(y.getSaledate())&&x.getGoodname().equals(y.getGoodname())){
                            x.setNum(x.getNum()+y.getNum());
                            flag[j]=false;
                        }
                    }
                }
                ans.add(list.get(i));
            }
        }
        return ans;
    }

    public void initsale() throws ParseException {
        List<Ord> list=ordRepository.selectall();
        for (Ord ord : list) {
            String[] goodlist = ord.getGoodlist().split(",");
            String[] numlist = ord.getNumlist().split(",");
            String date = ord.getSaledate();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date now = formatter1.parse(date);
            String nowdate = formatter2.format(now);
            for (int j = 0; j < goodlist.length; j++) {
                Sale x = new Sale(goodlist[j],nowdate,Integer.parseInt(numlist[j]));
                saleRepository.save(x);
            }
        }
    }
    public List<Ord> selectall(){
        return ordRepository.findAll();
    }
    public List<Ord> selectbyvipid(String id){
        return ordRepository.selectbyvipid(id);
    }
    public void deleteall(){
        ordRepository.deleteAll();
    }
    public void addnew(Ord ord){
        ordRepository.save(ord);
    }
}
