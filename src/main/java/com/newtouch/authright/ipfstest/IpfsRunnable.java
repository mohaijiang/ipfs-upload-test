package com.newtouch.authright.ipfstest;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.cid.Cid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class IpfsRunnable implements Runnable{

    private IPFS IPFS;
    private String name;
    private int loopSize;

    public IpfsRunnable(String name, IPFS ipfs,int loopSize){
        this.IPFS = ipfs;
        this.name = name;
        this.loopSize = loopSize;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i=0 ;i < loopSize ; i++ ) {
            UUID uuid = UUID.randomUUID();
            String str = "{\"userId\":83,\"graphId\":\"" + uuid.toString() + "\",\"accountName\":\"0x3eac1a6cdd232825476dd10b0cbb21252983868f3cb6a4cbe7a1b8415d5b5a93\",\"commitDate\":\"2022-07-22T08:10:32.798+00:00\",\"action\":\"U\",\"content\":\"用户:wangjingjing审核了任务idda53d112fe9b4feb8f69c38c35c46673的任务\",\"type\":\"updateTaskState\"}\n";
            byte[] data = str.getBytes(StandardCharsets.UTF_8);
            var cid = com.github.rmnvalera.Cid.encode(data,data.length, com.github.rmnvalera.Cid.FILE);
            long start = System.currentTimeMillis();
            String hash = upload(data);
            log.info("线程数： {},计算cid: {}, 接口返回: {}, 耗时： {}",name,cid.toBase58(), hash,System.currentTimeMillis()-start);
        }
    }

    public String upload(byte[] data) throws IOException {

        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }
}
