package com.michael.demo.mybatis.simple;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/7/1 9:22
 */
@Mapper
public interface EmployeeMapper {

    List<Employee> getAll();
}
