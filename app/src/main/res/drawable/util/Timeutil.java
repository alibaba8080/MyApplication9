package com.example.util;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class Timeutil {

        public  String formatSeconds(long seconds) {
            String timeStr = seconds + "ç§?";
            if (seconds > 60) {
                long second = seconds % 60;
                long min = seconds / 60;
                timeStr = min + "åˆ?" + second + "ç§?";
                if (min > 60) {
                    min = (seconds / 60) % 60;
                    long hour = (seconds / 60) / 60;
                    timeStr = hour + "å°æ—¶" + min + "åˆ?" + second + "ç§?";
                }
            }
            return timeStr;
        }
        public String timeFormate(long seconds){
            long second=0;
            long miao1=0;
            long miao2=0;
            long fen1=0;
            long fen2=0;
            long shi1=0;
            long shi2=0;
            String time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+seconds;
            if(seconds>999)
            {   miao1=seconds/1000;
                second=seconds%1000;

                time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                if(miao1>=10)
                {

                    miao2=seconds/10000;
                    miao1=miao1%10;
                    time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                    if(miao2>=6)
                    {
                        miao2=miao2%6;
                        fen1=seconds/60000;
                        time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                        if(fen1>=10){
                            fen1=fen1%10;
                            fen2=seconds/600000;
                            time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                            if(fen2>=6){
                                fen2=fen2%6;
                                shi1=seconds/3600000;
                                time=shi2+shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                                if(shi1>=10){
                                    time=shi1+":"+fen2+fen1+":"+miao2+miao1+":"+second;
                                }
                            }
                        }
                    }
                }
            }


           return  time;
        }
    }
