package com.newtouch.authright.ipfstest;

import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class IpfsTestApplicationTests {

	@Test
	void contextLoads() throws IOException {

		String input = "1112222233345ssacd\n";

//		var cid = Cid.decode("QmNmte41w9YuB8HgykEtfKQ3oLg1Adk4t8T9EXSZi58JLP");


		Multihash _1Id = com.github.rmnvalera.Cid.encode(new File("/Users/mohaijiang/tmp/ipfs-test/abc.txt"));
		System.out.println(_1Id.toBase58());

		Multihash _2Id = com.github.rmnvalera.Cid.encode(input.getBytes(StandardCharsets.UTF_8),input.getBytes(StandardCharsets.UTF_8).length,
				com.github.rmnvalera.Cid.FILE);
		System.out.println(_2Id.toBase58());

	}


}
