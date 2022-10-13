package com.newtouch.authright.ipfstest;

import io.ipfs.api.IPFS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IpfsClient implements CommandLineRunner {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * ipfs的服务器地址和端口,替换成自己的ip，port
     */
    private static IPFS IPFS = new IPFS("/ip4/127.0.0.1/tcp/5001");

    @Override
    public void run(String... args) throws Exception {

        int loopSize = 10000;

        for (int i=0; i< 5; i++) {
            IpfsRunnable rannable = new IpfsRunnable(i+"",IPFS,loopSize);
            threadPoolTaskExecutor.execute(rannable);
        }



    }


}
