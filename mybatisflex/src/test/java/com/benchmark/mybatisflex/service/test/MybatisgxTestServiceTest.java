package com.benchmark.mybatisflex.service.test;

import com.benchmark.mybatisflex.TestApplication;
import com.benchmark.mybatisflex.entity.User;
import com.benchmark.mybatisflex.service.UserService;
import com.navercorp.fixturemonkey.FixtureMonkey;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TestApplication.class)
@Transactional
public class MybatisgxTestServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testBatchInsert100() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.create();

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = fixtureMonkey.giveMeBuilder(User.class)
                    .set("id", null)
                    .set("username", usernameGenerator())
                    .set("email", usernameGenerator())
                    .sample();
            userList.add(user);
        }
        long startTime = System.currentTimeMillis();
        this.userService.batchInsert(userList);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("批量新增100条数据时间：%s", endTime - startTime));
    }

    @Test
    public void testBatchInsert10W() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.create();

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = fixtureMonkey.giveMeBuilder(User.class)
                    .set("id", null)
                    .set("username", usernameGenerator())
                    .set("email", usernameGenerator())
                    .sample();
            userList.add(user);
        }
        long startTime = System.currentTimeMillis();
        this.userService.batchInsert(userList);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("批量新增10W条数据时间：%s", endTime - startTime));
    }

    public static Arbitrary<String> usernameGenerator() {
        return Arbitraries.strings()
                .withChars("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
                .ofMinLength(8)
                .ofMaxLength(16);
    }
}
