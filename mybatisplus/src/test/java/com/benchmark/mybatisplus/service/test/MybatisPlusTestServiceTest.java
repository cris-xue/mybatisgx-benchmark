package com.benchmark.mybatisplus.service.test;

import com.benchmark.mybatisplus.TestApplication;
import com.benchmark.mybatisplus.entity.User;
import com.benchmark.mybatisplus.service.MybatisPlusTestService;
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
public class MybatisPlusTestServiceTest {

    private static final FixtureMonkey FIXTURE_MONKEY = FixtureMonkey.create();

    private static final List<User> userList100 = new ArrayList<>(100);
    private static final List<User> userList10000 = new ArrayList<>(10000);

    @Autowired
    private MybatisPlusTestService userService;

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
        User user = cloneUser(userList100.get(0));
        long startTime = System.nanoTime();
        this.userService.insert(user);
        long endTime = System.nanoTime();
        System.out.println(String.format("[Insert][1]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(2)
    @Rollback
    @RepeatedTest(15)
    public void testBatchInsert100() {
        List<User> userList = cloneUserList(userList100);
        long startTime = System.nanoTime();
        this.userService.batchInsert(userList);
        long endTime = System.nanoTime();
        System.out.println(String.format("[BatchInsert][100]执行时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(3)
    @Rollback
    @RepeatedTest(15)
    public void testBatchInsert1W() {
        List<User> userList = cloneUserList(userList10000);
        long startTime = System.nanoTime();
        this.userService.batchInsert(userList);
        long endTime = System.nanoTime();
        System.out.println(String.format("[BatchInsert][1W]执行时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(4)
    @Rollback
    @RepeatedTest(15)
    public void testUpdate() {
        User user = cloneUser(userList100.get(50));
        this.userService.insert(user);
        user.setUsername("modify_user_name");

        long startTime = System.nanoTime();
        this.userService.updateById(user);
        long endTime = System.nanoTime();
        System.out.println(String.format("[Update][1]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(5)
    @Rollback
    @RepeatedTest(15)
    public void testUpdateSelective() {
        User user = cloneUser(userList100.get(50));
        this.userService.insert(user);
        user.setUsername(null);
        user.setAge(null);

        long startTime = System.nanoTime();
        this.userService.updateSelectiveById(user);
        long endTime = System.nanoTime();
        System.out.println(String.format("[UpdateSelective][1]执行时间（动态）：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(6)
    @Rollback
    @RepeatedTest(15)
    public void testBatchUpdate100() {
        List<User> userList = cloneUserList(userList100);
        this.userService.batchInsert(userList);
        for (User user : userList) {
            user.setUsername("modify_user_name");
        }

        long startTime = System.nanoTime();
        this.userService.batchUpdate(userList);
        long endTime = System.nanoTime();
        System.out.println(String.format("[BatchUpdate][100]执行时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(7)
    @Rollback
    @RepeatedTest(15)
    public void testBatchUpdate1W() {
        List<User> userList = cloneUserList(userList10000);
        this.userService.batchInsert(userList);
        for (User user : userList) {
            user.setUsername("modify_user_name");
        }

        long startTime = System.nanoTime();
        this.userService.batchUpdate(userList);
        long endTime = System.nanoTime();
        System.out.println(String.format("[BatchUpdate][1W]执行时间：%sms", TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }

    @Order(8)
    @Rollback
    @RepeatedTest(15)
    public void testFindById() {
        List<User> userList = cloneUserList(userList100);
        this.userService.batchInsert(userList);

        long startTime = System.nanoTime();
        this.userService.findById(userList.get(50).getId());
        long endTime = System.nanoTime();
        System.out.println(String.format("[FindById]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(9)
    @Rollback
    @RepeatedTest(15)
    public void testFindByIdAndAgeAndStatus() {
        List<User> userList = cloneUserList(userList100);
        this.userService.batchInsert(userList);

        long startTime = System.nanoTime();
        this.userService.findByIdAndAgeAndStatus(
                userList.get(50).getId(),
                userList.get(50).getAge(),
                userList.get(50).getStatus()
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("[FindByIdAndAgeAndStatus]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(10)
    @Rollback
    @RepeatedTest(15)
    public void testFindByIdAndUsernameLikeAndAgeGtAndStatusIn() {
        List<User> userList = cloneUserList(userList100);
        this.userService.batchInsert(userList);

        long startTime = System.nanoTime();
        this.userService.findByIdAndUsernameLikeAndAgeGtAndStatusIn(
                userList.get(50).getId(),
                userList.get(50).getUsername(),
                userList.get(50).getAge(),
                Arrays.asList(userList.get(50).getStatus())
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("[FindByIdAndUsernameLikeAndAgeGtAndStatusIn]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    @Order(11)
    @Rollback
    @RepeatedTest(15)
    public void testFindDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn() {
        List<User> userList = cloneUserList(userList100);
        this.userService.batchInsert(userList);

        long startTime = System.nanoTime();
        this.userService.findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(
                userList.get(50).getId(),
                null,
                null,
                Arrays.asList(userList.get(50).getStatus())
        );
        long endTime = System.nanoTime();
        System.out.println(String.format("[FindDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn]执行时间：%sus", TimeUnit.NANOSECONDS.toMicros(endTime - startTime)));
    }

    public static Arbitrary<String> usernameGenerator() {
        return Arbitraries.strings()
                .withChars("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
                .ofMinLength(8)
                .ofMaxLength(16);
    }

    private List<User> cloneUserList(List<User> userList) {
        List<User> cloneUserList = new ArrayList(userList.size());
        for (int i = 0; i < userList.size(); i++) {
            cloneUserList.add(cloneUser(userList.get(i)));
        }
        return cloneUserList;
    }

    private User cloneUser(User user) {
        User cloneUser = new User();
        cloneUser.setId(user.getId());
        cloneUser.setUsername(user.getUsername());
        cloneUser.setEmail(user.getEmail());
        cloneUser.setPhone(user.getPhone());
        cloneUser.setAge(user.getAge());
        cloneUser.setStatus(user.getStatus());
        cloneUser.setCreateTime(user.getCreateTime());
        cloneUser.setUpdateTime(user.getUpdateTime());
        return cloneUser;
    }
}
