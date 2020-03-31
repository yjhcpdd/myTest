package com.sa.module.test.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 */
public class BloomFilterTest {
    
    /**
     * 预计要插入的数据
     */
    private static Long expectedInsertions=1000000L;
    
    /**
     * 期望误判率
     */
    private static Double fpp=0.01D;
    
    public static BloomFilter<Long> bloomFilter=BloomFilter.create(Funnels.longFunnel(),expectedInsertions,fpp);
    
    
    public static void main(String[] args) {
    
        System.out.println("-------写入数据---开始");
        for(long i=1;i<=1000000L;i++){
            bloomFilter.put(i);
        }
        System.out.println("-------写入数据---结束");
    
        long okCount=0L;
        for(long i=1;i<=1000000L;i++){
            if(bloomFilter.mightContain(i)){
                okCount++;
            }
        }
        System.out.println("全部范围内存在的数量："+okCount);
        
        long contaisCount=0;
        for(long i=1000000L;i<=2000000L;i++){
            if(bloomFilter.mightContain(i)){
                contaisCount++;
            }
        }
        System.out.println("范围外判断可能存在的结果："+contaisCount);
        
        
    }
    
}
