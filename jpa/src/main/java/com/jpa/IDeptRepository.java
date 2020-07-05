package com.jpa;

import com.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDeptRepository extends JpaRepository<Dept, Long> {


}
