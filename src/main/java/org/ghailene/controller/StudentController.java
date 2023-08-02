package org.ghailene.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.ghailene.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="student")
public class StudentController {

    private static final String PROCESS_DEFINITION_KEY ="student-manipulation";

    @PostMapping("/")
public ResponseEntity<HttpStatus> startProcess(@RequestBody Student student){

    //call process engine
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //call the runtime service
    RuntimeService runtimeService = processEngine.getRuntimeService();

    //send some process data
    Map<String,Object> processVariable = new HashMap<>();
    processVariable.put("id",student.getId());
    processVariable.put("name",student.getName());
    processVariable.put("age",student.getAge());

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY,processVariable);
    System.out.println("PROCESS instance id is " + processInstance.getId());
    return ResponseEntity.ok(HttpStatus.OK);

}

}
