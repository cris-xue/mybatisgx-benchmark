package com.benchmark.mybatisgx.service.test;

import com.benchmark.mybatisgx.TestApplication;
import com.benchmark.mybatisgx.entity.User;
import com.benchmark.mybatisgx.service.UserService;
import com.navercorp.fixturemonkey.FixtureMonkey;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TestApplication.class)
public class MybatisgxTestServiceTest {

    private static final FixtureMonkey FIXTURE_MONKEY = FixtureMonkey.create();

    private static final List<User> userList100 = new ArrayList<>(100);
    private static final List<User> userList10000 = new ArrayList<>(10000);

    @Autowired
    private UserService userService;

    @BeforeAll
    static void init() {
        for (int i = 0; i < 100; i++) {
            User user = FIXTURE_MONKEY.giveMeBuilder(User.class)
                    .set("id", null)
                    .set("username", usernameGenerator())
                    .set("email", usernameGenerator())
                    .sample();
            userList100.add(user);
        }

        for (int i = 0; i < 10000; i++) {
            User user = FIXTURE_MONKEY.giveMeBuilder(User.class)
                    .set("id", null)
                    .set("username", usernameGenerator())
                    .set("email", usernameGenerator())
                    .sample();
            userList10000.add(user);
        }
    }

    @Order(1)
    @Rollback
    @RepeatedTest(15)
    public void testInsert() {
        long startTime = System.nanoTime();
        this.userService.insert(userList100.get(0));
        long endTime = System.nanoTime();
        System.out.println(String.format("新增1条数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(2)
    @Rollback
    @RepeatedTest(15)
    public void testBatchInsert100() {
        long startTime = System.nanoTime();
        this.userService.batchInsert(userList100);
        long endTime = System.nanoTime();
        System.out.println(String.format("批量新增100条数据时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(3)
    @Rollback
    @RepeatedTest(15)
    public void testBatchInsert1W() {
        long startTime = System.nanoTime();
        this.userService.batchInsert(userList10000);
        long endTime = System.nanoTime();
        System.out.println(String.format("批量新增1W条数据时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(4)
    @Rollback
    @RepeatedTest(15)
    public void testUpdate() {
        User user = userList100.get(50);
        this.userService.insert(user);
        user.setUsername("modify_user_name");

        long startTime = System.nanoTime();
        this.userService.updateById(user);
        long endTime = System.nanoTime();
        System.out.println(String.format("更新1条数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(5)
    @Rollback
    @RepeatedTest(15)
    public void testUpdateSelective() {
        User userSelective = FIXTURE_MONKEY.giveMeBuilder(User.class)
                .set("id", null)
                .set("username", usernameGenerator())
                .set("email", usernameGenerator())
                .sample();

        this.userService.insert(userSelective);
        userSelective.setUsername(null);
        userSelective.setAge(null);

        long startTime = System.nanoTime();
        this.userService.updateSelectiveById(userSelective);
        long endTime = System.nanoTime();
        System.out.println(String.format("更新1条数据时间（动态）：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(6)
    @Rollback
    @RepeatedTest(15)
    public void testBatchUpdate100() {
        this.userService.batchInsert(userList100);
        for (User user : userList100) {
            user.setUsername("modify_user_name");
        }

        long startTime = System.nanoTime();
        this.userService.batchUpdate(userList100);
        long endTime = System.nanoTime();
        System.out.println(String.format("批量更新100条数据时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(7)
    @Rollback
    @RepeatedTest(15)
    public void testBatchUpdate1W() {
        this.userService.batchInsert(userList10000);
        for (User user : userList10000) {
            user.setUsername("modify_user_name");
        }

        long startTime = System.nanoTime();
        this.userService.batchUpdate(userList10000);
        long endTime = System.nanoTime();
        System.out.println(String.format("批量更新1W条数据时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(8)
    @Rollback
    @RepeatedTest(15)
    public void testFindById() {
        this.userService.batchInsert(userList100);

        long startTime = System.nanoTime();
        this.userService.findById(userList100.get(50).getId());
        long endTime = System.nanoTime();
        System.out.println(String.format("findById查询数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(9)
    @Rollback
    @RepeatedTest(15)
    public void testFindByIdAndAgeAndStatus() {
        this.userService.batchInsert(userList100);

        long startTime = System.nanoTime();
        this.userService.findByIdAndAgeAndStatus(
                userList100.get(50).getId(),
                userList100.get(50).getAge(),
                userList100.get(50).getStatus()
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("findByIdAndAgeAndStatus查询数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(10)
    @Rollback
    @RepeatedTest(15)
    public void testFindByIdAndUsernameLikeAndAgeGtAndStatusIn() {
        this.userService.batchInsert(userList100);

        long startTime = System.nanoTime();
        this.userService.findByIdAndUsernameLikeAndAgeGtAndStatusIn(
                userList100.get(50).getId(),
                userList100.get(50).getUsername(),
                userList100.get(50).getAge(),
                Arrays.asList(userList100.get(50).getStatus())
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("findByIdAndUsernameLikeAndAgeGtAndStatusIn查询数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(11)
    @Rollback
    @RepeatedTest(15)
    public void testFindDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn() {
        this.userService.batchInsert(userList100);

        long startTime = System.nanoTime();
        this.userService.findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(
                userList100.get(50).getId(),
                null,
                null,
                Arrays.asList(userList100.get(50).getStatus())
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn查询数据时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    public static Arbitrary<String> usernameGenerator() {
        return Arbitraries.strings()
                .withChars("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
                .ofMinLength(8)
                .ofMaxLength(16);
    }
}
