package com.example.redisDemo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {

    private static final String EMPLOYEE_KEY_PREFIX = "redisdemo:employee::";
    private static final String EMPLOYEE_LIST_KEY_PREFIX = "redisdemo:employee_list";
    private static final String EMPLOYEE_HASH_KEY_PREFIX = "redisdemo:employee_hash::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    // String Operations (Key - String, Value - String)
    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee) {
        redisTemplate.opsForValue().set(getKey(employee.getId()), employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") long employeeId) {
        return (Employee) redisTemplate.opsForValue().get(getKey(employeeId));
    }

    private String getKey(long id) {
        return EMPLOYEE_KEY_PREFIX + id;
    }

    // List Operations (Key - String, Value - List<Employee>)
    @PostMapping("lpush/employee")
    public void lpush(@RequestBody Employee employee) {
        redisTemplate.opsForList().leftPush(EMPLOYEE_LIST_KEY_PREFIX, employee);
    }

    @DeleteMapping("/lpop/employee")
    public Employee lpop() {
        return (Employee) redisTemplate.opsForList().leftPop(EMPLOYEE_LIST_KEY_PREFIX);
    }


    @GetMapping("lrange/employee")
    public List<Employee> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "stop", required = false, defaultValue = "-1") int stop) {
        return redisTemplate.opsForList().range(EMPLOYEE_LIST_KEY_PREFIX, start, stop)
                .stream().map(employeeObject -> (Employee) employeeObject)
                .collect(Collectors.toList());
    }

    //Set Operations

    //Hash Operations

    private String getHashKey(long id){
        return EMPLOYEE_HASH_KEY_PREFIX +id;
    }
    @PostMapping("/hash/employee")
    public void saveEmployeeInHash(@RequestBody List<Employee> employeeList) {

        employeeList.stream().forEach(employee -> {
            Map map = objectMapper.convertValue(employee,Map.class);
            redisTemplate.opsForHash().putAll(getHashKey(employee.getId()), map);
        });
    }

    @GetMapping("/hash/employee/all")
    public List<Employee> getEmployees(@RequestParam("ids") List<Long> ids){
        return ids. stream()
                .map(i -> redisTemplate.opsForHash().entries(getHashKey(i)))
                .map(entryMap -> objectMapper.convertValue(entryMap, Employee.class))
                .collect(Collectors.toList());
    }
}
