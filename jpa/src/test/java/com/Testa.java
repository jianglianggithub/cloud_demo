package com;

import com.entity.Crad;
import com.entity.Dept;
import com.entity.Role;
import com.entity.User;
import com.jpa.IDeptRepository;
import com.jpa.IRoleRepository;
import com.jpa.IUserRepository;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Testa {


    @Autowired
    com.jpa.IUserRepository iUserRepository;
    @Autowired
    com.jpa.IDeptRepository iDeptRepository;
    @Autowired
    IRoleRepository iRoleRepository;

    @Test
    public void select() {
//        List<User> all = userRepository.findAll();
//        for (User i : all) {
//            System.out.println(all);
//        }


//        List<User> username = userRepository.findAll(Sort.by(Sort.Direction.DESC, "username"));
//        System.out.println(username);


//        Pageable unpaged = Pageable.unpaged();
//        PageRequest pageRequest = new PageRequest(0, 1);
//        Page<User> all = userRepository.findAll(pageRequest);
//        Stream<User> userStream = all.get();
//        List<User> collect = userStream.collect(Collectors.toList());
//        System.out.println(collect);

//        User user = new User();
//        user.setId(1L);
        // 如果不指定  ExampleMatcher 那么 其实就等于  user对象 key value 就是where 后面的条件
        // 但是条件 有 like 那么 这儿就是指定这些条件的 属性
//        ExampleMatcher username = ExampleMatcher.matching()
//                .withMatcher("username", new ExampleMatcher.GenericPropertyMatcher().startsWith());
//        List<User> all = userRepository.findAll(Example.of(user,username));
//        System.out.println(all);
//
//        Optional<User> byId = userRepository.findById(1L);
//        if (byId.isPresent()) {
//
//        }


//        List<User> aa = userRepository.selectUser("cc",Sort.by(Sort.Direction.DESC,"username"));
//        System.out.println(aa);


//        Dept dept = new Dept();
//        dept.setName("cc");
//        ArrayList<User> objects = new ArrayList<>();
//        User user = new User();
//        user.setPassword("aa");
//        user.setUsername("cc");
//        user.setDept(dept);
//        objects.add(user);
//        dept.setUsers(objects);
//        iDeptRepository.save(dept);

//
//        User user = new User();
//        user.setUsername("aaq");
//        user.setPassword("asd");
//        iUserRepository.save(user);
//
//        Optional<Dept> byId = iDeptRepository.findById(1L);
//        if (byId.isPresent()) {
//            user.setDept(byId.get());
//            iUserRepository.save(user);
//        }
//        Optional<Role> byId1 = iRoleRepository.findById(1l);
//        Role role = byId1.get();
//        System.out.println(role.getUsers().size());
//        Optional<User> byId = iUserRepository.findById(1L);
//        User user = byId.get();
//        System.out.println(user.getRoles().size());

//        Role role = new Role();
//        role.setRoleName("admin_");
//        List<User> users=new ArrayList<>();
//        role.setUsers(users);
//
//        User user = new User();
//        user.setUsername("asd1");
//        user.setPassword("1");
//        users.add(user);
//        iRoleRepository.save(role);


//        User user = new User();
//        Crad crad = new Crad();
//        crad.setCradName("aaa");
//        crad.setCardId(123456L);
//        user.setCrad(crad);
//        user.setUsername("zxczxc");
//        user.setPassword("123zz");
//        iUserRepository.save(user);


        User user = iUserRepository.findById(2L).get();
        Crad crad = user.getCrad();
        System.out.println(crad);


//        Role role = new Role();
//        role.setRoleName("admin");
//
//        ArrayList<User> objects = new ArrayList<>();
//        User user = new User();
//        user.setPassword("Asd");
//        user.setUsername("213a");
//        objects.add(user);
//
//        ArrayList<Role> objects1 = new ArrayList<>();
//        objects1.add(role);
//
//        user.setRoles(objects1);
//
//
//        role.setUsers(objects);
//        iRoleRepository.save(role);
    }
}
